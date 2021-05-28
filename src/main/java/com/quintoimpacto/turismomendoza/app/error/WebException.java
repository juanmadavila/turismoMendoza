package com.quintoimpacto.turismomendoza.app.error;

public class WebException extends Exception {

	private static final long serialVersionUID = 123456L;

	public WebException(String msn) {
        super(msn);
    }

}