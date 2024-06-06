package br.edu.inteli.redisapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.inteli.redisapi.entities.PessoaFisica;

/**
 * Repository for Redis
 */
@Repository
public interface RedisRepository extends CrudRepository<PessoaFisica, String> {
}
