package com.compasso.desafio.productcatalog.exceptions;

public class EntityException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EntityException(String message){
		super(message);
	}

}
