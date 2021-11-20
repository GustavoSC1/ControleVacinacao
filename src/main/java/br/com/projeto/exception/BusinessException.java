package br.com.projeto.exception;

public class BusinessException extends RuntimeException {
	public BusinessException(String s) {
		super(s);
	}
}