package br.ufg.inf.avadoc;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufg.inf.avadoc.modelo.atividade.Producao;
import br.ufg.inf.avadoc.modelo.atividade.Produto;
import br.ufg.inf.avadoc.xml.XmlAtividades;

public class ProducaoTest {
	private Producao producao;
	@Before
	public void setUp() throws Exception {
		producao = new Producao();
	}

	@After
	public void tearDown() throws Exception {
		producao = null;
	}

	@Test
	public void testGetPontosProducaoCientifica() {
		Produto p1 =  XmlAtividades.getProduto("1.1", "ProducaoCientifica");
		producao.addProduto(p1);
		
		Produto p2 =  XmlAtividades.getProduto("15", "ProducaoCientifica");
		producao.addProduto(p2);
		
		assertEquals(41, producao.getPontos());
	}

	@Test
	public void testGetPontosProducaoArtisticaCultural() {
		Produto p1 =  XmlAtividades.getProduto("1", "ProducaoArtisticaCultural");
		producao.addProduto(p1);
		
		Produto p2 =  XmlAtividades.getProduto("17", "ProducaoArtisticaCultural");
		producao.addProduto(p2);
		
		assertEquals(27, producao.getPontos());
	}
	
	@Test
	public void testGetPontosProducaoTecnicaTecnologica() {
		Produto p1 =  XmlAtividades.getProduto("1", "ProducaoTecnicaTecnologica");
		producao.addProduto(p1);
		
		Produto p2 =  XmlAtividades.getProduto("22.2", "ProducaoTecnicaTecnologica");
		producao.addProduto(p2);
		
		assertEquals(25, producao.getPontos());
	}
	
	@Test
	public void testGetPontosProducaoOutra() {
		Produto p1 =  XmlAtividades.getProduto("1", "ProducaoOutro");
		producao.addProduto(p1);
		
		Produto p2 =  XmlAtividades.getProduto("7", "ProducaoOutro");
		producao.addProduto(p2);
		
		assertEquals(9, producao.getPontos());
	}
	
	@Test
	public void testGetPontosAtividadePesquisa() {
		Produto p1 =  XmlAtividades.getProduto("1", "AtividadePesquisa");
		producao.addProduto(p1);
		
		Produto p2 =  XmlAtividades.getProduto("3", "AtividadePesquisa");
		producao.addProduto(p2);
		
		assertEquals(15, producao.getPontos());
	}

	@Test
	public void testGetPontosAtividadeExtensao() {
		Produto p1 =  XmlAtividades.getProduto("1", "AtividadeExtensao");
		producao.addProduto(p1);
		
		Produto p2 =  XmlAtividades.getProduto("12.2", "AtividadeExtensao");
		producao.addProduto(p2);
		
		assertEquals(15, producao.getPontos());
	}
	
	@Test
	public void testGetPontosDirecaoFuncaoGratificada() {
		Produto p1 =  XmlAtividades.getProduto("1", "DirecaoFuncaoGratificada");
		producao.addProduto(p1);
		
		Produto p2 =  XmlAtividades.getProduto("11", "DirecaoFuncaoGratificada");
		producao.addProduto(p2);
		
		assertEquals(20, producao.getPontos());
	}
	
	@Test
	public void testGetPontosAtividadeAdministrativa() {
		Produto p1 =  XmlAtividades.getProduto("1", "AtividadeAdministrativa");
		producao.addProduto(p1);
		
		Produto p2 =  XmlAtividades.getProduto("5", "AtividadeAdministrativa");
		producao.addProduto(p2);
		
		assertEquals(15, producao.getPontos());
	}
	
	@Test
	public void testGetPontosAtividadeAdministrativaOutra() {
		Produto p1 =  XmlAtividades.getProduto("1", "AtividadeAdministrativaOutra");
		producao.addProduto(p1);
		
		Produto p2 =  XmlAtividades.getProduto("14", "AtividadeAdministrativaOutra");
		producao.addProduto(p2);
		
		assertEquals(10, producao.getPontos());
	}
	
	@Test
	public void testGetPontosAtividadeRepresentacaoFora() {
		Produto p1 =  XmlAtividades.getProduto("1", "AtividadeRepresentacaoFora");
		producao.addProduto(p1);
		
		Produto p2 =  XmlAtividades.getProduto("6", "AtividadeRepresentacaoFora");
		producao.addProduto(p2);
		
		assertEquals(20, producao.getPontos());
	}
	
	@Test
	public void testGetPontosOrientacao() {
		Produto p1 =  XmlAtividades.getProduto("1", "Orientacao");
		producao.addProduto(p1);
		
		Produto p2 =  XmlAtividades.getProduto("22", "Orientacao");
		producao.addProduto(p2);
		
		assertEquals(28, producao.getPontos());
	}
	
	@Test
	public void testGetPontosBancasCursos() {
		Produto p1 =  XmlAtividades.getProduto("1.1", "BancaCurso");
		producao.addProduto(p1);
		
		Produto p2 =  XmlAtividades.getProduto("9", "BancaCurso");
		producao.addProduto(p2);
		
		assertEquals(6, producao.getPontos());
	}
	
	@Test
	public void testGetPontosAtividadeAprendizadoAperfeicoamento() {
		Produto p1 =  XmlAtividades.getProduto("1", "AtividadeAprendizadoAperfeicoamento");
		producao.addProduto(p1);
		
		Produto p2 =  XmlAtividades.getProduto("6", "AtividadeAprendizadoAperfeicoamento");
		producao.addProduto(p2);
		
		assertEquals(13, producao.getPontos());
	}
	
}
