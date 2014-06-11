package br.edu.ifsp.ddm.ifbook.ws.servidor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import br.edu.ifsp.ddm.ifbook.ws.servidor.modelo.EstadoCivil;
import br.edu.ifsp.ddm.ifbook.ws.servidor.modelo.Usuario;
import br.edu.ifsp.ddm.ifbook.ws.servidor.jdbc.ConnectionFactory;

public class UsuarioDAO extends ConnectionFactory{


	public String update(Usuario usuario) {
		EstadoCivil estado = new EstadoCivil();
		Connection conn = null;
		conn = getConnection();
		PreparedStatement stmt = null;
		int sucesso = 0;
		try {
			stmt = conn
					.prepareStatement("update usuario set apelido = ?, estadocivil_idestadocivil = ?, telefone = ?, email = ?, localtrabalho = ?, foto = ? where idusuario = ?;");

			stmt.setString(1, usuario.getApelido());
			stmt.setInt(2, usuario.getIdEstadoCivil().getIdEstadoCivil());
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
	
	public String updatePasswd(Usuario usuario) {
		EstadoCivil estado = new EstadoCivil();
		Connection conn = null;
		conn = getConnection();
		PreparedStatement stmt = null;
		int sucesso = 0;
		try {
			stmt = conn
					.prepareStatement("update usuario set senha = ? where idusuario = ?;");

			stmt.setString(1, usuario.getSenha());
			stmt.setInt(2, usuario.getIdUsuario());			
			sucesso = stmt.executeUpdate();

			if (sucesso > 0) {
				return ("Senha alterado!");
			} else {
				return ("Senha não existe!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return ("Erro ao alterar senha!");
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
			stmt = conn.prepareStatement("select idusuario, " +
										"prontuario, "+
										"senha, "+
										"nivel, "+
										"nome, "+
										"nascimento, "+
										"apelido, "+
										"localtrabalho, "+
										"cidade, "+
										"email, "+
										"telefone, "+
										"foto, "+
										"estadocivil_idestadocivil, "+
										"estadocivil "+
										"from usuario, estadocivil "+
										"where prontuario = ? and estadocivil_idestadocivil = idestadocivil ;");
			stmt.setString(1, bv);
			resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				usuario = new Usuario();	
				System.out.println("FUNCIONA!!!!!!!!!!!");
				
				usuario.setIdUsuario(resultSet.getInt("idusuario"));
				usuario.setNome(resultSet.getString("nome"));
				usuario.setNascimento(resultSet.getString("nascimento"));
				usuario.setProntuario(resultSet.getString("prontuario"));
				usuario.setSenha(resultSet.getString("senha"));
				usuario.setApelido(resultSet.getString("apelido"));
				usuario.setLocalTrabalho(resultSet.getString("localtrabalho"));
				usuario.setCidade(resultSet.getString("cidade"));
				usuario.setEmail(resultSet.getString("email"));
				usuario.setTelefone(resultSet.getString("telefone"));
				EstadoCivil estado = new EstadoCivil();
				estado.setIdEstadoCivil(resultSet.getInt("estadocivil_idestadocivil"));
				estado.setEstadoCivil(resultSet.getString("estadocivil"));
				usuario.setFoto(resultSet.getBytes("foto"));
				usuario.setIdEstadoCivil(estado);
				usuario.setNivel(resultSet.getInt("nivel"));
				
				

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
			stmt = conn.prepareStatement("select idusuario, " +
										"prontuario, "+
										"senha, "+
										"nivel, "+
										"nome, "+
										"nascimento, "+
										"apelido, "+
										"localtrabalho, "+
										"cidade, "+
										"email, "+
										"telefone, "+
										"foto, "+
										"estadocivil_idestadocivil, "+
										"estadocivil "+
										"from usuario, estadocivil "+
										"where idusuario = ? and estadocivil_idestadocivil = idestadocivil ;");
			stmt.setInt(1, id);
			resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				usuario = new Usuario();			
				
				usuario.setIdUsuario(resultSet.getInt("idusuario"));
				usuario.setNome(resultSet.getString("nome"));
				usuario.setNascimento(resultSet.getString("nascimento"));
				usuario.setProntuario(resultSet.getString("prontuario"));
				usuario.setSenha(resultSet.getString("senha"));
				usuario.setApelido(resultSet.getString("apelido"));
				usuario.setLocalTrabalho(resultSet.getString("localtrabalho"));
				usuario.setCidade(resultSet.getString("cidade"));
				usuario.setEmail(resultSet.getString("email"));
				usuario.setTelefone(resultSet.getString("telefone"));
				EstadoCivil estado = new EstadoCivil();
				estado.setIdEstadoCivil(resultSet.getInt("estadocivil_idestadocivil"));
				estado.setEstadoCivil(resultSet.getString("estadocivil"));
				usuario.setFoto(resultSet.getBytes("foto"));
				usuario.setIdEstadoCivil(estado);
				usuario.setNivel(resultSet.getInt("nivel"));
				
				

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
					.prepareStatement("select " +
							"idusuario," +
							"nome, " +
							"date_format(nascimento,'%m/%d') as nascimento, " +
							"prontuario, " +
							"senha, " +
							"apelido, " +
							"localtrabalho, " +
							"cidade, " +
							"email, " +
							"telefone, " +
							"foto, " +
							"estadocivil_idestadocivil, " +
							"nivel " +
							"from usuario " +
							"where date_format(nascimento,'%m/%d') >= date_format(now(),'%m/%d') " +
							"and date_format(nascimento,'%m/%d') <= date_format(date_add(now(),interval 7 day) , '%m/%d') " +
							"order by nascimento;");
			resultSet = stmt.executeQuery();
			listaUsuarios = new ArrayList<Usuario>();

			while (resultSet.next()) {
				Usuario usuario = new Usuario();
				usuario.setIdUsuario(resultSet.getInt("idusuario"));
				usuario.setNome(resultSet.getString("nome"));
				usuario.setNascimento(resultSet.getString("nascimento"));
				usuario.setProntuario(resultSet.getString("prontuario"));
				usuario.setSenha(resultSet.getString("senha"));
				usuario.setApelido(resultSet.getString("apelido"));
				usuario.setLocalTrabalho(resultSet.getString("localtrabalho"));
				usuario.setCidade(resultSet.getString("cidade"));
				usuario.setEmail(resultSet.getString("email"));
				usuario.setTelefone(resultSet.getString("telefone"));
				EstadoCivil estado = new EstadoCivil();
				estado.setIdEstadoCivil(resultSet.getInt("estadocivil_idestadocivil"));
				usuario.setFoto(resultSet.getBytes("foto"));
				usuario.setIdEstadoCivil(estado);
				usuario.setNivel(resultSet.getInt("nivel"));
				

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
