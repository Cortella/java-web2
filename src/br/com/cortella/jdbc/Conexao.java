package br.com.cortella.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	public static Connection getConnection() {
		Connection con = null;
		String url = "jdbc:postgresql://localhost:5432/java-web1DataBase";
		String user = "postgres";
		String password = "postgres";
		try {
		    Class.forName("org.postgresql.Driver");
		    con = DriverManager.getConnection(url, user, password);
		    System.out.print("Conexao bem sucedida");
		} catch (ClassNotFoundException e) {
			System.out.println("Primeiro catch");
		    // Erro caso o driver JDBC não foi instalado
		    e.printStackTrace();
		} catch (SQLException e) {
		    // Erro caso haja problemas para se conectar ao banco de dados
		    e.printStackTrace();
		}
		return con;
	}
}
