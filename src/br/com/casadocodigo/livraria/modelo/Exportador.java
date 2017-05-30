package br.com.casadocodigo.livraria.modelo;

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
	 */
	public void exportarCSV(List<Produto> produtos){
		try {
			PrintStream ps = new PrintStream("produtos.csv");
			ps.println("Nome; Descrição; Valor; ISBN");
			
			for(Produto produto : produtos){
				ps.println(String.format("%s; %s; %s; %s",
						   produto.getNome(),
						   produto.getDescricao(),
						   produto.getValor(),
						   produto.getIsbn()));
			}
			
			ps.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
//	public static void main(String[] args){
//		
//		Livro livro = new LivroFisico(new Autor());
//		livro.setNome("Java 8 Prático");
//		livro.setDescricao("Novos recursos da linguagem");
//		livro.setValor(59.90);
//		livro.setIsbn("978-85-66250-46-6");
//		
//		Livro maisUmLivro = new LivroFisico(new Autor());
//		maisUmLivro.setNome("Desbravando a O.O");
//		maisUmLivro.setDescricao("Livro de Java e O.O");
//		maisUmLivro.setValor(59.90);
//		maisUmLivro.setIsbn("321-54-67890-11-2");
//		
//		new Exportador().exportarCSV(Arrays.asList(livro, maisUmLivro));
//	}
}
