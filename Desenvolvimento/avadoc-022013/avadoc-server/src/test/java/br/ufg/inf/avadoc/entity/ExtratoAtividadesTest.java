package br.ufg.inf.avadoc.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import br.ufg.inf.avadoc.model.AtividadeAdministrativaRepresentacao;
import br.ufg.inf.avadoc.model.AtividadeEnsino;
import br.ufg.inf.avadoc.model.AtividadeOutra;
import br.ufg.inf.avadoc.model.AtividadePesquisaExtensao;
import br.ufg.inf.avadoc.entity.ExtratoAtividades;
import br.ufg.inf.avadoc.model.ProducaoIntelectual;

public class ExtratoAtividadesTest {
	ExtratoAtividades extrato;

	@Before
	public void setUp() throws Exception {
		Docente docente = new Docente();
		docente.setMatricula("123");
		
		AtividadeEnsino ensino = new AtividadeEnsino();
		ProducaoIntelectual producaoIntelectual = new ProducaoIntelectual();
		AtividadePesquisaExtensao pesquisaExtensao = new AtividadePesquisaExtensao();
		AtividadeAdministrativaRepresentacao administrativaRepresentacao = new AtividadeAdministrativaRepresentacao();
		AtividadeOutra outra = new AtividadeOutra();
		
		extrato = new ExtratoAtividades();
		
		extrato.setDocente(docente);
		extrato.setDataInicial(new Date());
		extrato.setDataFinal(new Date());
		extrato.setAtividadeEnsino(ensino);
		extrato.setProducaoIntelectual(producaoIntelectual);
		extrato.setAtividadePesquisaExtensao(pesquisaExtensao);
		extrato.setAtividadeAdministrativaRepresentacao(administrativaRepresentacao);
		extrato.setAtividadeOutra(outra);
	}

	@Test
	public void testPontuacaoAtividadeEnsino() {
		AtividadeEnsino atividadeEnsino = extrato.getAtividadeEnsino();
		
		assertNotNull(extrato);
		
		assertEquals(0, atividadeEnsino.getPontosGraduacao());
		assertEquals(0, atividadeEnsino.getPontosPosGraduacao());
		assertEquals(0, atividadeEnsino.getPontos());
	}
	
	@Test
	public void testPontuacaoProducaoIntelectual() {
		ProducaoIntelectual producaoIntelectual = extrato
				.getProducaoIntelectual();
		
		assertNotNull(extrato);
		
		assertEquals(0, producaoIntelectual.getProducaoCientifica().getPontos());
		assertEquals(0, producaoIntelectual.getProducaoArtisticaCultural().getPontos());
		assertEquals(0, producaoIntelectual.getProducaoTecnicaTecnologica().getPontos());
		assertEquals(0, producaoIntelectual.getProducaoOutra().getPontos());
		assertEquals(0, producaoIntelectual.getPontos());
	}
	
	@Test
	public void testPontuacaoPesquisaExtensao() {
		AtividadePesquisaExtensao atividadePesquisaExtensao = extrato
				.getAtividadePesquisaExtensao();
		
		assertNotNull(extrato);
		
		assertEquals(0, atividadePesquisaExtensao.getPesquisa().getPontos());
		assertEquals(0, atividadePesquisaExtensao.getExtensao().getPontos());
		assertEquals(0, atividadePesquisaExtensao.getPontos());
	}
	
	@Test
	public void testPontuacaoAdministrativaRepresentacao() {
		AtividadeAdministrativaRepresentacao atividadeAdministrativaRepresentacao = extrato
				.getAtividadeAdministrativaRepresentacao();
		
		assertNotNull(extrato);
		
		assertEquals(0, atividadeAdministrativaRepresentacao.getDirecaoFuncaoGratificada().getPontos());
		assertEquals(0, atividadeAdministrativaRepresentacao.getAdministrativa().getPontos());
		assertEquals(0, atividadeAdministrativaRepresentacao.getOutraAdministrativa().getPontos());
		assertEquals(0, atividadeAdministrativaRepresentacao.getRepresentacaoFora().getPontos());
		assertEquals(0, atividadeAdministrativaRepresentacao.getPontos());
	}
	
	@Test
	public void testPontuacaoOutra() {
		AtividadeOutra atividadeOutra = extrato.getAtividadeOutra();
		
		assertNotNull(extrato);
		
		assertEquals(0, atividadeOutra.getOrientacao().getPontos());
		assertEquals(0, atividadeOutra.getBancaCurso().getPontos());
		assertEquals(0, atividadeOutra.getAprendizadoAperfeicoamento().getPontos());
		assertEquals(0, atividadeOutra.getPontos());
	}
	
	
	@Test
	public void testPontuacaoTotal() {
		assertNotNull(extrato);
		assertEquals(0, extrato.getPontos());
	}
}
