/**
 * This package contains the controller for the Enderecos application.
 */
package com.example.enderecos.controller;

import org.springframework.web.bind.annotation.RestController;
import com.example.enderecos.dto.EnderecosDTO;
import com.example.enderecos.service.EnderecosService;
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
 * This is the main controller for the Enderecos application.
 */
@RestController
@RequestMapping("/enderecos")
@CrossOrigin(origins = "http://localhost:8081")
public class EnderecosController {

    /**
     * This is the service class for the Enderecos application.
     */
    @Autowired
    private EnderecosService enderecospjService;

    /**
     * This method returns a list of all Enderecos.
     * 
     * @return a list of EnderecosDTO
     */
    @GetMapping()
    public List<EnderecosDTO> getEnderecos() {
        return enderecospjService.getEnderecos();
    }

    /**
     * This method returns an Enderecos by its id.
     * 
     * @param id The id of the Enderecos
     * @return a ResponseEntity containing the EnderecosDTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<EnderecosDTO> getenderecosbyId(@PathVariable("id") Integer id) {
        EnderecosDTO enderecos = enderecospjService.getEnderecosbyId(id);
        return ResponseEntity.ok(enderecos);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<EnderecosDTO>> findAllByIdUsuario(@PathVariable Integer idUsuario) {
        List<EnderecosDTO> usuarios = enderecospjService.findAllByIdUsuario(idUsuario);
        return ResponseEntity.ok(usuarios);
    }

    /**
     * This method creates a new Enderecos.
     * 
     * @param enderecosDTO The Enderecos to be created
     * @return a ResponseEntity containing the created EnderecosDTO
     */
    @PostMapping()
    public ResponseEntity<EnderecosDTO> createEnderecoPJ(@RequestBody EnderecosDTO enderecosDTO) {

        EnderecosDTO createEnderecoPJ = enderecospjService.createEnderecoPJ(enderecosDTO);

        return ResponseEntity.ok(createEnderecoPJ);
    }

    /**
     * This method updates an existing Enderecos.
     * 
     * @param id           The id of the Enderecos to be updated
     * @param enderecosDTO The updated Enderecos
     * @return a ResponseEntity containing the updated EnderecosDTO
     */
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<EnderecosDTO> updateEnderecoPJ(@PathVariable("id") Integer id,
            @RequestBody EnderecosDTO enderecosDTO) {
        EnderecosDTO updateEnderecoPJ = enderecospjService.updateEnderecoPJ(enderecosDTO, id);
        return ResponseEntity.ok(updateEnderecoPJ);
    }

    /**
     * This method deletes an existing Enderecos.
     * 
     * @param id The id of the Enderecos to be deleted
     * @return a String message indicating the result of the deletion
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public String deleteEnderecoPJ(@PathVariable("id") Integer id) {
        return enderecospjService.deleteEnderecoPJ(id);
    }

}
