package com.example.usuario_pj;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.usuario_pj.dto.UsuarioPJDTO;
import com.example.usuario_pj.model.entity.UserRole;
import com.example.usuario_pj.model.entity.UsuarioPJEntity;
import com.example.usuario_pj.repository.UsuarioPJRepository;
import com.example.usuario_pj.service.UsuarioPJService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.usuario_pj.controller.UsuarioPJController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

public class UsuarioPJApplicationUnitTests {

    @Mock
    private UsuarioPJRepository enderecosRepository;

    @InjectMocks
    private UsuarioPJService enderecosService;

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private UsuarioPJController enderecosController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getUsuarioPJsService() {
        // Configura o mock para retornar uma lista de entidades
        List<UsuarioPJEntity> mockEntities = new ArrayList<>(); // Initialize the mockEntities variable
        when(enderecosRepository.findAll()).thenReturn(mockEntities); // Use the correct syntax for stubbing

        // Chama o método getUsuarioPJ e verifica os resultados
        List<UsuarioPJDTO> result = enderecosService.getUsuariosPJ();
        // Adicione asserções para verificar o resultado
    }

    @Test
    public void getUsuarioPJbyIdService() {
        // Configura o mock para retornar uma entidade
        UsuarioPJEntity mockEntity = new UsuarioPJEntity(); // Initialize the mockEntity variable
        when(enderecosRepository.findByLogin("11122233344459")).thenReturn(java.util.Optional.of(mockEntity)); // Use the correct syntax
                                                                                             // for stubbing

        // Chama o método getEnderecoById e verifica os resultados
        UsuarioPJDTO result = enderecosService.getUsuarioPJbylogin("11122233344459");
    }

    @Test
    public void createUsuarioPJService() {
        // Configura o mock para retornar uma entidade
        UsuarioPJEntity mockEntity = new UsuarioPJEntity(); // Initialize the mockEntity variable
        when(enderecosRepository.save(mockEntity)).thenReturn(mockEntity); // Use the correct syntax for stubbing

        // Chama o método createEndereco e verifica os resultados
        UsuarioPJDTO result = enderecosService.createUsuarioPJ(new UsuarioPJDTO());
    }

    @Test
    public void updateUsuarioPJService() {
        // Configura o mock para retornar uma entidade
        UsuarioPJEntity mockEntity = new UsuarioPJEntity(); // Initialize the mockEntity variable
        when(enderecosRepository.findByLogin("11122233344459")).thenReturn(java.util.Optional.of(mockEntity)); // Use the correct syntax
                                                                                             // for stubbing

        // Chama o método updateEndereco e verifica os resultados
        UsuarioPJDTO result = enderecosService.updateUsuarioPJ(new UsuarioPJDTO(), "11122233344459");
    }

    @Test
    public void deleteUsuarioPJService() {
        // Configura o mock para retornar uma entidade
        UsuarioPJEntity mockEntity = new UsuarioPJEntity(); // Initialize the mockEntity variable
        when(enderecosRepository.findByLogin("11122233344459")).thenReturn(java.util.Optional.of(mockEntity)); // Use the correct syntax
                                                                                             // for stubbing

        // Chama o método deleteEndereco e verifica os resultados
        enderecosService.deleteUsuarioPJ("11122233344459");
    }

    @Test
    public void testGettersAndSettersEntity() {
        UsuarioPJEntity usuarioPJ = new UsuarioPJEntity();

        // Testing setters and getters for login
        usuarioPJ.setLogin("testLoginPJ");
        assertEquals("testLoginPJ", usuarioPJ.getLogin());

        // Testing setters and getters for razao_social
        usuarioPJ.setRazao_social("Razão Social Teste");
        assertEquals("Razão Social Teste", usuarioPJ.getRazao_social());

        // Testing setters and getters for senha
        usuarioPJ.setSenha("senhaTestePJ");
        assertEquals("senhaTestePJ", usuarioPJ.getSenha());

        // Testing setters and getters for data_nascimento
        usuarioPJ.setData_nascimento("01/01/1990");
        assertEquals("01/01/1990", usuarioPJ.getData_nascimento());

        // Testing setters and getters for id_usuario
        usuarioPJ.setId_usuario(1);
        assertEquals(1, usuarioPJ.getId_usuario());

        // Testing setters and getters for role
        usuarioPJ.setRole(UserRole.ADMIN);
        assertEquals(UserRole.ADMIN, usuarioPJ.getRole());
    }

     @Test
    public void testGettersAndSettersDTO() {
        UsuarioPJDTO usuarioPJ = new UsuarioPJDTO();

        // Testing setters and getters for login
        usuarioPJ.setLogin("testLoginPJ");
        assertEquals("testLoginPJ", usuarioPJ.getLogin());

        // Testing setters and getters for razao_social
        usuarioPJ.setRazao_social("Razão Social Teste");
        assertEquals("Razão Social Teste", usuarioPJ.getRazao_social());

        // Testing setters and getters for senha
        usuarioPJ.setSenha("senhaTestePJ");
        assertEquals("senhaTestePJ", usuarioPJ.getSenha());

        // Testing setters and getters for data_nascimento
        usuarioPJ.setData_nascimento("01/01/1990");
        assertEquals("01/01/1990", usuarioPJ.getData_nascimento());

        // Testing setters and getters for id_usuario
        usuarioPJ.setId_usuario(1);
        assertEquals(1, usuarioPJ.getId_usuario());

        // Testing setters and getters for role
        usuarioPJ.setRole(UserRole.ADMIN);
        assertEquals(UserRole.ADMIN, usuarioPJ.getRole());
    }

    @Test
    public void findByIdTest() {
        UsuarioPJEntity usuario_pj = new UsuarioPJEntity();
        usuario_pj.setLogin("11122233344459");
        // preencha os outros campos do endereco

        when(enderecosRepository.findByLogin("11122233344459")).thenReturn(Optional.of(usuario_pj));

        Optional<UsuarioPJEntity> foundEndereco = enderecosRepository.findByLogin("11122233344459");

        assertEquals(usuario_pj, foundEndereco.get());
    }

    // @Test
    // public void getUsuarioPJTest() throws Exception {
    // MockMvc mockMvc =
    // MockMvcBuilders.standaloneSetup(enderecosController).build();

    // UsuarioPJDTO endereco1 = new UsuarioPJDTO();
    // // preencha os detalhes do endereco1
    // UsuarioPJDTO endereco2 = new UsuarioPJDTO();
    // // preencha os detalhes do endereco2

    // List<UsuarioPJDTO> usuario_pj = Arrays.asList(endereco1, endereco2);

    // given(enderecosService.getUsuarioPJ()).willReturn(usuario_pj);

    // mockMvc.perform(get("/usuario_pj")
    // .contentType(MediaType.APPLICATION_JSON))
    // .andExpect(status().isOk())
    // .andExpect(content().json("[{'endereco1 details'}, {'endereco2 details'}]"));
    // }

}
