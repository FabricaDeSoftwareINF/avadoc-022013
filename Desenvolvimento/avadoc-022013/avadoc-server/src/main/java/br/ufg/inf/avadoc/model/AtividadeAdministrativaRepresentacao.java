package br.ufg.inf.avadoc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.ufg.inf.avadoc.entity.AbstractAtividade;
import br.ufg.inf.avadoc.entity.Producao;
import br.ufg.inf.avadoc.model.enums.EnumTipoAtividade;
import br.ufg.inf.avadoc.model.enums.EnumTipoProducao;

/**
 * AtividadeAdministrativaRepresentacao
 * 
 * Atividades e artefatos referentes a atividades administrativas e de
 * representação
 * 
 */
@Entity
public class AtividadeAdministrativaRepresentacao extends AbstractAtividade
		implements Serializable {

	private static final long serialVersionUID = -7989496005881283847L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_atividade_admin_represent")
	private Long id;
	/**
	 * Atividades de dire&ccedil;&atilde;o ou de fun&ccedil;&atilde;o
	 * gratificada
	 */
	private Producao direcaoFuncaoGratificada;
	/**
	 * Atividades administrativas
	 */
	private Producao administrativa;
	/**
	 * Outras atividades administrativas
	 */
	private Producao outraAdministrativa;
	/**
	 * Atividades de representa&ccedil;&atilde;o fora da UFG
	 */
	private Producao representacaoFora;

	public AtividadeAdministrativaRepresentacao() {
		direcaoFuncaoGratificada = new Producao();
		administrativa = new Producao();
		outraAdministrativa = new Producao();
		representacaoFora = new Producao();
	}

	@Override
	public double getPontos() {
		return direcaoFuncaoGratificada.getPontos()
				+ administrativa.getPontos() + outraAdministrativa.getPontos()
				+ representacaoFora.getPontos();
	}

	/**
	 * Sub-atividade referente a atividades de dire&ccedil;&atilde;o ou de
	 * fun&ccedil;&atilde;o gratificada
	 * 
	 * @return direcaoFuncaoGratificada
	 */
	public Producao getDirecaoFuncaoGratificada() {
		return direcaoFuncaoGratificada;
	}

	/**
	 * Altera Sub-atividade referente a atividades de dire&ccedil;&atilde;o ou
	 * de fun&ccedil;&atilde;o gratificada
	 * 
	 * @param direcaoFuncaoGratificada
	 */
	public void setDirecaoFuncaoGratificada(Producao direcaoFuncaoGratificada) {
		direcaoFuncaoGratificada
				.setTipoAtividade(EnumTipoAtividade.ATIVIDADE_ADMINISTRATIVA_REPRESENTACAO);
		direcaoFuncaoGratificada
				.setTipoProducao(EnumTipoProducao.DIRECAO_FUNCAO_GRATIFICADA);
		this.direcaoFuncaoGratificada = direcaoFuncaoGratificada;
	}

	/**
	 * Sub-atividade referente a atividades administrativas
	 * 
	 * @return administrativa
	 */
	public Producao getAdministrativa() {
		return administrativa;
	}

	/**
	 * Altera Sub-atividade referente a atividades administrativas
	 * 
	 * @param administrativa
	 */
	public void setAdministrativa(Producao administrativa) {
		administrativa
				.setTipoAtividade(EnumTipoAtividade.ATIVIDADE_ADMINISTRATIVA_REPRESENTACAO);
		administrativa
				.setTipoProducao(EnumTipoProducao.ATIVIDADE_ADMINISTRATIVA);
		this.administrativa = administrativa;
	}

	/**
	 * Sub-atividade referente a outros tipos de atividades administrativas ou
	 * de representação
	 * 
	 * @return outraAdministrativa
	 */
	public Producao getOutraAdministrativa() {
		return outraAdministrativa;
	}

	/**
	 * Altera Sub-atividade referente a outros tipos de atividades
	 * administrativas ou de representação
	 * 
	 * @param outraAdministrativa
	 */
	public void setOutraAdministrativa(Producao outraAdministrativa) {
		outraAdministrativa
				.setTipoAtividade(EnumTipoAtividade.ATIVIDADE_ADMINISTRATIVA_REPRESENTACAO);
		outraAdministrativa
				.setTipoProducao(EnumTipoProducao.ATIVIDADE_ADMINISTRATIVA_OUTRAS);
		this.outraAdministrativa = outraAdministrativa;
	}

	/**
	 * Sub-atividade referente a atividades de representação fora da UFG
	 * 
	 * @return representacaoFora
	 */
	public Producao getRepresentacaoFora() {
		return representacaoFora;
	}

	/**
	 * Altera Sub-atividade referente a atividades de representação fora da UFG
	 * 
	 * @param representacaoFora
	 */
	public void setRepresentacaoFora(Producao representacaoFora) {
		representacaoFora
				.setTipoAtividade(EnumTipoAtividade.ATIVIDADE_ADMINISTRATIVA_REPRESENTACAO);
		representacaoFora
				.setTipoProducao(EnumTipoProducao.ATIVIDADE_REPRESENTACAO);
		this.representacaoFora = representacaoFora;
	}

	/**
	 * Id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Altera id
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

}
