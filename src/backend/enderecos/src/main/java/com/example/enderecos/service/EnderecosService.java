package com.example.enderecos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.enderecos.dto.EnderecosDTO;
import com.example.enderecos.exception.ResourceNotFoundException;
import com.example.enderecos.model.entity.EnderecosEntity;
import com.example.enderecos.repository.EnderecosRepository;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;
import com.example.enderecos.model.entity.EnderecosEntity;

/**
 * Service class for managing PJ addresses.
 */
@Service
public class EnderecosService {

    @Autowired
    private EnderecosRepository enderecosRepository;

    /**
     * Converts an EnderecosEntity to an EnderecosDTO.
     *
     * @param enderecosEntity The entity to convert.
     * @return The converted DTO.
     */
    private EnderecosDTO converter(EnderecosEntity enderecosEntity) {
        EnderecosDTO result = new EnderecosDTO();

        result.setId_endereco(enderecosEntity.getId_endereco());
        result.setId_usuario(enderecosEntity.getId_usuario());
        result.setRua(enderecosEntity.getRua());
        result.setNumero(enderecosEntity.getNumero());
        result.setBairro(enderecosEntity.getBairro());
        result.setCidade(enderecosEntity.getCidade());
        result.setEstado(enderecosEntity.getEstado());
        result.setCep(enderecosEntity.getCep());

        return result;
    }

    /**
     * Retrieves all PJ addresses.
     *
     * @return A list of all PJ addresses.
     */
    public List<EnderecosDTO> getEnderecos() {
        return enderecosRepository
                .findAll()
                .stream()
                .map(this::converter).collect(Collectors.toList());
    }

    /**
     * Retrieves a PJ address by their ID.
     *
     * @param id The ID of the address to retrieve.
     * @return The retrieved address.
     * @throws ResourceNotFoundException If no address with the given ID is found.
     */
    public EnderecosDTO getEnderecosbyId(Integer id) {
        Optional<EnderecosEntity> enderecosPJOptional = enderecosRepository.findById(id);
        if (enderecosPJOptional.isPresent()) {
            EnderecosEntity enderecosPJ = enderecosPJOptional.get();
            return converter(enderecosPJ);
        } else {
            throw new ResourceNotFoundException("Endereço não encontrado com Id: " + id);
        }
    }

    public List<EnderecosDTO> findAllByIdUsuario(Integer idUsuario) {
        return enderecosRepository.findAllByIdUsuario(idUsuario)
                .stream()
                .map(this::converter).collect(Collectors.toList());
                
    }

    /**
     * Creates a new PJ address.
     *
     * @param enderecosDTO The DTO of the address to create.
     * @return The created address.
     */
    public EnderecosDTO createEnderecoPJ(EnderecosDTO enderecosDTO) {
        EnderecosEntity enderecosEntity = new EnderecosEntity();

        enderecosEntity.setId_usuario(enderecosDTO.getId_usuario());
        enderecosEntity.setRua(enderecosDTO.getRua());
        enderecosEntity.setNumero(enderecosDTO.getNumero());
        enderecosEntity.setCep(enderecosDTO.getCep());
        enderecosEntity.setBairro(enderecosDTO.getBairro());
        enderecosEntity.setCidade(enderecosDTO.getCidade());
        enderecosEntity.setEstado(enderecosDTO.getEstado());

        enderecosRepository.save(enderecosEntity);

        return enderecosDTO;
    }

    /**
     * Updates an existing PJ address.
     *
     * @param enderecosDTO The DTO of the address to update.
     * @param id           The ID of the address to update.
     * @return The updated address.
     * @throws ResourceNotFoundException If no address with the given ID is found.
     */
    public EnderecosDTO updateEnderecoPJ(EnderecosDTO enderecosDTO, Integer id) {
        Optional<EnderecosEntity> enderecosEntityOptional = enderecosRepository.findById(id);
        if (enderecosEntityOptional.isPresent()) {
            EnderecosEntity enderecosEntity = enderecosEntityOptional.get();

            enderecosEntity.setId_usuario(enderecosDTO.getId_usuario());
            enderecosEntity.setRua(enderecosDTO.getRua());
            enderecosEntity.setNumero(enderecosDTO.getNumero());
            enderecosEntity.setCep(enderecosDTO.getCep());
            enderecosEntity.setBairro(enderecosDTO.getBairro());
            enderecosEntity.setCidade(enderecosDTO.getCidade());
            enderecosEntity.setEstado(enderecosDTO.getEstado());

            enderecosRepository.save(enderecosEntity);

            return enderecosDTO;
        } else {
            throw new ResourceNotFoundException("Endereço não encontrado com Id: " + id);
        }
    }

    /**
     * Deletes a PJ address by their ID.
     *
     * @param id The ID of the address to delete.
     * @return A success message if the address was deleted.
     * @throws ResourceNotFoundException If no address with the given ID is found.
     */
    public String deleteEnderecoPJ(@PathVariable("id") Integer id) {
        Optional<EnderecosEntity> enderecosEntityOptional = enderecosRepository.findById(id);
        if (enderecosEntityOptional.isPresent()) {
            enderecosRepository.deleteById(id);
            return "Endereço deletado com sucesso";
        } else {
            throw new ResourceNotFoundException("Endereço não encontrado com Id: " + id);
        }
    }
}
