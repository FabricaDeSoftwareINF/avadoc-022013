package br.ufg.inf.avadoc.model.helper;

import br.ufg.inf.avadoc.entity.AtividadeAdministrativaRepresentacao;
import br.ufg.inf.avadoc.entity.AtividadeEnsino;
import br.ufg.inf.avadoc.entity.AtividadeOutra;
import br.ufg.inf.avadoc.entity.AtividadePesquisaExtensao;
import br.ufg.inf.avadoc.entity.ProducaoIntelectual;
import br.ufg.inf.avadoc.model.ProdutoPontosAnuais;
import br.ufg.inf.avadoc.model.ProdutoPontosHora;
import br.ufg.inf.avadoc.model.ProdutoPontosMensais;

/**
 * Calcula os pontos de cada atividade ou produ&ccedil;&atilde;o.
 * 
 * @author Ciro Anacleto
 * */
public class ContabilizaPontos {

	private AtividadeAdministrativaRepresentacao atividadeAdmRepresent;
	private AtividadeEnsino atividadeEnsino;
	private ProducaoIntelectual producaoIntelectual;
	private AtividadeOutra atividadeOutra;
	private AtividadePesquisaExtensao atividadePesquisaExtensao;
	private ProdutoPontosAnuais produtoPontosAnuais;
	private ProdutoPontosMensais produtoPontosMensais;
	private ProdutoPontosHora produtoPontosHora;

	public ContabilizaPontos() {
		this.atividadeAdmRepresent = new AtividadeAdministrativaRepresentacao();
		this.atividadeEnsino = new AtividadeEnsino();
		this.producaoIntelectual = new ProducaoIntelectual();
		this.atividadeOutra = new AtividadeOutra();
		this.atividadePesquisaExtensao = new AtividadePesquisaExtensao();
		this.produtoPontosAnuais = new ProdutoPontosAnuais();
		this.produtoPontosMensais = new ProdutoPontosMensais();
		this.produtoPontosHora = new ProdutoPontosHora();

	}

	/**
	 * Calcula a pontua&ccedil;&atilde;o de uma atividade adminstrativa de
	 * representa&ccedil;&atilde;o
	 * 
	 * @return resultado do somat&oacute;rio de cada atividade de
	 *         representa&ccedil;&atilde;o
	 * */
	public double calculaPontosAtividadeAdminstrativaRepresentacao() {
		double resultado = atividadeAdmRepresent.getDirecaoFuncaoGratificada()
				.getPontos()
				+ atividadeAdmRepresent.getAdministrativa().getPontos()
				+ atividadeAdmRepresent.getOutraAdministrativa().getPontos()
				+ atividadeAdmRepresent.getRepresentacaoFora().getPontos();
		return resultado;
	}

	/**
	 * Calcula a pontua&ccedil;&atilde;o de uma atividade de ensino;
	 * 
	 * @return resultado do somat&oacute;rio de cada hora aula presencial e 
	 * a dist&acirc;ncia de gradua&ccedil;&atilde;o e p&oacute;s 
	 * gradua&ccedil;&atilde;o multiplicado por 10.
	 * */
	public double calculaPontosAtividadeEnsino() {
		double resultado = 
				atividadeEnsino.getGraduacaoHorasAulaSemanaisDistancia()
				+ atividadeEnsino.getGraduacaoHorasAulaSemanaisPresenciais()
				+ atividadeEnsino.getPosGradHorasAulaSemanaisDistancia()
				+ atividadeEnsino.getPosGradHorasAulaSemanaisPresenciais();
		return resultado * 10;
	}
	
