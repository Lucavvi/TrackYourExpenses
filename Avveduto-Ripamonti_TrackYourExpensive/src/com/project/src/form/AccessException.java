package com.project.src.form;

/**
 * Custom exception class for handling access-related errors.
 * This exception is thrown when there is an issue with access credentials or permissions.
 *
 * @author Angelo Ripamonti & Luca Avveduto
 * @version 1.0
 */
public class AccessException extends RuntimeException{

    /**
     * Constructs a new AccessException with the specified detail message.
     *
     * @param msg the detail message, which provides more information about the cause of the exception
     */
    public AccessException(String msg) {
        super(msg);
    }
}