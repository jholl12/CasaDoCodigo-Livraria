package br.com.casadocodigo.livraria.modelo;

import java.util.ArrayList;
import java.util.List;
import br.com.casadocodigo.livraria.modelo.produtos.Produto;

/**
 * Classe responsável pelas caracteriscas e comportamentos para gerenciar um
 * carrinho de compras
 * 
 * @author Jhonata Santos
 * @version 1.0
 */
public class CarrinhoDeCompras {
	//Atributos
	private double total;
	private List<Produto> produtos;

	//Construtor
	public CarrinhoDeCompras() {
		this.produtos = new ArrayList<Produto>();
	}
	
	// GETTERS
	public double getTotal() {
		return total;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}
	
	/**
	 * Adiciona um novo produto na lista de carrinho de compras
	 * 
	 * @param produto
	 * @author Jhonata Santos
	 */
	public void adiciona(Produto produto) {
		this.produtos.add(produto);
	}

	/**
	 * Remove um produto da lista de carrinho de compras
	 * 
	 * @param posicao
	 * @author Jhonata Santos
	 */
	public void remove(int posicao) {
		this.produtos.remove(posicao);
	}
}
