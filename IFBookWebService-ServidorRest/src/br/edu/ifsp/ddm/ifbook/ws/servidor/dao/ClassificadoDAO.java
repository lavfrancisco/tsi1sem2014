package br.edu.ifsp.ddm.ifbook.ws.servidor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import br.edu.ifsp.ddm.ifbook.ws.servidor.modelo.AreaInteresse;
import br.edu.ifsp.ddm.ifbook.ws.servidor.modelo.Classificado;
import br.edu.ifsp.ddm.ifbook.ws.servidor.modelo.Mensagem;
import br.edu.ifsp.ddm.ifbook.ws.servidor.modelo.Usuario;

import br.edu.ifsp.ddm.ifbook.ws.servidor.jdbc.ConnectionFactory;


public class ClassificadoDAO extends ConnectionFactory {
	
	AreaInteresseDAO areaDao;
	

	public String inserir(Classificado c) {

		Connection conn = null;
		conn = getConnection();
		int sucesso = 0;

		PreparedStatement stmt = null;

	
				try {
					stmt = conn.prepareStatement("insert into classificado " +
							"(data, " +
							"titulo, " +
							"descricao, " +
							"imagem, " +
							"areainteresse_idareainteresse, " +
							"usuario_idusuario) " +
							"values (now(),?,?,?,?,?);");
					stmt.setString(1, c.getTitulo());
					stmt.setString(2, c.getDescricao());
					stmt.setBytes(3, c.getImagem());
					stmt.setInt(4, c.getAreaInteresse().getIdAreaInteresse());
					stmt.setInt(5, c.getUsuario().getIdUsuario());

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

	public String update(Classificado m) {

		Connection conn = null;
		conn = getConnection();
		PreparedStatement stmt = null;
		int sucesso = 0;
		try {
			stmt = conn
					.prepareStatement("update classificado set titulo = ?,descricao = ?,imagem = ?,areainteresse_idareainteresse = ? where idclassificado = ?;");

			
			stmt.setString(1, m.getTitulo());
			stmt.setString(2, m.getDescricao());
			stmt.setBytes(3, m.getImagem());
			stmt.setInt(4, m.getAreaInteresse().getIdAreaInteresse());
			stmt.setInt(5, m.getIdClassificado());
			sucesso = stmt.executeUpdate();
		
			if (sucesso > 0) {
				return ("CLASSIFICADO ALTERADO!");
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
				stmt = conn.prepareStatement("delete from classificado where idclassificado = ?;");
				stmt.setInt(1, id);
				excluidos = stmt.executeUpdate();

			if (excluidos > 0) {
				return ("CLASSIFICADO REMOVIDO!");
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

	public Classificado getById(int id) {

		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement stmt = null;
		conn = getConnection();
		Classificado c = null;
		try {
			stmt = conn.prepareStatement("select * from classificado, areainteresse, usuario where idclassificado = ? and idareainteresse = areainteresse_idareainteresse and idusuario = usuario_idusuario;");
			stmt.setInt(1, id);
			resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				AreaInteresse areaInteresse = new AreaInteresse();
				Usuario usuario = new Usuario();
				c = new Classificado();
				c.setIdClassificado(resultSet.getInt("idclassificado"));
				c.setData(resultSet.getString("data"));
				c.setTitulo(resultSet.getString("titulo"));
				c.setDescricao(resultSet.getString("descricao"));
				c.setImagem(resultSet.getBytes("imagem"));
				areaInteresse.setIdAreaInteresse(resultSet.getInt("areainteresse_idareainteresse"));
				areaInteresse.setNome(resultSet.getString("nome"));
				areaInteresse.setTipo(resultSet.getInt("tipo"));
				c.setAreaInteresse(areaInteresse);
				usuario.setIdUsuario(resultSet.getInt("usuario_idusuario"));
				usuario.setApelido(resultSet.getString("apelido"));
				usuario.setFoto(resultSet.getBytes("foto"));
				c.setUsuario(usuario);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn, stmt, resultSet);
		}
		return c;
	}

	public ArrayList<Classificado> buscarTodos() {

		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement stmt = null;
		conn = getConnection();
		ArrayList<Classificado> listaClassificados = null;

		try {

			stmt = conn
					.prepareStatement("select * from classificado, areainteresse, usuario where idareainteresse = areainteresse_idareainteresse and idusuario = usuario_idusuario order by idclassificado desc;");
			resultSet = stmt.executeQuery();
			listaClassificados = new ArrayList<Classificado>();
		//	System.out.println("LIST ALL CLASSIFICADO DAO!");

			while (resultSet.next()) {
				AreaInteresse interesse = new AreaInteresse();
				Usuario usuario = new Usuario();
				Classificado c = new Classificado();
				
				c.setIdClassificado(resultSet.getInt("idclassificado"));
				c.setData(resultSet.getString("data"));
				c.setTitulo(resultSet.getString("titulo"));
				c.setDescricao(resultSet.getString("descricao"));
				c.setImagem(resultSet.getBytes("Imagem"));
				interesse.setIdAreaInteresse(resultSet.getInt("areainteresse_idareainteresse"));
				interesse.setNome(resultSet.getString("nome"));
				interesse.setTipo(resultSet.getInt("tipo"));
				c.setAreaInteresse(interesse);
				usuario.setIdUsuario(resultSet.getInt("usuario_idusuario"));
				usuario.setApelido(resultSet.getString("apelido"));
				usuario.setFoto(resultSet.getBytes("foto"));
				c.setUsuario(usuario);
				

				listaClassificados.add(c);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			listaClassificados = null;
		} finally {
			closeConnection(conn, stmt, resultSet);
		}
		return listaClassificados;
	}
	public ArrayList<Classificado> buscarPorUsuario(int idUsuario) {

		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement stmt = null;
		conn = getConnection();
		ArrayList<Classificado> listaClassificados = null;

		try {

			stmt = conn
					.prepareStatement("select * from classificado, areainteresse, usuario  where usuario_idusuario = ? and idareainteresse = areainteresse_idareainteresse and idusuario = usuario_idusuario order by idclassificado desc;");
			stmt.setInt(1, idUsuario);
			resultSet = stmt.executeQuery();
			listaClassificados = new ArrayList<Classificado>();

			while (resultSet.next()) {
				AreaInteresse interesse = new AreaInteresse();
				Usuario usuario = new Usuario();
				Classificado c = new Classificado();
				c.setIdClassificado(resultSet.getInt("idclassificado"));
				c.setData(resultSet.getString("data"));
				c.setTitulo(resultSet.getString("titulo"));
				c.setDescricao(resultSet.getString("descricao"));
				c.setImagem(resultSet.getBytes("imagem"));
				interesse.setIdAreaInteresse(resultSet.getInt("areainteresse_idareainteresse"));
				interesse.setNome(resultSet.getString("nome"));
				interesse.setTipo(resultSet.getInt("tipo"));
				c.setAreaInteresse(interesse);
				usuario.setIdUsuario(resultSet.getInt("usuario_idusuario"));
				usuario.setApelido(resultSet.getString("apelido"));
				usuario.setFoto(resultSet.getBytes("foto"));
				c.setUsuario(usuario);

				listaClassificados.add(c);
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
