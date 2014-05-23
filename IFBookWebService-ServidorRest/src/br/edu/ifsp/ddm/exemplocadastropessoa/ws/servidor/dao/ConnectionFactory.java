package br.edu.ifsp.ddm.exemplocadastropessoa.ws.servidor.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class ConnectionFactory {

	// database URL
	private static final String DATABASE_URL = "jdbc:mysql://localhost/exemplopessoaws";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";

	public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		} catch (Exception e) {
			System.out.println("Erro ao criar conexao.");
			e.printStackTrace();
		}
		return con;
	}

	public void closeConnection(Connection conn, Statement stmt, ResultSet rs) {
		try {
			close(conn, stmt, rs);
		} catch (Exception e) {
			System.out.println("Erro ao fechar conexao.");
			e.printStackTrace();
		}
	}

	public void closeConnection(Connection conn, Statement stmt) {
		try {
			close(conn, stmt, null);
		} catch (Exception e) {
			System.out.println("Erro ao fechar conexao.");
			e.printStackTrace();
		}
	}

	public void closeConnection(Connection conn) {
		try {
			close(conn, null, null);
		} catch (Exception e) {
			System.out.println("Erro ao fechar conexao.");
			e.printStackTrace();
		}
	}

	private void close(Connection conn, Statement stmt, ResultSet rs) {

		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			System.out.println("Erro ao fechar conexao.");
			e.printStackTrace();
		}
	}
}