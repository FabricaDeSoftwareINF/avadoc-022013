package br.ufg.inf.avadoc.entity;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Docente
 * 
 * Dados do docente
 * 
 */
@Entity
public class Docente extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1556372292607007986L;

	/**
	 * Id do docente
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_docente")
	private Long id;

	/**
	 * Matricula do docente avaliado
	 */
	@Column(unique=true)
	private String matricula;
	
	/**
	 * Nome do docente avaliado
	 */
	private String nome;
	
	/**
	 * Matrícula SIAP do docente.
	 */
	private String matriculaSIAP;
	
	/**
	 * Lotação do docente.
	 */
	private String lotacao;
	
	/**
	 * Data de ingresso do professor.
	 */
	private Calendar dataIngresso;
	
	/**
	 * Classe do professor.
	 */
	private String classe;
	
	/**
	 * Nível do professor.
	 */
	private String nivel;
	
	/**
	 * Retorna matrícula do docente
	 * 
	 * @return matricula
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * Altera matricula do docente
	 * 
	 * @param matricula
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	/**
	 * Retorna o nome do docente
	 * 
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Id do docente
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Altera id do docente
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Altera o nome do docente
	 * 
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Obtém a lotação do docente.
	 * @return Lotação do docente.
	 */
	public String getLotacao() {
		return lotacao;
	}

	/**
	 * Define a lotação do docente.
	 * @param lotacao Descrição/Código da lotação.
	 */
	public void setLotacao(String lotacao) {
		this.lotacao = lotacao;
	}

	/**
	 * Obtém a data de ingresso do docente.
	 * @return Data de ingresso do docente.
	 */
	public Calendar getDataIngresso() {
		return dataIngresso;
	}

	/**
	 * Define a data de ingresso do docente.
	 * @param dataIngresso Data de ingresso do docente.
	 */
	public void setDataIngresso(Calendar dataIngresso) {
		this.dataIngresso = dataIngresso;
	}

	/**
	 * Obtém a classe do docente.
	 * @return Classe do docente.
	 */
	public String getClasse() {
		return classe;
	}

	/**
	 * Define a classe do docente.
	 * @param classe Classe do docente.
	 */
	public void setClasse(String classe) {
		this.classe = classe;
	}

	/**
	 * Obtém o nível do docente.
	 * @return Nível do docente.
	 */
	public String getNivel() {
		return nivel;
	}

	/**
	 * Define o nível do docente.
	 * @param nivel Nível do docente.
	 */
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	/**
	 * Obtém a matrícula SIAP do docente.
	 * @return Matrícula SIAP do docente.
	 */
	public String getMatriculaSIAP() {
		return matriculaSIAP;
	}

	/**
	 * Define a matrícula SIAP do docente.
	 * @param matriculaSIAP Matrícula SIAP do docente.
	 */
	public void setMatriculaSIAP(String matriculaSIAP) {
		this.matriculaSIAP = matriculaSIAP;
	}
}
