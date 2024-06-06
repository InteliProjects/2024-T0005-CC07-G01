package com.example.usuario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.usuario.dto.UsuarioDTO;
import com.example.usuario.model.entity.UsuarioEntity;
import com.example.usuario.repository.UsuarioRepository;
import com.example.usuario.exception.ResourceNotFoundException;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing users of type PJ (Pessoa Jurídica).
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Converts a UsuariosPJEntity to a UsuariosPJDTO.
     *
     * @param usuariosPJEntity The entity to convert.
     * @return The converted DTO.
     */
    private UsuarioDTO converter(UsuarioEntity usuarioEntity) {
        UsuarioDTO result = new UsuarioDTO();
        result.setId_usuario(usuarioEntity.getId_usuario());
        result.setNome(usuarioEntity.getNome());
        result.setEmail(usuarioEntity.getEmail());
        result.setSaldo(usuarioEntity.getSaldo());
        result.setTipo(usuarioEntity.getTipo());
        result.setData_cadastro(usuarioEntity.getData_cadastro());

        return result;
    }

    /**
     * Retrieves all PJ users.
     *
     * @return A list of all PJ users.
     */
    public List<UsuarioDTO> getUsuarios() {
        return usuarioRepository
                .findAll()
                .stream()
                .map(this::converter).collect(Collectors.toList());
    }

    /**
     * Retrieves a PJ user by their ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The retrieved user.
     * @throws ResourceNotFoundException If no user with the given ID is found.
     */
    public UsuarioDTO getUsuariosbyId(Integer id) {
        Optional<UsuarioEntity> usuariosPJEntityOptional = usuarioRepository.findById(id);
        if (usuariosPJEntityOptional.isPresent()) {
            UsuarioEntity usuariosPJEntity = usuariosPJEntityOptional.get();
            return converter(usuariosPJEntity);
        } else {
            throw new ResourceNotFoundException("Usuario não encontrado com Id " + id);
        }
    }

    /**
     * Creates a new PJ user.
     *
     * @param usuariosPJDTO The DTO of the user to create.
     * @return The created user.
     */
    public UsuarioEntity createUsuario(UsuarioDTO usuarioDTO) {
        UsuarioEntity usuariosPJEntity = new UsuarioEntity();
        usuariosPJEntity.setNome(usuarioDTO.getNome());
        usuariosPJEntity.setEmail(usuarioDTO.getEmail());
        usuariosPJEntity.setSaldo(usuarioDTO.getSaldo());
        usuariosPJEntity.setTipo(usuarioDTO.getTipo());
        usuariosPJEntity.setData_cadastro(usuarioDTO.getData_cadastro());
    
        usuarioRepository.save(usuariosPJEntity);
    
        // Retorna o objeto UsuarioEntity após a persistência
        return usuariosPJEntity;
    }
    

    /**
     * Updates an existing PJ user.
     *
     * @param usuariosPJDTO The DTO of the user to update.
     * @param id            The ID of the user to update.
     * @return The updated user.
     * @throws ResourceNotFoundException If no user with the given ID is found.
     */
    public UsuarioDTO updateUsuario(UsuarioDTO usuarioDTO, Integer id) {
        Optional<UsuarioEntity> usuariosPJEntityOptional = usuarioRepository.findById(id);
        if (usuariosPJEntityOptional.isPresent()) {
            UsuarioEntity usuariosPJEntity = usuariosPJEntityOptional.get();
            usuariosPJEntity.setNome(usuarioDTO.getNome());
            usuariosPJEntity.setEmail(usuarioDTO.getEmail());
            usuariosPJEntity.setSaldo(usuarioDTO.getSaldo());
            usuariosPJEntity.setTipo(usuarioDTO.getTipo());
            usuariosPJEntity.setData_cadastro(usuarioDTO.getData_cadastro());

            usuarioRepository.save(usuariosPJEntity);

            return usuarioDTO;
        } else {
            throw new ResourceNotFoundException("Usuario não encontrado com Id " + id);
        }
    }

    /**
     * Deletes a PJ user by their ID.
     *
     * @param id The ID of the user to delete.
     * @return A success message if the user was deleted.
     * @throws ResourceNotFoundException If no user with the given ID is found.
     */
    public String deleteUsuario(@PathVariable("id") Integer id) {
        Optional<UsuarioEntity> usuariosPJEntityOptional = usuarioRepository.findById(id);
        if (usuariosPJEntityOptional.isPresent()) {
            usuarioRepository.deleteById(id);
            return "Usuario deletado com sucesso";
        } else {
            throw new ResourceNotFoundException("Usuario não encontrado com Id " + id);
        }
    }

}
