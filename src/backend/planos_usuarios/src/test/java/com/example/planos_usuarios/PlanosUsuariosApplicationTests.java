package com.example.planos_usuarios;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.json.JSONArray;
import org.json.JSONObject;

@SpringBootTest
@AutoConfigureMockMvc
public class PlanosUsuariosApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreatePlanoUsuario() throws Exception {
        String planoUsuarioJson = "{\"contrato\":1,\"id_plano\":1,\"qtd_internet_consumido\":\"10GB\",\"qtd_internet_restante\":\"20GB\",\"data_inicio\":\"2024-03-27\",\"data_final\":\"2024-04-27\",\"fatura\":50.0,\"telefone\":\"123456789\",\"status\":\"Ativo\",\"id_endereco\":42,\"id_usuario\":1}";
        mockMvc.perform(post("/planos_usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(planoUsuarioJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.contrato", is(1)))
                .andExpect(jsonPath("$.id_plano", is(1)))
                .andExpect(jsonPath("$.qtd_internet_consumido", is("10GB")))
                .andExpect(jsonPath("$.qtd_internet_restante", is("20GB")))
                .andExpect(jsonPath("$.fatura", is(50.0)))
                .andExpect(jsonPath("$.telefone", is("123456789")))
                .andExpect(jsonPath("$.status", is("Ativo")))
                .andExpect(jsonPath("$.id_endereco", is(42)))
                .andExpect(jsonPath("$.id_usuario", is(1)));
    }

    @Test
    public void testGetPlanoUsuarios() throws Exception {
        mockMvc.perform(get("/planos_usuarios"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(0))));
    }

    @Test
    public void testGetPlanoUsuarioById() throws Exception {
        mockMvc.perform(get("/planos_usuarios/2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.contrato", is(2)));
    }

    @Test
    public void testUpdatePlanoUsuario() throws Exception {
        String planoUsuarioJson = "{\"contrato\":2,\"id_plano\":1,\"qtd_internet_consumido\":\"5GB\",\"qtd_internet_restante\":\"25GB\",\"data_inicio\":\"2024-03-27\",\"data_final\":\"2024-04-27\",\"fatura\":60.0,\"telefone\":\"987654321\",\"status\":\"Inativo\",\"id_endereco\":42,\"id_usuario\":1}";
        
        mockMvc.perform(put("/planos_usuarios/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(planoUsuarioJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.contrato", is(2)))
                .andExpect(jsonPath("$.qtd_internet_consumido", is("5GB")))
                .andExpect(jsonPath("$.qtd_internet_restante", is("25GB")))
                .andExpect(jsonPath("$.data_inicio", is(1711497600000L)))
                .andExpect(jsonPath("$.data_final", is(1714176000000L)))
                .andExpect(jsonPath("$.fatura", is(60.0)))
                .andExpect(jsonPath("$.telefone", is("987654321")))
                .andExpect(jsonPath("$.status", is("Inativo")))
                .andExpect(jsonPath("$.id_endereco", is(42)))
                .andExpect(jsonPath("$.id_usuario", is(1)));
    }

    @Test
    public void testDeletePlanoUsuario() throws Exception {
        // First, perform a request to get the highest ID of addresses
        MvcResult result = mockMvc.perform(get("/planos_usuarios")
                                .contentType(MediaType.APPLICATION_JSON))
                                .andReturn();
        
        // Extract the response content as a String
        String content = result.getResponse().getContentAsString();
        
        // Parse the JSON response to extract the ID of the last address
        JSONArray jsonArray = new JSONArray(content);
        JSONObject lastAddress = jsonArray.getJSONObject(jsonArray.length() - 1);
        int highestId = lastAddress.getInt("contrato");

        // Perform the delete request with the highest ID
        mockMvc.perform(delete("/planos_usuarios/" + highestId))
                .andExpect(status().isOk())
                .andExpect(content().string("Plano deletado com sucesso"));
    }
}
