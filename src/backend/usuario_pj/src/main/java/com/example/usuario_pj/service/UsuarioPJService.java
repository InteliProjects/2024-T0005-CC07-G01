package com.example.usuario_pj.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.example.usuario_pj.dto.UsuarioPJDTO;
import com.example.usuario_pj.model.entity.UsuarioPJEntity;
import com.example.usuario_pj.repository.UsuarioPJLoginRepository;
import com.example.usuario_pj.repository.UsuarioPJRepository;

import java.util.Collections;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.usuario_pj.exception.ResourceNotFoundException;
import com.example.usuario_pj.infra.security.TokenService;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing users of type PJ (Pessoa Jurídica).
 */
@Service
public class UsuarioPJService implements UserDetailsService {

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
    private UsuarioPJRepository usuario_pjRepository;

    @Autowired
    private UsuarioPJLoginRepository usuario_pjLoginRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private final RestTemplate restTemplate;

    public UsuarioPJService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Converts a UsuariosPJEntity to a UsuariosPJDTO.
     *
     * @param usuario_pjsPJEntity The entity to convert.
     * @return The converted DTO.
     */
    private UsuarioPJDTO converter(UsuarioPJEntity usuario_pjEntity) {
        UsuarioPJDTO result = new UsuarioPJDTO();
        result.setLogin(usuario_pjEntity.getLogin());
        result.setRazao_social(usuario_pjEntity.getRazao_social());
        result.setSenha(usuario_pjEntity.getSenha());
        result.setData_nascimento(usuario_pjEntity.getData_nascimento());
        result.setId_usuario(usuario_pjEntity.getId_usuario());

        return result;
    }

    /**
     * Retrieves all PJ users.
     *
     * @return A list of all PJ users.
     */
    public List<UsuarioPJDTO> getUsuariosPJ() {
        return usuario_pjRepository
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

    public UsuarioPJDTO getUsuarioPJbylogin(String login) {
        Optional<UsuarioPJEntity> usuario_pfsPJEntityOptional = usuario_pjRepository.findByLogin(login);
        if (usuario_pfsPJEntityOptional.isPresent()) {
            UsuarioPJEntity usuario_pfsPJEntity = usuario_pfsPJEntityOptional.get();
            return converter(usuario_pfsPJEntity);
        } else {
            return null;
        }
    }

    public JSONObject getUsuarioPJeUsuariobylogin(String token) {

        String login = tokenService.extractLoginFromToken(token);

        Optional<UsuarioPJEntity> usuario_pfsPJEntityOptional = usuario_pjRepository.findByLogin(login);

        System.out.println("usuario_pfsPJEntityOptional: " + usuario_pfsPJEntityOptional);

        if (usuario_pfsPJEntityOptional.isPresent()) {
            UsuarioPJEntity usuario_pfsPJEntity = usuario_pfsPJEntityOptional.get();
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

            UsuarioPJDTO usuarioPJDTO = converter(usuario_pfsPJEntity);

            System.out.println("UsuarioPJDTO" + usuarioPJDTO);
            System.out.println("Response" + response.getBody());

            if (response != null && response.getBody() != null && !response.getBody().isEmpty()) {
                // Processar a resposta e criar o objeto JSON
                JSONObject jsonObject = new JSONObject();
                // Adiciona os campos de usuarioPJDTO
                jsonObject.put("senha", usuarioPJDTO.getSenha());
                jsonObject.put("id_usuario", usuarioPJDTO.getId_usuario());
                jsonObject.put("data_nascimento", usuarioPJDTO.getData_nascimento());
                jsonObject.put("razao_social", usuarioPJDTO.getRazao_social());
                jsonObject.put("login", usuarioPJDTO.getLogin());
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
        Optional<UsuarioPJEntity> usuario_pfsPJEntityOptional = usuario_pjRepository.findByLogin(login);
    
        if (usuario_pfsPJEntityOptional.isPresent()) {
            UsuarioPJEntity usuario_pfsPJEntity = usuario_pfsPJEntityOptional.get();

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
                String url_plano = String.format("http://%s:%s/plano/{plano_id}", url, port_plano);
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
        Optional<UsuarioPJEntity> usuario_pfsPJEntityOptional = usuario_pjRepository.findByLogin(login);
    
        if (usuario_pfsPJEntityOptional.isPresent()) {
        UsuarioPJEntity usuario_pfsPJEntity = usuario_pfsPJEntityOptional.get();

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
     * @param usuario_pjsPJDTO The DTO of the user to create.
     * @return The created user.
     */
    public UsuarioPJDTO createUsuarioPJ(UsuarioPJDTO usuario_pjDTO) {
        UsuarioPJEntity usuario_pjEntity = new UsuarioPJEntity();

        usuario_pjEntity.setLogin(usuario_pjDTO.getLogin());
        usuario_pjEntity.setRazao_social(usuario_pjDTO.getRazao_social());
        usuario_pjEntity.setSenha(usuario_pjDTO.getSenha());
        usuario_pjEntity.setData_nascimento(usuario_pjDTO.getData_nascimento());
        usuario_pjEntity.setId_usuario(usuario_pjDTO.getId_usuario());
        usuario_pjRepository.save(usuario_pjEntity);

        return usuario_pjDTO;
    }

    /**
     * Updates an existing PJ user.
     *
     * @param usuario_pjsPJDTO The DTO of the user to update.
     * @param login            The ID of the user to update.
     * @return The updated user.
     * @throws ResourceNotFoundException If no user with the given ID is found.
     */
    public UsuarioPJDTO updateUsuarioPJ(UsuarioPJDTO usuario_pjDTO, String login) {
        Optional<UsuarioPJEntity> usuario_pjEntityOptional = usuario_pjRepository.findByLogin(login);
        if (usuario_pjEntityOptional.isPresent()) {
            UsuarioPJEntity usuario_pjEntity = usuario_pjEntityOptional.get();
            usuario_pjEntity.setLogin(usuario_pjDTO.getLogin());
            usuario_pjEntity.setRazao_social(usuario_pjDTO.getRazao_social());
            usuario_pjEntity.setSenha(usuario_pjDTO.getSenha());
            usuario_pjEntity.setData_nascimento(usuario_pjDTO.getData_nascimento());
            usuario_pjEntity.setId_usuario(usuario_pjDTO.getId_usuario());

            usuario_pjRepository.save(usuario_pjEntity);

            return usuario_pjDTO;
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
    public String deleteUsuarioPJ(@PathVariable("login") String login) {
        Optional<UsuarioPJEntity> usuarioPJEntityOptional = usuario_pjRepository.findByLogin(login);
        if (usuarioPJEntityOptional.isPresent()) {
            usuario_pjRepository.deleteById(login);
            return "Usuario deletado com sucesso";
        } else {
            throw new ResourceNotFoundException("Usuario não encontrado com login: " + login);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        return usuario_pjLoginRepository.findByLogin(username);
    }

}
