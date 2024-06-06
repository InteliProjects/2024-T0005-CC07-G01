package com.example.plano;

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
public class PlanoApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreatePlano() throws Exception {
        String planoJson = "{\"id_plano\":1,\"nome\":\"Plano A\",\"valor\":50.0,\"qtd_internet\":\"10GB\"}";
        mockMvc.perform(post("/plano")
                .contentType(MediaType.APPLICATION_JSON)
                .content(planoJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome", is("Plano A")))
                .andExpect(jsonPath("$.valor", is(50.0)))
                .andExpect(jsonPath("$.qtd_internet", is("10GB")));
    }

    @Test
    public void testCreatePlano2() throws Exception {
        String planoJson = "{\"id_plano\":2,\"nome\":\"Plano B\",\"valor\":60.0,\"qtd_internet\":\"20GB\"}";
        mockMvc.perform(post("/plano")
                .contentType(MediaType.APPLICATION_JSON)
                .content(planoJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome", is("Plano B")))
                .andExpect(jsonPath("$.valor", is(60.0)))
                .andExpect(jsonPath("$.qtd_internet", is("20GB")));
    }

    @Test
    public void testCreatePlano3() throws Exception {
        String planoJson = "{\"id_plano\":3,\"nome\":\"Plano C\",\"valor\":70.0,\"qtd_internet\":\"30GB\"}";
        mockMvc.perform(post("/plano")
                .contentType(MediaType.APPLICATION_JSON)
                .content(planoJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome", is("Plano C")))
                .andExpect(jsonPath("$.valor", is(70.0)))
                .andExpect(jsonPath("$.qtd_internet", is("30GB")));
    }

    @Test
    public void testGetPlanos() throws Exception {
        mockMvc.perform(get("/plano"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(0))));
    }

    @Test
    public void testGetPlanoById() throws Exception {
        // First, perform a request to get the highest ID of plans
        MvcResult result = mockMvc.perform(get("/plano")
                                .contentType(MediaType.APPLICATION_JSON))
                                .andReturn();
        
        // Extract the response content as a String
        String content = result.getResponse().getContentAsString();
        
        // Parse the JSON response to extract the ID of the last plan
        JSONArray jsonArray = new JSONArray(content);
        JSONObject lastPlan = jsonArray.getJSONObject(jsonArray.length() - 1);
        int highestId = lastPlan.getInt("id_plano");

        mockMvc.perform(get("/plano/" + highestId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id_plano", is(highestId)));
    }

    @Test
    public void testUpdatePlano() throws Exception {
        // First, perform a request to get the highest ID of plans
        MvcResult result = mockMvc.perform(get("/plano")
                                .contentType(MediaType.APPLICATION_JSON))
                                .andReturn();
        
        // Extract the response content as a String
        String content = result.getResponse().getContentAsString();
        
        // Parse the JSON response to extract the ID of the last plan
        JSONArray jsonArray = new JSONArray(content);
        JSONObject lastPlan = jsonArray.getJSONObject(jsonArray.length() - 1);
        int highestId = lastPlan.getInt("id_plano");

        // Construct the JSON string with the highest ID included
        String planoJson = "{\"id_plano\":" + highestId + ",\"nome\":\"Plano D\",\"valor\":80.0,\"qtd_internet\":\"40GB\"}";

        mockMvc.perform(put("/plano/" + highestId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(planoJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id_plano", is(highestId)))
                .andExpect(jsonPath("$.nome", is("Plano D")))
                .andExpect(jsonPath("$.valor", is(80.0)))
                .andExpect(jsonPath("$.qtd_internet", is("40GB")));
    }

    @Test
    public void testDeletePlano() throws Exception {
        // First, perform a request to get the highest ID of plans
        MvcResult result = mockMvc.perform(get("/plano")
                                .contentType(MediaType.APPLICATION_JSON))
                                .andReturn();
        
        // Extract the response content as a String
        String content = result.getResponse().getContentAsString();
        
        // Parse the JSON response to extract the ID of the last plan
        JSONArray jsonArray = new JSONArray(content);
        JSONObject lastPlan = jsonArray.getJSONObject(jsonArray.length() - 1);
        int highestId = lastPlan.getInt("id_plano");

        // Perform the delete request with the highest ID
        mockMvc.perform(delete("/plano/" + highestId))
                .andExpect(status().isOk())
                .andExpect(content().string("Plano deletado com sucesso"));
    }
}
