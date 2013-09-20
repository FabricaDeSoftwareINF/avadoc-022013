package br.ufg.inf.avadoc.entity;

/**
 * Classe abstrata que mant&eacute;m o atributo de pontos utilizado em todas as
 * atividades.
 * */
public abstract class AbstractAtividade extends AbstractEntity {

	private double pontos;

	public double getPontos() {
		return pontos;
	}

	public void setPontos(double pontos) {
		this.pontos = pontos;
	}
}
