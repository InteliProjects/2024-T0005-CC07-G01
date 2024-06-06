package com.example.usuario_pf.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.usuario_pf.dto.UsuarioPFDTO;
import com.example.usuario_pf.model.entity.UsuarioPFEntity;
import com.example.usuario_pf.repository.UsuarioPFLoginRepository;
import com.example.usuario_pf.repository.UsuarioPFRepository;


import com.example.usuario_pf.exception.ResourceNotFoundException;

import java.util.Collections;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.usuario_pf.exception.ResourceNotFoundException;
import com.example.usuario_pf.infra.security.TokenService;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;

/**
 * Service class for managing users of type PJ (Pessoa Jurídica).
 */
@Service
public class UsuarioPFService implements UserDetailsService {   
    @Value("${server.url}")
    private String url;

    @Value("${server.port_enderecos}")
    private String port_enderecos;
    
    @Value("${server.port_usuario}")
    private String port_usuario;

    @Value("${server.port_plano}")
    private String port_plano;
    
    @Value("${server.port_planos_usuarios}")
    private String port_planos_usuarios;

    @Autowired
    private UsuarioPFRepository usuario_pfRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioPFLoginRepository usuario_pfloginRepository;

    @Autowired
    private final RestTemplate restTemplate;


    
    public UsuarioPFService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    /**
     * Converts a UsuariosPJEntity to a UsuariosPJDTO.
     *
     * @param usuario_pfsPJEntity The entity to convert.
     * @return The converted DTO.
     */
    private UsuarioPFDTO converter(UsuarioPFEntity usuario_pfEntity) {
        UsuarioPFDTO result = new UsuarioPFDTO();
        result.setLogin(usuario_pfEntity.getLogin());
        result.setNome(usuario_pfEntity.getNome());
        result.setRg(usuario_pfEntity.getRg());
        result.setSenha(usuario_pfEntity.getSenha());
        result.setData_nascimento(usuario_pfEntity.getData_nascimento());
        result.setId_usuario(usuario_pfEntity.getId_usuario());

        return result;
    }

    /**
     * Retrieves all PJ users.
     *UsuarioPFEntity
     * @return A list of all PJ users.
     */
    public List<UsuarioPFDTO> getUsuariosPF() {
        return usuario_pfRepository
                .findAll()
                .stream()
                .map(this::converter).collect(Collectors.toList());
    }

    /**
     * Retrieves a PJ user by their ID.
     *
     * @param login The ID of the user to retrieve.
     * @return The retrieved user.
     * @throws ResourceNotFoundException If no user with the given ID is found.
     */
    public UsuarioPFDTO getUsuarioPFbylogin(String login) {
        Optional<UsuarioPFEntity> usuario_pfsPJEntityOptional = usuario_pfRepository.findByLogin(login);
        if (usuario_pfsPJEntityOptional.isPresent()) {
            UsuarioPFEntity usuario_pfsPJEntity = usuario_pfsPJEntityOptional.get();
            return converter(usuario_pfsPJEntity);
        } else {
            return null;
        }
    }

    public UserDetails getUsuariosPFbyLoginAuth(String login) {
        Optional<UserDetails> usuario_pfsPJEntityOptional = usuario_pfloginRepository.findByLogin(login);
        if (usuario_pfsPJEntityOptional.isPresent()) {
            UserDetails usuario_pfsPJEntity = usuario_pfsPJEntityOptional.get();
            return (UserDetails) usuario_pfsPJEntity;
        } else {
            return null;
        }

        
    }

