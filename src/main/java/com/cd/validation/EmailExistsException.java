package com.cd.validation;

public class EmailExistsException extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6455596089025064818L;

    public EmailExistsException() {
        super();
    }

    
    public EmailExistsException(String message) {
        super(message);
    }

    public EmailExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailExistsException(Throwable cause) {
        super(cause);
    }

}
