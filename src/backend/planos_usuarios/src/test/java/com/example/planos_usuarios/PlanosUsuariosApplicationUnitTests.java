package com.example.planos_usuarios;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.planos_usuarios.dto.PlanosUsuariosDTO;
import com.example.planos_usuarios.model.entity.PlanosUsuariosEntity;
import com.example.planos_usuarios.repository.PlanosUsuariosRepository;
import com.example.planos_usuarios.service.PlanosUsuariosService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.planos_usuarios.controller.PlanosUsuariosController;
import com.example.planos_usuarios.dto.PlanosUsuariosDTO;
import com.example.planos_usuarios.service.PlanosUsuariosService;
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

public class PlanosUsuariosApplicationUnitTests {

    @Mock
    private PlanosUsuariosRepository enderecosRepository;

    @InjectMocks
    private PlanosUsuariosService enderecosService;

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private PlanosUsuariosController enderecosController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getPlanosUsuariossService() {
        // Configura o mock para retornar uma lista de entidades
        List<PlanosUsuariosEntity> mockEntities = new ArrayList<>(); // Initialize the mockEntities variable
        when(enderecosRepository.findAll()).thenReturn(mockEntities); // Use the correct syntax for stubbing

        // Chama o método getPlanosUsuarios e verifica os resultados
        List<PlanosUsuariosDTO> result = enderecosService.getPlanosUsuarios();
        // Adicione asserções para verificar o resultado
    }

    @Test
    public void getPlanosUsuariosbyIdService() {
        // Configura o mock para retornar uma entidade
        PlanosUsuariosEntity mockEntity = new PlanosUsuariosEntity(); // Initialize the mockEntity variable
        when(enderecosRepository.findById(1)).thenReturn(java.util.Optional.of(mockEntity)); // Use the correct syntax
                                                                                             // for stubbing

        // Chama o método getEnderecoById e verifica os resultados
        PlanosUsuariosDTO result = enderecosService.getPlanoUsuariobyId(1);
    }

    @Test
    public void createPlanosUsuariosService() {
        // Configura o mock para retornar uma entidade
        PlanosUsuariosEntity mockEntity = new PlanosUsuariosEntity(); // Initialize the mockEntity variable
        when(enderecosRepository.save(mockEntity)).thenReturn(mockEntity); // Use the correct syntax for stubbing

        // Chama o método createEndereco e verifica os resultados
        PlanosUsuariosDTO result = enderecosService.createPlanoUsuario(new PlanosUsuariosDTO());
    }

    @Test
    public void updatePlanosUsuariosService() {
        // Configura o mock para retornar uma entidade
        PlanosUsuariosEntity mockEntity = new PlanosUsuariosEntity(); // Initialize the mockEntity variable
        when(enderecosRepository.findById(1)).thenReturn(java.util.Optional.of(mockEntity)); // Use the correct syntax
                                                                                             // for stubbing

        // Chama o método updateEndereco e verifica os resultados
        PlanosUsuariosDTO result = enderecosService.updatePlanoUsuario(new PlanosUsuariosDTO(), 1);
    }

    @Test
    public void deletePlanosUsuariosService() {
        // Configura o mock para retornar uma entidade
        PlanosUsuariosEntity mockEntity = new PlanosUsuariosEntity(); // Initialize the mockEntity variable
        when(enderecosRepository.findById(1)).thenReturn(java.util.Optional.of(mockEntity)); // Use the correct syntax
                                                                                             // for stubbing

        // Chama o método deleteEndereco e verifica os resultados
        enderecosService.deletePlanoUsuario(1);
    }

