package it.pirelli.colloquiopieno.exception;

public class DuplicateResourceException extends RuntimeException {

    public DuplicateResourceException(String message) {
        super(message);
    }

    public DuplicateResourceException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s con %s '%s' già esistente", resourceName, fieldName, fieldValue));
    }
}