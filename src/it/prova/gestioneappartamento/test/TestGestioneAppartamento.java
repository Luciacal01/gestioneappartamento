package it.prova.gestioneappartamento.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import it.prova.gestioneappartamento.dao.AppartamentoDAO;
import it.prova.gestioneappartamento.model.Appartamento;

public class TestGestioneAppartamento {
	public static void main(String[] args) {
		AppartamentoDAO appartamentoDAOInstance = new AppartamentoDAO();

		System.out.println("in tabella appartamento ci sono: " + appartamentoDAOInstance.list().size());

		testIntertAppartamento(appartamentoDAOInstance);
		System.out.println("in tabella appartamento ci sono: " + appartamentoDAOInstance.list().size());

		testSelectByIdAppartamento(appartamentoDAOInstance);

		testDelete(appartamentoDAOInstance);
		testUpdate(appartamentoDAOInstance);

	}

	public static void testIntertAppartamento(AppartamentoDAO appartamentoDAOInstance) {
		System.out.println("......testInserimentoAppartamento inizio.......");

		Date dataInput = null;
		try {
			dataInput = new SimpleDateFormat("dd/MM/yyyy").parse("11/02/2013");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int quantiAppartamentiInseriti = appartamentoDAOInstance
				.insert(new Appartamento("San Paolo", 97, 50000, dataInput));

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

	public static void testDelete(AppartamentoDAO appartamentoDAOInstance) {
		System.out.println("......testDeleteAppartamento......");
		List<Appartamento> elencoAppartamenti = appartamentoDAOInstance.list();
		if (elencoAppartamenti.size() < 1)
			throw new RuntimeException("testDelete: FAILED, non ci sono articoli presenti");

		Appartamento appartamentoDaCancellare = elencoAppartamenti.get(0);
		long appartamentoCancellato = appartamentoDAOInstance.delete(appartamentoDaCancellare);
		if (appartamentoCancellato < 1)
			throw new RuntimeException("testDelete: FAILLED, l'appartamento non è stato cancellato");
		System.out.println("......testDeleteAppartamento fine ......");
	}

	public static void testUpdate(AppartamentoDAO appartamentoDAOInstance) {
		System.out.println("........testUpdate Appartamento.......");
		Date dataInput = null;
		try {
			dataInput = new SimpleDateFormat("dd/MM/yyyy").parse("30/12/2016");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Appartamento appartDaModificare = new Appartamento(appartamentoDAOInstance.selectById(5L).getId(),
				"Gianicolense", 150, 150000, dataInput);

		int appartamentoModificato = appartamentoDAOInstance.update(appartDaModificare);
		if (appartamentoModificato < -1)
			System.out.println("..........testUpdate Appartamento FAILED..........");

		System.out.println(appartamentoDAOInstance.selectById(5L).getQuartiere() + " "
				+ appartamentoDAOInstance.selectById(5L).getPrezzo());

		System.out.println("..............testUpdate Appartamento Passed...........");
	}

}
