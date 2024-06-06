package com.example.usuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.usuario.model.entity.UsuarioEntity;

/**
 * Repository interface for 'Pessoa Jurídica' users.
 * This interface manages database interactions for {@link UsuarioEntity}.
 * It extends JpaRepository, providing CRUD operations and JPA capabilities.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

    /**
     * Retrieves an {@link UsuarioEntity} by its id.
     *
     * @param id The primary key ID of the 'Pessoa Jurídica' user to find.
     * @return An {@link Optional} of {@link UsuarioEntity} if found, or an empty
     *         {@link Optional} if not.
     */
    Optional<UsuarioEntity> findById(Integer id);
}
