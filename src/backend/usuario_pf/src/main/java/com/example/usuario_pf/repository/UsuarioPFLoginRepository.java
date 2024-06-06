package com.example.usuario_pf.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.example.usuario_pf.model.entity.UsuarioPFEntity;

/**
 * Repository interface for 'Pessoa Jurídica' users.
 * This interface manages database interactions for {@link UsuarioPFEntity}.
 * It extends JpaRepository, providing CRUD operations and JPA capabilities.
 */
@Repository
public interface UsuarioPFLoginRepository extends JpaRepository<UsuarioPFEntity, String> {

    /**
     * Retrieves an {@link UsuarioPFEntity} by its id.
     *
     * @param id The primary key ID of the 'Pessoa Jurídica' user to find.
     * @return An {@link Optional} of {@link UsuarioPFEntity} if found, or an empty
     *         {@link Optional} if not.
     */


    //  @Query("SELECT u.nome, u.email, up.data_nascimento, up.cpf " +
    //         "FROM usuario u " +
    //         "INNER JOIN usuario_pf up ON u.id_usuario = up.id_usuario " +
    //         "WHERE up.cpf = :cpf")
    //  Optional<UsuarioPFEntity> findByCpfid(@Param("cpf") String cpf);
    Optional<UserDetails> findByLogin(String login);


}
