package br.com.casadocodigo.livraria.io;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;
import br.com.casadocodigo.livraria.modelo.produtos.Produto;

public class Exportador {

	/**
	 * Realiza a exportação de todos os produtos para um arquivo CSV
	 * 
	 * @param produtos
	 * @author Jhonata Santos
	 * @throws FileNotFoundException 
	 */
	public void exportarCSV(List<Produto> produtos) throws FileNotFoundException {
		PrintStream ps = new PrintStream("produtos.csv");
		ps.println("Nome; Descrição; Valor; ISBN");

		for (Produto produto : produtos) {
			ps.println(String.format("%s; %s; %s; %s", 
					produto.getNome(), 
					produto.getDescricao(), 
					produto.getValor(),
					produto.getIsbn()));
		}

		ps.close();
	}
}
