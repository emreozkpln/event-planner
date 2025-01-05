package dev.buddly.event_planner.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseHandler {

    public static ResponseEntity<Object> handle(
            String message,
            HttpStatus status,
            Object responseObject
    ){

        Map<String,Object> response = new HashMap<>();

        response.put("data",responseObject);
        response.put("message",message);
        response.put("status",status);

        return new ResponseEntity<>(response,status);
    }

    public static ResponseEntity<Map<String, Object>> handleExceptionJson(
            HttpStatus status,
            ExceptionResponse exceptionResponse,
            List<String> includedFields
    ){

        Map<String,Object> response = new HashMap<>();

        response.put("status",status);

        if (includedFields.contains("businessErrorCode") && exceptionResponse.getBusinessErrorCode() != null) {
            response.put("businessErrorCode", exceptionResponse.getBusinessErrorCode());
        }
        if (includedFields.contains("businessErrorDescription") && exceptionResponse.getBusinessErrorDescription() != null) {
            response.put("businessErrorDescription", exceptionResponse.getBusinessErrorDescription());
        }
        if (includedFields.contains("error") && exceptionResponse.getError() != null) {
            response.put("error", exceptionResponse.getError());
        }
        if (includedFields.contains("validationError") && exceptionResponse.getValidationError() != null) {
            response.put("validationError", exceptionResponse.getValidationError());
        }
        if (includedFields.contains("errors") && exceptionResponse.getError() != null) {
            response.put("errors", exceptionResponse.getError());
        }


        return ResponseEntity.status(status).body(response);
    }
}

