package com.example.usuario.controller;

import com.example.usuario.dto.UsuarioDTO;
import com.example.usuario.model.entity.UsuarioEntity;
import com.example.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RestController for handling 'Pessoa Jurídica' (corporate entity) user-related
 * requests.
 * Provides CRUD operations for 'Pessoa Jurídica' users.
 */
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    /**
     * GET endpoint to retrieve a list of all 'Pessoa Jurídica' users.
     * 
     * @return List of {@link UsuarioDTO}
     */
    @GetMapping()
    public List<UsuarioDTO> getUsuarios() {
        return usuarioService.getUsuarios();
    }

    /**
     * GET endpoint to retrieve a specific 'Pessoa Jurídica' user by their ID.
     * 
     * @param id The ID of the user to retrieve.
     * @return ResponseEntity containing the {@link UsuarioDTO}
     */
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuariosbyId(@PathVariable("id") Integer id) {
        UsuarioDTO usuariosPJ = usuarioService.getUsuariosbyId(id);
        return ResponseEntity.ok(usuariosPJ);
    }

    /**
     * POST endpoint to create a new 'Pessoa Jurídica' user.
     * 
     * @param usuarioDTO DTO containing the user data to create.
     * @return ResponseEntity containing the created {@link UsuarioDTO}
     */
    
    @PostMapping()
    public ResponseEntity<UsuarioDTO> createUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        // Ajuste o tipo da variável para UsuarioEntity
        UsuarioEntity createUsuario = usuarioService.createUsuario(usuarioDTO);

        // Converte UsuarioEntity para UsuarioDTO e inclui o ID gerado automaticamente
        UsuarioDTO usuarioDTOResponse = new UsuarioDTO();
        usuarioDTOResponse.setId_usuario(createUsuario.getId_usuario());
        usuarioDTOResponse.setNome(createUsuario.getNome());
        usuarioDTOResponse.setEmail(createUsuario.getEmail());
        usuarioDTOResponse.setSaldo(createUsuario.getSaldo());
        usuarioDTOResponse.setTipo(createUsuario.getTipo());
        usuarioDTOResponse.setData_cadastro(createUsuario.getData_cadastro());

        return ResponseEntity.ok(usuarioDTOResponse);
    }

    /**
     * PUT endpoint to update an existing 'Pessoa Jurídica' user.
     * 
     * @param id         The ID of the user to update.
     * @param usuarioDTO DTO containing the updated user data.
     * @return ResponseEntity containing the updated {@link UsuarioDTO}
     */
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> updateUsuario(@PathVariable("id") Integer id,
            @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO updateUsuario = usuarioService.updateUsuario(usuarioDTO, id);
        return ResponseEntity.ok(updateUsuario);
    }

    /**
     * DELETE endpoint to delete a 'Pessoa Jurídica' user by their ID.
     * 
     * @param id The ID of the user to delete.
     * @return String indicating the result of the deletion process.
     */
    @DeleteMapping("/{id}")
    public String deleteUsuario(@PathVariable("id") Integer id) {
        return usuarioService.deleteUsuario(id);
    }
}
