package com.example.plano;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.plano.dto.PlanoDTO;
import com.example.plano.model.entity.PlanoEntity;
import com.example.plano.repository.PlanoRepository;
import com.example.plano.service.PlanoService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.plano.controller.PlanoController;
import com.example.plano.dto.PlanoDTO;
import com.example.plano.service.PlanoService;
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

public class PlanoApplicationUnitTests {

    @Mock
    private PlanoRepository enderecosRepository;

    @InjectMocks
    private PlanoService enderecosService;

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private PlanoController enderecosController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getPlanosService() {
        // Configura o mock para retornar uma lista de entidades
        List<PlanoEntity> mockEntities = new ArrayList<>(); // Initialize the mockEntities variable
        when(enderecosRepository.findAll()).thenReturn(mockEntities); // Use the correct syntax for stubbing

        // Chama o método getPlano e verifica os resultados
        List<PlanoDTO> result = enderecosService.getPlanos();
        // Adicione asserções para verificar o resultado
    }

    @Test
    public void getPlanobyIdService() {
        // Configura o mock para retornar uma entidade
        PlanoEntity mockEntity = new PlanoEntity(); // Initialize the mockEntity variable
        when(enderecosRepository.findById(1)).thenReturn(java.util.Optional.of(mockEntity)); // Use the correct syntax
                                                                                             // for stubbing

        // Chama o método getEnderecoById e verifica os resultados
        PlanoDTO result = enderecosService.getPlanobyId(1);
    }

    @Test
    public void createPlanoService() {
        // Configura o mock para retornar uma entidade
        PlanoEntity mockEntity = new PlanoEntity(); // Initialize the mockEntity variable
        when(enderecosRepository.save(mockEntity)).thenReturn(mockEntity); // Use the correct syntax for stubbing

        // Chama o método createEndereco e verifica os resultados
        PlanoDTO result = enderecosService.createPlano(new PlanoDTO());
    }

    @Test
    public void updatePlanoService() {
        // Configura o mock para retornar uma entidade
        PlanoEntity mockEntity = new PlanoEntity(); // Initialize the mockEntity variable
        when(enderecosRepository.findById(1)).thenReturn(java.util.Optional.of(mockEntity)); // Use the correct syntax
                                                                                             // for stubbing

        // Chama o método updateEndereco e verifica os resultados
        PlanoDTO result = enderecosService.updatePlano(new PlanoDTO(), 1);
    }

    @Test
    public void deletePlanoService() {
        // Configura o mock para retornar uma entidade
        PlanoEntity mockEntity = new PlanoEntity(); // Initialize the mockEntity variable
        when(enderecosRepository.findById(1)).thenReturn(java.util.Optional.of(mockEntity)); // Use the correct syntax
                                                                                             // for stubbing

        // Chama o método deleteEndereco e verifica os resultados
        enderecosService.deletePlano(1);
    }

    @Test
    public void testGettersAndSettersEntity() {
        PlanoEntity plano = new PlanoEntity();

        plano.setId_plano(1);
        assertEquals(1, plano.getId_plano());

        plano.setNome("Plano Teste");
        assertEquals("Plano Teste", plano.getNome());

        plano.setValor(123.45f);
        assertEquals(123.45f, plano.getValor());

        plano.setQtd_internet("10GB");
        assertEquals("10GB", plano.getQtd_internet());
    }
    

    @Test
    public void testGettersAndSettDTO() {
        PlanoDTO plano = new PlanoDTO();

        plano.setId_plano(1);
        assertEquals(1, plano.getId_plano());

        plano.setNome("Plano Teste");
        assertEquals("Plano Teste", plano.getNome());

        plano.setValor(123.45f);
        assertEquals(123.45f, plano.getValor());

        plano.setQtd_internet("10GB");
        assertEquals("10GB", plano.getQtd_internet());
    }


    @Test
    public void findByIdTest() {
        PlanoEntity plano = new PlanoEntity();
        plano.setId_plano(1);
        // preencha os outros campos do endereco

        when(enderecosRepository.findById(1)).thenReturn(Optional.of(plano));

        Optional<PlanoEntity> foundEndereco = enderecosRepository.findById(1);

        assertEquals(plano, foundEndereco.get());
    }

    // @Test
    // public void getPlanoTest() throws Exception {
    // MockMvc mockMvc =
    // MockMvcBuilders.standaloneSetup(enderecosController).build();

    // PlanoDTO endereco1 = new PlanoDTO();
    // // preencha os detalhes do endereco1
    // PlanoDTO endereco2 = new PlanoDTO();
    // // preencha os detalhes do endereco2

    // List<PlanoDTO> plano = Arrays.asList(endereco1, endereco2);

    // given(enderecosService.getPlano()).willReturn(plano);

    // mockMvc.perform(get("/plano")
    // .contentType(MediaType.APPLICATION_JSON))
    // .andExpect(status().isOk())
    // .andExpect(content().json("[{'endereco1 details'}, {'endereco2 details'}]"));
    // }

}
