package br.com.casadocodigo.livraria.modelo.produtos;

public interface Promocional {

	boolean aplicaDescontoDe(double porcentagem);
	
	default boolean aplicaDescontoDe10Porcento() {
		return aplicaDescontoDe(0.1);
	}
}