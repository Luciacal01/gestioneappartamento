package it.prova.gestioneappartamento.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import it.prova.gestioneappartamento.dao.AppartamentoDAO;
import it.prova.gestioneappartamento.model.Appartamento;

public class TestGestioneAppartamento {
	public static void main(String[] args) {
		AppartamentoDAO appartamentoDAOInstance = new AppartamentoDAO();

		System.out.println("in tabella appartamento ci sono: " + appartamentoDAOInstance.list().size());

		testIntertAppartamento(appartamentoDAOInstance);
		System.out.println("in tabella appartamento ci sono: " + appartamentoDAOInstance.list().size());
	}

	public static void testIntertAppartamento(AppartamentoDAO appartamentoDAOInstance) {
		System.out.println("......testInserimentoAppartamento inizio.......");

		Date dataInput = null;
		try {
			dataInput = new SimpleDateFormat("dd/MM/yyyy").parse("23/05/2022");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int quantiAppartamentiInseriti = appartamentoDAOInstance
				.insert(new Appartamento("San Paolo", 86, 10000, dataInput));

		if (quantiAppartamentiInseriti < 1)
			throw new RuntimeException("testInserimentoAppartamento: FAILLED");

		System.out.println("......testInserimentoAppartamento fine: PASSED.......");
	}
}
