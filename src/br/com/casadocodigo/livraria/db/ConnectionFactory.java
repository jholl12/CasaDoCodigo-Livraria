package br.com.casadocodigo.livraria.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsável por fazer as devidas conexões de acesso com o bando de dados
 * 
 * @author Jhonata Santos
 * @version 1.0
 */
public class ConnectionFactory {

	/**
	 * Realiza abertura de conexão com o banco de dados
	 * 
	 * @return Retorna uma conexão aberta
	 * @author Jhonata Santos
	 */
	public Connection getConnetion(){
		String url = "jdbc:mysql://localhost/livraria?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
		try {
			return DriverManager.getConnection(url, "root", "admin");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
