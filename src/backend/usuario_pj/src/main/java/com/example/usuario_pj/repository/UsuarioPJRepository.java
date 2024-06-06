package com.example.usuario_pj.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.usuario_pj.model.entity.UsuarioPJEntity;

@Repository
public interface UsuarioPJRepository extends JpaRepository<UsuarioPJEntity, String> {

    Optional<UsuarioPJEntity> findByLogin(String login);

    
}