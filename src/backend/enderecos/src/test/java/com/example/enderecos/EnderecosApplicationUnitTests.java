package com.example.enderecos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.enderecos.dto.EnderecosDTO;
import com.example.enderecos.model.entity.EnderecosEntity;
import com.example.enderecos.repository.EnderecosRepository;
import com.example.enderecos.service.EnderecosService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.enderecos.controller.EnderecosController;
import com.example.enderecos.dto.EnderecosDTO;
import com.example.enderecos.service.EnderecosService;
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

public class EnderecosApplicationUnitTests {

    @Mock
    private EnderecosRepository enderecosRepository;

    @InjectMocks
    private EnderecosService enderecosService;

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private EnderecosController enderecosController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getEnderecosService() {
        // Configura o mock para retornar uma lista de entidades
        List<EnderecosEntity> mockEntities = new ArrayList<>(); // Initialize the mockEntities variable
        when(enderecosRepository.findAll()).thenReturn(mockEntities); // Use the correct syntax for stubbing
        // Chama o método getEnderecos e verifica os resultados
        List<EnderecosDTO> result = enderecosService.getEnderecos();
        // Adicione asserções para verificar o resultado
    }

    @Test
    public void getEnderecoByIdService() {
        // Configura o mock para retornar uma entidade
        EnderecosEntity mockEntity = new EnderecosEntity();
        when(enderecosRepository.findById(1)).thenReturn(java.util.Optional.of(mockEntity));
        // Chama o método getEnderecoById e verifica os resultados
        EnderecosDTO result = enderecosService.getEnderecosbyId(1);
    }

    
    @Test
    public void createEnderecoPJService() {
        // Configura o mock para retornar uma entidade
        EnderecosEntity mockEntity = new EnderecosEntity(); // Initialize the mockEntity variable
        when(enderecosRepository.save(mockEntity)).thenReturn(mockEntity); // Use the correct syntax for stubbing

        // Chama o método createEndereco e verifica os resultados
        EnderecosDTO result = enderecosService.createEnderecoPJ(new EnderecosDTO());
    }

    @Test
    public void updateEnderecoPJService() {
        // Configura o mock para retornar uma entidade
        EnderecosEntity mockEntity = new EnderecosEntity(); // Initialize the mockEntity variable
        when(enderecosRepository.findById(1)).thenReturn(java.util.Optional.of(mockEntity)); // Use the correct syntax
                                                                                             // for stubbing

        // Chama o método updateEndereco e verifica os resultados
        EnderecosDTO result = enderecosService.updateEnderecoPJ(new EnderecosDTO(), 1);
    }

    @Test
    public void deleteEnderecoPJService() {
        // Configura o mock para retornar uma entidade
        EnderecosEntity mockEntity = new EnderecosEntity(); // Initialize the mockEntity variable
        when(enderecosRepository.findById(1)).thenReturn(java.util.Optional.of(mockEntity)); // Use the correct syntax
                                                                                             // for stubbing

        // Chama o método deleteEndereco e verifica os resultados
        enderecosService.deleteEnderecoPJ(1);
    }

    @Test
    public void testGettersAndSettersEntity() {
        EnderecosEntity enderecos = new EnderecosEntity();

        enderecos.setId_endereco(1);
        assertEquals(1, enderecos.getId_endereco());

        enderecos.setId_usuario(2);
        assertEquals(2, enderecos.getId_usuario());

        enderecos.setRua("Rua Teste");
        assertEquals("Rua Teste", enderecos.getRua());

        enderecos.setCep("12345-678");
        assertEquals("12345-678", enderecos.getCep());

        enderecos.setNumero(123);
        assertEquals(123, enderecos.getNumero());

        enderecos.setBairro("Bairro Teste");
        assertEquals("Bairro Teste", enderecos.getBairro());

        enderecos.setCidade("Cidade Teste");
        assertEquals("Cidade Teste", enderecos.getCidade());

        enderecos.setEstado("Estado Teste");
        assertEquals("Estado Teste", enderecos.getEstado());
    }

    @Test
    public void testGettersAndSett() {
        EnderecosDTO enderecos = new EnderecosDTO();

        enderecos.setId_endereco(1);
        assertEquals(1, enderecos.getId_endereco());

        enderecos.setId_usuario(2);
        assertEquals(2, enderecos.getId_usuario());

        enderecos.setRua("Rua Teste");
        assertEquals("Rua Teste", enderecos.getRua());

        enderecos.setCep("12345-678");
        assertEquals("12345-678", enderecos.getCep());

        enderecos.setNumero(123);
        assertEquals(123, enderecos.getNumero());

        enderecos.setBairro("Bairro Teste");
        assertEquals("Bairro Teste", enderecos.getBairro());

        enderecos.setCidade("Cidade Teste");
        assertEquals("Cidade Teste", enderecos.getCidade());

        enderecos.setEstado("Estado Teste");
        assertEquals("Estado Teste", enderecos.getEstado());
    }


    @Test
    public void findByIdTest() {
        EnderecosEntity endereco = new EnderecosEntity();
        endereco.setId_endereco(1);
        // preencha os outros campos do endereco

        when(enderecosRepository.findById(1)).thenReturn(Optional.of(endereco));

        Optional<EnderecosEntity> foundEndereco = enderecosRepository.findById(1);

        assertEquals(endereco, foundEndereco.get());
    }

    // @Test
    // public void getEnderecosTest() throws Exception {
    // MockMvc mockMvc =
    // MockMvcBuilders.standaloneSetup(enderecosController).build();

    // EnderecosDTO endereco1 = new EnderecosDTO();
    // // preencha os detalhes do endereco1
    // EnderecosDTO endereco2 = new EnderecosDTO();
    // // preencha os detalhes do endereco2

    // List<EnderecosDTO> enderecos = Arrays.asList(endereco1, endereco2);

    // given(enderecosService.getEnderecos()).willReturn(enderecos);

    // mockMvc.perform(get("/enderecos")
    // .contentType(MediaType.APPLICATION_JSON))
    // .andExpect(status().isOk())
    // .andExpect(content().json("[{'endereco1 details'}, {'endereco2 details'}]"));
    // }

}
