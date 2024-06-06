package com.example.usuario_pf.controller;

import com.example.usuario_pf.dto.AuthDTO;
import com.example.usuario_pf.dto.LoginResponseDTO;
import com.example.usuario_pf.dto.UsuarioPFDTO;
import com.example.usuario_pf.infra.security.TokenService;
import com.example.usuario_pf.model.entity.UsuarioPFEntity;
import com.example.usuario_pf.service.UsuarioPFService;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RestController for handling 'Pessoa Jurídica' (corporate entity) user-related
 * requests.
 * Provlogines CRUD operations for 'Pessoa Jurídica' users.
 */
@RestController
@RequestMapping("/usuario_pf")
public class UsuarioPFController {

    @Autowired
    private UsuarioPFService usuarioPFService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    /**
     * GET endpoint to retrieve a list of all 'Pessoa Jurídica' users.
     * 
     * @return List of {@link UsuarioPFDTO}
     */
    @GetMapping()
    public List<UsuarioPFDTO> getUsuariosPF() {
        return usuarioPFService.getUsuariosPF();
    }

    /**
     * GET endpoint to retrieve a specific 'Pessoa Jurídica' user by their ID.
     * 
     * @param login The ID of the user to retrieve.
     * @return ResponseEntity containing the {@link UsuarioPFDTO}
     */
    @GetMapping("/{login}")
    public ResponseEntity<UsuarioPFDTO> getUsuarioPFbylogin(@PathVariable("login") String login) {
        UsuarioPFDTO usuario_pf = usuarioPFService.getUsuarioPFbylogin(login);
        return ResponseEntity.ok(usuario_pf);
    }
    // @GetMapping("/login/{login}")
    // public ResponseEntity<List<Object[]>> getUsuariosPorlogin(@PathVariable
    // String login) {
    // List<Object[]> usuarios = usuarioPFService.buscarUsuariosPorlogin(login);
    // return ResponseEntity.ok(usuarios);
    // }

    /**
     * POST endpoint to create a new 'Pessoa Jurídica' user.
     * 
     * @param usuario_pfDTO DTO containing the user data to create.
     * @return ResponseEntity containing the created {@link UsuarioPFDTO}
     */
    @PostMapping()
    public ResponseEntity<UsuarioPFDTO> createUsuarioPF(@RequestBody UsuarioPFDTO usuario_pfDTO) {
        UsuarioPFDTO createUsuarioPF = usuarioPFService.createUsuarioPF(usuario_pfDTO);
        return ResponseEntity.ok(createUsuarioPF);
    }

    @GetMapping("/enderecos")
    public ResponseEntity<String> getEnderecosbyId(
            @RequestHeader(value = "authorization", defaultValue = "") String auth) {
        if (auth != null && !auth.isEmpty()) {
            String token = auth.replace("Bearer ", "");

            String usuarioResponse = usuarioPFService.getEnderecobyId(token);
            return ResponseEntity.ok(usuarioResponse);
        } else {
            return ResponseEntity.badRequest().body("Token de autorização não fornecido.");
        }
    }

    @GetMapping("/usuario")
    public ResponseEntity<Map<String, Object>> getUsuarioPJeUsuariobylogin(
            @RequestHeader(value = "authorization", defaultValue = "") String auth) throws JsonProcessingException {
        if (auth != null && !auth.isEmpty()) {

            String token = auth.substring(7);
            // Integer userId = tokenService.extractUserIdFromToken(token);

            // String token = auth.replace("Bearer ", "");

            JSONObject usuarioResponse = usuarioPFService.getUsuarioPJeUsuariobylogin(token);
            // Converte o JSONObject para Map<String, Object>
            Map<String, Object> responseMap = usuarioResponse.toMap();
            return ResponseEntity.ok(responseMap);
        } else {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Token de autorização não fornecido.");
            return ResponseEntity.badRequest().body(error);
        }
    }

    @GetMapping("/planos_usuario")
    public ResponseEntity<Map<String, Object>> getPlanoePlanosUsuariosbylogin(
            @RequestHeader(value = "authorization", defaultValue = "") String auth) throws JsonProcessingException {
        if (auth != null && !auth.isEmpty()) {

            // String token = auth.substring(7);
            // Integer userId = tokenService.extractUserIdFromToken(token);

            String token = auth.replace("Bearer ", "");

            JSONObject planosUsuarios = usuarioPFService.getPlanoePlanosUsuariosbylogin(token);
            // Converte o JSONObject para Map<String, Object>
            Map<String, Object> responseMap = planosUsuarios.toMap();
            return ResponseEntity.ok(responseMap);
        } else {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Token de autorização não fornecido.");
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUsuarioPF(@RequestBody @Valid AuthDTO authDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(authDTO.login(), authDTO.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UsuarioPFEntity) auth.getPrincipal());

        System.out.println("Token: " + token);

        return ResponseEntity.ok(new LoginResponseDTO(token));

    }

    @PostMapping("/logout")
    public ResponseEntity logout(HttpServletRequest request, HttpServletResponse response) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity registerUsuarioPF(@RequestBody @Valid UsuarioPFDTO usuario_pfDTO) {

        if ((this.usuarioPFService.getUsuarioPFbylogin(usuario_pfDTO.getLogin()) != null))
            return ResponseEntity.badRequest().build();
        else {
            System.out.println("User " + usuario_pfDTO);
            String encryptPassword = new BCryptPasswordEncoder().encode(usuario_pfDTO.getSenha());
            usuario_pfDTO.setSenha(encryptPassword);
            UsuarioPFDTO createUsuarioPF = usuarioPFService.createUsuarioPF(usuario_pfDTO);

            return ResponseEntity.ok(createUsuarioPF);

        }
    }

    /**
     * PUT endpoint to update an existing 'Pessoa Jurídica' user.
     * 
     * @param login         The ID of the user to update.
     * @param usuario_pfDTO DTO containing the updated user data.
     * @return ResponseEntity containing the updated {@link UsuarioPFDTO}
     */
    @PutMapping("/{login}")
    public ResponseEntity<UsuarioPFDTO> updateUsuarioPF(@PathVariable("login") String login,
            @RequestBody UsuarioPFDTO usuario_pfDTO) {
        UsuarioPFDTO updateUsuarioPF = usuarioPFService.updateUsuarioPF(usuario_pfDTO, login);
        return ResponseEntity.ok(updateUsuarioPF);
    }

    /**
     * DELETE endpoint to delete a 'Pessoa Jurídica' user by their ID.
     * 
     * @param login The ID of the user to delete.
     * @return String indicating the result of the deletion process.
     */
    @DeleteMapping("/{login}")
    public String deleteUsuarioPF(@PathVariable("login") String login) {
        return usuarioPFService.deleteUsuarioPF(login);
    }
}
