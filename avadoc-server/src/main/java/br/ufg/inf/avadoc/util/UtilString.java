package br.ufg.inf.avadoc.util;


/**
 * 
 * @author Wilker Machado
 * 
 * @version 1.0
 */
public final class UtilString {

	/**
	 * Construtor.
	 */
	private UtilString() {
		super();
	}

	/**
	 * Retorna true se a string estiver vazia.
	 * 
	 * @param string
	 *            String
	 * 
	 * @return true se a string estiver vazia
	 */
	public static boolean isVazio(final String string) {
		return (string == null || string.trim().equals(""));
	}

	/**
	 * Retorna true se alguma string tiver vazia.
	 * 
	 * @param string1
	 *            String
	 * 
	 * @param string2
	 *            String
	 * 
	 * @return true se alguma string tiver vazia.
	 */
	public static boolean isVazio(final String string1, final String string2) {

		return (isVazio(string1) || isVazio(string2));
	}

	/**
	 * Retorna true se alguma string tiver vazia.
	 * 
	 * @param strings
	 *            Strings
	 * 
	 * @return true se alguma string tiver vazia.
	 */
	public static boolean isVazioTodos(final String... strings) {

		boolean isVazio = true;

		if (strings != null) {

			isVazio = false;

			for (int idx = 0; idx < strings.length && (isVazio == false); idx++) {

				isVazio = isVazio(strings[idx]);
			}
		}

		return isVazio;
	}

	/**
	 * Retorna true se as duas strings forem iguais.
	 * 
	 * @param string0
	 *            String validada
	 * 
	 * @param string1
	 *            String validada
	 * 
	 * @return booleano
	 */
	public static boolean isStringsIguais(final String string0,
			final String string1) {

		boolean resultado = false;

		if (!isVazio(string0, string1)) {

			resultado = string0.equals(string1);
		}

		return resultado;
	}

}