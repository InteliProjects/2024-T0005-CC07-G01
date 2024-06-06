package com.example.usuario_pf;

import com.example.usuario_pf.dto.UsuarioPFDTO;
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
public class UsuarioPfApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateUsuarioPF() throws Exception {
        // Construct the JSON string for creating a new usuario_pf
        String usuarioPFJson = "{\"cpf\":\"12345678901\",\"nome\":\"John Doe\",\"rg\":\"1234567\",\"senha\":\"password\",\"data_nascimento\":\"1990-01-01\",\"id_usuario\":1}";

        mockMvc.perform(post("/usuario_pf")
                .contentType(MediaType.APPLICATION_JSON)
                .content(usuarioPFJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.cpf", is("12345678901")))
                .andExpect(jsonPath("$.nome", is("John Doe")))
                .andExpect(jsonPath("$.rg", is("1234567")))
                .andExpect(jsonPath("$.senha", is("password")));
    }

    @Test
    public void testCreateUsuarioPF2() throws Exception {
        // Construct the JSON string for creating a new usuario_pf
        String usuarioPFJson = "{\"cpf\":\"98765432109\",\"nome\":\"Jane Doe\",\"rg\":\"7654321\",\"senha\":\"password123\",\"data_nascimento\":\"1985-05-05\",\"id_usuario\":1}";

        mockMvc.perform(post("/usuario_pf")
                .contentType(MediaType.APPLICATION_JSON)
                .content(usuarioPFJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.cpf", is("98765432109")))
                .andExpect(jsonPath("$.nome", is("Jane Doe")))
                .andExpect(jsonPath("$.rg", is("7654321")))
                .andExpect(jsonPath("$.senha", is("password123")));
    }

    @Test
    public void testCreateUsuarioPF3() throws Exception {
        // Construct the JSON string for creating a new usuario_pf
        String usuarioPFJson = "{\"cpf\":\"11122233344\",\"nome\":\"Alice Smith\",\"rg\":\"9876543\",\"senha\":\"test123\",\"data_nascimento\":\"1988-08-08\",\"id_usuario\":1}";

        mockMvc.perform(post("/usuario_pf")
                .contentType(MediaType.APPLICATION_JSON)
                .content(usuarioPFJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.cpf", is("11122233344")))
                .andExpect(jsonPath("$.nome", is("Alice Smith")))
                .andExpect(jsonPath("$.rg", is("9876543")))
                .andExpect(jsonPath("$.senha", is("test123")));
    }

    @Test
    public void testGetUsuariosPF() throws Exception {
        mockMvc.perform(get("/usuario_pf"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(0))));
    }

    @Test
    public void testGetUsuarioPFByCpf() throws Exception {
        // Fetch the first CPF from the database
        String firstCPF = getFirstCPF();

        mockMvc.perform(get("/usuario_pf/" + firstCPF))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.cpf", is(firstCPF)));
    }

    @Test
    public void testUpdateUsuarioPF() throws Exception {
        // Fetch the first CPF from the database
        String firstCPF = getFirstCPF();

        // Construct the JSON string with the first CPF included
        String usuarioPFJson = "{\"cpf\":\"" + firstCPF + "\",\"nome\":\"John Doe Updated\",\"rg\":\"1234567\",\"senha\":\"password\",\"data_nascimento\":\"1990-01-01\",\"id_usuario\":1}";

        // Perform the PUT request using the constructed JSON string
        mockMvc.perform(put("/usuario_pf/" + firstCPF)
                .contentType(MediaType.APPLICATION_JSON)
                .content(usuarioPFJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.cpf", is(firstCPF)))
                .andExpect(jsonPath("$.nome", is("John Doe Updated")));
    }

    @Test
    public void testDeleteUsuarioPF() throws Exception {
        // Fetch the first CPF from the database
        String firstCPF = getFirstCPF();

        // Perform the delete request with the first CPF
        mockMvc.perform(delete("/usuario_pf/" + firstCPF))
                .andExpect(status().isOk())
                .andExpect(content().string("Usuario deletado com sucesso"));
    }

    // Helper method to fetch the first CPF from the database
    private String getFirstCPF() throws Exception {
        MvcResult result = mockMvc.perform(get("/usuario_pf")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        // Extract the response content as a String
        String content = result.getResponse().getContentAsString();

        // Parse the JSON response to extract the first CPF
        JSONArray jsonArray = new JSONArray(content);
        JSONObject firstEntry = jsonArray.getJSONObject(0);
        return firstEntry.getString("cpf");
    }
}
