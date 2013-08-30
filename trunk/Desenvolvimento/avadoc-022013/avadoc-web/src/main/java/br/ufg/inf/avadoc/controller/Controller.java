package br.ufg.inf.avadoc.controller;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * <b>Title:</b> Controller
 * </p>
 * 
 * <p>
 * <b>Description:</b> Classe responsável pelo fluxo de navegação do sistema. <br />
 * </p>
 * 
 * @autor Wilker Machado
 * 
 * @version 1.0
 */
public abstract class Controller implements Serializable {

	private static final long serialVersionUID = -7859663056918687753L;

	protected Logger logger = Logger.getLogger(this.getClass().getSimpleName());

	/**
	 * @{link Inherited}
	 */

	public String abreIniciar() {

		this.iniciarDados();

		return this.getNavigationAbreIniciar();
	}

	/**
	 * @{link Inherited}
	 */

	public String abreDetalhar() {

		return this.getNavigationAbreDetalhar();
	}

	/**
	 * @{link Inherited}
	 */

	public String abreIncluir() {

		return this.getNavigationAbreIncluir();
	}

	/**
	 * @{link Inherited}
	 */

	public String abreAlterar() {

		return this.getNavigationAbreAlterar();
	}

	/**
	 * Obtem o objeto de requisição
	 */
	public HttpServletRequest getRequest() {

		return ((HttpServletRequest) this.getFacesContext()
				.getExternalContext().getRequest());
	}

	/**
	 * Obtem o objeto de resposta
	 */
	public HttpServletResponse getResponse() {

		return ((HttpServletResponse) this.getFacesContext()
				.getExternalContext().getResponse());
	}

	/**
	 * Obtem o objeto corrente do FacesContext
	 */
	public FacesContext getFacesContext() {

		return FacesContext.getCurrentInstance();
	}

	/**
	 * Obtem um parámetro da requisição
	 * 
	 * @param parameter
	 */
	public <T> String getParameterFromRequest(final String parameter) {

		return this.getRequest().getParameter(parameter);
	}

	/**
	 * Obtem um objeto da sessão a partir de uma chave de atributo e a classe do
	 * objeto retornado
	 * 
	 * @param key
	 *            chave do objeto na sessão
	 * @param clazz
	 *            tipo do objeto
	 */
	@SuppressWarnings("unchecked")
	public <T> T getFromSessao(final String key, final Class<T> clazz) {

		try {

			return ((T) this.getFacesContext().getExternalContext()
					.getSessionMap().get(key));

		} catch (final Exception e) {

			this.logger.log(Level.SEVERE, e.getMessage());
		}

		return null;
	}

	/**
	 * Inicia dados utilizados na tela inicial do caso de uso.
	 * 
	 * <b>Obs.</b> Esse método deve ser sobrescrito, se necessário.
	 */
	public void iniciarDados() {

		return;
	}

	/**
	 * Retorna o <i>navigation-rule</i> responsável pela tela de inclusão do
	 * caso de uso.
	 * 
	 * @return <i>navigation-rule</i>
	 */
	protected String getNavigationAbreIniciar() {

		return this.getClass().getSimpleName() + "/inicial";
	}

	/**
	 * Retorna o <i>navigation-rule</i> responsável pela tela de inclusão do
	 * caso de uso.
	 * 
	 * @return <i>navigation-rule</i>
	 */
	protected String getNavigationAbreIncluir() {

		return this.getClass().getSimpleName() + "/incluir";
	}

	/**
	 * Retorna o <i>navigation-rule</i> responsável pela tela de alteração do
	 * caso de uso.
	 * 
	 * @return <i>navigation-rule</i>
	 */
	protected String getNavigationAbreAlterar() {

		return this.getClass().getSimpleName() + "/alterar";
	}

	/**
	 * Retorna o <i>navigation-rule</i> responsável pela tela de detalhe do caso
	 * de uso.
	 * 
	 * @return <i>navigation-rule</i>
	 */
	protected String getNavigationAbreDetalhar() {

		return this.getClass().getSimpleName() + "/detalhar";
	}

	/**
	 * Retorna a página inicial do caso de uso.
	 * 
	 * @return String indicando qual <i>recurso</i> será seguido.
	 */
	protected String voltarInicio() {

		return this.abreIniciar();
	}

}
