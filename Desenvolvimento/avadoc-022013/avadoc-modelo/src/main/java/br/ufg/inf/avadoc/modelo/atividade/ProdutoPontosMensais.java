package br.ufg.inf.avadoc.modelo.atividade;

/**
 * ProdutoPontosMensais
 * 
 * Produto obtido em atividades que possuem pontuação baseadas na quantidade de
 * meses exercidos.
 * 
 */
public class ProdutoPontosMensais extends Produto {
	private static final long serialVersionUID = -1439468789674146176L;
	/**
	 * Meses de efetivo exercício da atividade
	 */
	private int meses = 1;
	
	public ProdutoPontosMensais(){
	}

	/**
	 * Retorna meses de efetivo exercício da atividade
	 * 
	 * @return meses
	 */
	public int getMeses() {
		return meses;
	}

	/**
	 * Altera meses de efetivo exercício da atividade
	 * 
	 * @param meses
	 */
	public void setMeses(int meses) {
		this.meses = meses;
	}

	/**
	 * Retorna pontuação do produto * quantidade de meses exercendo atividade.
	 */
	@Override
	public int getPontos() {
		return meses * super.getPontos();
	}
}
