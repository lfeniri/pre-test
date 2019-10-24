package com.priceminister.account;


public class TechnicalException extends Exception {

    private static final long serialVersionUID = -9204191749972551939L;

	private String message;

    public TechnicalException(String message){
        this.message = message;
    }
    
    public String toString() {
        return "Technical exception: " + message;
    }
}
