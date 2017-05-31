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

public class RepositorioDeProdutos {

	public ObservableList<Produto> lista() {
		ObservableList<Produto> produtos = FXCollections.observableArrayList();
		Connection conn = new ConnectionFactory().getConnetion();
		String query = "SELECT * FROM livraria.produtos";
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(query);
			ResultSet result = ps.executeQuery();
			
			while(result.next()){
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
}
