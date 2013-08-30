package br.ufg.inf.avadoc;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufg.inf.avadoc.enums.EnumTipoAtividade;
import br.ufg.inf.avadoc.enums.EnumTipoProducao;
import br.ufg.inf.avadoc.modelo.atividade.AtividadeAdministrativaRepresentacao;
import br.ufg.inf.avadoc.modelo.atividade.Producao;
import br.ufg.inf.avadoc.modelo.atividade.Produto;

public class AtividadeAdministrativaRepresentacaoTest {
	private AtividadeAdministrativaRepresentacao atividadeAdministrativaRepresentacao;
	@Before
	public void setUp() throws Exception {
		atividadeAdministrativaRepresentacao = new AtividadeAdministrativaRepresentacao();
	}

	@After
	public void tearDown() throws Exception {
		atividadeAdministrativaRepresentacao = null;
	}

	@Test
	public void testGetPontos() {
		Producao direcaoFuncaoGratificada = new Producao();
		Produto produto1 = new Produto();
		produto1.setPontos(120);
		direcaoFuncaoGratificada.addProduto(produto1);
		
		Producao administrativa = new Producao();
		Produto produto2 = new Produto();
		produto2.setPontos(14);
		administrativa.addProduto(produto2);
		
		Producao outraAdministrativa = new Producao();
		Produto produto3 = new Produto();
		produto3.setPontos(71);
		outraAdministrativa.addProduto(produto3);
		
		Producao representacaoFora = new Producao();
		Produto produto4 = new Produto();
		produto4.setPontos(18);
		representacaoFora.addProduto(produto4);
		
		atividadeAdministrativaRepresentacao.setDirecaoFuncaoGratificada(direcaoFuncaoGratificada);
		atividadeAdministrativaRepresentacao.setAdministrativa(administrativa);
		atividadeAdministrativaRepresentacao.setOutraAdministrativa(outraAdministrativa);
		atividadeAdministrativaRepresentacao.setRepresentacaoFora(representacaoFora);
		
		assertEquals(EnumTipoAtividade.ATIVIDADE_ADMINISTRATIVA_REPRESENTACAO, atividadeAdministrativaRepresentacao.getDirecaoFuncaoGratificada().getTipoAtividade());
		assertEquals(EnumTipoAtividade.ATIVIDADE_ADMINISTRATIVA_REPRESENTACAO, atividadeAdministrativaRepresentacao.getAdministrativa().getTipoAtividade());
		assertEquals(EnumTipoAtividade.ATIVIDADE_ADMINISTRATIVA_REPRESENTACAO, atividadeAdministrativaRepresentacao.getOutraAdministrativa().getTipoAtividade());
		assertEquals(EnumTipoAtividade.ATIVIDADE_ADMINISTRATIVA_REPRESENTACAO, atividadeAdministrativaRepresentacao.getRepresentacaoFora().getTipoAtividade());
		
		assertEquals(EnumTipoProducao.DIRECAO_FUNCAO_GRATIFICADA, atividadeAdministrativaRepresentacao.getDirecaoFuncaoGratificada().getTipoProducao());
		assertEquals(EnumTipoProducao.ATIVIDADE_ADMINISTRATIVA, atividadeAdministrativaRepresentacao.getAdministrativa().getTipoProducao());
		assertEquals(EnumTipoProducao.ATIVIDADE_ADMINISTRATIVA_OUTRAS, atividadeAdministrativaRepresentacao.getOutraAdministrativa().getTipoProducao());
		assertEquals(EnumTipoProducao.ATIVIDADE_REPRESENTACAO, atividadeAdministrativaRepresentacao.getRepresentacaoFora().getTipoProducao());
		
		assertEquals(1, atividadeAdministrativaRepresentacao.getDirecaoFuncaoGratificada().getProdutos().size());
		assertEquals(1, atividadeAdministrativaRepresentacao.getAdministrativa().getProdutos().size());
		assertEquals(1, atividadeAdministrativaRepresentacao.getOutraAdministrativa().getProdutos().size());
		assertEquals(1, atividadeAdministrativaRepresentacao.getRepresentacaoFora().getProdutos().size());
		
		assertEquals(120, atividadeAdministrativaRepresentacao.getDirecaoFuncaoGratificada().getPontos());
		assertEquals(14, atividadeAdministrativaRepresentacao.getAdministrativa().getPontos());
		assertEquals(71, atividadeAdministrativaRepresentacao.getOutraAdministrativa().getPontos());
		assertEquals(18, atividadeAdministrativaRepresentacao.getRepresentacaoFora().getPontos());
		
		assertEquals(223, atividadeAdministrativaRepresentacao.getPontos());
	}
	
}
