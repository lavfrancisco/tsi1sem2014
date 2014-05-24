package br.edu.ifsp.ddm.exemplocadastropessoa.ws.servidor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.ddm.exemplocadastropessoa.ws.servidor.modelo.Mensagem;
import br.edu.ifsp.ddm.exemplocadastropessoa.ws.servidor.modelo.AreaInteresse;
import br.edu.ifsp.ddm.exemplocadastropessoa.ws.servidor.modelo.Usuario;;


public class MensagemDAO extends ConnectionFactory {
	
	AreaInteresseDAO areaDao;
	
	public String inserir(Mensagem m) {

		Connection conn = null;
		conn = getConnection();
		int sucesso = 0;

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement("INSERT INTO "
					+ "MENSAGEM (IDMENSAGEM,DATA,TITULO,DESCRICAO,IMAGEM,AREAINTERESSE,USUARIO) VALUES(?,?,?,?,?,?,?)");

			
			
			stmt.setInt(1, m.getIdMensagem());
			stmt.setString(2, m.getData());
			stmt.setString(3, m.getTitulo());
			stmt.setString(4, m.getDescricao());
			//stmt.setString(5, m.getImagem());
			stmt.setInt(6, m.getAreaInteresse().getIdAreaInteresse());
			stmt.setInt(7, m.getUsuario().getIdUsuario());
			
			sucesso = stmt.executeUpdate();

			if (sucesso > 0) {
				return ("MENSAGEM INSERIDA!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return ("ERRO AO INSERIR MENSAGEM!");
		} finally {
			closeConnection(conn, stmt);
		}
		return ("ERRO AO INSERIR MENSAGEM!");

	}

	public String inserir(List<Mensagem> mensagens) {

		Connection conn = null;
		conn = getConnection();
		int sucesso = 0;

		PreparedStatement stmt = null;

		if (mensagens != null) {
			for (Mensagem m : mensagens) {
				try {
					stmt = conn.prepareStatement("INSERT INTO "
							+ "MENSAGEM (IDMENSAGEM,DATA,TITULO,DESCRICAO,IMAGEM,AREAINTERESSE,USUARIO) VALUES(?,?,?,?,?,?,?)");

					stmt.setInt(1, m.getIdMensagem());
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
			return ("MENSAGENS INSERIDAS!");
		} else {
			return ("ERRO AO INSERIR AS MENSAGENS!");
		}

	}

	public String alterar(Mensagem m) {

		Connection conn = null;
		conn = getConnection();
		PreparedStatement stmt = null;
		int sucesso = 0;
		try {
			stmt = conn
					.prepareStatement("UPDATE MENSAGEM SET IDMENSAGEM = ?,DATA = ?,TITULO = ?,DESCRICAO = ?,IMAGEM = ?,AREAINTERESSE = ? WHERE ID = ?");

			stmt.setInt(1, m.getIdMensagem());
			stmt.setString(2, m.getData());
			stmt.setString(3, m.getTitulo());
			stmt.setString(4, m.getDescricao());
			//stmt.setString(5, m.getImagem());
			stmt.setInt(6, m.getAreaInteresse().getIdAreaInteresse());
			stmt.setInt(7, m.getUsuario().getIdUsuario());
			sucesso = stmt.executeUpdate();

			if (sucesso > 0) {
				return ("MENSAGEM ALTERADA!");
			} else {
				return ("MENSAGEM NÃO EXISTE!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return ("ERRO AO ALTERAR MENSAGEM!");
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
			stmt = conn.prepareStatement("DELETE FROM MENSAGEM WHERE ID = ?");
			stmt.setInt(1, id);
			excluidos = stmt.executeUpdate();

			if (excluidos > 0) {
				return ("MENSAGEM REMOVIDA!");
			} else {
				return ("MENSAGEM NÃO EXISTE!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return ("ERRO AO DELETAR MENSAGEM!");
		} finally {
			closeConnection(conn, stmt);
		}

	}

	public Mensagem buscar(int id) {

		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement stmt = null;
		conn = getConnection();
		Mensagem m = null;
		try {
			stmt = conn.prepareStatement("SELECT * FROM MENSAGEM WHERE ID = ?");
			stmt.setInt(1, id);
			resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				m = new Mensagem();
				m.setIdMensagem(resultSet.getInt("idMensagem"));
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

	public ArrayList<Mensagem> buscarTodos() {

		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement stmt = null;
		conn = getConnection();
		ArrayList<Mensagem> listaMensagens = null;

		try {

			stmt = conn
					.prepareStatement("SELECT * FROM MENSAGEM ORDER BY NOME ASC");
			resultSet = stmt.executeQuery();
			listaMensagens = new ArrayList<Mensagem>();

			while (resultSet.next()) {
				Mensagem m = new Mensagem();
				m.setIdMensagem(resultSet.getInt("idMensagem"));
				m.setData(resultSet.getString("data"));
				m.setTitulo(resultSet.getString("titulo"));
				m.setDescricao(resultSet.getString("descricao"));
				//m.setImagem(resultSet.getString("imagem"));
				//m.setAreaInteresse(resultSet.getString("areaInteresse_idAreaInteresse"));
				//m.setUsuario(resultSet.getString("usuario_idUsuario"));

				listaMensagens.add(m);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			listaMensagens = null;
		} finally {
			closeConnection(conn, stmt, resultSet);
		}
		return listaMensagens;
	}
}
