package br.com.casadocodigo.livraria.modelo.produtos;

import br.com.casadocodigo.livraria.exceptions.AutorNuloException;
import br.com.casadocodigo.livraria.modelo.Autor;

/**
 * Classe responsável pelas caracteriscas e comportamentos de um Livro no qual
 * um autor escreve
 * 
 * @author Jhonata Santos
 * @version 1.0
 */
public abstract class Livro implements Produto {
	// Atributos
	private String nome;
	private String descricao;
	private double valor;
	private String isbn;
	private Autor autor;

	// Construtor
	public Livro(Autor autor) {
		if (autor == null) {
			throw new AutorNuloException("O Autor do Livro não pode ser nulo");
		}
		this.autor = autor;
		this.isbn = "000-00-00000-00-0";
	}

	// GETTERS

	public double getValor() {
		return valor;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getIsbn() {
		return isbn;
	}

	public Autor getAutor() {
		return autor;
	}

	// SETTERS

	public void setValor(double valor) {
		this.valor = valor;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	// CUSTOM

	private boolean temAutor() {
		return this.autor != null;
	}

	/**
	 * Mostra os detalhes completo do autor no console
	 * 
	 * @author Jhonata Santos
	 */
	public void mostrarDetalhes() {
		System.out.println("Mostrando detalhes do livro ");
		System.out.println("Nome: " + nome);
		System.out.println("DescriÃ§Ã£o: " + descricao);
		System.out.println("Valor: " + valor);
		System.out.println("ISBN: " + isbn);

		if (this.temAutor()) {
			autor.mostrarDetalhes();
		}
		System.out.println("--");
	}

	/**
	 * Critério de igualdade deste método foi definido através do valor de um
	 * produto
	 * 
	 * @author Jhonata Santos
	 */
	@Override
	public int compareTo(Produto outro) {

		if (this.getValor() < outro.getValor()) {
			return -1;
		}
		if (this.getValor() > outro.getValor()) {
			return 1;
		}
		return 0;
	}
}