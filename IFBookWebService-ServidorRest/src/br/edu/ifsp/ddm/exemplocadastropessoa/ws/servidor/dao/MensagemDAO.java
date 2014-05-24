package br.edu.ifsp.ddm.exemplocadastropessoa.ws.servidor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.ddm.exemplocadastropessoa.ws.servidor.modelo.Mensagem;


public class MensagemDAO extends ConnectionFactory {
	
	public String inserir(Mensagem m) {

		Connection conn = null;
		conn = getConnection();
		int sucesso = 0;

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement("INSERT INTO "
					+ "MENSAGEM (IDMENSAGEM,DATA,TITULO,DESCRICAO,IMAGEM,AREAINTERESSE,USUARIO) VALUES(?,?,?,?,?,?,?)");

			
			
			stmt.setInt(1, m.getIdade());
			stmt.setString(2, m.getNome());
			stmt.setString(3, m.getSexo());
			sucesso = stmt.executeUpdate();

			if (sucesso > 0) {
				return ("PESSOA INSERIDA!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return ("ERRO AO INSERIR PESSOA!");
		} finally {
			closeConnection(conn, stmt);
		}
		return ("ERRO AO INSERIR PESSOA!");

	}

	public String inserir(List<Pessoa> pessoas) {

		Connection conn = null;
		conn = getConnection();
		int sucesso = 0;

		PreparedStatement stmt = null;

		if (pessoas != null) {
			for (Pessoa p : pessoas) {
				try {
					stmt = conn.prepareStatement("INSERT INTO "
							+ "PESSOA (IDADE,NOME,SEXO) VALUES(?,?,?)");

					stmt.setInt(1, p.getIdade());
					stmt.setString(2, p.getNome());
					stmt.setString(3, p.getSexo());
					sucesso = stmt.executeUpdate();

				} catch (SQLException e) {
					e.printStackTrace();
					// return ("ERRO AO INSERIR PESSOA!");
				} 

			}

		}
		
		closeConnection(conn, stmt);
		
		if (sucesso > 0) {
			return ("PESSOAS INSERIDAS!");
		} else {
			return ("ERRO AO INSERIR AS PESSOAS!");
		}

	}

	public String alterar(Pessoa p) {

		Connection conn = null;
		conn = getConnection();
		PreparedStatement stmt = null;
		int sucesso = 0;
		try {
			stmt = conn
					.prepareStatement("UPDATE PESSOA SET IDADE = ?, NOME = ?, SEXO = ? WHERE ID = ?");

			stmt.setInt(1, p.getIdade());
			stmt.setString(2, p.getNome());
			stmt.setString(3, p.getSexo());
			stmt.setInt(4, p.getId());
			sucesso = stmt.executeUpdate();

			if (sucesso > 0) {
				return ("PESSOA ALTERADO!");
			} else {
				return ("PESSOA NÃO EXISTE!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return ("ERRO AO ALTERAR PESSOA!");
		} finally {
			closeConnection(conn, stmt);
		}

	}

	public String deletar(int id) {

		Connection conn = null;
		conn = getConnection();
		int excluidos = 0;
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement("DELETE FROM PESSOA WHERE ID = ?");
			stmt.setInt(1, id);
			excluidos = stmt.executeUpdate();

			if (excluidos > 0) {
				return ("PESSOA REMOVIDA!");
			} else {
				return ("PESSOA NÃO EXISTE!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return ("ERRO AO DELETAR PESSOA!");
		} finally {
			closeConnection(conn, stmt);
		}

	}

	public Pessoa buscar(int id) {

		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement stmt = null;
		conn = getConnection();
		Pessoa p = null;
		try {
			stmt = conn.prepareStatement("SELECT * FROM PESSOA WHERE ID = ?");
			stmt.setInt(1, id);
			resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				p = new Pessoa();
				p.setId(resultSet.getInt("id"));
				p.setIdade(resultSet.getInt("idade"));
				p.setNome(resultSet.getString("nome"));
				p.setSexo(resultSet.getString("sexo"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn, stmt, resultSet);
		}
		return p;
	}

	public ArrayList<Pessoa> buscarTodos() {

		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement stmt = null;
		conn = getConnection();
		ArrayList<Pessoa> listaPessoas = null;

		try {

			stmt = conn
					.prepareStatement("SELECT * FROM PESSOA ORDER BY NOME ASC");
			resultSet = stmt.executeQuery();
			listaPessoas = new ArrayList<Pessoa>();

			while (resultSet.next()) {
				Pessoa p = new Pessoa();
				p.setId(resultSet.getInt("id"));
				p.setIdade(resultSet.getInt("idade"));
				p.setNome(resultSet.getString("nome"));
				p.setSexo(resultSet.getString("sexo"));

				listaPessoas.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			listaPessoas = null;
		} finally {
			closeConnection(conn, stmt, resultSet);
		}
		return listaPessoas;
	}
}