     public JSONObject getUsuarioPJeUsuariobylogin(String token) {

        String login = tokenService.extractLoginFromToken(token);

        Optional<UsuarioPFEntity> usuario_pfsPJEntityOptional = usuario_pfRepository.findByLogin(login);

        System.out.println("usuario_pfsPJEntityOptional: " + usuario_pfsPJEntityOptional);

        if (usuario_pfsPJEntityOptional.isPresent()) {
            UsuarioPFEntity usuario_pfsPJEntity = usuario_pfsPJEntityOptional.get();
            int usuarioId = usuario_pfsPJEntity.getId_usuario(); // Supondo que você tenha um método getId()
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

            System.out.println("usuario_pfsPJEntity: " + usuario_pfsPJEntity);
            System.out.println("usuarioId: " + usuarioId);

            // Utilizando URI template para inserir a variável usuario na URL
            String url_usuario = String.format("http://%s:%s/usuario/{usuario_id}", url, port_usuario);
            Map<String, Integer> uriVariables = Collections.singletonMap("usuario_id", usuarioId);

            ResponseEntity<String> response = restTemplate.exchange(url_usuario, HttpMethod.GET, entity, String.class,
                    uriVariables);

            UsuarioPFDTO usuarioPFDTO = converter(usuario_pfsPJEntity);

            System.out.println("UsuarioPJDTO" + usuarioPFDTO);
            System.out.println("Response" + response.getBody());

            if (response != null && response.getBody() != null && !response.getBody().isEmpty()) {
                // Processar a resposta e criar o objeto JSON
                JSONObject jsonObject = new JSONObject();
                // Adiciona os campos de usuarioPJDTO
                jsonObject.put("senha", usuarioPFDTO.getSenha());
                jsonObject.put("id_usuario", usuarioPFDTO.getId_usuario());
                jsonObject.put("data_nascimento", usuarioPFDTO.getData_nascimento());
                jsonObject.put("rg", usuarioPFDTO.getRg());
                jsonObject.put("login", usuarioPFDTO.getLogin());
                // Adiciona os campos de usuarioResponse
                JSONObject usuarioResponse = new JSONObject(response.getBody());
                for (String key : usuarioResponse.keySet()) {
                    jsonObject.put(key, usuarioResponse.get(key));
                }
                System.out.println("jsonObject: " + jsonObject);
                return jsonObject;
            } else {
                // Lidar com a resposta vazia ou nula
                System.out.println("A resposta da requisição está vazia ou nula.");
                return null; // Ou lança uma exceção, dependendo do seu caso
            }

        } else {
            return null;
        }
    }
    public JSONObject getPlanoePlanosUsuariosbylogin(String token) {
        String login = tokenService.extractLoginFromToken(token);
        Optional<UsuarioPFEntity> usuario_pfsPJEntityOptional = usuario_pfRepository.findByLogin(login);
    
        if (usuario_pfsPJEntityOptional.isPresent()) {
            UsuarioPFEntity usuario_pfsPJEntity = usuario_pfsPJEntityOptional.get();

            int usuarioId = usuario_pfsPJEntity.getId_usuario(); 

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
    
            // Primeira requisição
            String url_plano_usuarios = String.format("http://%s:%s/planos_usuarios/{usuario_id}", url, port_planos_usuarios);
            Map<String, Integer> uriVariables = Collections.singletonMap("usuario_id", usuarioId);
            ResponseEntity<String> response = restTemplate.exchange(url_plano_usuarios, HttpMethod.GET, entity, String.class, uriVariables);
    
            if (response != null && response.getBody() != null && !response.getBody().isEmpty()) {
                JSONObject planos_usuarios_response = new JSONObject(response.getBody());
                int id_plano = planos_usuarios_response.getInt("id_plano");
    
                // Segunda requisição
                String url_plano =  String.format("http://%s:%s/plano/{plano_id}", url, port_plano);
                Map<String, Integer> uriVariablesPlano = Collections.singletonMap("plano_id", id_plano);
                ResponseEntity<String> responsePlano = restTemplate.exchange(url_plano, HttpMethod.GET, entity, String.class, uriVariablesPlano);
    
                if (responsePlano != null && responsePlano.getBody() != null && !responsePlano.getBody().isEmpty()) {
                    JSONObject plano_response = new JSONObject(responsePlano.getBody());
    
                    // Combina as duas respostas em um único JSONObject
                    JSONObject combinedResponse = new JSONObject();
                    for (String key : planos_usuarios_response.keySet()) {
                        combinedResponse.put(key, planos_usuarios_response.get(key));
                    }
                    for (String key : plano_response.keySet()) {
                        combinedResponse.put(key, plano_response.get(key));
                    }
    
                    return combinedResponse;
                } else {
                    System.out.println("A resposta da segunda requisição está vazia ou nula.");
                    return null; // Ou lança uma exceção, dependendo do seu caso
                }
            } else {
                System.out.println("A resposta da primeira requisição está vazia ou nula.");
                return null; // Ou lança uma exceção, dependendo do seu caso
            }
        } else {
            return null;
        }
    }

    
    public String getEnderecobyId(String token) {

        String login = tokenService.extractLoginFromToken(token);
        Optional<UsuarioPFEntity> usuario_pfsPJEntityOptional = usuario_pfRepository.findByLogin(login);
    
        if (usuario_pfsPJEntityOptional.isPresent()) {
        UsuarioPFEntity usuario_pfsPJEntity = usuario_pfsPJEntityOptional.get();

        int usuario_id= usuario_pfsPJEntity.getId_usuario(); // Supondo que você tenha um método getId()

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        // Utilizando URI template para inserir a variável usuario na URL
        String url_enderecos = String.format("http://%s:%s/enderecos/usuario/{usuario_id}", url, port_enderecos);
        Map<String, Integer> uriVariables = Collections.singletonMap("usuario_id", usuario_id);

        ResponseEntity<String> response = restTemplate.exchange(url_enderecos, HttpMethod.GET, entity, String.class, uriVariables);
        return response.getBody();
        } else {
            return null;
        }
    }


