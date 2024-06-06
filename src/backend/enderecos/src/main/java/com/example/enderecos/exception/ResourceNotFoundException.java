/**
 * This package contains the custom exceptions for the Enderecos application.
 */
package com.example.enderecos.exception;

/**
 * This class represents a custom exception that is thrown when a resource is not found.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructor for the ResourceNotFoundException.
     * @param message The message to be passed to the exception
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
