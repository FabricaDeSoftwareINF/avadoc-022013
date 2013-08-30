package br.ufg.inf.avadoc.modelo.pontuacao;

import java.util.List;

import br.ufg.inf.avadoc.enums.EnumJornadaTrabalho;
import br.ufg.inf.avadoc.modelo.Nota;

/**
 * Classe responsável em realizar cálculos das notas finais ou parciais dos
 * docentes que estão sob estágio probatório ou progressão horizontal.
 */
public final class CalculaPontuacao {

	/**
	 * Classe interna a qual contém todos os cálculos para obtenção das notas
	 * do docente a qual está em estágio probatório.
	 */
	public static class EstagioProbatorio {

		/** Tempo de duração do estágio probatório. */
		public static final double TEMPO = 36;

		/**
		 * Calcula a nota final do docente com base em uma lista de notas
		 * parciais a qual o mesmo fora avaliado no decorrer de 36 meses,
		 * conforme a CONSUNI 21/2009 estabelece.
		 * @param notasNP lista de notas a ser calculada a média ponderada
		 * @return retorna a média ponderada
		 */
		public static double calculaNF(final List<Nota> notasNP) {

			double total = 0;

			for (Nota nota : notasNP) {
				total = total + (nota.getNota() * nota.getQuantidadeMeses());
			}

			total = total / TEMPO;

			return total;
		}

	}

	/**
	 * Classe interna a qual contém todos os cálculos para obtenção das notas
	 * do docente a qual está em progressão horizontal.
	 */
	public static class ProgressaoHorizontal {

		/** Tempo de duração da progressão horizontal. */
		public static final double TEMPO = 24;

		/** Peso da progressão horizontal das atividades do docente. */
		public static final double PESO_NCAD = 0.7;

		/** Peso da progressão horizontal do corpo discente. */
		public static final double PESO_ND = 0.3;

		/**
		 * Calcula a nota final do docente com base em uma lista de notas
		 * parciais a qual o mesmo fora avaliado no decorrer de 24 meses,
		 * conforme a CONSUNI 21/2009 estabelece.
		 * @param notasNP lista de notas a ser calculada a média ponderada
		 * @return retorna a média ponderada
		 */
		public static double calculaNCAD(final List<Nota> notasNP) {
			double total = 0;

			for (Nota nota : notasNP) {
				total = total + (nota.getNota() * nota.getQuantidadeMeses());
			}

			total = total / TEMPO;

			return total;
		}

		/**
		 * Calcula a nota global (NG) do docente com base na nota do
		 * interstício e nota de avaliação do corpo discente.
		 * @param ncad nota do interstício
		 * @param notaDiscente nota média do corpo discente
		 * @return retorna a nota com base na fórmula:
		 * (NG = NCAD * 0,7 + ND * 0,3)
		 */
		public static double calculaNG(final double ncad,
				final double notaDiscente) {
			return (ncad * PESO_NCAD) + (notaDiscente * PESO_ND);
		}

	}

	/** Constante do cálculo da N1 para o MMC. */
	public static final int CONSTANTE_AVALIACAO = 10;

	/** Quantidade de meses em um ano letivo. */
	public static final int QUANTIDADE_MESES = 12;

	/** Regime de 20 horas do docente. */
	public static final int JORNADA_20_HORAS = 8;

	/** Regime de 40 horas ou dedicação exclusiva do docente. */
	public static final int JORNADA_40_HORAS_OU_DE = 16;

	/** Peso do estágio probatório das atividades do docente. */
	public static final double PESO_N1 = 0.6;

	/** Peso do estágio probatório da direção ou chefia de departamento. */
	public static final double PESO_N2 = 0.2;

	/** Peso do estágio probatório da avaliação do corpo discente. */
	public static final double PESO_N3 = 0.2;

	/**
	 * Calcula a nota N1 (nota das atividades) de um docente em um período
	 * de atuação.
	 * @param nota nota referente ao período do docente
	 * @param jorTra jornada de trabalho a qual o docente rege
	 * @return retorna a nota N1
	 */
	public static Nota calculaN1(final Nota nota, final EnumJornadaTrabalho jorTra) {

		Nota n1 = new Nota();

		n1.setQuantidadeMeses(nota.getQuantidadeMeses());

		/* Verificação para não ser inserido nenhum valor negativo */
		if ((nota.getQuantidadeMeses() <= 0) || (nota.getNota() < 0)) {
			n1.setNota(0);
		} else {
			if (jorTra.equals(EnumJornadaTrabalho.jornada20)) {
				n1.setNota(((QUANTIDADE_MESES * nota.getNota())
						/ nota.getQuantidadeMeses()) / JORNADA_20_HORAS);
			} else {
				n1.setNota(((QUANTIDADE_MESES * nota.getNota())
						/ nota.getQuantidadeMeses()) / JORNADA_40_HORAS_OU_DE);
			}

			n1.setNota(CONSTANTE_AVALIACAO >= n1.getNota()
					? n1.getNota() : CONSTANTE_AVALIACAO);
		}

		return n1;
	}

	/**
	 * Calcula a nota parcial de um docente em estágio probatório ou
	 * progressão horizontal, dado um determinado período. Essa nota
	 * comporá a Nota Final (NF) do docente quando terminar o período de 36
	 * meses de atuação do estágio probatório ou a Nota Global (NG) no caso
	 * da progressão horizontal.
	 * @param n1 nota das atividades do docente
	 * @param notaChefia nota da diretoria ou chefia de gabinete
	 * @param notaDiscente nota do corpo discente
	 * @return retorna a nota parcial do docente referido à um período
	 */
	public static Nota calculaNP(final Nota n1, final double notaChefia,
			final double notaDiscente) {

		Nota notaParcial = new Nota();
		notaParcial.setQuantidadeMeses(n1.getQuantidadeMeses());

		/* Verificação para não ser calculado nenhum valor negativo */
		if ((notaChefia < 0) || (notaDiscente < 0)) {
			notaParcial.setNota(0);
		} else {
			notaParcial.setNota((n1.getNota() * PESO_N1)
					+ (notaChefia * PESO_N2)
					+ (notaDiscente * PESO_N3));
		}

		return notaParcial;
	}

	/**
	 * Construtor para evitar a instanciação dessa classe.
	 */
	private CalculaPontuacao() {

	}

}