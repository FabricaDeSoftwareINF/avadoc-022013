package br.ufg.inf.avadoc.modelo.atividade;

/**
 * ProdutoPontosAnuais
 * 
 * Produto obtido em atividades que possuem pontuação baseadas na quantidade de
 * anos exercidos.
 * 
 */
public class ProdutoPontosAnuais extends Produto {
	private static final long serialVersionUID = -5512027341406741446L;
	/**
	 * Anos de efetivo exercício da atividade
	 */
	private int anos = 1;
	
	public ProdutoPontosAnuais(){
	}

	/**
	 * Retorna anos de efetivo exercício da atividade
	 * 
	 * @return anos
	 */
	public int getAnos() {
		return anos;
	}

	/**
	 * Altera anos de efetivo exercício da atividade
	 * 
	 * @param anos
	 */
	public void setAnos(int anos) {
		this.anos = anos;
	}

	/**
	 * Retorna pontuação do produto * quantidade de anos exercendo atividade.
	 */
	@Override
	public int getPontos() {
		return anos * super.getPontos();
	}
}
