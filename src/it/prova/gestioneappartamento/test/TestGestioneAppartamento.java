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

		// testIntertAppartamento(appartamentoDAOInstance);
		// System.out.println("in tabella appartamento ci sono: " +
		// appartamentoDAOInstance.list().size());

		testSelectByIdAppartamento(appartamentoDAOInstance);

	}

	public static void testIntertAppartamento(AppartamentoDAO appartamentoDAOInstance) {
		System.out.println("......testInserimentoAppartamento inizio.......");

		Date dataInput = null;
		try {
			dataInput = new SimpleDateFormat("dd/MM/yyyy").parse("05/01/2011");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int quantiAppartamentiInseriti = appartamentoDAOInstance
				.insert(new Appartamento("Gianicolense", 75, 24000, dataInput));

		if (quantiAppartamentiInseriti < 1)
			throw new RuntimeException("testInserimentoAppartamento: FAILED");

		System.out.println("......testInserimentoAppartamento fine: PASSED.......");
	}

	public static void testSelectByIdAppartamento(AppartamentoDAO appartamentoDAOInstance) {
		System.out.println("......testSelectByIdAppartamento inizio.....");

		Appartamento appartamentoCheRicerco = appartamentoDAOInstance.selectById(1L);

		if (appartamentoCheRicerco == null) {
			System.out.println("negozio non presente");
		}

		System.out.println(appartamentoCheRicerco.getQuartiere() + " ha : " + appartamentoCheRicerco.getMetriQuadri()
				+ " mq e costo: " + appartamentoCheRicerco.getPrezzo());
		System.out.println(".......testSelectByIdAppartamento fine: PASSED");
	}
}
