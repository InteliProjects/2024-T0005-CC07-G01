package com.example.usuario_pj.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;

import com.example.usuario_pj.dto.AuthDTO;
import com.example.usuario_pj.dto.LoginResponseDTO;
import com.example.usuario_pj.dto.UsuarioPJDTO;
import com.example.usuario_pj.infra.security.TokenService;
import com.example.usuario_pj.model.entity.UsuarioPJEntity;
// import com.example.usuario_pj.service.EnderecoService;
import com.example.usuario_pj.service.UsuarioPJService;

import ch.qos.logback.core.subst.Token;
import io.jsonwebtoken.Claims;

import org.springframework.http.HttpHeaders;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;

// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.oauth2.jwt.Jwt;
// import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.List;
import java.util.Map;

/**
 * RestController for handling 'Pessoa Jurídica' (corporate entity) user-related
 * requests.
 * Provlogines CRUD operations for 'Pessoa Jurídica' users.
 */
@RestController
@RequestMapping("/usuario_pj")
public class UsuarioPJController {

    @Autowired
    private Environment env;

    @Autowired
    private UsuarioPJService usuarioPJService;

    // @Autowired
    // private EnderecoService enderecoService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    /**
     * GET endpoint to retrieve a list of all 'Pessoa Jurídica' users.
     * 
     * @return List of {@link UsuarioPJDTO}
     */
    @GetMapping()
    public List<UsuarioPJDTO> getUsuariosPJ() {
        return usuarioPJService.getUsuariosPJ();
    }

    /**
     * GET endpoint to retrieve a specific 'Pessoa Jurídica' user by their ID.
     * 
     * @param login The ID of the user to retrieve.
     * @return ResponseEntity containing the {@link UsuarioPJDTO}
     */
    @GetMapping("/{login}")
    public ResponseEntity<UsuarioPJDTO> getUsuariosPJbylogin(@PathVariable("login") String login) {
        UsuarioPJDTO usuario_pj = usuarioPJService.getUsuarioPJbylogin(login);
        return ResponseEntity.ok(usuario_pj);
    }

    /**
     * POST endpoint to create a new 'Pessoa Jurídica' user.
     * 
     * @param usuario_pjDTO DTO containing the user data to create.
     * @return ResponseEntity containing the created {@link UsuarioPJDTO}
     */
    @PostMapping()
    public ResponseEntity<UsuarioPJDTO> createUsuarioPJ(@RequestBody UsuarioPJDTO usuario_pjDTO) {
        UsuarioPJDTO createUsuarioPJ = usuarioPJService.createUsuarioPJ(usuario_pjDTO);
        return ResponseEntity.ok(createUsuarioPJ);
    }

    /**
     * PUT endpoint to update an existing 'Pessoa Jurídica' user.
     * 
     * @param login         The ID of the user to update.
     * @param usuario_pjDTO DTO containing the updated user data.
     * @return ResponseEntity containing the updated {@link UsuarioPJDTO}
     */
    @PutMapping("/{login}")
    public ResponseEntity<UsuarioPJDTO> updateUsuarioPJ(@PathVariable("login") String login,
            @RequestBody UsuarioPJDTO usuario_pjDTO) {
        UsuarioPJDTO updateUsuarioPJ = usuarioPJService.updateUsuarioPJ(usuario_pjDTO, login);
        return ResponseEntity.ok(updateUsuarioPJ);
    }

    /**
     * DELETE endpoint to delete a 'Pessoa Jurídica' user by their ID.
     * 
     * @param login The ID of the user to delete.
     * @return String indicating the result of the deletion process.
     */
    @DeleteMapping("/{login}")
    public String deleteUsuarioPJ(@PathVariable("login") String login) {
        return usuarioPJService.deleteUsuarioPJ(login);
    }

    @GetMapping("/enderecos")
    public ResponseEntity<String> getEnderecosbyId(
            @RequestHeader(value = "authorization", defaultValue = "") String auth) {
        if (auth != null && !auth.isEmpty()) {
            String token = auth.replace("Bearer ", "");

            String usuarioResponse = usuarioPJService.getEnderecobyId(token);
            return ResponseEntity.ok(usuarioResponse);
        } else {
            return ResponseEntity.badRequest().body("Token de autorização não fornecido.");
        }
    }

    @GetMapping("/usuario")
    public ResponseEntity<Map<String, Object>> getUsuarioPJeUsuariobylogin(
            @RequestHeader(value = "authorization", defaultValue = "") String auth) throws JsonProcessingException {
        if (auth != null && !auth.isEmpty()) {

            // String token = auth.substring(7);
            // Integer userId = tokenService.extractUserIdFromToken(token);

            String token = auth.replace("Bearer ", "");

            JSONObject usuarioResponse = usuarioPJService.getUsuarioPJeUsuariobylogin(token);
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

            JSONObject planosUsuarios = usuarioPJService.getPlanoePlanosUsuariosbylogin(token);
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

        var token = tokenService.generateToken((UsuarioPJEntity) auth.getPrincipal());

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
    public ResponseEntity registerUsuarioPF(@RequestBody @Valid UsuarioPJDTO usuario_pfDTO) {

        if ((this.usuarioPJService.getUsuarioPJbylogin(usuario_pfDTO.getLogin()) != null))
            return ResponseEntity.badRequest().build();
        else {
            System.out.println("User " + usuario_pfDTO);
            String encryptPassword = new BCryptPasswordEncoder().encode(usuario_pfDTO.getSenha());
            usuario_pfDTO.setSenha(encryptPassword);
            UsuarioPJDTO createUsuarioPF = usuarioPJService.createUsuarioPJ(usuario_pfDTO);

            return ResponseEntity.ok(createUsuarioPF);

        }

    }

}
