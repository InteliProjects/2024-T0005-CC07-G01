package br.edu.inteli.databus.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.edu.inteli.databus.services.*;
import br.edu.inteli.databus.entities.*;

@RestController
@RequestMapping("/")
public class DataBusController {
    @Value("${USUARIOS_PF_URL}")
    private String apiHost;

    @Value("${REDIS_API_URL}")
    private String cacheHost;

    /**
     * Index route
     * 
     * @return ResponseEntity<String> with the response
     */
    @GetMapping
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("Hello from the API");
    }

    /**
     * Get all private individuals
     * 
     * @return ResponseEntity<String> with the response
     */
    @GetMapping("/usuario_pf")
    public ResponseEntity<String> getAllUsuarioPf() {
        final String apiUrl = String.format("%s/usuario_pf", apiHost);
        final String cacheUrl = String.format("%s/usuario_pf", cacheHost);
        return BusHandler.handleBusGetRequest(apiUrl, cacheUrl);
    }

    /**
     * Get private individual by CPF
     * 
     * @param login Private individual CPF
     * @return ResponseEntity<String> with the response
     */
    @GetMapping("/usuario_pf/login={login}")
    public ResponseEntity<String> getUsuarioPfById(@PathVariable String login) {
        final String apiUrl = String.format("%s/usuario_pf/%s", apiHost, login);
        final String cacheUrl = String.format("%s/usuario_pf/login=%s", cacheHost, login);
        return BusHandler.handleBusGetRequest(apiUrl, cacheUrl);
    }

    /**
     * Create a new private individual
     * 
     * @param body Request body
     * @return ResponseEntity<String> with the response
     */
    @PostMapping("/usuario_pf")
    public ResponseEntity<String> getUsuarioPfById(@RequestBody PessoaFisica body) {
        final String apiUrl = String.format("%s/usuario_pf", apiHost);
        final String cacheUrl = String.format("%s/usuario_pf", cacheHost);
        return BusHandler.handleBusPostRequest(apiUrl, cacheUrl, body);
    }
}
