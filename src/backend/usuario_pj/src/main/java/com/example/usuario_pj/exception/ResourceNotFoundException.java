package com.example.usuario_pj.exception;

/**
 * Custom exception class that represents a scenario where a resource is not found.
 * This exception is typically thrown in cases where a lookup for a specific entity
 * by its identifier yields no result.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructs a new ResourceNotFoundException with the specified detail message.
     *
     * @param message the detail message. The detail message is saved for 
     *                later retrieval by the {@link Throwable#getMessage()} method.
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
