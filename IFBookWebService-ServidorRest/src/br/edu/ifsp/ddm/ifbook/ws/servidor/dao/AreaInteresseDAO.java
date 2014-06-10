package br.edu.ifsp.ddm.ifbook.ws.servidor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.ddm.ifbook.ws.servidor.jdbc.ConnectionFactory;
import br.edu.ifsp.ddm.ifbook.ws.servidor.modelo.AreaInteresse;





public class AreaInteresseDAO  extends ConnectionFactory {
	

	public AreaInteresse getById(int id) {

		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement stmt = null;
		conn = getConnection();
		AreaInteresse area = null;
		try {
			stmt = conn.prepareStatement("select * from areainteresse where idareainteresse = ?");
			stmt.setInt(1, id);
			resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				area = new AreaInteresse();			
				
				area.setIdAreaInteresse(resultSet.getInt("idareainteresse"));
				area.setTipo(resultSet.getInt("tipo"));
				area.setNome(resultSet.getString("nome"));

				
				

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn, stmt, resultSet);
		}
		return area;
	}



	public ArrayList<AreaInteresse> listarTodos() {

		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement stmt = null;
		conn = getConnection();
		ArrayList<AreaInteresse> listaAreas = null;

		try {

			stmt = conn.prepareStatement("select * from areainteresse;");
			resultSet = stmt.executeQuery();
			listaAreas = new ArrayList<AreaInteresse>();

			while (resultSet.next()) {
				AreaInteresse area = new AreaInteresse();
				area.setIdAreaInteresse(resultSet.getInt("idareainteresse"));
				area.setTipo(resultSet.getInt("tipo"));
				area.setNome(resultSet.getString("nome"));
				listaAreas.add(area);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			listaAreas = null;
		} finally {
			closeConnection(conn, stmt, resultSet);
		}
		return listaAreas;
	}
}
	
	

