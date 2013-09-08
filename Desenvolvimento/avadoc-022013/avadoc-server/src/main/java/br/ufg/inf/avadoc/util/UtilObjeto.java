package br.ufg.inf.avadoc.util;

import java.util.Collection;

/**
 * 
 * @author Wilker Machado
 * 
 * @version 1.0
 */
public class UtilObjeto {

	/**
	 * Construtor.
	 */
	private UtilObjeto() {
		super();
	}

	/**
	 * Retorna true se o objeto é uma referência.
	 * 
	 * @param objeto
	 *            Objeto
	 * 
	 * @return true se o objeto é uma referência.
	 */
	public static boolean isReferencia(final Object objeto) {

		return (objeto != null);
	}

	/**
	 * Retorna true se o objeto é uma referência.
	 * 
	 * @param objeto1
	 *            Objeto
	 * @param objeto2
	 *            Objeto
	 * 
	 * @return true se o objeto é uma referência.
	 */
	public static boolean isReferencia(final Object objeto1,
			final Object objeto2) {

		return (isReferencia(objeto1) && isReferencia(objeto2));
	}

	/**
	 * Retorna true se o objeto é uma referência.
	 * 
	 * @param objeto1
	 *            Objeto
	 * @param objeto2
	 *            Objeto
	 * @param objeto3
	 *            Objeto
	 * 
	 * @return true se o objeto é uma referência.
	 */
	public static boolean isReferencia(final Object objeto1,
			final Object objeto2, final Object objeto3) {

		return (isReferencia(objeto1, objeto2) && isReferencia(objeto3));
	}

	/**
	 * Retorna true se o objeto é uma referência.
	 * 
	 * @param objetos
	 *            Objetos
	 * 
	 * @return true se o objeto é uma referência.
	 */
	public static boolean isReferenciaTodos(final Object... objetos) {

		boolean res = false;

		if (isReferencia(objetos)) {

			res = true;

			for (int idx = 0; idx < objetos.length && (res == true); idx++) {

				res = isReferencia(objetos[idx]);
			}
		}

		return res;
	}

	/**
	 * Retorna true se o tipo for uma coleção.
	 * 
	 * @param objeto
	 *            objeto que será validado.
	 * 
	 * @return true se o tipo for uma coleção.
	 */
	public static boolean isColecao(final Object objeto) {

		return (objeto instanceof Collection);
	}

	/**
	 * Retorna true se o tipo for uma coleção.
	 * 
	 * @param classe
	 *            Classe que será validada.
	 * 
	 * @return true se o tipo for uma coleção.
	 */
	public static boolean isColecao(final Class<?> classe) {

		final Class<?> colecao = Collection.class;

		return (isReferencia(classe) && colecao.isAssignableFrom(classe));
	}

	/**
	 * Retorna true se o tipo for um Comparable.
	 * 
	 * @param objeto
	 *            Objeto que será validado.
	 * 
	 * @return true se o tipo for um Comparable.
	 */
	public static boolean isComparable(final Object objeto) {

		return (objeto instanceof Comparable<?>);
	}

	/**
	 * Retorna true se o tipo for um Comparable.
	 * 
	 * @param classe
	 *            Classe que será validada.
	 * @return true se o tipo for um Comparable.
	 */
	public static boolean isComparable(final Class<?> classe) {

		final Class<?> colecao = Comparable.class;

		return (isReferencia(classe) && colecao.isAssignableFrom(classe));
	}

	/**
	 * Retorna true se o objeto for do tipo informado.
	 * 
	 * @param objeto
	 *            Objeto validado.
	 * @param tipo
	 *            Tipo desejado
	 * 
	 * @return true se o objeto for do tipo informado.
	 */
	public static boolean isObjetoDoTipo(final Object objeto, Class<?> tipo) {

		boolean res = false;

		if (isReferencia(objeto, tipo)) {

			Class<?> classe = getClasse(objeto);

			res = tipo.isAssignableFrom(classe);
		}

		return res;
	}

	/**
	 * Retorna a classe do objeto.
	 * 
	 * @param <T>
	 *            Tipo do objeto da classe.
	 * @param objeto
	 *            Objeto
	 * 
	 * @return Classe
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getClasse(final T objeto) {

		Class<T> classe = null;

		if (isReferencia(objeto)) {

			if (isClasse(objeto)) {

				classe = (Class<T>) objeto;
			} else {

				classe = (Class<T>) objeto.getClass();
			}
		}

		return classe;
	}

	/**
	 * Retorna true se o objeto for uma classe.
	 * 
	 * @param objeto
	 *            Objeto validado.
	 * 
	 * @return true se o objeto for uma classe.
	 */
	public static boolean isClasse(final Object objeto) {

		return (isReferencia(objeto) && (objeto instanceof Class));
	}

}
