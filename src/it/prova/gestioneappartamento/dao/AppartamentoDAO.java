package it.prova.gestioneappartamento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.prova.gestioneappartamento.connection.MyConnection;
import it.prova.gestioneappartamento.model.Appartamento;

public class AppartamentoDAO {
	public List<Appartamento> list() {

		List<Appartamento> result = new ArrayList<Appartamento>();
		Appartamento temp = null;

		try (Connection c = MyConnection.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery("select * from appartamento a ")) {

			while (rs.next()) {
				temp = new Appartamento();
				temp.setId(rs.getLong("id"));
				temp.setQuartiere(rs.getString("quartiere"));
				temp.setMetriQuadri(rs.getInt("metriquadrati"));
				temp.setPrezzo(rs.getInt("prezzo"));
				temp.setDataCostruzione(rs.getDate("datacostruzione"));

				result.add(temp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}

	public int insert(Appartamento appartamentoInput) {

		if (appartamentoInput == null)
			throw new RuntimeException("Impossibile inserire Appartamento: input mancante!");

		int result = 0;
		try (Connection c = MyConnection.getConnection();
				PreparedStatement ps = c.prepareStatement(
						"INSERT INTO appartamento (quartiere, metriquadrati, prezzo, datacostruzione) VALUES (?, ?, ?, ?)")) {

			ps.setString(1, appartamentoInput.getQuartiere());
			ps.setInt(2, appartamentoInput.getMetriQuadri());
			ps.setInt(3, appartamentoInput.getPrezzo());
			ps.setDate(4, new java.sql.Date(appartamentoInput.getDataCostruzione().getTime()));
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}

	public Appartamento selectById(Long idAppartamentoInput) {

		if (idAppartamentoInput == null || idAppartamentoInput < 1)
			throw new RuntimeException("Impossibile cercare Appartamento : id mancante!");

		Appartamento result = null;
		try (Connection c = MyConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("select * from appartamento a where a.id=?")) {

			ps.setLong(1, idAppartamentoInput);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					result = new Appartamento();
					result.setId(rs.getLong("id"));
					result.setQuartiere(rs.getString("quartiere"));
					result.setMetriQuadri(rs.getInt("metriquadrati"));
					;
					result.setDataCostruzione(rs.getDate("datacostruzione"));
				} else {
					result = null;
				}
			} // niente catch qui

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}

}
// new java.sql.Date(appartamentoInput.getDataCostruzione().getTime
