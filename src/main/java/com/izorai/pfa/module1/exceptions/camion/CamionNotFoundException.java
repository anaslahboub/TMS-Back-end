package com.izorai.pfa.module1.exceptions.camion;

/**
 * Exception thrown when a Camion (Truck) is not found in the system.
 * This exception provides detailed information about the search criteria
 * that failed to locate the truck.
 */
public class CamionNotFoundException extends RuntimeException {

    // Unique error code for this type of exception
    private static final String ERROR_CODE = "CAMION_NOT_FOUND";

    // Additional details about the not found truck
    private final Long camionId;
    private final String additionalDetails;

    /**
     * Constructor with a simple error message
     * @param message Descriptive error message
     */
    public CamionNotFoundException(String message) {
        super(message);
        this.camionId = null;
        this.additionalDetails = null;
    }

    /**
     * Constructor with camion ID
     * @param camionId ID of the truck that was not found
     */
    public CamionNotFoundException(Long camionId) {
        super("Camion not found with ID: " + camionId);
        this.camionId = camionId;
        this.additionalDetails = null;
    }

    /**
     * Comprehensive constructor
     * @param message Custom error message
     * @param camionId ID of the truck that was not found
     * @param additionalDetails Extra context about the error
     */
    public CamionNotFoundException(String message, Long camionId, String additionalDetails) {
        super(message);
        this.camionId = camionId;
        this.additionalDetails = additionalDetails;
    }

    /**
     * Get the error code for this exception
     * @return Constant error code
     */
    public String getErrorCode() {
        return ERROR_CODE;
    }

    /**
     * Get the ID of the truck that was not found
     * @return Truck ID or null if not applicable
     */
    public Long getCamionId() {
        return camionId;
    }

    /**
     * Get additional details about the error
     * @return Additional error context or null
     */
    public String getAdditionalDetails() {
        return additionalDetails;
    }

    /**
     * Generates a detailed error response
     * @return Detailed error information
     */
    public ErrorResponse toErrorResponse() {
        return new ErrorResponse(
                ERROR_CODE,
                getMessage(),
                camionId != null ? camionId.toString() : null,
                additionalDetails
        );
    }

    /**
     * Inner class to represent a standardized error response
     */
    public static class ErrorResponse {
        private final String errorCode;
        private final String message;
        private final String camionId;
        private final String additionalDetails;

        public ErrorResponse(String errorCode, String message, String camionId, String additionalDetails) {
            this.errorCode = errorCode;
            this.message = message;
            this.camionId = camionId;
            this.additionalDetails = additionalDetails;
        }

        // Getters
        public String getErrorCode() { return errorCode; }
        public String getMessage() { return message; }
        public String getCamionId() { return camionId; }
        public String getAdditionalDetails() { return additionalDetails; }
    }
}
