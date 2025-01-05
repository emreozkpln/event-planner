package dev.buddly.event_planner.handler;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExceptionResponse {
    private String businessErrorCode;
    private String businessErrorDescription;
    private String error;
    private Set<String> validationError;

    // Private constructor to prevent direct instantiation
    private ExceptionResponse(Builder builder) {
        this.businessErrorCode = builder.businessErrorCode;
        this.businessErrorDescription = builder.businessErrorDescription;
        this.error = builder.error;
        this.validationError = builder.validationError;
    }

    // Getters
    public String getBusinessErrorCode() {
        return businessErrorCode;
    }

    public String getBusinessErrorDescription() {
        return businessErrorDescription;
    }

    public String getError() {
        return error;
    }

    public Set<String> getValidationError() {
        return validationError;
    }

    // Builder class
    public static class Builder {
        private String businessErrorCode;
        private String businessErrorDescription;
        private String error;
        private Set<String> validationError;

        // Builder methods
        public Builder businessErrorCode(String businessErrorCode) {
            this.businessErrorCode = businessErrorCode;
            return this;
        }

        public Builder businessErrorDescription(String businessErrorDescription) {
            this.businessErrorDescription = businessErrorDescription;
            return this;
        }

        public Builder error(String error) {
            this.error = error;
            return this;
        }

        public Builder validationError(Set<String> validationError) {
            this.validationError = validationError;
            return this;
        }

        // Build method to create the final object
        public ExceptionResponse build() {
            return new ExceptionResponse(this);
        }
    }
}

