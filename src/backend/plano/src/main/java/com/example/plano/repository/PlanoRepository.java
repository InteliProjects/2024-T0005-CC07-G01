package com.example.plano.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.plano.model.entity.PlanoEntity;

/**
 * Repository interface for managing PJ mobile plans.
 */
@Repository
public interface PlanoRepository extends JpaRepository<PlanoEntity, Integer> {

    /**
     * Retrieves a PJ mobile plan by their ID.
     *
     * @param id The ID of the mobile plan to retrieve.
     * @return An Optional containing the retrieved mobile plan, or empty if no mobile plan with the given ID is found.
     */
    Optional<PlanoEntity> findById(Integer id);

}
