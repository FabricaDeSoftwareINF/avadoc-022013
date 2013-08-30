package br.ufg.inf.avadoc;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufg.inf.avadoc.modelo.atividade.AtividadeEnsino;

public class AtividadeEnsinoTest {
	private AtividadeEnsino atividadeEnsino;
	@Before
	public void setUp() throws Exception {
		atividadeEnsino = new AtividadeEnsino();
	}

	@After
	public void tearDown() throws Exception {
		atividadeEnsino = null;
	}

	@Test
	public void testGetPontos() {
		atividadeEnsino.setGraduacaoHorasAulaSemanaisDistancia(16);
		atividadeEnsino.setGraduacaoHorasAulaSemanaisPresenciais(4);
		atividadeEnsino.setPosGradHorasAulaSemanaisDistancia(16);
		atividadeEnsino.setPosGradHorasAulaSemanaisPresenciais(4);
		
		assertEquals(400, atividadeEnsino.getPontos());
	}
	
}
