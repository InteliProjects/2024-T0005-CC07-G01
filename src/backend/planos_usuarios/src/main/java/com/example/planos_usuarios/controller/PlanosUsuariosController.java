package com.example.planos_usuarios.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.planos_usuarios.dto.PlanosUsuariosDTO;
import com.example.planos_usuarios.service.PlanosUsuariosService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Controller class for managing PJ mobile plans.
 */
@RestController
@RequestMapping("/planos_usuarios")
@CrossOrigin(origins = {"http://projetonimbus.tech", "http://localhost:5173"})

public class PlanosUsuariosController {

    @Autowired
    private PlanosUsuariosService planos_usuariosService;

    /**
     * Retrieves all PJ mobile plans.
     *
     * @return A list of all PJ mobile plans.
     */
    @GetMapping()
    public List<PlanosUsuariosDTO> getPlanosUsuarios() {
        return planos_usuariosService.getPlanosUsuarios();
    }

    /**
     * Retrieves a PJ mobile plan by their ID.
     *
     * @param id The ID of the mobile plan to retrieve.
     * @return The retrieved mobile plan.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PlanosUsuariosDTO> getPlanosUsuariosbyId(@PathVariable("id") Integer id) {
        PlanosUsuariosDTO planos_usuarios = planos_usuariosService.getPlanoUsuariobyId(id);
        return ResponseEntity.ok(planos_usuarios);
    }

    /**
     * Creates a new PJ mobile plan.
     *
     * @param planos_usuariosDTO The DTO of the mobile plan to create.
     * @return The created mobile plan.
     */
    @PostMapping()
    @ResponseBody
    public ResponseEntity<PlanosUsuariosDTO> createPlanoUsuario(@RequestBody PlanosUsuariosDTO planos_usuariosDTO) {
        PlanosUsuariosDTO createPlano = planos_usuariosService.createPlanoUsuario(planos_usuariosDTO);

        return ResponseEntity.ok(createPlano);
    }

    /**
     * Updates an existing PJ mobile plan.
     *
     * @param id               The ID of the mobile plan to update.
     * @param planos_usuariosDTO The DTO of the mobile plan to update.
     * @return The updated mobile plan.
     */
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<PlanosUsuariosDTO> updatePlanoUsuario(@PathVariable("id") Integer id,
            @RequestBody PlanosUsuariosDTO planos_usuariosDTO) {
        PlanosUsuariosDTO updatePlano = planos_usuariosService.updatePlanoUsuario(planos_usuariosDTO, id);

        return ResponseEntity.ok(updatePlano);
    }

    /**
     * Deletes a PJ mobile plan by their ID.
     *
     * @param id The ID of the mobile plan to delete.
     * @return A success message if the mobile plan was deleted.
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public String deletePlanoUsuario(@PathVariable("id") Integer id) {
        return planos_usuariosService.deletePlanoUsuario(id);
    }

}
