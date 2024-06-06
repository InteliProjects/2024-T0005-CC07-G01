package com.example.usuario_pf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.usuario_pf.dto.UsuarioPFDTO;
import com.example.usuario_pf.model.entity.UsuarioPFEntity;
import com.example.usuario_pf.model.entity.UsuarioRole;
import com.example.usuario_pf.repository.UsuarioPFRepository;
import com.example.usuario_pf.service.UsuarioPFService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.usuario_pf.controller.UsuarioPFController;
import com.example.usuario_pf.dto.UsuarioPFDTO;
import com.example.usuario_pf.service.UsuarioPFService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

public class UsuarioPFApplicationUnitTests {

    @Mock
    private UsuarioPFRepository enderecosRepository;

    @InjectMocks
    private UsuarioPFService enderecosService;

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private UsuarioPFController enderecosController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getUsuarioPFsService() {
        // Configura o mock para retornar uma lista de entidades
        List<UsuarioPFEntity> mockEntities = new ArrayList<>(); // Initialize the mockEntities variable
        when(enderecosRepository.findAll()).thenReturn(mockEntities); // Use the correct syntax for stubbing

        // Chama o método getUsuarioPF e verifica os resultados
        List<UsuarioPFDTO> result = enderecosService.getUsuariosPF();
        // Adicione asserções para verificar o resultado
    }

    @Test
    public void getUsuarioPFbyIdService() {
        // Configura o mock para retornar uma entidade
        UsuarioPFEntity mockEntity = new UsuarioPFEntity(); // Initialize the mockEntity variable
        when(enderecosRepository.findByLogin("22345674805")).thenReturn(java.util.Optional.of(mockEntity)); // Use the correct syntax
                                                                                             // for stubbing

        // Chama o método getEnderecoById e verifica os resultados
        UsuarioPFDTO result = enderecosService.getUsuarioPFbylogin("22345674805");
    }

    @Test
    public void createUsuarioPFService() {
        // Configura o mock para retornar uma entidade
        UsuarioPFEntity mockEntity = new UsuarioPFEntity(); // Initialize the mockEntity variable
        when(enderecosRepository.save(mockEntity)).thenReturn(mockEntity); // Use the correct syntax for stubbing

        // Chama o método createEndereco e verifica os resultados
        UsuarioPFDTO result = enderecosService.createUsuarioPF(new UsuarioPFDTO());
    }

    @Test
    public void updateUsuarioPFService() {
        // Configura o mock para retornar uma entidade
        UsuarioPFEntity mockEntity = new UsuarioPFEntity(); // Initialize the mockEntity variable
        when(enderecosRepository.findByLogin("22345674805")).thenReturn(java.util.Optional.of(mockEntity)); // Use the correct syntax
                                                                                             // for stubbing

        // Chama o método updateEndereco e verifica os resultados
        UsuarioPFDTO result = enderecosService.updateUsuarioPF(new UsuarioPFDTO(), "22345674805");
    }

    @Test
    public void deleteUsuarioPFService() {
        // Configura o mock para retornar uma entidade
        UsuarioPFEntity mockEntity = new UsuarioPFEntity(); // Initialize the mockEntity variable
        when(enderecosRepository.findByLogin("22345674805")).thenReturn(java.util.Optional.of(mockEntity)); // Use the correct syntax
                                                                                             // for stubbing

        // Chama o método deleteEndereco e verifica os resultados
        enderecosService.deleteUsuarioPF("22345674805");
    }
@Test
    public void testGettersAndSettersEntity() {
        UsuarioPFEntity usuarioPF = new UsuarioPFEntity();

        // Testing setters and getters for login
        usuarioPF.setLogin("testLoginPF");
        assertEquals("testLoginPF", usuarioPF.getLogin());

        // Testing setters and getters for nome
        usuarioPF.setNome("Nome Teste PF");
        assertEquals("Nome Teste PF", usuarioPF.getNome());

        // Testing setters and getters for rg
        usuarioPF.setRg("123456789");
        assertEquals("123456789", usuarioPF.getRg());

        // Testing setters and getters for senha
        usuarioPF.setSenha("senhaTestePF");
        assertEquals("senhaTestePF", usuarioPF.getSenha());

        // Testing setters and getters for data_nascimento
        usuarioPF.setData_nascimento("01/01/1990");
        assertEquals("01/01/1990", usuarioPF.getData_nascimento());

        // Testing setters and getters for id_usuario
        usuarioPF.setId_usuario(1);
        assertEquals(1, usuarioPF.getId_usuario());

        // Testing setters and getters for role
        usuarioPF.setRole(UsuarioRole.ADMIN);
        assertEquals(UsuarioRole.ADMIN, usuarioPF.getRole());
    }
    @Test
    public void testGettersAndSettersDTO() {
        UsuarioPFDTO usuarioPF = new UsuarioPFDTO();

        // Testing setters and getters for login
        usuarioPF.setLogin("testLoginPF");
        assertEquals("testLoginPF", usuarioPF.getLogin());

        // Testing setters and getters for nome
        usuarioPF.setNome("Nome Teste PF");
        assertEquals("Nome Teste PF", usuarioPF.getNome());

        // Testing setters and getters for rg
        usuarioPF.setRg("123456789");
        assertEquals("123456789", usuarioPF.getRg());

        // Testing setters and getters for senha
        usuarioPF.setSenha("senhaTestePF");
        assertEquals("senhaTestePF", usuarioPF.getSenha());

        // Testing setters and getters for data_nascimento
        usuarioPF.setData_nascimento("01/01/1990");
        assertEquals("01/01/1990", usuarioPF.getData_nascimento());

        // Testing setters and getters for id_usuario
        usuarioPF.setId_usuario(1);
        assertEquals(1, usuarioPF.getId_usuario());

        // Testing setters and getters for role
        usuarioPF.setRole(UsuarioRole.ADMIN);
        assertEquals(UsuarioRole.ADMIN, usuarioPF.getRole());
    }

    @Test
    public void findByIdTest() {
        UsuarioPFEntity usuario_pf = new UsuarioPFEntity();
        usuario_pf.setLogin("22345674805");
        // preencha os outros campos do endereco

        when(enderecosRepository.findByLogin("22345674805")).thenReturn(Optional.of(usuario_pf));

        Optional<UsuarioPFEntity> foundEndereco = enderecosRepository.findByLogin("22345674805");

        assertEquals(usuario_pf, foundEndereco.get());
    }

    // @Test
    // public void getUsuarioPFTest() throws Exception {
    // MockMvc mockMvc =
    // MockMvcBuilders.standaloneSetup(enderecosController).build();

    // UsuarioPFDTO endereco1 = new UsuarioPFDTO();
    // // preencha os detalhes do endereco1
    // UsuarioPFDTO endereco2 = new UsuarioPFDTO();
    // // preencha os detalhes do endereco2

    // List<UsuarioPFDTO> usuario_pf = Arrays.asList(endereco1, endereco2);

    // given(enderecosService.getUsuarioPF()).willReturn(usuario_pf);

    // mockMvc.perform(get("/usuario_pf")
    // .contentType(MediaType.APPLICATION_JSON))
    // .andExpect(status().isOk())
    // .andExpect(content().json("[{'endereco1 details'}, {'endereco2 details'}]"));
    // }

}
