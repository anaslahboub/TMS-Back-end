package com.izorai.pfa.module1.exceptions.partenaire.chaufeur;


/**
 * Exception thrown when a Chaufeur (Driver) is not found in the system.
 * This exception provides detailed information about the search criteria
 * that failed to locate the driver.
 */
public class ChaufeurNotFoundException extends RuntimeException {

    // Unique error code for this type of exception
    private static final String ERROR_CODE = "DRIVER_NOT_FOUND";

    // Additional details about the not found driver
    private final Long driverId;
    private final String additionalDetails;

    /**
     * Constructor with a simple error message
     * @param message Descriptive error message
     */
    public ChaufeurNotFoundException(String message) {
        super(message);
        this.driverId = null;
        this.additionalDetails = null;
    }

    /**
     * Constructor with driver ID
     * @param driverId ID of the driver that was not found
     */
    public ChaufeurNotFoundException(Long driverId) {
        super("Chaufeur not found with ID: " + driverId);
        this.driverId = driverId;
        this.additionalDetails = null;
    }

    /**
     * Comprehensive constructor
     * @param message Custom error message
     * @param driverId ID of the driver that was not found
     * @param additionalDetails Extra context about the error
     */
    public ChaufeurNotFoundException(String message, Long driverId, String additionalDetails) {
        super(message);
        this.driverId = driverId;
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
     * Get the ID of the driver that was not found
     * @return Driver ID or null if not applicable
     */
    public Long getDriverId() {
        return driverId;
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
                driverId != null ? driverId.toString() : null,
                additionalDetails
        );
    }

    /**
     * Inner class to represent a standardized error response
     */
    public static class ErrorResponse {
        private final String errorCode;
        private final String message;
        private final String driverId;
        private final String additionalDetails;

        public ErrorResponse(String errorCode, String message, String driverId, String additionalDetails) {
            this.errorCode = errorCode;
            this.message = message;
            this.driverId = driverId;
            this.additionalDetails = additionalDetails;
        }

        // Getters
        public String getErrorCode() { return errorCode; }
        public String getMessage() { return message; }
        public String getDriverId() { return driverId; }
        public String getAdditionalDetails() { return additionalDetails; }
    }
}