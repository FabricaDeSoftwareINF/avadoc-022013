package br.ufg.inf.avadoc.modelo.atividade;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import br.ufg.inf.avadoc.enums.EnumTipoAtividade;
import br.ufg.inf.avadoc.enums.EnumTipoProducao;
import br.ufg.inf.avadoc.modelo.AbstractEntity;

/**
 * AtividadePesquisaExtensao
 * 
 * Atividades e artefatos referentes a Pesquisa e Extensão
 * 
 */
@Entity
public class AtividadePesquisaExtensao extends AbstractEntity implements
		IAtividade, Serializable {
	private static final long serialVersionUID = -7628360259213604614L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_atividade_pesquisa_extensao")
	private Long id;

	/**
	 * Atividades de pesquisa
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Producao pesquisa;
	/**
	 * Atividades de Extensão
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Producao extensao;

	public AtividadePesquisaExtensao() {
		pesquisa = new Producao();
		extensao = new Producao();
	}

	@Override
	public int getPontos() {
		return pesquisa.getPontos() + extensao.getPontos();
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
