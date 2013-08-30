package br.ufg.inf.avadoc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufg.inf.avadoc.modelo.atividade.AtividadeAdministrativaRepresentacao;
import br.ufg.inf.avadoc.modelo.atividade.AtividadeEnsino;
import br.ufg.inf.avadoc.modelo.atividade.AtividadeOutra;
import br.ufg.inf.avadoc.modelo.atividade.AtividadePesquisaExtensao;
import br.ufg.inf.avadoc.modelo.atividade.ExtratoAtividades;
import br.ufg.inf.avadoc.modelo.atividade.ProducaoIntelectual;
import br.ufg.inf.avadoc.ods.OdsGerador;
import br.ufg.inf.avadoc.xml.XmlExtratoAtividades;

public class ExtratoAtividadesTest {
	ExtratoAtividades extrato;

	@Before
	public void setUp() throws Exception {
		String xml = null;

		try {
			InputStream inputStream = ClassLoader
					.getSystemResourceAsStream("exemploExtratoXML.xml");
			xml = IOUtils.toString(inputStream);
			// System.out.println(xml);
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		extrato = XmlExtratoAtividades.getExtrato(xml);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPontuacao() {
		AtividadeEnsino atividadeEnsino = extrato.getAtividadeEnsino();
		ProducaoIntelectual producaoIntelectual = extrato
				.getProducaoIntelectual();
		AtividadePesquisaExtensao atividadePesquisaExtensao = extrato
				.getAtividadePesquisaExtensao();
		AtividadeAdministrativaRepresentacao atividadeAdministrativaRepresentacao = extrato
				.getAtividadeAdministrativaRepresentacao();
		AtividadeOutra atividadeOutra = extrato.getAtividadeOutra();
		
		assertNotNull(extrato);
		
		assertEquals(200, atividadeEnsino.getPontosGraduacao());
		assertEquals(200, atividadeEnsino.getPontosPosGraduacao());
		assertEquals(400, atividadeEnsino.getPontos());
		
		assertEquals(66, producaoIntelectual.getProducaoCientifica().getPontos());
		assertEquals(38, producaoIntelectual.getProducaoArtisticaCultural().getPontos());
		assertEquals(54, producaoIntelectual.getProducaoTecnicaTecnologica().getPontos());
		assertEquals(9, producaoIntelectual.getProducaoOutra().getPontos());
		assertEquals(167, producaoIntelectual.getPontos());
		
		assertEquals(30, atividadePesquisaExtensao.getPesquisa().getPontos());
		assertEquals(25, atividadePesquisaExtensao.getExtensao().getPontos());
		assertEquals(55, atividadePesquisaExtensao.getPontos());
		
		assertEquals(120, atividadeAdministrativaRepresentacao.getDirecaoFuncaoGratificada().getPontos());
		assertEquals(14, atividadeAdministrativaRepresentacao.getAdministrativa().getPontos());
		assertEquals(71, atividadeAdministrativaRepresentacao.getOutraAdministrativa().getPontos());
		assertEquals(18, atividadeAdministrativaRepresentacao.getRepresentacaoFora().getPontos());
		assertEquals(223, atividadeAdministrativaRepresentacao.getPontos());
		
		assertEquals(28, atividadeOutra.getOrientacao().getPontos());
		assertEquals(6, atividadeOutra.getBancaCurso().getPontos());
		assertEquals(13, atividadeOutra.getAprendizadoAperfeicoamento().getPontos());
		assertEquals(47, atividadeOutra.getPontos());
		
		assertEquals(892, extrato.getPontos());
		
		imprimeAtividades(extrato);
	}
	
	@Test
	public void criarOds(){
		/*try {
			File file = OdsGerador.gerarPontuacaoOds(extrato).saveAs(new File("sumarioTest"));
			OOUtils.open(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		int result = Integer.parseInt(OdsGerador.gerarPontuacaoOds(extrato).getSheet(0).getCellAt("C37").getValue().toString());
		assertEquals(892, result);	}
	
	public void imprimeAtividades(ExtratoAtividades extrato){
		AtividadeEnsino atividadeEnsino = extrato.getAtividadeEnsino();
		ProducaoIntelectual producaoIntelectual = extrato
				.getProducaoIntelectual();
		AtividadePesquisaExtensao atividadePesquisaExtensao = extrato
				.getAtividadePesquisaExtensao();
		AtividadeAdministrativaRepresentacao atividadeAdministrativaRepresentacao = extrato
				.getAtividadeAdministrativaRepresentacao();
		AtividadeOutra atividadeOutra = extrato.getAtividadeOutra();
		
		System.out.println("Docente: " + extrato.getDocente().getMatricula());
		DateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("Período: " + sourceFormat.format(extrato.getDataInicial()) + " a " + sourceFormat.format(extrato.getDataFinal()));
		System.out.println();
		
		System.out.println("I - Ensino");
		System.out.println("Graduação: \t" + atividadeEnsino.getPontosGraduacao());
		System.out.println("Pós-Graduação: \t" + atividadeEnsino.getPontosPosGraduacao());
		System.out.println("Total: \t\t" + atividadeEnsino.getPontos());
		System.out.println("---\n");
		
		System.out.println("II - Produção Intelectual");
		System.out.println("Científica: \t\t" + producaoIntelectual.getProducaoCientifica().getPontos());
		System.out.println("Artística Cultural: \t" + producaoIntelectual.getProducaoArtisticaCultural().getPontos());
		System.out.println("Técnica Tecnológica: \t" + producaoIntelectual.getProducaoTecnicaTecnologica().getPontos());
		System.out.println("Outra: \t\t\t" + producaoIntelectual.getProducaoOutra().getPontos());
		System.out.println("Total: \t\t\t" + producaoIntelectual.getPontos());
		System.out.println("---\n");
		
		System.out.println("III - Pesquisa Extensão");
		System.out.println("Pesquisa: " + atividadePesquisaExtensao.getPesquisa().getPontos());
		System.out.println("Extensão: " + atividadePesquisaExtensao.getExtensao().getPontos());
		System.out.println("Total:\t  " + atividadePesquisaExtensao.getPontos());
		System.out.println("---\n");
		
		System.out.println("IV - Administrativa Representação");
		System.out.println("Direção Função Gratificada: " + atividadeAdministrativaRepresentacao.getDirecaoFuncaoGratificada().getPontos());
		System.out.println("Administrativa:\t\t    " + atividadeAdministrativaRepresentacao.getAdministrativa().getPontos());
		System.out.println("Outra Administrativa:\t    " + atividadeAdministrativaRepresentacao.getOutraAdministrativa().getPontos());
		System.out.println("Representação Fora:\t    " + atividadeAdministrativaRepresentacao.getRepresentacaoFora().getPontos());
		System.out.println("Total:\t\t\t    " + atividadeAdministrativaRepresentacao.getPontos());
		System.out.println("---\n");

		System.out.println("V - Outra");
		System.out.println("Orientação:\t\t     " + atividadeOutra.getOrientacao().getPontos());
		System.out.println("Banca Curso:\t\t     " + atividadeOutra.getBancaCurso().getPontos());
		System.out.println("Aprendizado Aperfeiçoamento: " + atividadeOutra.getAprendizadoAperfeicoamento().getPontos());
		System.out.println("Total:\t\t\t     " + atividadeOutra.getPontos());
		System.out.println("---\n");
		
		System.out.println("PONTUAÇÃO TOTAL ( I + II + III + IV + V ) = " + extrato.getPontos());
	}

}
