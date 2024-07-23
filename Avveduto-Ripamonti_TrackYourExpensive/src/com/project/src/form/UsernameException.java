package com.project.src.form;

/**
 * Exception thrown when a username-related error occurs.
 * This custom exception is used to indicate issues such as
 * an already existing username or other username validation errors.
 *
 * @author Angelo Ripamonti & Luca Avveduto
 * @version 1.0
 */
public class UsernameException extends RuntimeException{

    /**
     * Constructs a new UsernameException with the specified detail message.
     *
     * @param msg the detail message, which provides more information about the cause of the exception
     */
    public UsernameException(String msg) {
        super(msg);
    }
}