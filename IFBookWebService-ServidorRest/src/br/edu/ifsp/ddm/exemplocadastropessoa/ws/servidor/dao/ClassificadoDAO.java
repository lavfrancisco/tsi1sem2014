package br.edu.ifsp.ddm.exemplocadastropessoa.ws.servidor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.ddm.exemplocadastropessoa.ws.servidor.modelo.Classificado;
import br.edu.ifsp.ddm.exemplocadastropessoa.ws.servidor.modelo.AreaInteresse;
import br.edu.ifsp.ddm.exemplocadastropessoa.ws.servidor.modelo.Usuario;;


public class ClassificadoDAO extends ConnectionFactory {
	
	AreaInteresseDAO areaDao;
	
	public String inserir(Classificado m) {

		Connection conn = null;
		conn = getConnection();
		int sucesso = 0;

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement("INSERT INTO "
					+ "CLASSIFICADO (IDCLASSIFICADO,DATA,TITULO,DESCRICAO,IMAGEM,AREAINTERESSE,USUARIO) VALUES(?,?,?,?,?,?,?)");

			
			
			stmt.setInt(1, m.getIdClassificado());
			stmt.setString(2, m.getData());
			stmt.setString(3, m.getTitulo());
			stmt.setString(4, m.getDescricao());
			//stmt.setString(5, m.getImagem());
			stmt.setInt(6, m.getAreaInteresse().getIdAreaInteresse());
			stmt.setInt(7, m.getUsuario().getIdUsuario());
			
			sucesso = stmt.executeUpdate();

			if (sucesso > 0) {
				return ("CLASSIFICADO INSERIDA!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return ("ERRO AO INSERIR CLASSIFICADO!");
		} finally {
			closeConnection(conn, stmt);
		}
		return ("ERRO AO INSERIR CLASSIFICADO!");

	}

	public String inserir(List<Classificado> classificados) {

		Connection conn = null;
		conn = getConnection();
		int sucesso = 0;

		PreparedStatement stmt = null;

		if (classificados != null) {
			for (Classificado m : classificados) {
				try {
					stmt = conn.prepareStatement("INSERT INTO "
							+ "CLASSIFICADO (IDCLASSIFICADO,DATA,TITULO,DESCRICAO,IMAGEM,AREAINTERESSE,USUARIO) VALUES(?,?,?,?,?,?,?)");

					stmt.setInt(1, m.getIdClassificado());
					stmt.setString(2, m.getData());
					stmt.setString(3, m.getTitulo());
					stmt.setString(4, m.getDescricao());
					//stmt.setString(5, m.getImagem());
					stmt.setInt(6, m.getAreaInteresse().getIdAreaInteresse());
					stmt.setInt(7, m.getUsuario().getIdUsuario());
					sucesso = stmt.executeUpdate();

				} catch (SQLException e) {
					e.printStackTrace();
					// return ("ERRO AO INSERIR PESSOA!");
				} 

			}

		}
		
		closeConnection(conn, stmt);
		
		if (sucesso > 0) {
			return ("CLASSIFICADO INSERIDAS!");
		} else {
			return ("ERRO AO INSERIR AS CLASSIFICADO!");
		}

	}

	public String alterar(Classificado m) {

		Connection conn = null;
		conn = getConnection();
		PreparedStatement stmt = null;
		int sucesso = 0;
		try {
			stmt = conn
					.prepareStatement("UPDATE CLASSIFICADO SET IDCLASSIFICADO = ?,DATA = ?,TITULO = ?,DESCRICAO = ?,IMAGEM = ?,AREAINTERESSE = ? WHERE ID = ?");

			stmt.setInt(1, m.getIdClassificado());
			stmt.setString(2, m.getData());
			stmt.setString(3, m.getTitulo());
			stmt.setString(4, m.getDescricao());
			//stmt.setString(5, m.getImagem());
			stmt.setInt(6, m.getAreaInteresse().getIdAreaInteresse());
			stmt.setInt(7, m.getUsuario().getIdUsuario());
			sucesso = stmt.executeUpdate();

			if (sucesso > 0) {
				return ("CLASSIFICADO ALTERADA!");
			} else {
				return ("CLASSIFICADO NÃO EXISTE!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return ("ERRO AO ALTERAR CLASSIFICADO!");
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
			stmt = conn.prepareStatement("DELETE FROM CLASSIFICADO WHERE ID = ?");
			stmt.setInt(1, id);
			excluidos = stmt.executeUpdate();

			if (excluidos > 0) {
				return ("CLASSIFICADO REMOVIDA!");
			} else {
				return ("CLASSIFICADO NÃO EXISTE!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return ("ERRO AO DELETAR CLASSIFICADO!");
		} finally {
			closeConnection(conn, stmt);
		}

	}

	public Classificado buscar(int id) {

		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement stmt = null;
		conn = getConnection();
		Classificado m = null;
		try {
			stmt = conn.prepareStatement("SELECT * FROM CLASSIFICADO WHERE ID = ?");
			stmt.setInt(1, id);
			resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				m = new Classificado();
				m.setIdClassificado(resultSet.getInt("idClassificado"));
				m.setData(resultSet.getString("data"));
				m.setTitulo(resultSet.getString("titulo"));
				m.setDescricao(resultSet.getString("descricao"));
				//m.setImagem(resultSet.getString("imagem"));
				//m.setAreaInteresse(resultSet.getString("areaInteresse_idAreaInteresse"));
				//m.setUsuario(resultSet.getString("usuario_idUsuario"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn, stmt, resultSet);
		}
		return m;
	}

	public ArrayList<Classificado> buscarTodos() {

		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement stmt = null;
		conn = getConnection();
		ArrayList<Classificado> listaClassificados = null;

		try {

			stmt = conn
					.prepareStatement("SELECT * FROM CLASSIFICADO ORDER BY NOME ASC");
			resultSet = stmt.executeQuery();
			listaClassificados = new ArrayList<Classificado>();

			while (resultSet.next()) {
				Classificado m = new Classificado();
				m.setIdClassificado(resultSet.getInt("idcCassificado"));
				m.setData(resultSet.getString("data"));
				m.setTitulo(resultSet.getString("titulo"));
				m.setDescricao(resultSet.getString("descricao"));
				//m.setImagem(resultSet.getString("imagem"));
				//m.setAreaInteresse(resultSet.getString("areaInteresse_idAreaInteresse"));
				//m.setUsuario(resultSet.getString("usuario_idUsuario"));

				listaClassificados.add(m);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			listaClassificados = null;
		} finally {
			closeConnection(conn, stmt, resultSet);
		}
		return listaClassificados;
	}
}
