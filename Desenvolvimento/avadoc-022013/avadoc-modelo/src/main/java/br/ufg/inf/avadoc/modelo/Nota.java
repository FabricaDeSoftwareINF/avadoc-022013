package br.ufg.inf.avadoc.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Nota extends AbstractEntity{
	/**
	 * Id da nota
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_nota")
	private Long id;

	private int quantidadeMeses;
	private double nota;

	public double getNota() {
		return nota;
	}

	public int getQuantidadeMeses() {
		return quantidadeMeses;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public void setQuantidadeMeses(int quantidadeMeses) {
		this.quantidadeMeses = quantidadeMeses;
	}

	/**
	 * Id da nota
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Altera id da nota
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

}
