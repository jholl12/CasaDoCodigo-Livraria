package br.com.casadocodigo.livraria.io;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;
import br.com.casadocodigo.livraria.modelo.produtos.Produto;

/**
 * Classe responsável por fazer todas as ações de exportar
 * 
 * @author Jhonata Santos
 * @version 1.0
 */
public class Exportador {

	/**
	 * Realiza a exportação de todos os produtos listados para um arquivo CSV
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
