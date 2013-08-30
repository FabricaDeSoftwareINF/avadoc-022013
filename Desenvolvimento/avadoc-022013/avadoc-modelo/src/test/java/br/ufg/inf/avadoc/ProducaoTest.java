package br.ufg.inf.avadoc;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufg.inf.avadoc.modelo.atividade.Producao;
import br.ufg.inf.avadoc.modelo.atividade.Produto;

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
		Produto p1 = new Produto();
		p1.setPontos(20);
		producao.addProduto(p1);
		
		Produto p2 = new Produto();
		p2.setPontos(21);
		producao.addProduto(p2);
		
		assertEquals(41, producao.getPontos());
	}

	@Test
	public void testGetPontosProducaoArtisticaCultural() {
		Produto p1 = new Produto();
		p1.setPontos(20);
		producao.addProduto(p1);
		
		Produto p2 = new Produto();
		p2.setPontos(7);
		producao.addProduto(p2);
		
		assertEquals(27, producao.getPontos());
	}
	
	@Test
	public void testGetPontosProducaoTecnicaTecnologica() {
		Produto p1 = new Produto();
		p1.setPontos(10);
		producao.addProduto(p1);
		
		Produto p2 = new Produto();
		p2.setPontos(15);
		producao.addProduto(p2);
		
		assertEquals(25, producao.getPontos());
	}
	
	@Test
	public void testGetPontosProducaoOutra() {
		Produto p1 = new Produto();
		p1.setPontos(4);
		producao.addProduto(p1);
		
		Produto p2 = new Produto();
		p2.setPontos(5);
		producao.addProduto(p2);
		
		assertEquals(9, producao.getPontos());
	}
	
	@Test
	public void testGetPontosAtividadePesquisa() {
		Produto p1 = new Produto();
		p1.setPontos(10);
		producao.addProduto(p1);
		
		Produto p2 = new Produto();
		p2.setPontos(5);
		producao.addProduto(p2);
		
		assertEquals(15, producao.getPontos());
	}

	@Test
	public void testGetPontosAtividadeExtensao() {
		Produto p1 = new Produto();
		p1.setPontos(9);
		producao.addProduto(p1);
		
		Produto p2 = new Produto();
		p2.setPontos(6);
		producao.addProduto(p2);
		
		assertEquals(15, producao.getPontos());
	}
	
	@Test
	public void testGetPontosDirecaoFuncaoGratificada() {
		Produto p1 = new Produto();
		p1.setPontos(17);
		producao.addProduto(p1);
		
		Produto p2 = new Produto();
		p2.setPontos(3);
		producao.addProduto(p2);
		
		assertEquals(20, producao.getPontos());
	}
	
	@Test
	public void testGetPontosAtividadeAdministrativa() {
		Produto p1 = new Produto();
		p1.setPontos(8);
		producao.addProduto(p1);
		
		Produto p2 = new Produto();
		p2.setPontos(7);
		producao.addProduto(p2);
		
		assertEquals(15, producao.getPontos());
	}
	
	@Test
	public void testGetPontosAtividadeAdministrativaOutra() {
		Produto p1 = new Produto();
		p1.setPontos(6);
		producao.addProduto(p1);
		
		Produto p2 = new Produto();
		p2.setPontos(4);
		producao.addProduto(p2);
		
		assertEquals(10, producao.getPontos());
	}
	
	@Test
	public void testGetPontosAtividadeRepresentacaoFora() {
		Produto p1 = new Produto();
		p1.setPontos(19);
		producao.addProduto(p1);
		
		Produto p2 = new Produto();
		p2.setPontos(1);
		producao.addProduto(p2);
		
		assertEquals(20, producao.getPontos());
	}
	
	@Test
	public void testGetPontosOrientacao() {
		Produto p1 = new Produto();
		p1.setPontos(20);
		producao.addProduto(p1);
		
		Produto p2 = new Produto();
		p2.setPontos(8);
		producao.addProduto(p2);
		
		assertEquals(28, producao.getPontos());
	}
	
	@Test
	public void testGetPontosBancasCursos() {
		Produto p1 = new Produto();
		p1.setPontos(3);
		producao.addProduto(p1);
		
		Produto p2 = new Produto();
		p2.setPontos(3);
		producao.addProduto(p2);
		
		assertEquals(6, producao.getPontos());
	}
	
	@Test
	public void testGetPontosAtividadeAprendizadoAperfeicoamento() {
		Produto p1 = new Produto();
		p1.setPontos(10);
		producao.addProduto(p1);
		
		Produto p2 = new Produto();
		p2.setPontos(3);
		producao.addProduto(p2);
		
		assertEquals(13, producao.getPontos());
	}
	
}
