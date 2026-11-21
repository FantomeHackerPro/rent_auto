package fr.ece.rentauto.configs;

import java.time.LocalDateTime;
import java.util.Map;

public class ApiError {
    private int status;
    private String message;
    private Map<String, String> errors;
    private LocalDateTime timestamp = LocalDateTime.now();

    public ApiError(int value, String validationError, Map<String, String> errors) {
    }
}
