package com.example.plano.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.plano.dto.PlanoDTO;
import com.example.plano.service.PlanoService;

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
@RequestMapping("/plano")
@CrossOrigin(origins = {"http://projetonimbus.tech", "http://localhost:5173"})
public class PlanoController {

    @Autowired
    private PlanoService planoService;

    /**
     * Retrieves all PJ mobile plans.
     *
     * @return A list of all PJ mobile plans.
     */
    @GetMapping()
    public List<PlanoDTO> getPlanos() {
        return planoService.getPlanos();
    }

    /**
     * Retrieves a PJ mobile plan by their ID.
     *
     * @param id The ID of the mobile plan to retrieve.
     * @return The retrieved mobile plan.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PlanoDTO> getPlanosbyId(@PathVariable("id") Integer id) {
        PlanoDTO plano = planoService.getPlanobyId(id);
        return ResponseEntity.ok(plano);
    }

    /**
     * Creates a new PJ mobile plan.
     *
     * @param planoDTO The DTO of the mobile plan to create.
     * @return The created mobile plan.
     */
    @PostMapping()
    @ResponseBody
    public ResponseEntity<PlanoDTO> createPlano(@RequestBody PlanoDTO planoDTO) {
        PlanoDTO createPlano = planoService.createPlano(planoDTO);

        return ResponseEntity.ok(createPlano);
    }

    /**
     * Updates an existing PJ mobile plan.
     *
     * @param id               The ID of the mobile plan to update.
     * @param planoDTO The DTO of the mobile plan to update.
     * @return The updated mobile plan.
     */
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<PlanoDTO> updatePlano(@PathVariable("id") Integer id,
            @RequestBody PlanoDTO planoDTO) {
        PlanoDTO updatePlano = planoService.updatePlano(planoDTO, id);

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
    public String deletePlano(@PathVariable("id") Integer id) {
        return planoService.deletePlano(id);
    }

}
