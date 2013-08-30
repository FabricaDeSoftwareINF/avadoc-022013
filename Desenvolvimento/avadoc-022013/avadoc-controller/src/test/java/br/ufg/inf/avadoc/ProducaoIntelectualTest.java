package br.ufg.inf.avadoc;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufg.inf.avadoc.modelo.atividade.Producao;
import br.ufg.inf.avadoc.modelo.atividade.ProducaoIntelectual;
import br.ufg.inf.avadoc.xml.XmlAtividades;

public class ProducaoIntelectualTest {
	private ProducaoIntelectual producaoIntelectual;
	@Before
	public void setUp() throws Exception {
		producaoIntelectual = new ProducaoIntelectual();
	}

	@After
	public void tearDown() throws Exception {
		producaoIntelectual = null;
	}

	@Test
	public void testGetPontos() {
		Producao producaoCientifica = new Producao();
		producaoCientifica.addProduto(XmlAtividades.getProduto("6.7", "ProducaoCientifica"));
		
		Producao producaoArtisticaCultural = new Producao();
		producaoArtisticaCultural.addProduto(XmlAtividades.getProduto("9", "ProducaoArtisticaCultural"));

		Producao producaoTecnicaTecnologica = new Producao();
		producaoTecnicaTecnologica.addProduto(XmlAtividades.getProduto("14.1", "ProducaoTecnicaTecnologica"));
		
		Producao producaoOutra = new Producao();
		producaoOutra.addProduto(XmlAtividades.getProduto("6", "ProducaoOutro"));
		
		producaoIntelectual.setProducaoCientifica(producaoCientifica);
		producaoIntelectual.setProducaoArtisticaCultural(producaoArtisticaCultural);
		producaoIntelectual.setProducaoTecnicaTecnologica(producaoTecnicaTecnologica);
		producaoIntelectual.setProducaoOutra(producaoOutra);
		
		assertEquals(30, producaoIntelectual.getPontos());
	}
	
}
