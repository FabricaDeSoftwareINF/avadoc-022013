package br.ufg.avadoc.entity;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufg.inf.avadoc.entity.AtividadeOutra;
import br.ufg.inf.avadoc.entity.Producao;
import br.ufg.inf.avadoc.entity.Produto;
import br.ufg.inf.avadoc.model.enums.EnumTipoAtividade;
import br.ufg.inf.avadoc.model.enums.EnumTipoProducao;

public class AtividadeOutraTest {
	private static final double DELTA = 1e-15;
	private AtividadeOutra atividadeOutra;
	@Before
	public void setUp() throws Exception {
		atividadeOutra = new AtividadeOutra();
	}

	@After
	public void tearDown() throws Exception {
		atividadeOutra = null;
	}

	@Test
	public void testGetPontos() {
		Producao orientacao = new Producao();
		Produto produto1 = new Produto();
		produto1.setPontos(8);
		orientacao.addProduto(produto1);
		
		Producao bancaCurso = new Producao();
		Produto produto2 = new Produto();
		produto2.setPontos(6);
		bancaCurso.addProduto(produto2);
		
		Producao aprendizadoAperfeicoamento = new Producao();
		Produto produto3 = new Produto();
		produto3.setPontos(3);
		aprendizadoAperfeicoamento.addProduto(produto3);
		
		orientacao.setTipoAtividade(EnumTipoAtividade.ATIVIDADE_OUTRA);
		orientacao.setTipoProducao(EnumTipoProducao.ATIVIDADE_ACADEMICA_ORIENTACAO);
		
		bancaCurso.setTipoAtividade(EnumTipoAtividade.ATIVIDADE_OUTRA);
		bancaCurso.setTipoProducao(EnumTipoProducao.ATIVIDADE_ACADEMICA_BANCAS_CURSOS);
		
		aprendizadoAperfeicoamento.setTipoAtividade(EnumTipoAtividade.ATIVIDADE_OUTRA);
		aprendizadoAperfeicoamento.setTipoProducao(EnumTipoProducao.ATIVIDADE_APRENDIZADO_APERFEICOAMENTO);
		
		atividadeOutra.setOrientacao(orientacao);
		atividadeOutra.setBancaCurso(bancaCurso);
		atividadeOutra.setAprendizadoAperfeicoamento(aprendizadoAperfeicoamento);
		
		assertEquals(EnumTipoAtividade.ATIVIDADE_OUTRA, atividadeOutra.getOrientacao().getTipoAtividade());
		assertEquals(EnumTipoAtividade.ATIVIDADE_OUTRA, atividadeOutra.getBancaCurso().getTipoAtividade());
		assertEquals(EnumTipoAtividade.ATIVIDADE_OUTRA, atividadeOutra.getAprendizadoAperfeicoamento().getTipoAtividade());
		
		assertEquals(EnumTipoProducao.ATIVIDADE_ACADEMICA_ORIENTACAO, atividadeOutra.getOrientacao().getTipoProducao());
		assertEquals(EnumTipoProducao.ATIVIDADE_ACADEMICA_BANCAS_CURSOS, atividadeOutra.getBancaCurso().getTipoProducao());
		assertEquals(EnumTipoProducao.ATIVIDADE_APRENDIZADO_APERFEICOAMENTO, atividadeOutra.getAprendizadoAperfeicoamento().getTipoProducao());
		
		assertEquals(8, atividadeOutra.getOrientacao().getPontos(), DELTA);
		assertEquals(6, atividadeOutra.getBancaCurso().getPontos(), DELTA);
		assertEquals(3, atividadeOutra.getAprendizadoAperfeicoamento().getPontos(), DELTA);
		
		assertEquals(17, atividadeOutra.getPontos(), DELTA);
	}
	
}
