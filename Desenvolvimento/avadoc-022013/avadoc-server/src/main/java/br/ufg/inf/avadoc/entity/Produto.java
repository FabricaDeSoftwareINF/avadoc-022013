package br.ufg.inf.avadoc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * Produto
 * 
 * Item produzido pelo docente.
 * 
 */
@Entity
public class Produto extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 5401913319005557356L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_produto")
	private Long id;
	/**
	 * Código do artefato/atividade.
	 */
	@Column (name ="codigo", nullable = false)
	private String codigo;
	/**
	 * Pontos do produto.
	 */
	@Column (name ="pontos")
	private double pontos;
	/**
	 * Pontuação máxima do artefato/atividade
	 */
	@Column (name ="pontuacao_maxima")
	private int pontuacaoMaxima;

	/**
	 * Produção a qual o produto faz parte
	 */
	@ManyToOne
	@JoinColumn(name = "id_producao")
	private Producao producao;

	/**
	 * Retorna o código do artefato/atividade.
	 * 
	 * @return codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Altera o código do artefato/atividade.
	 * 
	 * @param codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Retorna pontos do produto.
	 * 
	 * @return pontos
	 */
	public double getPontos() {
		return pontos;
	}

	/**
	 * Altera pontos do produto.
	 * 
	 * @param pontos
	 */
	public void setPontos(double pontos) {
		this.pontos = pontos;
	}

	/**
	 * Retorna o máximo de pontos que o artefato/atividade pode ter.
	 * 
	 * @return
	 */
	public int getPontuacaoMaxima() {
		return pontuacaoMaxima;
	}

	/**
	 * Altera a pontuação máxima do artefato/atividade.
	 * 
	 * @param pontuacaoMaxima
	 */
	public void setPontuacaoMaxima(int pontuacaoMaxima) {
		this.pontuacaoMaxima = pontuacaoMaxima;
	}

	/**
	 * Retorna Produção relacionada ao produto
	 * 
	 * @return
	 */
	public Producao getProducao() {
		return producao;
	}

	/**
	 * Altera a produção que o produto faz parte
	 * 
	 * @param producao
	 */
	public void setProducao(Producao producao) {
		this.producao = producao;
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