    /**
     * Creates a new PJ user.
     *
     * @param usuario_pfsPJDTO The DTO of the user to create.
     * @return The created user.
     */
    public UsuarioPFDTO createUsuarioPF(UsuarioPFDTO usuario_pfDTO) {
        UsuarioPFEntity usuario_pfEntity = new UsuarioPFEntity();

        usuario_pfEntity.setLogin(usuario_pfDTO.getLogin());
        usuario_pfEntity.setNome(usuario_pfDTO.getNome());
        usuario_pfEntity.setRg(usuario_pfDTO.getRg());
        usuario_pfEntity.setRole(usuario_pfDTO.getRole());
        usuario_pfEntity.setSenha(usuario_pfDTO.getSenha());
        usuario_pfEntity.setData_nascimento(usuario_pfDTO.getData_nascimento());
        usuario_pfEntity.setId_usuario(usuario_pfDTO.getId_usuario());

        usuario_pfRepository.save(usuario_pfEntity);

        return usuario_pfDTO;
    }

    /**
     * Updates an existing PJ user.
     *
     * @param usuario_pfsPJDTO The DTO of the user to update.
     * @param login            The ID of the user to update.
     * @return The updated user.
     * @throws ResourceNotFoundException If no user with the given ID is found.
     */
    public UsuarioPFDTO updateUsuarioPF(UsuarioPFDTO usuario_pfDTO, String login) {
        Optional<UsuarioPFEntity> usuario_pfEntityOptional = usuario_pfRepository.findByLogin(login);
        if (usuario_pfEntityOptional.isPresent()) {
            UsuarioPFEntity usuario_pfEntity = usuario_pfEntityOptional.get();
            usuario_pfEntity.setLogin(usuario_pfDTO.getLogin());
            usuario_pfEntity.setNome(usuario_pfDTO.getNome());
            usuario_pfEntity.setRole(usuario_pfDTO.getRole());
            usuario_pfEntity.setRg(usuario_pfDTO.getRg());
            usuario_pfEntity.setSenha(usuario_pfDTO.getSenha());
            usuario_pfEntity.setData_nascimento(usuario_pfDTO.getData_nascimento());
            usuario_pfEntity.setId_usuario(usuario_pfDTO.getId_usuario());

            usuario_pfRepository.save(usuario_pfEntity);

            return usuario_pfDTO;
        } else {
            throw new ResourceNotFoundException("Usuario não encontrado com login: " + login);
        }
    }

    /**
     * Deletes a PJ user by their ID.
     *
     * @param login The ID of the user to delete.
     * @return A success message if the user was deleted.
     * @throws ResourceNotFoundException If no user with the given ID is found.
     */
    public String deleteUsuarioPF(@PathVariable("login") String login) {
        Optional<UsuarioPFEntity> usuario_pfsPJEntityOptional = usuario_pfRepository.findByLogin(login);
        if (usuario_pfsPJEntityOptional.isPresent()) {
            usuario_pfRepository.deleteById(login);
            return "Usuario deletado com sucesso";
        } else {
            throw new ResourceNotFoundException("Usuario não encontrado com login: " + login);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<UserDetails> userDetailsOptional = usuario_pfloginRepository.findByLogin(login);
        return userDetailsOptional
                .orElseThrow(() -> new UsernameNotFoundException("User not found with login: " + login));
    }

}
