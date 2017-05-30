package br.com.casadocodigo.livraria.modelo.produtos;

public interface Produto extends Comparable<Produto> {
	public abstract double getValor();
	public abstract String getNome();
	public abstract String getDescricao();
	public abstract String getIsbn();
}
