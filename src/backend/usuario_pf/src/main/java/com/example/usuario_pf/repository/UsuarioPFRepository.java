package com.example.usuario_pf.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.usuario_pf.model.entity.UsuarioPFEntity;

@Repository
public interface UsuarioPFRepository extends JpaRepository<UsuarioPFEntity, String> {

    Optional<UsuarioPFEntity> findByLogin(String login);

    
}
