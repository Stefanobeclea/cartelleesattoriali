package it.prova.cartelleesattoriali.web.api.exception;

public class CartellaEsattorialeNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public CartellaEsattorialeNotFoundException(String message) {
		super(message);
	}
}
