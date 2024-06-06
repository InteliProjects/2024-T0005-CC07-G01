package com.example.planos_usuarios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.planos_usuarios.dto.PlanosUsuariosDTO;
import com.example.planos_usuarios.exception.ResourceNotFoundException;
import com.example.planos_usuarios.model.entity.PlanosUsuariosEntity;
import com.example.planos_usuarios.repository.PlanosUsuariosRepository;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing PJ mobile plans.
 */
@Service
public class PlanosUsuariosService {

    @Autowired
    private PlanosUsuariosRepository planos_usuariosRepository;

    /**
     * Converts a PlanosMovelPJEntity to a PlanosMovelPJDTO.
     *
     * @param PlanosMovelPJEntity The entity to convert.
     * @return The converted DTO.
     */
    private PlanosUsuariosDTO converter(PlanosUsuariosEntity planos_usuariossEntity) {
        PlanosUsuariosDTO result = new PlanosUsuariosDTO();

        result.setContrato(planos_usuariossEntity.getContrato());
        result.setQtd_internet_consumido(planos_usuariossEntity.getQtd_internet_consumido());
        result.setQtd_internet_restante(planos_usuariossEntity.getQtd_internet_restante());
        result.setData_inicio(planos_usuariossEntity.getData_inicio());
        result.setData_final(planos_usuariossEntity.getData_final());
        result.setFatura(planos_usuariossEntity.getFatura());
        result.setId_plano(planos_usuariossEntity.getId_plano());
        result.setTelefone(planos_usuariossEntity.getTelefone());
        result.setStatus(planos_usuariossEntity.getStatus());
        result.setId_endereco(planos_usuariossEntity.getId_endereco());
        result.setId_usuario(planos_usuariossEntity.getId_usuario());

        return result;
    }

    /**
     * Retrieves all PJ mobile plans.
     *
     * @return A list of all PJ mobile plans.
     */
    public List<PlanosUsuariosDTO> getPlanosUsuarios() {
        return planos_usuariosRepository
                .findAll()
                .stream()
                .map(this::converter).collect(Collectors.toList());
    }

    /**
     * Retrieves a PJ mobile plan by their ID.
     *
     * @param id The ID of the mobile plan to retrieve.
     * @return The retrieved mobile plan.
     * @throws ResourceNotFoundException If no mobile plan with the given ID is
     *                                   found.
     */
    public PlanosUsuariosDTO getPlanoUsuariobyId(Integer id) {
        Optional<PlanosUsuariosEntity> planos_usuariossOptional = planos_usuariosRepository.findById(id);
        if (planos_usuariossOptional.isPresent()) {
            PlanosUsuariosEntity planos_usuariosEntity = planos_usuariossOptional.get();
            return converter(planos_usuariosEntity);
        } else {
            throw new ResourceNotFoundException("Plano não encontrado com Id: " + id);
        }
    }

    /**
     * Creates a new PJ mobile plan.
     *
     * @param PlanosMovelPJDTO The DTO of the mobile plan to create.
     * @return The created mobile plan.
     */
    public PlanosUsuariosDTO createPlanoUsuario(PlanosUsuariosDTO planos_usuariosDTO) {
        PlanosUsuariosEntity planos_usuariosEntity = new PlanosUsuariosEntity();

        planos_usuariosEntity.setId_plano(planos_usuariosDTO.getId_plano());
        planos_usuariosEntity.setQtd_internet_consumido(planos_usuariosDTO.getQtd_internet_consumido());
        planos_usuariosEntity.setQtd_internet_restante(planos_usuariosDTO.getQtd_internet_restante());
        planos_usuariosEntity.setData_inicio(planos_usuariosDTO.getData_inicio());
        planos_usuariosEntity.setData_final(planos_usuariosDTO.getData_final());
        planos_usuariosEntity.setFatura(planos_usuariosDTO.getFatura());
        planos_usuariosEntity.setTelefone(planos_usuariosDTO.getTelefone());
        planos_usuariosEntity.setStatus(planos_usuariosDTO.getStatus());
        planos_usuariosEntity.setId_endereco(planos_usuariosDTO.getId_endereco());
        planos_usuariosEntity.setId_usuario(planos_usuariosDTO.getId_usuario());

        planos_usuariosRepository.save(planos_usuariosEntity);

        return planos_usuariosDTO;
    }

    /**
     * Updates an existing PJ mobile plan.
     *
     * @param PlanosMovelPJDTO The DTO of the mobile plan to update.
     * @param id               The ID of the mobile plan to update.
     * @return The updated mobile plan.
     * @throws ResourceNotFoundException If no mobile plan with the given ID is
     *                                   found.
     */
    public PlanosUsuariosDTO updatePlanoUsuario(PlanosUsuariosDTO planos_usuariosDTO, Integer id) {
        Optional<PlanosUsuariosEntity> planos_usuariosOptional = planos_usuariosRepository.findById(id);
        if (planos_usuariosOptional.isPresent()) {
            PlanosUsuariosEntity planos_usuariosEntity = planos_usuariosOptional.get();

            planos_usuariosEntity.setId_plano(planos_usuariosDTO.getId_plano());
            planos_usuariosEntity.setQtd_internet_consumido(planos_usuariosDTO.getQtd_internet_consumido());
            planos_usuariosEntity.setQtd_internet_restante(planos_usuariosDTO.getQtd_internet_restante());
            planos_usuariosEntity.setData_inicio(planos_usuariosDTO.getData_inicio());
            planos_usuariosEntity.setData_final(planos_usuariosDTO.getData_final());
            planos_usuariosEntity.setFatura(planos_usuariosDTO.getFatura());
            planos_usuariosEntity.setTelefone(planos_usuariosDTO.getTelefone());
            planos_usuariosEntity.setStatus(planos_usuariosDTO.getStatus());
            planos_usuariosEntity.setId_endereco(planos_usuariosDTO.getId_endereco());
            planos_usuariosEntity.setId_usuario(planos_usuariosDTO.getId_usuario());

            planos_usuariosRepository.save(planos_usuariosEntity);

            return planos_usuariosDTO;
        } else {
            throw new ResourceNotFoundException("Plano não encontrado com Id: " + id);
        }
    }

    /**
     * Deletes a PJ mobile plan by their ID.
     *
     * @param id The ID of the mobile plan to delete.
     * @return A success message if the mobile plan was deleted.
     * @throws ResourceNotFoundException If no mobile plan with the given ID is
     *                                   found.
     */
    public String deletePlanoUsuario(@PathVariable("id") Integer id) {
        Optional<PlanosUsuariosEntity> planos_usuariossOptional = planos_usuariosRepository.findById(id);
        if (planos_usuariossOptional.isPresent()) {
            planos_usuariosRepository.deleteById(id);
            return "Plano deletado com sucesso";
        } else {
            throw new ResourceNotFoundException("Plano não encontrado com Id: " + id);
        }
    }
}
