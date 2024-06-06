package com.example.planos_usuarios.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.planos_usuarios.model.entity.PlanosUsuariosEntity;

/**
 * Repository interface for managing PJ mobile plans.
 */
@Repository
public interface PlanosUsuariosRepository extends JpaRepository<PlanosUsuariosEntity, Integer> {

    /**
     * Retrieves a PJ mobile plan by their ID.
     *
     * @param id The ID of the mobile plan to retrieve.
     * @return An Optional containing the retrieved mobile plan, or empty if no mobile plan with the given ID is found.
     */
    Optional<PlanosUsuariosEntity> findById(Integer id);

}
