package br.ufg.avadoc.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import br.ufg.inf.avadoc.entity.AtividadeAdministrativaRepresentacao;
import br.ufg.inf.avadoc.entity.AtividadeEnsino;
import br.ufg.inf.avadoc.entity.AtividadeOutra;
import br.ufg.inf.avadoc.entity.AtividadePesquisaExtensao;
import br.ufg.inf.avadoc.entity.Docente;
import br.ufg.inf.avadoc.entity.ExtratoAtividades;
import br.ufg.inf.avadoc.entity.ProducaoIntelectual;

public class ExtratoAtividadesTest {
	private static final double DELTA = 1e-15;
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

		assertEquals(0, atividadeEnsino.getPontosGraduacao(), DELTA);
		assertEquals(0, atividadeEnsino.getPontosPosGraduacao(), DELTA);
		assertEquals(0, atividadeEnsino.getPontos(), DELTA);
	}

	@Test
	public void testPontuacaoProducaoIntelectual() {
		ProducaoIntelectual producaoIntelectual = extrato
				.getProducaoIntelectual();

		assertNotNull(extrato);

		assertEquals(0,
				producaoIntelectual.getProducaoCientifica().getPontos(), DELTA);
		assertEquals(0, producaoIntelectual.getProducaoArtisticaCultural()
				.getPontos(), DELTA);
		assertEquals(0, producaoIntelectual.getProducaoTecnicaTecnologica()
				.getPontos(), DELTA);
		assertEquals(0, producaoIntelectual.getProducaoOutra().getPontos(),
				DELTA);
		assertEquals(0, producaoIntelectual.getPontos(), DELTA);
	}

	@Test
	public void testPontuacaoPesquisaExtensao() {
		AtividadePesquisaExtensao atividadePesquisaExtensao = extrato
				.getAtividadePesquisaExtensao();

		assertNotNull(extrato);

		assertEquals(0, atividadePesquisaExtensao.getPesquisa().getPontos(),
				DELTA);
		assertEquals(0, atividadePesquisaExtensao.getExtensao().getPontos(),
				DELTA);
		assertEquals(0, atividadePesquisaExtensao.getPontos(), DELTA);
	}

	@Test
	public void testPontuacaoAdministrativaRepresentacao() {
		AtividadeAdministrativaRepresentacao atividadeAdministrativaRepresentacao = extrato
				.getAtividadeAdministrativaRepresentacao();

		assertNotNull(extrato);

		assertEquals(0, atividadeAdministrativaRepresentacao
				.getDirecaoFuncaoGratificada().getPontos(), DELTA);
		assertEquals(0, atividadeAdministrativaRepresentacao
				.getAdministrativa().getPontos(), DELTA);
		assertEquals(0, atividadeAdministrativaRepresentacao
				.getOutraAdministrativa().getPontos(), DELTA);
		assertEquals(0, atividadeAdministrativaRepresentacao
				.getRepresentacaoFora().getPontos(), DELTA);
		assertEquals(0, atividadeAdministrativaRepresentacao.getPontos(), DELTA);
	}

	@Test
	public void testPontuacaoOutra() {
		AtividadeOutra atividadeOutra = extrato.getAtividadeOutra();

		assertNotNull(extrato);

		assertEquals(0, atividadeOutra.getOrientacao().getPontos(), DELTA);
		assertEquals(0, atividadeOutra.getBancaCurso().getPontos(), DELTA);
		assertEquals(0, atividadeOutra.getAprendizadoAperfeicoamento()
				.getPontos(), DELTA);
		assertEquals(0, atividadeOutra.getPontos(), DELTA);
	}

	@Test
	public void testPontuacaoTotal() {
		assertNotNull(extrato);
		assertEquals(0, extrato.getPontos(), DELTA);
	}
}
