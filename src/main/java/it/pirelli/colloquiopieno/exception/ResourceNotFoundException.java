package it.pirelli.colloquiopieno.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String resourceName, Long resourceId) {
        super(String.format("%s con ID %d non trovato", resourceName, resourceId));
    }
}