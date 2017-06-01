package br.com.casadocodigo.livraria.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.casadocodigo.livraria.ConnectionFactory;
import br.com.casadocodigo.livraria.modelo.produtos.LivroFisico;
import br.com.casadocodigo.livraria.modelo.produtos.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Classo responsável por fazer a comunição das ações com o banco de dados
 * 
 * @author Jhonata Santos
 * @version 1.0
 */
public class RepositorioDeProdutos {

	/**
	 * Lista todos os produtos existentes no banco de dados referente a tabela
	 * de produtos
	 * 
	 * @return todos os produtos do banco
	 * @author Jhonata Santos
	 */
	public ObservableList<Produto> lista() {
		ObservableList<Produto> produtos = FXCollections.observableArrayList();
		Connection conn = new ConnectionFactory().getConnetion();
		String query = "SELECT * FROM livraria.produtos";
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement(query);
			ResultSet result = ps.executeQuery();

			while (result.next()) {
				LivroFisico livro = new LivroFisico(new Autor());
				livro.setNome(result.getString("nome"));
				livro.setDescricao(result.getString("descricao"));
				livro.setValor(result.getDouble("valor"));
				livro.setIsbn(result.getString("isbn"));
				produtos.add(livro);
			}
			result.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return produtos;
	}

	/**
	 * Realiza a inserção do produto no banco de dados
	 * 
	 * @param produto
	 * @author Jhonata Santos
	 */
	public void adiciona(Produto produto) {
		String query = "INSERT INTO produtos (nome, descricao, valor, isbn) " + "VALUES (?, ?, ?, ?)";

		try (Connection conn = new ConnectionFactory().getConnetion()) {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, produto.getNome());
			ps.setString(2, produto.getDescricao());
			ps.setDouble(3, produto.getValor());
			ps.setString(4, produto.getIsbn());

			ps.execute();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * MAIN PARA TESTAR OS MÈTODOS IMPLEMENTADOS
	 * @param args
	 */
	public static void main(String[] args) {
		RepositorioDeProdutos rp = new RepositorioDeProdutos();
		LivroFisico produto = new LivroFisico(new Autor());
		produto.setNome("Teste01");
		produto.setDescricao("Teste01");
		produto.setValor(00.00);
		produto.setIsbn("Teste01");
		;

		rp.adiciona(produto);
	}
}
