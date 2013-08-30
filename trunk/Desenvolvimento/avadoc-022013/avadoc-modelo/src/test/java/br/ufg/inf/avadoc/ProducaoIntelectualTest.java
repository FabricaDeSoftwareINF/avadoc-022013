package br.ufg.inf.avadoc;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufg.inf.avadoc.enums.EnumTipoAtividade;
import br.ufg.inf.avadoc.enums.EnumTipoProducao;
import br.ufg.inf.avadoc.modelo.atividade.Producao;
import br.ufg.inf.avadoc.modelo.atividade.ProducaoIntelectual;
import br.ufg.inf.avadoc.modelo.atividade.Produto;

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
		Produto produto1 = new Produto();
		produto1.setPontos(6);
		producaoCientifica.addProduto(produto1);
		
		Producao producaoArtisticaCultural = new Producao();
		Produto produto2 = new Produto();
		produto2.setPontos(3);
		producaoArtisticaCultural.addProduto(produto2);

		Producao producaoTecnicaTecnologica = new Producao();
		Produto produto3 = new Produto();
		produto3.setPontos(4);
		producaoTecnicaTecnologica.addProduto(produto3);
		
		Producao producaoOutra = new Producao();
		Produto produto4 = new Produto();
		produto4.setPontos(9);
		producaoOutra.addProduto(produto4);
		
		producaoIntelectual.setProducaoCientifica(producaoCientifica);
		producaoIntelectual.setProducaoArtisticaCultural(producaoArtisticaCultural);
		producaoIntelectual.setProducaoTecnicaTecnologica(producaoTecnicaTecnologica);
		producaoIntelectual.setProducaoOutra(producaoOutra);
		
		assertEquals(EnumTipoAtividade.PRODUCAO_INTELECTUAL, producaoIntelectual.getProducaoCientifica().getTipoAtividade());
		assertEquals(EnumTipoAtividade.PRODUCAO_INTELECTUAL, producaoIntelectual.getProducaoArtisticaCultural().getTipoAtividade());
		assertEquals(EnumTipoAtividade.PRODUCAO_INTELECTUAL, producaoIntelectual.getProducaoTecnicaTecnologica().getTipoAtividade());
		assertEquals(EnumTipoAtividade.PRODUCAO_INTELECTUAL, producaoIntelectual.getProducaoOutra().getTipoAtividade());
		
		assertEquals(EnumTipoProducao.PRODUCAO_CIENTIFICA, producaoIntelectual.getProducaoCientifica().getTipoProducao());
		assertEquals(EnumTipoProducao.PRODUCAO_ARTISTICA_CULTURAL, producaoIntelectual.getProducaoArtisticaCultural().getTipoProducao());
		assertEquals(EnumTipoProducao.PRODUCAO_TECNICA_TECNOLOGICA, producaoIntelectual.getProducaoTecnicaTecnologica().getTipoProducao());
		assertEquals(EnumTipoProducao.PRODUCAO_OUTRO, producaoIntelectual.getProducaoOutra().getTipoProducao());
		
		assertEquals(1, producaoIntelectual.getProducaoCientifica().getProdutos().size());
		assertEquals(1, producaoIntelectual.getProducaoArtisticaCultural().getProdutos().size());
		assertEquals(1, producaoIntelectual.getProducaoTecnicaTecnologica().getProdutos().size());
		assertEquals(1, producaoIntelectual.getProducaoOutra().getProdutos().size());
		
		assertEquals(6, producaoIntelectual.getProducaoCientifica().getPontos());
		assertEquals(3, producaoIntelectual.getProducaoArtisticaCultural().getPontos());
		assertEquals(4, producaoIntelectual.getProducaoTecnicaTecnologica().getPontos());
		assertEquals(9, producaoIntelectual.getProducaoOutra().getPontos());
		
		assertEquals(22, producaoIntelectual.getPontos());
	}
	
}
