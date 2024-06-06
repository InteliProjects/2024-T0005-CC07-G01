package com.example.usuario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.usuario.dto.UsuarioDTO;
import com.example.usuario.model.entity.UsuarioEntity;
import com.example.usuario.repository.UsuarioRepository;
import com.example.usuario.service.UsuarioService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.usuario.controller.UsuarioController;
import com.example.usuario.dto.UsuarioDTO;
import com.example.usuario.service.UsuarioService;
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

public class UsuarioApplicationUnitTests {

    @Mock
    private UsuarioRepository enderecosRepository;

    @InjectMocks
    private UsuarioService enderecosService;

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private UsuarioController enderecosController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getUsuariosService() {
        // Configura o mock para retornar uma lista de entidades
        List<UsuarioEntity> mockEntities = new ArrayList<>(); // Initialize the mockEntities variable
        when(enderecosRepository.findAll()).thenReturn(mockEntities); // Use the correct syntax for stubbing

        // Chama o método getUsuario e verifica os resultados
        List<UsuarioDTO> result = enderecosService.getUsuarios();
        // Adicione asserções para verificar o resultado
    }

    @Test
    public void getUsuariobyIdService() {
        // Configura o mock para retornar uma entidade
        UsuarioEntity mockEntity = new UsuarioEntity(); // Initialize the mockEntity variable
        when(enderecosRepository.findById(1)).thenReturn(java.util.Optional.of(mockEntity)); // Use the correct syntax
                                                                                             // for stubbing

        // Chama o método getEnderecoById e verifica os resultados
        UsuarioDTO result = enderecosService.getUsuariosbyId(1);
    }

    // @Test
    // public void createUsuarioService() {
    //     // Configura o mock para retornar uma entidade
    //     UsuarioEntity mockEntity = new UsuarioEntity(); // Initialize the mockEntity variable
    //     when(enderecosRepository.save(mockEntity)).thenReturn(mockEntity); // Use the correct syntax for stubbing

    //     // Chama o método createEndereco e verifica os resultados
    //     UsuarioDTO result = enderecosService.createUsuario(new UsuarioDTO());
    // }

    @Test
    public void updateUsuarioService() {
        // Configura o mock para retornar uma entidade
        UsuarioEntity mockEntity = new UsuarioEntity(); // Initialize the mockEntity variable
        when(enderecosRepository.findById(1)).thenReturn(java.util.Optional.of(mockEntity)); // Use the correct syntax
                                                                                             // for stubbing

        // Chama o método updateEndereco e verifica os resultados
        UsuarioDTO result = enderecosService.updateUsuario(new UsuarioDTO(), 1);
    }

    @Test
    public void deleteUsuarioService() {
        // Configura o mock para retornar uma entidade
        UsuarioEntity mockEntity = new UsuarioEntity(); // Initialize the mockEntity variable
        when(enderecosRepository.findById(1)).thenReturn(java.util.Optional.of(mockEntity)); // Use the correct syntax
                                                                                             // for stubbing

        // Chama o método deleteEndereco e verifica os resultados
        enderecosService.deleteUsuario(1);
    }

    @Test
    public void testGettersAndSettersEntity() {
        UsuarioEntity usuario = new UsuarioEntity();

        // Testing setters and getters for id_usuario
        usuario.setId_usuario(1);
        assertEquals(1, usuario.getId_usuario());

        // Testing setters and getters for tipo
        usuario.setTipo("PF");
        assertEquals("PF", usuario.getTipo());

        // Testing setters and getters for nome
        usuario.setNome("Nome Teste");
        assertEquals("Nome Teste", usuario.getNome());

        // Testing setters and getters for email
        usuario.setEmail("teste@email.com");
        assertEquals("teste@email.com", usuario.getEmail());

        // Testing setters and getters for saldo
        usuario.setSaldo("R$1000,00");
        assertEquals("R$1000,00", usuario.getSaldo());

        // Testing setters and getters for data_cadastro
        Date dataCadastro = new Date();
        usuario.setData_cadastro(dataCadastro);
        assertEquals(dataCadastro, usuario.getData_cadastro());
    }

    @Test
    public void testGettersAndSettersDTO() {
        UsuarioDTO usuario = new UsuarioDTO();

        // Testing setters and getters for id_usuario
        usuario.setId_usuario(1);
        assertEquals(1, usuario.getId_usuario());

        // Testing setters and getters for tipo
        usuario.setTipo("PF");
        assertEquals("PF", usuario.getTipo());

        // Testing setters and getters for nome
        usuario.setNome("Nome Teste");
        assertEquals("Nome Teste", usuario.getNome());

        // Testing setters and getters for email
        usuario.setEmail("teste@email.com");
        assertEquals("teste@email.com", usuario.getEmail());

        // Testing setters and getters for saldo
        usuario.setSaldo("R$1000,00");
        assertEquals("R$1000,00", usuario.getSaldo());

        // Testing setters and getters for data_cadastro
        Date dataCadastro = new Date();
        usuario.setData_cadastro(dataCadastro);
        assertEquals(dataCadastro, usuario.getData_cadastro());
    }


    @Test
    public void findByIdTest() {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setId_usuario(1);
        // preencha os outros campos do endereco

        when(enderecosRepository.findById(1)).thenReturn(Optional.of(usuario));

        Optional<UsuarioEntity> foundEndereco = enderecosRepository.findById(1);

        assertEquals(usuario, foundEndereco.get());
    }

    // @Test
    // public void getUsuarioTest() throws Exception {
    // MockMvc mockMvc =
    // MockMvcBuilders.standaloneSetup(enderecosController).build();

    // UsuarioDTO endereco1 = new UsuarioDTO();
    // // preencha os detalhes do endereco1
    // UsuarioDTO endereco2 = new UsuarioDTO();
    // // preencha os detalhes do endereco2

    // List<UsuarioDTO> usuario = Arrays.asList(endereco1, endereco2);

    // given(enderecosService.getUsuario()).willReturn(usuario);

    // mockMvc.perform(get("/usuario")
    // .contentType(MediaType.APPLICATION_JSON))
    // .andExpect(status().isOk())
    // .andExpect(content().json("[{'endereco1 details'}, {'endereco2 details'}]"));
    // }

}
