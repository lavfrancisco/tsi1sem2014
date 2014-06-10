package br.edu.ifsp.ddm.ifbook.ws.servidor.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.edu.ifsp.ddm.ifbook.ws.servidor.jdbc.ConnectionFactory;
import br.edu.ifsp.ddm.ifbook.ws.servidor.modelo.EstadoCivil;


public class EstadoCivilDAO extends ConnectionFactory {

	public EstadoCivil getById(int id){
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement stmt = null;
		conn = getConnection();
		EstadoCivil estadoCivil = null;
		try {
			stmt = conn.prepareStatement("select idestadocivil, estadocivil from estadocivil where idestadocivil = ?;");
			stmt.setInt(1, id);
			resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				estadoCivil = new EstadoCivil();			
				estadoCivil.setIdEstadoCivil(resultSet.getInt("idestadocivil"));
				estadoCivil.setEstadoCivil(resultSet.getString("estadocivil"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn, stmt, resultSet);
		}		
		return estadoCivil;
	}
	
	public ArrayList<EstadoCivil> listarTodos(){
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement stmt = null;
		conn = getConnection();
		
		ArrayList<EstadoCivil> listaEstadosCivis = null;
		try {
			stmt = conn.prepareStatement("select idestadocivil, estadocivil from estadocivil;");
			resultSet = stmt.executeQuery();
			listaEstadosCivis = new ArrayList<EstadoCivil>();
			while (resultSet.next()) {
				EstadoCivil estadoCivil = new EstadoCivil();
				estadoCivil = new EstadoCivil();			
				estadoCivil.setIdEstadoCivil(resultSet.getInt("idestadocivil"));
				estadoCivil.setEstadoCivil(resultSet.getString("estadocivil"));
				listaEstadosCivis.add(estadoCivil);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn, stmt, resultSet);
		}		
		return listaEstadosCivis;
	}

}
