/**
 * This package contains the main application class for the Enderecos application.
 */
package com.example.enderecos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is the main application class for the Enderecos application.
 */
@SpringBootApplication
public class EnderecosApplication {

    /**
     * The main method that starts the Spring Boot application.
     * @param args The command line arguments
     */
	public static void main(String[] args) {
		SpringApplication.run(EnderecosApplication.class, args);
	}

    /**
     * The port number on which the server is running.
     */
	@Value("${server.port}")
	private int port;

}
