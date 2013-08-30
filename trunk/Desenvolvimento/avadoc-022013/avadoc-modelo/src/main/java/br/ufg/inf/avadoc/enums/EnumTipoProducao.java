package br.ufg.inf.avadoc.enums;

/**
 * Tipo de produção/artefato de uma atividade
 *
 */
public enum EnumTipoProducao {
	/**
	 * Constante para produção científica para se procurar a pontuação de uma
	 * atividade
	 */
	PRODUCAO_CIENTIFICA("ProducaoCientifica"),
	/**
	 * Constante para produção artística para se procurar a pontuação de uma
	 * atividade
	 */
	PRODUCAO_ARTISTICA_CULTURAL("ProducaoArtisticaCultural"),
	/**
	 * Constante para produção técnica e tecnológica para se procurar a
	 * pontuação de uma atividade
	 */
	PRODUCAO_TECNICA_TECNOLOGICA("ProducaoTecnicaTecnologica"),
	/**
	 * Constante para outro tipo de produção para se procurar a pontuação de uma
	 * atividade
	 */
	PRODUCAO_OUTRO("ProducaoOutro"),
	/**
	 * Constante para atividades de pesquisa para se procurar a pontuação de uma
	 * atividade
	 */
	ATIVIDADE_PESQUISA("AtividadePesquisa"),
	/**
	 * Constante para atividades de extensão para se procurar a pontuação de uma
	 * atividade
	 */
	ATIVIDADE_EXTENSAO("AtividadeExtensao"),
	/**
	 * Constante para atividades de direção e/ou função gratificada para se
	 * procurar a pontuação de uma atividade
	 */
	DIRECAO_FUNCAO_GRATIFICADA("DirecaoFuncaoGratificada"),
	/**
	 * Constante para atividades administrativas para se procurar a pontuação de
	 * uma atividade
	 */
	ATIVIDADE_ADMINISTRATIVA("AtividadeAdministrativa"),
	/**
	 * Constante para outros tipos de atividades administrativas para se
	 * procurar a pontuação de uma atividade
	 */
	ATIVIDADE_ADMINISTRATIVA_OUTRAS("AtividadeAdministrativaOutra"),
	/**
	 * Constante para atividades de representação fora da UFG para se procurar a
	 * pontuação de uma atividade
	 */
	ATIVIDADE_REPRESENTACAO("AtividadeRepresentacaoFora"),
	/**
	 * Constante para atividades acadêmicas de orientação para se procurar a
	 * pontuação de uma atividade
	 */
	ATIVIDADE_ACADEMICA_ORIENTACAO("Orientacao"),
	/**
	 * Constante para atividades acadêmicas de bancas e cursos para se procurar
	 * a pontuação de uma atividade
	 */
	ATIVIDADE_ACADEMICA_BANCAS_CURSOS("BancaCurso"),
	/**
	 * Constante para atividades de aprendizado e aperfeiçoamento para se
	 * procurar a pontuação de uma atividade
	 */
	ATIVIDADE_APRENDIZADO_APERFEICOAMENTO("AtividadeAprendizadoAperfeicoamento");

	/**
	 * Tipo de producao
	 */
	private String key;
	
	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param key
	 * 
	 */
	private EnumTipoProducao(String key) {
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
