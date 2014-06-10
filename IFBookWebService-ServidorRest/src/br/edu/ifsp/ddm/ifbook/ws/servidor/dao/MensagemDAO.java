package br.edu.ifsp.ddm.ifbook.ws.servidor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.ddm.ifbook.ws.servidor.modelo.Mensagem;
import br.edu.ifsp.ddm.ifbook.ws.servidor.modelo.AreaInteresse;
import br.edu.ifsp.ddm.ifbook.ws.servidor.modelo.Usuario;
import br.edu.ifsp.ddm.ifbook.ws.servidor.jdbc.ConnectionFactory;


public class MensagemDAO extends ConnectionFactory {
	
	AreaInteresseDAO areaDao;
	
	public String inserir(Mensagem m) {

		Connection conn = null;
		conn = getConnection();
		int sucesso = 0;

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement("insert into mensagem " +
																"(data, " +
																"titulo, " +
																"descricao, " +
																"imagem, " +
																"areainteresse_idareainteresse, " +
																"usuario_idusuario) " +
																"values (now(),?,?,?,?,?);");

			
			
			stmt.setString(1, m.getTitulo());
			stmt.setString(2, m.getDescricao());
			stmt.setBytes(3, m.getImagem());
			stmt.setInt(4, m.getAreaInteresse().getIdAreaInteresse());
			stmt.setInt(5, m.getUsuario().getIdUsuario());
			
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


	public String update(Mensagem m) {

		Connection conn = null;
		conn = getConnection();
		PreparedStatement stmt = null;
		int sucesso = 0;
		try {
			stmt = conn
					.prepareStatement("update mensagem set titulo = ?,descricao = ?,imagem = ?,areainteresse_idareainteresse = ? where idmensagem = ?;");

			
			stmt.setString(1, m.getTitulo());
			stmt.setString(2, m.getDescricao());
			stmt.setBytes(3, m.getImagem());
			stmt.setInt(4, m.getAreaInteresse().getIdAreaInteresse());
			stmt.setInt(5, m.getIdMensagem());
			sucesso = stmt.executeUpdate();
			

			if (sucesso > 0) {
				return ("Mensagem alterada!");
			} else {
				return ("Mensagem não existe!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return ("Erro ao alterar mensagem!");
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
			stmt = conn.prepareStatement("delete from mensagem where idmensagem = ?;");
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

	public Mensagem getById(int id) {

		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement stmt = null;
		conn = getConnection();
		Mensagem m = null;
		try {
			stmt = conn.prepareStatement("select * from mensagem, areainteresse, usuario where idmensagem = ? and idareainteresse = areainteresse_idareainteresse and idusuario = usuario_idusuario;");
			stmt.setInt(1, id);
			resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				AreaInteresse areaInteresse = new AreaInteresse();
				Usuario usuario = new Usuario();
				m = new Mensagem();
				m.setIdMensagem(resultSet.getInt("idmensagem"));
				m.setData(resultSet.getString("data"));
				m.setTitulo(resultSet.getString("titulo"));
				m.setDescricao(resultSet.getString("descricao"));
				m.setImagem(resultSet.getBytes("imagem"));
				areaInteresse.setIdAreaInteresse(resultSet.getInt("areainteresse_idareainteresse"));
				areaInteresse.setNome(resultSet.getString("nome"));
				areaInteresse.setTipo(resultSet.getInt("tipo"));
				m.setAreaInteresse(areaInteresse);
				usuario.setIdUsuario(resultSet.getInt("usuario_idusuario"));
				usuario.setApelido(resultSet.getString("apelido"));
				usuario.setFoto(resultSet.getBytes("foto"));
				m.setUsuario(usuario);

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
					.prepareStatement("select * from mensagem, areainteresse, usuario where idareainteresse = areainteresse_idareainteresse and idusuario = usuario_idusuario order by idmensagem desc;");
			resultSet = stmt.executeQuery();
			listaMensagens = new ArrayList<Mensagem>();

			while (resultSet.next()) {
				AreaInteresse interesse = new AreaInteresse();
				Usuario usuario = new Usuario();
				Mensagem m = new Mensagem();
				m.setIdMensagem(resultSet.getInt("idmensagem"));
				m.setData(resultSet.getString("data"));
				m.setTitulo(resultSet.getString("titulo"));
				m.setDescricao(resultSet.getString("descricao"));
				m.setImagem(resultSet.getBytes("imagem"));
				interesse.setIdAreaInteresse(resultSet.getInt("areainteresse_idareainteresse"));
				interesse.setNome(resultSet.getString("nome"));
				interesse.setTipo(resultSet.getInt("tipo"));
				m.setAreaInteresse(interesse);
				usuario.setIdUsuario(resultSet.getInt("usuario_idusuario"));
				usuario.setApelido(resultSet.getString("apelido"));
				usuario.setFoto(resultSet.getBytes("foto"));
				m.setUsuario(usuario);

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
	
	public ArrayList<Mensagem> buscarPorUsuario(int idUsuario) {

		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement stmt = null;
		conn = getConnection();
		ArrayList<Mensagem> listaMensagens = null;

		try {

			stmt = conn
					.prepareStatement("select * from mensagem, areainteresse, usuario  where usuario_idusuario = ? and idareainteresse = areainteresse_idareainteresse and idusuario = usuario_idusuario order by idmensagem desc;");
			stmt.setInt(1, idUsuario);
			resultSet = stmt.executeQuery();
			listaMensagens = new ArrayList<Mensagem>();

			while (resultSet.next()) {
				AreaInteresse interesse = new AreaInteresse();
				Usuario usuario = new Usuario();
				Mensagem m = new Mensagem();
				m.setIdMensagem(resultSet.getInt("idmensagem"));
				m.setData(resultSet.getString("data"));
				m.setTitulo(resultSet.getString("titulo"));
				m.setDescricao(resultSet.getString("descricao"));
				m.setImagem(resultSet.getBytes("imagem"));
				interesse.setIdAreaInteresse(resultSet.getInt("areainteresse_idareainteresse"));
				interesse.setNome(resultSet.getString("nome"));
				interesse.setTipo(resultSet.getInt("tipo"));
				m.setAreaInteresse(interesse);
				usuario.setIdUsuario(resultSet.getInt("usuario_idusuario"));
				usuario.setApelido(resultSet.getString("apelido"));
				usuario.setFoto(resultSet.getBytes("foto"));
				m.setUsuario(usuario);

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
