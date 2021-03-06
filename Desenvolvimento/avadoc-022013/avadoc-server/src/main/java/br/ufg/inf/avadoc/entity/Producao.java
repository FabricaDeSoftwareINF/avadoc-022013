package br.ufg.inf.avadoc.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.ufg.inf.avadoc.model.enums.EnumTipoAtividade;
import br.ufg.inf.avadoc.model.enums.EnumTipoProducao;

/**
 * Producao
 * 
 * Representa o que foi produzido em uma subatividade.
 * 
 */
@Entity
public class Producao extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 3041614986590545497L;
	
	public Producao() {
		this.produtos = new ArrayList<Produto>();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_producao")
	private Long id;
	
	/**
	 * Produtos da produção
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="producao")
	private List<Produto> produtos;
	
	/**
	 * Tipo de atividade
	 */
	@Enumerated(EnumType.STRING)
	private EnumTipoAtividade tipoAtividade;

	/**
	 * Tipo de produção
	 */
	@Enumerated(EnumType.STRING)
	private EnumTipoProducao tipoProducao;
	
	private double pontos;

	/**
	 * Total de pontos
	 * 
	 * @return pontos
	 */
	public double getPontos() {
		return pontos;
	}

	public void setPontos(double pontos) {
		this.pontos = pontos;
	}

	/**
	 * Lista de produtos
	 * 
	 * @return lista
	 */

	public List<Produto> getProdutos() {
		return produtos;
	}

	/**
	 * Altera lista de produtos
	 * 
	 * @param produtos
	 */
	public void addProduto(Produto produto) {
		produto.setProducao(this);
		this.pontos += produto.getPontos();
		this.produtos.add(produto);
	}

	/**
	 * Retorna o grupo de atividade que pertence
	 * @return tipoAtividade
	 */
	public EnumTipoAtividade getTipoAtividade() {
		return tipoAtividade;
	}

	/**
	 * Altera o grupo de atividades que pertence
	 * @param tipoAtividade
	 */
	public void setTipoAtividade(EnumTipoAtividade tipoAtividade) {
		this.tipoAtividade = tipoAtividade;
	}
	
	/**
	 * Retorna o tipo de produção
	 * @return tipoProducao
	 */
	public EnumTipoProducao getTipoProducao() {
		return tipoProducao;
	}

	/**
	 * Altera o tipo de produção
	 * @param tipoProducao
	 */
	public void setTipoProducao(EnumTipoProducao tipoProducao) {
		this.tipoProducao = tipoProducao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	
}
