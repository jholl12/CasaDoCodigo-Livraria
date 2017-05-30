package br.com.casadocodigo.livraria.modelo;

/**
 * Classe responsável pelas caracteriscas e comportamentos de um Autor de um
 * livro
 * 
 * @author Jhonata Santos
 * @version 1.0
 */
public class Autor {
	// Atributos
	private String nome;
	private String email;
	private String cpf;

	// Construtor
	public Autor() {
		// VAZIO
	}

	// GETTERS

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getCpf() {
		return cpf;
	}

	// SETTERS

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	// CUSTOM

	/**
	 * Mostra os detalhes completo do autor no console
	 * 
	 * @author Jhonata Santos
	 */
	public void mostrarDetalhes() {
		System.out.println("Mostrando detalhes do autor ");
		System.out.println("Nome: " + getNome());
		System.out.println("Email: " + getEmail());
		System.out.println("CPF: " + getCpf());
	}

	/**
	 * Verifica se o objeto recebido é da instância de Autor e compara através
	 * do nome
	 * 
	 * @author Jhonata Santos
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Autor))
			return false;
		Autor outro = (Autor) obj;
		return this.nome.equals(outro.nome);
	}
}
