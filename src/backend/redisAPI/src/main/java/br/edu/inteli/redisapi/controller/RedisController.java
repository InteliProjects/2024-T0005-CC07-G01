package br.edu.inteli.redisapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import br.edu.inteli.redisapi.entities.PessoaFisica;
import br.edu.inteli.redisapi.repository.RedisRepository;
import br.edu.inteli.redisapi.service.RedisService;

/**
 * Controller for Redis
 */
@RestController
@RequestMapping("/")
public class RedisController {

    @Autowired
    private RedisRepository repo;
    @Autowired
    private RedisService service;

    /**
     * Index route
     * 
     * @return Hello message
     */
    @GetMapping()
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("Hello from API");
    }

    /**
     * Get all users
     * 
     * @return List of users
     */
    @GetMapping("/usuario_pf")
    public ResponseEntity<Iterable<PessoaFisica>> getAll() {
        return ResponseEntity.ok(repo.findAll());
    }

    /**
     * Get user by ID
     * 
     * @param id User ID
     * @return User
     */
    @GetMapping("/usuario_pf/id={id}")
    public ResponseEntity<PessoaFisica> getById(@PathVariable String id) {
        return ResponseEntity.ok(repo.findById(id).get());
    }

    /**
     * Get user by login
     * 
     * @param login User login
     * @return User
     */
    @GetMapping("/usuario_pf/login={login}")
    public ResponseEntity<?> getByLogin(@PathVariable String login) {
        String id = service.encodePessoaFisicaId(login);
        return ResponseEntity.ok(repo.findById(id).get());
    }

    /**
     * Save user
     * 
     * @param pessoaFisica User
     * @return User
     */
    @PostMapping("/usuario_pf")
    public ResponseEntity<PessoaFisica> save(@RequestBody PessoaFisica pessoaFisica) {
        pessoaFisica.setId(service.encodePessoaFisicaId(pessoaFisica.getLogin()));
        return ResponseEntity.ok(repo.save(pessoaFisica));
    }

    /**
     * Update user
     * 
     * @param pessoaFisica User
     * @return User
     */
    @PutMapping("/usuario_pf/id={id}")
    public ResponseEntity<PessoaFisica> update(@RequestBody PessoaFisica pessoaFisica) {
        pessoaFisica.setId(service.encodePessoaFisicaId(pessoaFisica.getLogin()));
        return ResponseEntity.ok(repo.save(pessoaFisica));
    }

    /**
     * Delete user by ID
     * 
     * @param id User ID
     */
    @DeleteMapping("/usuario_pf/id={id}")
    public void delete(@PathVariable String id) {
        repo.deleteById(id);
    }

    /**
     * Delete user by login
     * 
     * @param login User login
     */
    @DeleteMapping("/usuario_pf/login={login}")
    public void deleteByLogin(@PathVariable String login) {
        String id = service.encodePessoaFisicaId(login);
        repo.deleteById(id);
    }

}
