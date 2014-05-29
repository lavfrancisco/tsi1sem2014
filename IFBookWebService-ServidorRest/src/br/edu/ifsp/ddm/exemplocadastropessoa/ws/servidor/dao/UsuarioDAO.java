package br.edu.ifsp.ddm.exemplocadastropessoa.ws.servidor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import br.edu.ifsp.ddm.exemplocadastropessoa.ws.servidor.modelo.EstadoCivil;
import br.edu.ifsp.ddm.exemplocadastropessoa.ws.servidor.modelo.Usuario;

public class UsuarioDAO extends ConnectionFactory{


	public String update(Usuario usuario) {
EstadoCivil estado = new EstadoCivil();
		Connection conn = null;
		conn = getConnection();
		PreparedStatement stmt = null;
		int sucesso = 0;
		try {
			stmt = conn
					.prepareStatement("UPDATE usuario SET Apelido = ?, EstadoCivil_idEstadoCivil = ?, Telefone = ?, Email = ?, LocalTrabalho = ?, Foto = ? WHERE idUsuario = ?");

			stmt.setString(1, usuario.getApelido());
			stmt.setInt(2, estado.getIdEstadoCivil());
			usuario.setIdEstadoCivil(estado);
			stmt.setString(3, usuario.getTelefone());
			stmt.setString(4, usuario.getEmail());
			stmt.setString(5, usuario.getLocalTrabalho());
			stmt.setBytes(6, usuario.getFoto());
			stmt.setInt(7, usuario.getIdUsuario());
			
			sucesso = stmt.executeUpdate();

			if (sucesso > 0) {
				return ("Usuario alterado!");
			} else {
				return ("Usuario não existe!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return ("Erro ao alterar Usuario!");
		} finally {
			closeConnection(conn, stmt);
		}

	}
	
	public Usuario getProntuario(String bv) {

		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement stmt = null;
		conn = getConnection();
		Usuario usuario = null;
		try {
			stmt = conn.prepareStatement("SELECT * FROM usuario WHERE Prontuario = ?");
			stmt.setString(1, bv);
			resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				usuario = new Usuario();			
				
				usuario.setIdUsuario(resultSet.getInt("idUsuario"));
				usuario.setNome(resultSet.getString("Nome"));
				usuario.setIdUsuario(resultSet.getInt("Nascimento"));
				usuario.setProntuario(resultSet.getString("Prontuario"));
				usuario.setSenha(resultSet.getString("Senha"));
				usuario.setApelido(resultSet.getString("Apelido"));
				usuario.setLocalTrabalho(resultSet.getString("LocalTrabalho"));
				usuario.setCidade(resultSet.getString("Cidade"));
				usuario.setEmail(resultSet.getString("Email"));
				usuario.setTelefone(resultSet.getString("Telefone"));
				EstadoCivil estado = new EstadoCivil();
				estado.setIdEstadoCivil(resultSet.getInt("EstadoCivil_idEstadoCivil"));
				usuario.setFoto(resultSet.getBytes("Foto"));
				usuario.setIdEstadoCivil(estado);
				usuario.setNivel(resultSet.getInt("Nivel"));
				
				

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn, stmt, resultSet);
		}
		return usuario;
	}



	public Usuario getById(int id) {

		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement stmt = null;
		conn = getConnection();
		Usuario usuario = null;
		try {
			stmt = conn.prepareStatement("SELECT * FROM usuario WHERE idUsuario = ?");
			stmt.setInt(1, id);
			resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				usuario = new Usuario();			
				
				usuario.setIdUsuario(resultSet.getInt("idUsuario"));
				usuario.setNome(resultSet.getString("Nome"));
				usuario.setIdUsuario(resultSet.getInt("Nascimento"));
				usuario.setProntuario(resultSet.getString("Prontuario"));
				usuario.setSenha(resultSet.getString("Senha"));
				usuario.setApelido(resultSet.getString("Apelido"));
				usuario.setLocalTrabalho(resultSet.getString("LocalTrabalho"));
				usuario.setCidade(resultSet.getString("Cidade"));
				usuario.setEmail(resultSet.getString("Email"));
				usuario.setTelefone(resultSet.getString("Telefone"));
				EstadoCivil estado = new EstadoCivil();
				estado.setIdEstadoCivil(resultSet.getInt("EstadoCivil_idEstadoCivil"));
				usuario.setFoto(resultSet.getBytes("Foto"));
				usuario.setIdEstadoCivil(estado);
				usuario.setNivel(resultSet.getInt("Nivel"));
				
				

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn, stmt, resultSet);
		}
		return usuario;
	}

	public ArrayList<Usuario> Aniversariantes() {

		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement stmt = null;
		conn = getConnection();
		ArrayList<Usuario> listaUsuarios = null;

		try {

			stmt = conn
					.prepareStatement("SELECT " +
							"idUsuario," +
							"Nome, " +
							"DATE_FORMAT(Nascimento,'%m/%d'), " +
							"Prontuario, " +
							"Senha, " +
							"Apelido, " +
							"LocalTrabalho, " +
							"Cidade, " +
							"Email, " +
							"Telefone, " +
							"Foto, " +
							"EstadoCivil_idEstadoCivil, " +
							"Nivel " +
							"FROM usuario " +
							"WHERE DATE_FORMAT(Nascimento,'%m/%d') >= DATE_FORMAT(NOW(),'%m/%d') " +
							"AND DATE_FORMAT(Nascimento,'%m/%d') <= DATE_FORMAT(DATE_ADD(NOW(),INTERVAL 7 DAY) , '%m/%d') " +
							"ORDER BY Nascimento;");
			resultSet = stmt.executeQuery();
			listaUsuarios = new ArrayList<Usuario>();

			while (resultSet.next()) {
				Usuario usuario = new Usuario();
				usuario.setIdUsuario(resultSet.getInt("idUsuario"));
				usuario.setNome(resultSet.getString("Nome"));
				usuario.setNascimento(resultSet.getString("Nascimento"));
				usuario.setProntuario(resultSet.getString("Prontuario"));
				usuario.setSenha(resultSet.getString("Senha"));
				usuario.setApelido(resultSet.getString("Apelido"));
				usuario.setLocalTrabalho(resultSet.getString("LocalTrabalho"));
				usuario.setCidade(resultSet.getString("Cidade"));
				usuario.setEmail(resultSet.getString("Email"));
				usuario.setTelefone(resultSet.getString("Telefone"));
				EstadoCivil estado = new EstadoCivil();
				estado.setIdEstadoCivil(resultSet.getInt("EstadoCivil_idEstadoCivil"));
				usuario.setFoto(resultSet.getBytes("Foto"));
				usuario.setIdEstadoCivil(estado);
				usuario.setNivel(resultSet.getInt("Nivel"));
				

				listaUsuarios.add(usuario);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			listaUsuarios = null;
		} finally {
			closeConnection(conn, stmt, resultSet);
		}
		return listaUsuarios;
	}
}
