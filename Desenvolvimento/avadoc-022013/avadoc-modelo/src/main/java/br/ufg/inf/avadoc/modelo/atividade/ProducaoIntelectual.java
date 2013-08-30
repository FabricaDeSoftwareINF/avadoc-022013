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
 * ProducaoIntelectual
 * 
 * Atividades e artefatos referentes a Produção Intelectual
 * 
 */
@Entity
public class ProducaoIntelectual extends AbstractEntity implements IAtividade,
		Serializable {
	private static final long serialVersionUID = -8781139957824478900L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_producao_intelectual")
	private Long id;
	/**
	 * Produçao Científica
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Producao producaoCientifica;
	/**
	 * Produção Artística e Cultural
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Producao producaoArtisticaCultural;
	/**
	 * Produção Técnica e Tecnológica
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Producao producaoTecnicaTecnologica;
	/**
	 * Outros tipos de produção
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Producao producaoOutra;
	
	public ProducaoIntelectual(){
		producaoCientifica = new Producao();
		producaoArtisticaCultural = new Producao();
		producaoTecnicaTecnologica = new Producao();
		producaoOutra = new Producao();
	}

	@Override
	public int getPontos() {
		return producaoCientifica.getPontos()
				+ producaoArtisticaCultural.getPontos()
				+ producaoTecnicaTecnologica.getPontos()
				+ producaoOutra.getPontos();
	}

	/**
	 * Sub-atividade referente a produção científica.
	 * 
	 * @return producaoCientifica
	 */
	public Producao getProducaoCientifica() {
		return producaoCientifica;
	}

	/**
	 * Altera Sub-atividade referente a produção científica.
	 * 
	 * @param producaoCientifica
	 */
	public void setProducaoCientifica(Producao producaoCientifica) {
		producaoCientifica
				.setTipoAtividade(EnumTipoAtividade.PRODUCAO_INTELECTUAL);
		producaoCientifica
				.setTipoProducao(EnumTipoProducao.PRODUCAO_CIENTIFICA);
		this.producaoCientifica = producaoCientifica;
	}

	/**
	 * Sub-atividade referente a produção artística e cultural.
	 * 
	 * @return producaoArtisticaCultural
	 */
	public Producao getProducaoArtisticaCultural() {
		return producaoArtisticaCultural;
	}

	/**
	 * Altera Sub-atividade referente a produção artística e cultural.
	 * 
	 * @param producaoArtisticaCultural
	 */
	public void setProducaoArtisticaCultural(Producao producaoArtisticaCultural) {
		producaoArtisticaCultural
				.setTipoAtividade(EnumTipoAtividade.PRODUCAO_INTELECTUAL);
		producaoArtisticaCultural
				.setTipoProducao(EnumTipoProducao.PRODUCAO_ARTISTICA_CULTURAL);
		this.producaoArtisticaCultural = producaoArtisticaCultural;
	}

	/**
	 * Sub-atividade referente a produção técnica e tecnológica.
	 * 
	 * @return producaoTecnicaTecnologica
	 */
	public Producao getProducaoTecnicaTecnologica() {
		return producaoTecnicaTecnologica;
	}

	/**
	 * Altera Sub-atividade referente a produção técnica e tecnológica.
	 * 
	 * @param producaoTecnicaTecnologica
	 */
	public void setProducaoTecnicaTecnologica(
			Producao producaoTecnicaTecnologica) {
		producaoTecnicaTecnologica
				.setTipoAtividade(EnumTipoAtividade.PRODUCAO_INTELECTUAL);
		producaoTecnicaTecnologica
				.setTipoProducao(EnumTipoProducao.PRODUCAO_TECNICA_TECNOLOGICA);
		this.producaoTecnicaTecnologica = producaoTecnicaTecnologica;
	}

	/**
	 * Sub-atividade referente a outros tipos de produção.
	 * 
	 * @return producaoOutra
	 */
	public Producao getProducaoOutra() {
		return producaoOutra;
	}

	/**
	 * Altera Sub-atividade referente a outros tipos de produção.
	 * 
	 * @param producaoOutra
	 */
	public void setProducaoOutra(Producao producaoOutra) {
		producaoOutra.setTipoAtividade(EnumTipoAtividade.PRODUCAO_INTELECTUAL);
		producaoOutra.setTipoProducao(EnumTipoProducao.PRODUCAO_OUTRO);
		this.producaoOutra = producaoOutra;
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
