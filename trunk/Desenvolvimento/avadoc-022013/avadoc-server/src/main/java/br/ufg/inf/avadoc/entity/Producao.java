package br.ufg.inf.avadoc.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_producao")
	private Long id;
	
	/**
	 * Produtos da produção
	 */
	private List<Produto> produtos;
	
	/**
	 * Tipo de atividade
	 */
	private EnumTipoAtividade tipoAtividade;

	/**
	 * Tipo de produção
	 */
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
