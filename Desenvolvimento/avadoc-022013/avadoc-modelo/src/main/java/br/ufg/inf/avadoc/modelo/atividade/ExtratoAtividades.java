/**
 * 
 */
package br.ufg.inf.avadoc.modelo.atividade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.ufg.inf.avadoc.modelo.AbstractEntity;
import br.ufg.inf.avadoc.modelo.Docente;

/**
 * ExtratoAtividades
 * 
 * Extrato com atividades do docente (Radoc)
 */
@Entity
public class ExtratoAtividades extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = -7740600208068664832L;

	/**
	 * Docente avaliado
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_extrato_atividades")
	private Long id;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_docente", nullable = false)
	private Docente docente;
	/**
	 * Data inicial das atividades
	 */
	@Temporal(TemporalType.DATE)
	private Date dataInicial;
	/**
	 * Data final das atividades
	 */
	@Temporal(TemporalType.DATE)
	private Date dataFinal;
	/**
	 * Atividades de ensino
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_atividade_ensino")
	private AtividadeEnsino atividadeEnsino;
	/**
	 * Atividades de produção intelectual
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_producao_intelectual")
	private ProducaoIntelectual producaoIntelectual;
	/**
	 * Atividades de pesquisa e extensão
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_atividade_pesquisa_extensao")
	private AtividadePesquisaExtensao atividadePesquisaExtensao;
	/**
	 * Atividades administrativas e de representação
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_atividade_administrativa_representacao")
	private AtividadeAdministrativaRepresentacao atividadeAdministrativaRepresentacao;
	/**
	 * Outras atividades
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_atividade_outra")
	private AtividadeOutra atividadeOutra;

	public ExtratoAtividades() {
	}

	/**
	 * Retorna Docente avaliado
	 * 
	 * @return docente
	 */
	public Docente getDocente() {
		return docente;
	}

	/**
	 * Altera docente avaliado
	 * 
	 * @param docente
	 */
	public void setDocente(Docente docente) {
		this.docente = docente;
	}

	/**
	 * Data inicial das atividades
	 * 
	 * @return dataInicial
	 */
	public Date getDataInicial() {
		return dataInicial;
	}

	/**
	 * Altera data inicial das atividades
	 * 
	 * @param dataInicial
	 */
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	/**
	 * Data final das atividades
	 * 
	 * @return dataFinal
	 */
	public Date getDataFinal() {
		return dataFinal;
	}

	/**
	 * Altera data final das atividades
	 * 
	 * @param dataFinal
	 */
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	/**
	 * Atividades de ensino
	 * 
	 * @return atividadeEnsino
	 */
	public AtividadeEnsino getAtividadeEnsino() {
		return atividadeEnsino;
	}

	/**
	 * Altera atividades de ensino
	 * 
	 * @param atividadeEnsino
	 */
	public void setAtividadeEnsino(AtividadeEnsino atividadeEnsino) {
		this.atividadeEnsino = atividadeEnsino;
	}

	/**
	 * Atividades de produção intelectual
	 * 
	 * @return producaoIntelectual
	 */
	public ProducaoIntelectual getProducaoIntelectual() {
		return producaoIntelectual;
	}

	/**
	 * Altera atividades de produção intelectual
	 * 
	 * @param producaoIntelectual
	 */
	public void setProducaoIntelectual(ProducaoIntelectual producaoIntelectual) {
		this.producaoIntelectual = producaoIntelectual;
	}

	/**
	 * Atividades de pesquisa e extensão
	 * 
	 * @return atividadePesquisaExtensao
	 */
	public AtividadePesquisaExtensao getAtividadePesquisaExtensao() {
		return atividadePesquisaExtensao;
	}

	/**
	 * Altera atividades de pesquisa e extensão
	 * 
	 * @param atividadePesquisaExtensao
	 */
	public void setAtividadePesquisaExtensao(
			AtividadePesquisaExtensao atividadePesquisaExtensao) {
		this.atividadePesquisaExtensao = atividadePesquisaExtensao;
	}

	/**
	 * Atividades administrativas e de representação
	 * 
	 * @return atividadeAdministrativaRepresentacao
	 */
	public AtividadeAdministrativaRepresentacao getAtividadeAdministrativaRepresentacao() {
		return atividadeAdministrativaRepresentacao;
	}

	/**
	 * Altera atividades administrativas e de representação
	 * 
	 * @param atividadeAdministrativaRepresentacao
	 */
	public void setAtividadeAdministrativaRepresentacao(
			AtividadeAdministrativaRepresentacao atividadeAdministrativaRepresentacao) {
		this.atividadeAdministrativaRepresentacao = atividadeAdministrativaRepresentacao;
	}

	/**
	 * Outras atividades
	 * 
	 * @return atividadeOutra
	 */
	public AtividadeOutra getAtividadeOutra() {
		return atividadeOutra;
	}

	/**
	 * Altera outras atividades
	 * 
	 * @param atividadeOutra
	 */
	public void setAtividadeOutra(AtividadeOutra atividadeOutra) {
		this.atividadeOutra = atividadeOutra;
	}

	/**
	 * Retorna quantidade total de pontos
	 * 
	 * @return pontos
	 */
	public int getPontos() {
		return atividadeEnsino.getPontos() + producaoIntelectual.getPontos()
				+ atividadePesquisaExtensao.getPontos()
				+ atividadeAdministrativaRepresentacao.getPontos()
				+ atividadeOutra.getPontos();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
