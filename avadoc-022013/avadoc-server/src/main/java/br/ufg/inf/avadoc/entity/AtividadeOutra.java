package br.ufg.inf.avadoc.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import br.ufg.inf.avadoc.model.enums.EnumTipoAtividade;
import br.ufg.inf.avadoc.model.enums.EnumTipoProducao;

/**
 * AtividadeOutra
 * 
 * Outras atividades
 * 
 */
@Entity
public class AtividadeOutra extends AbstractAtividade implements Serializable {
	private static final long serialVersionUID = 5832616413995668556L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_atividade_outra")	
	private Long id;
	/**
	 * Atividades Acadêmicas - Orientação
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Producao orientacao;
	/**
	 * Atividades Acadêmicas – Bancas e Cursos
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Producao bancaCurso;
	/**
	 * Atividades de Aprendizado e Aperfeiçoamento
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Producao aprendizadoAperfeicoamento;

	public AtividadeOutra() {
		orientacao = new Producao();
		bancaCurso = new Producao();
		aprendizadoAperfeicoamento = new Producao();
	}

	/**
	 * Sub-atividade referente a atividades de orientação.
	 * 
	 * @return orientacao
	 */
	public Producao getOrientacao() {
		return orientacao;
	}

	/**
	 * Altera Sub-atividade referente a atividades de orientação.
	 * 
	 * @param orientacao
	 */
	public void setOrientacao(Producao orientacao) {
		orientacao.setTipoAtividade(EnumTipoAtividade.ATIVIDADE_OUTRA);
		orientacao.setTipoProducao(EnumTipoProducao.ATIVIDADE_ACADEMICA_ORIENTACAO);
		super.setPontos(super.getPontos() + orientacao.getPontos());
		this.orientacao = orientacao;
	}

	/**
	 * Sub-atividade referente a atividades de bancas e cursos.
	 * 
	 * @return bancaCurso
	 */
	public Producao getBancaCurso() {
		return bancaCurso;
	}

	/**
	 * Altera Sub-atividade referente a atividades de bancas e cursos.
	 * 
	 * @param bancaCurso
	 */
	public void setBancaCurso(Producao bancaCurso) {
		bancaCurso.setTipoAtividade(EnumTipoAtividade.ATIVIDADE_OUTRA);
		bancaCurso.setTipoProducao(EnumTipoProducao.ATIVIDADE_ACADEMICA_BANCAS_CURSOS);
		super.setPontos(super.getPontos() + bancaCurso.getPontos());
		this.bancaCurso = bancaCurso;
	}

	/**
	 * Sub-atividade referente a atividades de aprendizado e aperfeiçoamento.
	 * 
	 * @return aprendizadoAperfeicoamento
	 */
	public Producao getAprendizadoAperfeicoamento() {
		return aprendizadoAperfeicoamento;
	}

	/**
	 * Altera Sub-atividade referente a atividades de aprendizado e
	 * aperfeiçoamento.
	 * 
	 * @param aprendizadoAperfeicoamento
	 */
	public void setAprendizadoAperfeicoamento(
			Producao aprendizadoAperfeicoamento) {
		aprendizadoAperfeicoamento
				.setTipoAtividade(EnumTipoAtividade.ATIVIDADE_OUTRA);
		aprendizadoAperfeicoamento
				.setTipoProducao(EnumTipoProducao.ATIVIDADE_APRENDIZADO_APERFEICOAMENTO);
		super.setPontos(super.getPontos() + aprendizadoAperfeicoamento.getPontos());
		this.aprendizadoAperfeicoamento = aprendizadoAperfeicoamento;
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
