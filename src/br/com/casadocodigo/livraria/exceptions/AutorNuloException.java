package br.com.casadocodigo.livraria.exceptions;

/**
 * Exception criada para tratar todos os autores nulos
 * 
 * @author Jhonata Santos
 * @version 1.0
 */
public class AutorNuloException extends RuntimeException {

	public AutorNuloException(String mensagem) {
		super(mensagem);
	}

}
