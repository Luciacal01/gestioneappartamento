package it.prova.gestioneappartamento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

	public int delete(Appartamento appartamentoInput) {
		if (appartamentoInput == null || appartamentoInput.getId() < 1) {
			return 0;
		}

		int result = 0;

		try (Connection c = MyConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("Delete from appartamento where id=?")) {
			ps.setLong(1, appartamentoInput.getId());

			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int update(Appartamento appartamentoInput) {
		if (appartamentoInput == null) {
			throw new RuntimeException("Errore articolo in input errato");
		}

		int result = -1;
		try (Connection c = MyConnection.getConnection();
				PreparedStatement ps = c.prepareStatement(
						"update appartamento set quartiere = ?, metriquadrati = ?, prezzo = ?, datacostruzione = ? where id = ?;")) {
			// Setto le varie proprieta
			ps.setString(1, appartamentoInput.getQuartiere());
			ps.setInt(2, appartamentoInput.getMetriQuadri());
			ps.setInt(3, appartamentoInput.getPrezzo());
			ps.setDate(4, new java.sql.Date(appartamentoInput.getDataCostruzione().getTime()));
			// where
			ps.setLong(5, appartamentoInput.getId());

			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}

	public List<Appartamento> findByExample(Appartamento example) {
		if (example == null && example.getId() < 1)
			throw new RuntimeException("errore Appartamento inesistente");

		Date dataSbagliata = null;
		try {
			dataSbagliata = new SimpleDateFormat("dd/MM/yyyy").parse("00/00/0000");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Appartamento> results = new ArrayList<Appartamento>();
		// Date d = new Date("00/00/0000");
		boolean quartiereEx = false;
		boolean metriQuadratiEx = false;
		boolean prezzoEx = false;
		boolean dateEx = false;
		Appartamento temp = null;
		String query = "Select * from appartamento where ";

		if (example.getQuartiere() != null && example.getQuartiere() != " ") {
			quartiereEx = true;
			query += "quartiere=?";
		}
		if (example.getMetriQuadri() > 0) {
			metriQuadratiEx = true;
			query += " AND metriquadri=?";
		}
		if (example.getPrezzo() > 0) {
			prezzoEx = true;
			query += " AND prezo=? AND\t";
		}
		if (example.getDataCostruzione().after(dataSbagliata)) {
			dateEx = true;
			query += " AND datacostruzione=?;";
		}
		System.out.println(query);
		try (Connection c = MyConnection.getConnection(); PreparedStatement ps = c.prepareStatement(query)) {
			int contatore = 1;
			if (quartiereEx) {
				ps.setString(contatore, example.getQuartiere());
				contatore++;
			}
			if (metriQuadratiEx) {
				ps.setInt(contatore, example.getMetriQuadri());
				contatore++;
			}
			if (prezzoEx) {
				ps.setInt(contatore, example.getPrezzo());
				contatore++;
			}
			if (dateEx) {
				ps.setDate(contatore, new java.sql.Date(example.getDataCostruzione().getTime()));
				contatore++;
			}
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					temp = new Appartamento();
					temp.setId(rs.getLong("id"));
					temp.setQuartiere(rs.getString("quartiere"));
					temp.setMetriQuadri(rs.getInt("metriquadrati"));
					temp.setPrezzo(rs.getInt("prezzo"));
					temp.setDataCostruzione(rs.getDate("datacostruzione"));

					results.add(temp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return results;

	}

}
// new java.sql.Date(appartamentoInput.getDataCostruzione().getTime