    @Test
    public void testGettersAndSettersEntity() {
        PlanosUsuariosEntity planosUsuarios = new PlanosUsuariosEntity();

        // Testing setters and getters for contrato
        planosUsuarios.setContrato(1);
        assertEquals(1, planosUsuarios.getContrato());

        // Testing setters and getters for id_plano
        planosUsuarios.setId_plano(2);
        assertEquals(2, planosUsuarios.getId_plano());

        // Testing setters and getters for qtd_internet_consumido
        planosUsuarios.setQtd_internet_consumido("10GB");
        assertEquals("10GB", planosUsuarios.getQtd_internet_consumido());

        // Testing setters and getters for qtd_internet_restante
        planosUsuarios.setQtd_internet_restante("20GB");
        assertEquals("20GB", planosUsuarios.getQtd_internet_restante());

        // Testing setters and getters for data_inicio
        Date dataInicio = new Date();
        planosUsuarios.setData_inicio(dataInicio);
        assertEquals(dataInicio, planosUsuarios.getData_inicio());

        // Testing setters and getters for data_final
        Date dataFinal = new Date();
        planosUsuarios.setData_final(dataFinal);
        assertEquals(dataFinal, planosUsuarios.getData_final());

        // Testing setters and getters for fatura
        planosUsuarios.setFatura(123.45f);
        assertEquals(123.45f, planosUsuarios.getFatura());

        // Testing setters and getters for telefone
        planosUsuarios.setTelefone("1234567890");
        assertEquals("1234567890", planosUsuarios.getTelefone());

        // Testing setters and getters for status
        planosUsuarios.setStatus("Ativo");
        assertEquals("Ativo", planosUsuarios.getStatus());

        // Testing setters and getters for id_endereco
        planosUsuarios.setId_endereco(3);
        assertEquals(3, planosUsuarios.getId_endereco());

        // Testing setters and getters for id_usuario
        planosUsuarios.setId_usuario(4);
        assertEquals(4, planosUsuarios.getId_usuario());
    }
    
    @Test
    public void testGettersAndSettersDTO() {
        PlanosUsuariosDTO planosUsuarios = new PlanosUsuariosDTO();

        // Testing setters and getters for contrato
        planosUsuarios.setContrato(1);
        assertEquals(1, planosUsuarios.getContrato());

        // Testing setters and getters for id_plano
        planosUsuarios.setId_plano(2);
        assertEquals(2, planosUsuarios.getId_plano());

        // Testing setters and getters for qtd_internet_consumido
        planosUsuarios.setQtd_internet_consumido("10GB");
        assertEquals("10GB", planosUsuarios.getQtd_internet_consumido());

        // Testing setters and getters for qtd_internet_restante
        planosUsuarios.setQtd_internet_restante("20GB");
        assertEquals("20GB", planosUsuarios.getQtd_internet_restante());

        // Testing setters and getters for data_inicio
        Date dataInicio = new Date();
        planosUsuarios.setData_inicio(dataInicio);
        assertEquals(dataInicio, planosUsuarios.getData_inicio());

        // Testing setters and getters for data_final
        Date dataFinal = new Date();
        planosUsuarios.setData_final(dataFinal);
        assertEquals(dataFinal, planosUsuarios.getData_final());

        // Testing setters and getters for fatura
        planosUsuarios.setFatura(123.45f);
        assertEquals(123.45f, planosUsuarios.getFatura());

        // Testing setters and getters for telefone
        planosUsuarios.setTelefone("1234567890");
        assertEquals("1234567890", planosUsuarios.getTelefone());

        // Testing setters and getters for status
        planosUsuarios.setStatus("Ativo");
        assertEquals("Ativo", planosUsuarios.getStatus());

        // Testing setters and getters for id_endereco
        planosUsuarios.setId_endereco(3);
        assertEquals(3, planosUsuarios.getId_endereco());

        // Testing setters and getters for id_usuario
        planosUsuarios.setId_usuario(4);
        assertEquals(4, planosUsuarios.getId_usuario());
    }
    


    @Test
    public void findByIdTest() {
        PlanosUsuariosEntity planos_usuarios = new PlanosUsuariosEntity();
        planos_usuarios.setId_plano(1);
        // preencha os outros campos do endereco

        when(enderecosRepository.findById(1)).thenReturn(Optional.of(planos_usuarios));

        Optional<PlanosUsuariosEntity> foundEndereco = enderecosRepository.findById(1);

        assertEquals(planos_usuarios, foundEndereco.get());
    }

    // @Test
    // public void getPlanosUsuariosTest() throws Exception {
    // MockMvc mockMvc =
    // MockMvcBuilders.standaloneSetup(enderecosController).build();

    // PlanosUsuariosDTO endereco1 = new PlanosUsuariosDTO();
    // // preencha os detalhes do endereco1
    // PlanosUsuariosDTO endereco2 = new PlanosUsuariosDTO();
    // // preencha os detalhes do endereco2

    // List<PlanosUsuariosDTO> planos_usuarios = Arrays.asList(endereco1, endereco2);

    // given(enderecosService.getPlanosUsuarios()).willReturn(planos_usuarios);

    // mockMvc.perform(get("/planos_usuarios")
    // .contentType(MediaType.APPLICATION_JSON))
    // .andExpect(status().isOk())
    // .andExpect(content().json("[{'endereco1 details'}, {'endereco2 details'}]"));
    // }

}
