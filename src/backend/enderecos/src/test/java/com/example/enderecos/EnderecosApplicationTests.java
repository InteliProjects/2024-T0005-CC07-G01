package com.example.enderecos;

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
public class EnderecosApplicationTests {
    

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateEndereco() throws Exception {
        String enderecoJson = "{\"id_endereco\":2,\"id_usuario\":1,\"rua\":\"Rua ABCD\",\"cep\":\"12345-678\",\"numero\":123,\"bairro\":\"Centro\",\"cidade\":\"Cidade X\",\"estado\":\"Estado Y\"}";
        mockMvc.perform(post("/enderecos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(enderecoJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.rua", is("Rua ABCD")))
                .andExpect(jsonPath("$.cep", is("12345-678")))
                .andExpect(jsonPath("$.numero", is(123)))
                .andExpect(jsonPath("$.bairro", is("Centro")))
                .andExpect(jsonPath("$.cidade", is("Cidade X")))
                .andExpect(jsonPath("$.estado", is("Estado Y")));
    }
    @Test
    public void testCreateEndereco2() throws Exception {
        String enderecoJson = "{\"id_endereco\":2,\"id_usuario\":1,\"rua\":\"Rua ABCD\",\"cep\":\"12345-678\",\"numero\":456,\"bairro\":\"Centro\",\"cidade\":\"Cidade X\",\"estado\":\"Estado Y\"}";
        mockMvc.perform(post("/enderecos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(enderecoJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.rua", is("Rua ABCD")))
                .andExpect(jsonPath("$.cep", is("12345-678")))
                .andExpect(jsonPath("$.numero", is(456)))
                .andExpect(jsonPath("$.bairro", is("Centro")))
                .andExpect(jsonPath("$.cidade", is("Cidade X")))
                .andExpect(jsonPath("$.estado", is("Estado Y")));
    }
    @Test
    public void testCreateEndereco3() throws Exception {
        String enderecoJson = "{\"id_endereco\":2,\"id_usuario\":1,\"rua\":\"Rua ABCD\",\"cep\":\"12345-678\",\"numero\":789,\"bairro\":\"Centro\",\"cidade\":\"Cidade X\",\"estado\":\"Estado Y\"}";
        mockMvc.perform(post("/enderecos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(enderecoJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.rua", is("Rua ABCD")))
                .andExpect(jsonPath("$.cep", is("12345-678")))
                .andExpect(jsonPath("$.numero", is(789)))
                .andExpect(jsonPath("$.bairro", is("Centro")))
                .andExpect(jsonPath("$.cidade", is("Cidade X")))
                .andExpect(jsonPath("$.estado", is("Estado Y")));
    }
  

    @Test
    public void testGetEnderecos() throws Exception {
        mockMvc.perform(get("/enderecos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(0))));
    }

    @Test
    public void testGetEnderecoById() throws Exception {
        MvcResult result = mockMvc.perform(get("/enderecos")
                                .contentType(MediaType.APPLICATION_JSON))
                                .andReturn();
        
        // Extract the response content as a String
        String content = result.getResponse().getContentAsString();
        
        // Parse the JSON response to extract the ID of the last address
        JSONArray jsonArray = new JSONArray(content);
        JSONObject lastAddress = jsonArray.getJSONObject(jsonArray.length() - 1);
        int highestId = lastAddress.getInt("id_endereco");
        System.out.println("Highest ID: " + highestId);

        mockMvc.perform(get("/enderecos/"+highestId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id_endereco", is(highestId)));
    }

    @Test
    public void testUpdateEndereco() throws Exception {
        MvcResult result = mockMvc.perform(get("/enderecos")
                                .contentType(MediaType.APPLICATION_JSON))
                                .andReturn();
        
        // Extract the response content as a String
        String content = result.getResponse().getContentAsString();
        
        // Parse the JSON response to extract the ID of the last address
        JSONArray jsonArray = new JSONArray(content);
        JSONObject lastAddress = jsonArray.getJSONObject(jsonArray.length() - 1);
        int highestId = lastAddress.getInt("id_endereco");

         // Construct the JSON string with the highest ID included
        String enderecoJson = "{\"id_endereco\":" + highestId + ",\"id_usuario\":1,\"rua\":\"Rua XYZ\",\"cep\":\"87654-321\",\"numero\":456,\"bairro\":\"Periferia\",\"cidade\":\"Cidade Z\",\"estado\":\"Estado W\"}";

        // Perform the PUT request using the constructed JSON string
        mockMvc.perform(put("/enderecos/" + highestId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(enderecoJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id_endereco", is(highestId)))
                .andExpect(jsonPath("$.rua", is("Rua XYZ")))
                .andExpect(jsonPath("$.cep", is("87654-321")))
                .andExpect(jsonPath("$.numero", is(456)))
                .andExpect(jsonPath("$.bairro", is("Periferia")))
                .andExpect(jsonPath("$.cidade", is("Cidade Z")))
                .andExpect(jsonPath("$.estado", is("Estado W")));
    }

    @Test
    public void testDeleteEndereco() throws Exception {
        // First, perform a request to get the highest ID of addresses
        MvcResult result = mockMvc.perform(get("/enderecos")
                                .contentType(MediaType.APPLICATION_JSON))
                                .andReturn();
        
        // Extract the response content as a String
        String content = result.getResponse().getContentAsString();
        
        // Parse the JSON response to extract the ID of the last address
        JSONArray jsonArray = new JSONArray(content);
        JSONObject lastAddress = jsonArray.getJSONObject(jsonArray.length() - 1);
        int highestId = lastAddress.getInt("id_endereco");

        // Perform the delete request with the highest ID
        mockMvc.perform(delete("/enderecos/" + highestId))
                .andExpect(status().isOk())
                .andExpect(content().string("Endereço deletado com sucesso"));
    }
}
