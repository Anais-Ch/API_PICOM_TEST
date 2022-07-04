package com.hb.PICOM_hibernate.initialisation.exception;

public class InvalidCountryCodeException extends NumberFormatException {

    static final long serialVersionUID = 1L;

    public InvalidCountryCodeException(String errorMessage) {
        super(errorMessage);
    }
}
