package br.ufg.inf.avadoc.modelo;

import java.io.Serializable;

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
	 * Construtor
	 */
	public Docente() {
		matricula = "";
		nome = "";
	}

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

}
