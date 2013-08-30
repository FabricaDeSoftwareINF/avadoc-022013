package br.ufg.inf.avadoc.modelo.atividade;

/**
 * ProdutoPontosHora
 * 
 * Produto obtido em atividades que possuem pontuação baseadas na quantidade de
 * horas exercidos.
 * 
 */
public class ProdutoPontosHora extends Produto {
	private static final long serialVersionUID = 2861223992605895127L;
	
	private static final int HORAS_ANUAIS = 150;
	/**
	 * Horas de efetivo exercício da atividade
	 */
	private int horas = 1;
	
	public ProdutoPontosHora(){
	}

	/**
	 * Retorna horas de efetivo exercício da atividade
	 * 
	 * @return horas
	 */
	public int getHoras() {
		return horas;
	}

	/**
	 * Altera horas de efetivo exercício da atividade
	 * 
	 * @param horas
	 */
	public void setHoras(int horas) {
		this.horas = horas;
	}

	/**
	 * Retorna pontuação do produto de acordo com quantidade de horas
	 * efetivamente realizadas.<BR>
	 * As atividades com esforço de carga horária inferior a 150 horas serão
	 * pontuadas proporcionalmente as horas efetivamente realizadas com a
	 * correspondência de 10 pontos para 150 horas.
	 */
	@Override
	public int getPontos() {
		return (int) ((horas * super.getPontos()) / HORAS_ANUAIS);
	}

}
