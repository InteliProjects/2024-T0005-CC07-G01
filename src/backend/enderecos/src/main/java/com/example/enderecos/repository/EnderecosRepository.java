/**
 * This package contains the repository for the Enderecos application.
 */
package com.example.enderecos.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.enderecos.model.entity.EnderecosEntity;

/**
 * This interface represents the repository for the EnderecosEntity.
 */
@Repository
public interface EnderecosRepository extends JpaRepository<EnderecosEntity, Integer> {

    /**
     * This method finds an EnderecosEntity by its id.
     * @param id The id of the EnderecosEntity
     * @return an Optional containing the EnderecosEntity if found
     */
    Optional<EnderecosEntity> findById(Integer id);

    @Query( "SELECT e FROM EnderecosEntity e WHERE e.id_usuario = :id_usuario")
    List<EnderecosEntity> findAllByIdUsuario(Integer id_usuario);


}
