package br.ufg.inf.avadoc;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufg.inf.avadoc.enums.EnumTipoAtividade;
import br.ufg.inf.avadoc.enums.EnumTipoProducao;
import br.ufg.inf.avadoc.modelo.atividade.AtividadePesquisaExtensao;
import br.ufg.inf.avadoc.modelo.atividade.Producao;
import br.ufg.inf.avadoc.modelo.atividade.Produto;

public class AtividadePesquisaExtensaoTest {
	private AtividadePesquisaExtensao atividadePesquisaExtensao;
	@Before
	public void setUp() throws Exception {
		atividadePesquisaExtensao = new AtividadePesquisaExtensao();
	}

	@After
	public void tearDown() throws Exception {
		atividadePesquisaExtensao = null;
	}

	@Test
	public void testGetPontos() {
		Producao pesquisa = new Producao();
		Produto produto1 = new Produto();
		produto1.setPontos(6);
		pesquisa.addProduto(produto1);
		
		Producao extensao = new Producao();
		Produto produto2 = new Produto();
		produto2.setPontos(8);
		extensao.addProduto(produto2);

		atividadePesquisaExtensao.setPesquisa(pesquisa);
		atividadePesquisaExtensao.setExtensao(extensao);
		
		assertEquals(EnumTipoAtividade.ATIVIDADE_PESQUISA_EXTENSAO, atividadePesquisaExtensao.getPesquisa().getTipoAtividade());
		assertEquals(EnumTipoAtividade.ATIVIDADE_PESQUISA_EXTENSAO, atividadePesquisaExtensao.getExtensao().getTipoAtividade());
		
		assertEquals(EnumTipoProducao.ATIVIDADE_PESQUISA, atividadePesquisaExtensao.getPesquisa().getTipoProducao());
		assertEquals(EnumTipoProducao.ATIVIDADE_EXTENSAO, atividadePesquisaExtensao.getExtensao().getTipoProducao());
		
		assertEquals(6, atividadePesquisaExtensao.getPesquisa().getPontos());
		assertEquals(8, atividadePesquisaExtensao.getExtensao().getPontos());
		
		assertEquals(14, atividadePesquisaExtensao.getPontos());
	}
	
}
