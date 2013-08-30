package br.ufg.inf.avadoc;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufg.inf.avadoc.modelo.atividade.AtividadePesquisaExtensao;
import br.ufg.inf.avadoc.modelo.atividade.Producao;
import br.ufg.inf.avadoc.xml.XmlAtividades;

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
		pesquisa.addProduto(XmlAtividades.getProduto("2", "AtividadePesquisa"));
		
		Producao extensao = new Producao();
		extensao.addProduto(XmlAtividades.getProduto("9.1", "AtividadeExtensao"));
		
		atividadePesquisaExtensao.setPesquisa(pesquisa);
		atividadePesquisaExtensao.setExtensao(extensao);
		
		assertEquals(14, atividadePesquisaExtensao.getPontos());
	}
	
}
