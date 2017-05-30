package br.com.casadocodigo.livraria.modelo.produtos;

import br.com.casadocodigo.livraria.modelo.Autor;

public class LivroFisico extends Livro implements Promocional {

	// Construtor
	public LivroFisico(Autor autor) {
		super(autor);
	}

	// CUSTOM

	/**
	 * Obt�m a taxa de impress�o e realiza o calculo da taxa
	 * 
	 * @return Retorna o valor da taxa de impress�o
	 * @author Jhonata Santos
	 */
	public double getTaxaImpressao() {
		return this.getValor() * 0.05;
	}

	/**
	 * Aplica o desconto no valor com base na porcentagem, sendo que um livro
	 * fisico pode-se receber at� 30%
	 * 
	 * @author Jhonata Santos
	 */
	public boolean aplicaDescontoDe(double porcentagem) {
		if (porcentagem > 0.3) {
			return false;
		}

		double desconto = getValor() * porcentagem;
		setValor(getValor() - desconto);
		return true;
	}
}
