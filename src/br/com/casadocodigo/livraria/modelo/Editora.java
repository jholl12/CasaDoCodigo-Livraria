package br.com.casadocodigo.livraria.modelo;

/**
 * Classe responsável pelas caracteriscas e comportamentos de editora de um
 * livro
 * 
 * @author Jhonata Santos
 * @version 1.0
 */
public class Editora {
	// Atributos
	private String nomeFantasia;
	private String razaoSocial;
	private String cnpj;

	// GETTERS
	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	// SETTERS
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
}