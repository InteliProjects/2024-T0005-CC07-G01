package com.example.plano.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.plano.dto.PlanoDTO;
import com.example.plano.exception.ResourceNotFoundException;
import com.example.plano.model.entity.PlanoEntity;
import com.example.plano.repository.PlanoRepository;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing PJ mobile plans.
 */
@Service
public class PlanoService {

    @Autowired
    private PlanoRepository planoRepository;

    /**
     * Converts a PlanosMovelPJEntity to a PlanosMovelPJDTO.
     *
     * @param PlanosMovelPJEntity The entity to convert.
     * @return The converted DTO.
     */
    private PlanoDTO converter(PlanoEntity planosEntity) {
        PlanoDTO result = new PlanoDTO();

        result.setId_plano(planosEntity.getId_plano());
        result.setNome(planosEntity.getNome());
        result.setValor(planosEntity.getValor());
        result.setQtd_internet(planosEntity.getQtd_internet());

        return result;
    }

    /**
     * Retrieves all PJ mobile plans.
     *
     * @return A list of all PJ mobile plans.
     */
    public List<PlanoDTO> getPlanos() {
        return planoRepository
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
    public PlanoDTO getPlanobyId(Integer id) {
        Optional<PlanoEntity> planosOptional = planoRepository.findById(id);
        if (planosOptional.isPresent()) {
            PlanoEntity planoEntity = planosOptional.get();
            return converter(planoEntity);
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
    public PlanoDTO createPlano(PlanoDTO planoDTO) {
        PlanoEntity planoEntity = new PlanoEntity();

        planoEntity.setNome(planoDTO.getNome());
        planoEntity.setValor(planoDTO.getValor());
        planoEntity.setQtd_internet(planoDTO.getQtd_internet());

        planoRepository.save(planoEntity);

        return planoDTO;
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
    public PlanoDTO updatePlano(PlanoDTO planoDTO, Integer id) {
        Optional<PlanoEntity> planoOptional = planoRepository.findById(id);
        if (planoOptional.isPresent()) {
            PlanoEntity planoEntity = planoOptional.get();

            planoEntity.setNome(planoDTO.getNome());
            planoEntity.setValor(planoDTO.getValor());
            planoEntity.setQtd_internet(planoDTO.getQtd_internet());

            planoRepository.save(planoEntity);

            return planoDTO;
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
    public String deletePlano(@PathVariable("id") Integer id) {
        Optional<PlanoEntity> planosOptional = planoRepository.findById(id);
        if (planosOptional.isPresent()) {
            planoRepository.deleteById(id);
            return "Plano deletado com sucesso";
        } else {
            throw new ResourceNotFoundException("Plano não encontrado com Id: " + id);
        }
    }
}
