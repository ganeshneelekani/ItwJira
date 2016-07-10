package com.agileidc.itw.helper;

public class DefaultValidException extends Exception {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = -5664009916589411540L;
	private String message = null;
 
    public DefaultValidException() {
        super();
    }
 
    public DefaultValidException(String message) {
        super(message);
        this.message = message;
    }
 
    public DefaultValidException(Throwable cause) {
        super(cause);
    }
 
    @Override
    public String toString() {
        return message;
    }
 
    @Override
    public String getMessage() {
        return message;
    }
}