package br.edu.ifsp.ddm.exemplocadastropessoa.ws.servidor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.edu.ifsp.ddm.exemplocadastropessoa.ws.servidor.modelo.AreaInteresse;





public class AreaInteresseDAO  extends ConnectionFactory {
	

	public AreaInteresse getById(int id) {

		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement stmt = null;
		conn = getConnection();
		AreaInteresse area = null;
		try {
			stmt = conn.prepareStatement("SELECT * FROM areaInteresse WHERE idAreaInteresse = ?");
			stmt.setInt(1, id);
			resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				area = new AreaInteresse();			
				
				area.setIdAreaInteresse(resultSet.getInt("idAreaInteresse"));
				area.setTipo(resultSet.getInt("Tipo"));
				area.setNome(resultSet.getString("Nome"));

				
				

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn, stmt, resultSet);
		}
		return area;
	}



	public ArrayList<AreaInteresse> Aniversariantes() {

		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement stmt = null;
		conn = getConnection();
		ArrayList<AreaInteresse> listaAreas = null;

		try {

			stmt = conn.prepareStatement("SELECT * FROM areaInteresse;");
			resultSet = stmt.executeQuery();
			listaAreas = new ArrayList<AreaInteresse>();

			while (resultSet.next()) {
				AreaInteresse area = new AreaInteresse();
				area.setIdAreaInteresse(resultSet.getInt("idAreaInteresse"));
				area.setTipo(resultSet.getInt("Tipo"));
				area.setNome(resultSet.getString("Nome"));
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
	
	

