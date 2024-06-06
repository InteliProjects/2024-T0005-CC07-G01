package com.example.usuario_pj;

import com.example.usuario_pj.dto.UsuarioPJDTO;
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
public class UsuarioPjApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateUsuarioPJ() throws Exception {
        // Construct the JSON string for creating a new usuario_pj
        String usuarioPJJson = "{\"cnpj\":\"12345678901234\",\"razao_social\":\"ABC Company\",\"senha\":\"password\",\"data_nascimento\":\"1990-01-01\",\"id_usuario\":1}";

        mockMvc.perform(post("/usuario_pj")
                .contentType(MediaType.APPLICATION_JSON)
                .content(usuarioPJJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.cnpj", is("12345678901234")))
                .andExpect(jsonPath("$.razao_social", is("ABC Company")))
                .andExpect(jsonPath("$.senha", is("password")));
    }

    @Test
    public void testCreateUsuarioPJ2() throws Exception {
        // Construct the JSON string for creating a new usuario_pj
        String usuarioPJJson = "{\"cnpj\":\"98765432109876\",\"razao_social\":\"XYZ Corp\",\"senha\":\"password123\",\"data_nascimento\":\"1985-05-05\",\"id_usuario\":1}";

        mockMvc.perform(post("/usuario_pj")
                .contentType(MediaType.APPLICATION_JSON)
                .content(usuarioPJJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.cnpj", is("98765432109876")))
                .andExpect(jsonPath("$.razao_social", is("XYZ Corp")))
                .andExpect(jsonPath("$.senha", is("password123")));
    }

    @Test
    public void testCreateUsuarioPJ3() throws Exception {
        // Construct the JSON string for creating a new usuario_pj
        String usuarioPJJson = "{\"cnpj\":\"11122233344455\",\"razao_social\":\"123 Inc\",\"senha\":\"test123\",\"data_nascimento\":\"1988-08-08\",\"id_usuario\":1}";

        mockMvc.perform(post("/usuario_pj")
                .contentType(MediaType.APPLICATION_JSON)
                .content(usuarioPJJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.cnpj", is("11122233344455")))
                .andExpect(jsonPath("$.razao_social", is("123 Inc")))
                .andExpect(jsonPath("$.senha", is("test123")));
    }

    @Test
    public void testGetUsuariosPJ() throws Exception {
        mockMvc.perform(get("/usuario_pj"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(0))));
    }

    @Test
    public void testGetUsuarioPJByCnpj() throws Exception {
        // Fetch the first CNPJ from the database
        String firstCnpj = getFirstCnpj();

        mockMvc.perform(get("/usuario_pj/" + firstCnpj))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.cnpj", is(firstCnpj)));
    }

    @Test
    public void testUpdateUsuarioPJ() throws Exception {
        // Fetch the first CNPJ from the database
        String firstCnpj = getFirstCnpj();

        // Construct the JSON string with the first CNPJ included
        String usuarioPJJson = "{\"cnpj\":\"" + firstCnpj + "\",\"razao_social\":\"ABC Company Updated\",\"senha\":\"password\",\"data_nascimento\":\"1990-01-01\",\"id_usuario\":1}";

        // Perform the PUT request using the constructed JSON string
        mockMvc.perform(put("/usuario_pj/" + firstCnpj)
                .contentType(MediaType.APPLICATION_JSON)
                .content(usuarioPJJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.cnpj", is(firstCnpj)))
                .andExpect(jsonPath("$.razao_social", is("ABC Company Updated")));
    }

    @Test
    public void testDeleteUsuarioPJ() throws Exception {
        // Fetch the first CNPJ from the database
        String firstCnpj = getFirstCnpj();

        // Perform the delete request with the first CNPJ
        mockMvc.perform(delete("/usuario_pj/" + firstCnpj))
                .andExpect(status().isOk())
                .andExpect(content().string("Usuario deletado com sucesso"));
    }

    // Helper method to fetch the first CNPJ from the database
    private String getFirstCnpj() throws Exception {
        MvcResult result = mockMvc.perform(get("/usuario_pj")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        // Extract the response content as a String
        String content = result.getResponse().getContentAsString();

        // Parse the JSON response to extract the first CNPJ
        JSONArray jsonArray = new JSONArray(content);
        JSONObject firstEntry = jsonArray.getJSONObject(0);
        return firstEntry.getString("cnpj");
    }
}
