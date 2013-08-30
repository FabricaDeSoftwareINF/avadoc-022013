package br.ufg.inf.avadoc.enums;

/**
 * Tipo de atividade
 *
 */
public enum EnumTipoAtividade {
	/**
	 * Atividade de ensino
	 */
	ATIVIDADE_ENSINO("AtividadeEnsino"),
	/**
	 * Produção intelectual
	 */
	PRODUCAO_INTELECTUAL("ProducaoIntelectual"),
	/**
	 * Atividade de pesquisa e extensão
	 */
	ATIVIDADE_PESQUISA_EXTENSAO("AtividadePesquisaExtensao"),
	/**
	 * Atividade administrativa e de representação
	 */
	ATIVIDADE_ADMINISTRATIVA_REPRESENTACAO("AtividadeAdministrativaRepresentacao"),
	/**
	 * Outro tipo de atividade
	 */
	ATIVIDADE_OUTRA("AtividadeOutra");

	/**
	 * Tipo de atividade
	 */
	private String key;
	
	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param key
	 * 
	 */
	private EnumTipoAtividade(String key) {
		this.key = key;
	}
	
	/**
	 * Retorna o valor do atributo <code>key</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getKey() {
		return key;
	}
	/**
	 * Define o valor do atributo <code>key</code>.
	 * 
	 * @param key
	 */
	public void setKey(String key) {
		this.key = key;
	}
}
