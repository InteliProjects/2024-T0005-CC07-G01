package br.edu.inteli.databus.services;

import java.net.URI;
import java.net.http.*;
import java.util.concurrent.CompletableFuture;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.edu.inteli.databus.entities.*;

public class BusHandler {
    /**
     * Handles a GET request to the API and cache
     * 
     * @param apiUrl   URL of the API
     * @param cacheUrl URL of the cache
     * @return ResponseEntity<String> with the response
     */
    public static ResponseEntity<String> handleBusGetRequest(String apiUrl, String cacheUrl) {
        HttpRequest requestDb = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        HttpRequest requestCache = HttpRequest.newBuilder()
                .uri(URI.create(cacheUrl))
                .build();

        return BusHandler.fetchFirstAsync(requestDb, requestCache);
    }

    /**
     * Handles a POST request to the API and cache
     * 
     * @param apiUrl   URL of the API
     * @param cacheUrl URL of the cache
     * @param body     Request body
     * @return ResponseEntity<String> with the response
     */
    public static ResponseEntity<String> handleBusPostRequest(String apiUrl, String cacheUrl, PessoaFisica body) {
        String jsonBody;
        try {
            jsonBody = new ObjectMapper().writeValueAsString(body);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        HttpRequest requestDb = HttpRequest
                .newBuilder()
                .uri(URI.create(apiUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpRequest requestCache = HttpRequest
                .newBuilder()
                .uri(URI.create(cacheUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        return BusHandler.fetchAllAsync(requestDb, requestCache);
    }

    /**
     * Gets the first response from the API and cache
     * 
     * @param dbRequest    Request to the API
     * @param cacheRequest Request to the cache
     * @return ResponseEntity<String> with the response
     */
    private static ResponseEntity<String> fetchFirstAsync(HttpRequest dbRequest, HttpRequest cacheRequest) {
        HttpClient client = HttpClient.newHttpClient();

        CompletableFuture<HttpResponse<String>> futureResponseCache = client
                .sendAsync(cacheRequest, HttpResponse.BodyHandlers.ofString());
        CompletableFuture<HttpResponse<String>> futureResponseDb = client
                .sendAsync(dbRequest, HttpResponse.BodyHandlers.ofString());

        HttpResponse<?> firstResponse = (HttpResponse<?>) CompletableFuture
                .anyOf(futureResponseCache, futureResponseDb)
                .join();

        if (firstResponse.statusCode() != 200) {
            var secondFuture = firstResponse == (futureResponseDb.join()) ? futureResponseCache : futureResponseDb;
            var secondResponse = (HttpResponse<?>) secondFuture.join();
            if (secondResponse.statusCode() != 200) {
                return ResponseEntity
                        .internalServerError()
                        .body("Unable to reach our servers. Internal error code 001");
            }
            return ResponseEntity.ok((String) secondResponse.body());
        }
        return ResponseEntity.ok((String) firstResponse.body());
    }

    /**
     * Gets all responses from the API and cache
     * 
     * @param dbRequest    Request to the API
     * @param cacheRequest Request to the cache
     * @return ResponseEntity<String> with the response
     */
    private static ResponseEntity<String> fetchAllAsync(HttpRequest dbRequest, HttpRequest cacheRequest) {
        HttpClient client = HttpClient.newHttpClient();

        CompletableFuture<HttpResponse<String>> futureReponseCache = client
                .sendAsync(cacheRequest, HttpResponse.BodyHandlers.ofString());
        CompletableFuture<HttpResponse<String>> futureResponseDb = client
                .sendAsync(dbRequest, HttpResponse.BodyHandlers.ofString());

        CompletableFuture
                .allOf(futureResponseDb, futureReponseCache)
                .join();

        HttpResponse<String> responseCache = futureReponseCache.join();
        HttpResponse<String> responseDb = futureResponseDb.join();

        if (200 == responseDb.statusCode() && 200 == responseCache.statusCode()) {
            return ResponseEntity.ok(responseDb.body());
        } else if (400 == responseDb.statusCode() || 400 == responseCache.statusCode()) {
            return ResponseEntity.badRequest().body("Bad request");
        }
        return ResponseEntity
                .internalServerError()
                .body("Unable to reach our servers. Internal error code 002");

    }
}
