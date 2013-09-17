package br.ufg.inf.avadoc.model;

import java.io.Serializable;

import br.ufg.inf.avadoc.entity.AbstractAtividade;
import br.ufg.inf.avadoc.entity.Producao;
import br.ufg.inf.avadoc.model.enums.EnumTipoAtividade;
import br.ufg.inf.avadoc.model.enums.EnumTipoProducao;

/**
 * AtividadePesquisaExtensao
 * 
 * Atividades e artefatos referentes a Pesquisa e Extensão
 * 
 */
public class AtividadePesquisaExtensao extends AbstractAtividade implements
		Serializable {
	private static final long serialVersionUID = -7628360259213604614L;

	private Long id;

	/**
	 * Atividades de pesquisa
	 */
	private Producao pesquisa;
	/**
	 * Atividades de Extensão
	 */
	private Producao extensao;

	
	@Override
	public double getPontos() {
		return pontos;
	}

	public void setPontos(double pontos) {
		this.pontos = pontos;
	}

	/**
	 * Sub-atividade referente a atividades de pesquisa.
	 * 
	 * @return pesquisa
	 */
	public Producao getPesquisa() {
		return pesquisa;
	}

	/**
	 * Altera Sub-atividade referente a atividades de pesquisa.
	 * 
	 * @param pesquisa
	 */
	public void setPesquisa(Producao pesquisa) {
		pesquisa.setTipoAtividade(EnumTipoAtividade.ATIVIDADE_PESQUISA_EXTENSAO);
		pesquisa.setTipoProducao(EnumTipoProducao.ATIVIDADE_PESQUISA);
		this.pesquisa = pesquisa;
	}

	/**
	 * Sub-atividade referente a atividades de extensão.
	 * 
	 * @return extensao
	 */
	public Producao getExtensao() {
		return extensao;
	}

	/**
	 * Altera Sub-atividade referente a atividades de extensão.
	 * 
	 * @param extensao
	 */
	public void setExtensao(Producao extensao) {
		extensao.setTipoAtividade(EnumTipoAtividade.ATIVIDADE_PESQUISA_EXTENSAO);
		extensao.setTipoProducao(EnumTipoProducao.ATIVIDADE_EXTENSAO);
		this.extensao = extensao;
	}

	/**
	 * Id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Altera id
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

}