	/**
	 * Calcula a pontua&ccedil;&atilde;o de uma atividade de ensino de gradua&ccedil;&atilde;o;
	 * 
	 * @return resultado do somat&oacute;rio de cada hora aula presencial e 
	 * a dist&acirc;ncia de gradua&ccedil;&atilde;o multiplicado por 10.
	 * */
	public double calculaPontosAtividadeGraduacao(){
		double resultado=
				atividadeEnsino.getGraduacaoHorasAulaSemanaisPresenciais()
				+ atividadeEnsino.getGraduacaoHorasAulaSemanaisDistancia();
		return resultado * 10;
	}
	/**
	 * Calcula a pontua&ccedil;&atilde;o de uma atividade de ensino de 
	 * p&oacute;s gradua&ccedil;&atilde;o;
	 * 
	 * @return resultado do somat&oacute;rio de cada hora aula presencial e 
	 * a dist&acirc;ncia de p&oacute;s gradua&ccedil;&atilde;o; multiplicado por 10.
	 * */
	public double calculaPontosAtividadePosGraduacao(){
		double resultado =
				atividadeEnsino.getPosGradHorasAulaSemanaisPresenciais()
				+atividadeEnsino.getPosGradHorasAulaSemanaisDistancia();
		return resultado *10;
	}
	
	/**
	 * Calcula a pontua&ccedil;&atilde;o de uma produ&ccedil;&atilde;o intelectual
	 * 
	 * @return resultado do somat&oacute;rio de cada artefato de produ&ccedil;&atilde;o
	 * intelectual do docente.
	 * */
	public double calculaPontosProducaoIntelectual(){
		double resultado = 
				producaoIntelectual.getProducaoCientifica().getPontos()
				+ producaoIntelectual.getProducaoArtisticaCultural().getPontos()
				+ producaoIntelectual.getProducaoTecnicaTecnologica().getPontos()
				+ producaoIntelectual.getProducaoOutra().getPontos();
		return resultado;
	}
	/**
	 * Calcula a pontua&ccedil;&atilde;o de outras atividades desempenhadas pelo
	 * docente.
	 * 
	 * @return resultado do somat&oacute;rio de cada ponto de outros tipos de 
	 * atividades desempenhadas pelo docente.
	 * */
	public double calculaPontosAtividadeOutra (){
		double resultado = 
				atividadeOutra.getBancaCurso().getPontos()
				+ atividadeOutra.getOrientacao().getPontos()
				+ atividadeOutra.getAprendizadoAperfeicoamento().getPontos();
		return resultado;
	}
	/**
	 * Calcula a pontua&ccedil;&atilde;o de atividades de pesquisa e 
	 * exten&ccedil;&atilde;o executados pelo docente.
	 * 
	 * @return resultado do somat&oacute;rio de cada ponto de pesquisa e 
	 * exten&ccedil;&atilde;o
	 * */
	public double calculaPontosAtividadePesquisaExtensao(){
		double resultado = 
				atividadePesquisaExtensao.getPesquisa().getPontos()
				+ atividadePesquisaExtensao.getExtensao().getPontos();
		return resultado;
	}
	/**
	 * Calcula a pontua&ccedil;&atilde;o de produtos produzidos anualmente.
	 * 
	 * @return resultado do multiplica&ccedil;&atilde;o produto pelos anos.
	 * */
	public double calculaPontosProdutosAnuais(){
		double resultado = 
				produtoPontosAnuais.getAnos() * produtoPontosAnuais.getPontos();
		return resultado;
	}
	/**
	 * Calcula a pontua&ccedil;&atilde;o de produtos produzidos mensalmente.
	 * 
	 * @return resultado do multiplica&ccedil;&atilde;o produto pelos meses.
	 * */
	public double calculaPontosProdutosMensais(){
		double resultado = 
				produtoPontosMensais.getMeses() * produtoPontosMensais.getPontos();
		return resultado;
	}
	/**
	 * Calcula a pontua&ccedil;&atilde;o de produtos produzidos por hora.
	 * 
	 * @return resultado do multiplica&ccedil;&atilde;o produto pelas horas.
	 * */
	public double calculaPontosProdutosHora(){
		double resultado = 
				produtoPontosHora.getHoras() * produtoPontosHora.getPontos();
		return resultado/ProdutoPontosHora.getHorasAnuais();
	}
	

}
