package com.example.usuario;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateUsuario() throws Exception {
        // Construct the JSON string for creating a new usuario
        String usuarioJson = "{\"id_usuario\":1,\"tipo\":\"Empresa\",\"nome\":\"Empresa ABC\",\"email\":\"empresa@example.com\",\"saldo\":\"1000.00\",\"data_cadastro\":\"2024-03-27\"}";

        mockMvc.perform(post("/usuario")
                .contentType(MediaType.APPLICATION_JSON)
                .content(usuarioJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome", is("Empresa ABC")))
                .andExpect(jsonPath("$.email", is("empresa@example.com")))
                .andExpect(jsonPath("$.saldo", is("1000.00")));
    }

    @Test
    public void testCreateUsuario2() throws Exception {
        // Construct the JSON string for creating a new usuario
        String usuarioJson = "{\"id_usuario\":1,\"tipo\":\"Empresa\",\"nome\":\"Empresa XYZ\",\"email\":\"empresa_xyz@example.com\",\"saldo\":\"2000.00\",\"data_cadastro\":\"2024-03-27\"}";

        mockMvc.perform(post("/usuario")
                .contentType(MediaType.APPLICATION_JSON)
                .content(usuarioJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome", is("Empresa XYZ")))
                .andExpect(jsonPath("$.email", is("empresa_xyz@example.com")))
                .andExpect(jsonPath("$.saldo", is("2000.00")));
    }

    @Test
    public void testCreateUsuario3() throws Exception {
        // Construct the JSON string for creating a new usuario
        String usuarioJson = "{\"id_usuario\":1,\"tipo\":\"Empresa\",\"nome\":\"Empresa 123\",\"email\":\"empresa123@example.com\",\"saldo\":\"3000.00\",\"data_cadastro\":\"2024-03-27\"}";

        mockMvc.perform(post("/usuario")
                .contentType(MediaType.APPLICATION_JSON)
                .content(usuarioJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome", is("Empresa 123")))
                .andExpect(jsonPath("$.email", is("empresa123@example.com")))
                .andExpect(jsonPath("$.saldo", is("3000.00")));
    }

    @Test
    public void testGetUsuarios() throws Exception {
        mockMvc.perform(get("/usuario"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(0))));
    }

    @Test
    public void testGetUsuarioById() throws Exception {
        // Fetch the maximum ID of usuario
        int highestId = getMaxIdFromTable("/usuario");

        mockMvc.perform(get("/usuario/" + highestId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id_usuario", is(highestId)));
    }

    @Test
    public void testUpdateUsuario() throws Exception {
        // Fetch the maximum ID of usuario
        int highestId = getMaxIdFromTable("/usuario");

        // Construct the JSON string with the highest ID included
        String usuarioJson = "{\"id_usuario\":" + highestId + ",\"tipo\":\"Empresa\",\"nome\":\"Empresa Updated\",\"email\":\"empresa_updated@example.com\",\"saldo\":\"4000.00\",\"data_cadastro\":\"2024-03-27\"}";

        // Perform the PUT request using the constructed JSON string
        mockMvc.perform(put("/usuario/" + highestId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(usuarioJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id_usuario", is(highestId)))
                .andExpect(jsonPath("$.nome", is("Empresa Updated")))
                .andExpect(jsonPath("$.email", is("empresa_updated@example.com")))
                .andExpect(jsonPath("$.saldo", is("4000.00")));
    }

    @Test
    public void testDeleteUsuario() throws Exception {
        // Fetch the maximum ID of usuario
        int highestId = getMaxIdFromTable("/usuario");

        // Perform the delete request with the highest ID
        mockMvc.perform(delete("/usuario/" + highestId))
                .andExpect(status().isOk())
                .andExpect(content().string("Usuario deletado com sucesso"));
    }

    // Helper method to get the maximum ID from a given table
    private int getMaxIdFromTable(String tableUrl) throws Exception {
        MvcResult result = mockMvc.perform(get(tableUrl)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        // Extract the response content as a String
        String content = result.getResponse().getContentAsString();

        // Parse the JSON response to extract the ID of the last entry
        JSONArray jsonArray = new JSONArray(content);
        JSONObject lastEntry = jsonArray.getJSONObject(jsonArray.length() - 1);
        return lastEntry.getInt("id_usuario");
    }
}
