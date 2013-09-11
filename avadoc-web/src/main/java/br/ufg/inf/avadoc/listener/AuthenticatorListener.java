package br.ufg.inf.avadoc.listener;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.WebAttributes;

/**
 * @author Wilker Machado
 * 
 * @version 1.0.0
 */
public class AuthenticatorListener implements PhaseListener {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 5828300273736864870L;

	/** Constante PARAM_ERROR_MESSAGE. */
	private static final String PARAM_ERROR_MESSAGE = "badCredentialsMessage";

	/** Constante PARAM_VISIBLE_DIALOG. */
	private static final String PARAM_VISIBLE_DIALOG = "visibleAlertDialog";

	/**
	 * Método responsável por executar depois da fase de renderização da resposta ao cliente.
	 * 
	 * @author Wilker Machado
	 * 
	 * @param event
	 */
	@Override
	public void afterPhase(final PhaseEvent event) {

		return;
	}

	/**
	 * Método responsável por adicionar uma mensagem de erro de autenticação ao objeto FacesMessage, caso o usuário não informe credenciais válidas.
	 * 
	 * @author Wilker Machado
	 * 
	 * @param event
	 */
	@Override
	public void beforePhase(final PhaseEvent event) {

		final Exception usuarioInvalidoException = (Exception) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(WebAttributes.AUTHENTICATION_EXCEPTION);

		if (usuarioInvalidoException instanceof BadCredentialsException) {

			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(WebAttributes.AUTHENTICATION_EXCEPTION, null);

			this.getRequest().setAttribute(AuthenticatorListener.PARAM_ERROR_MESSAGE, usuarioInvalidoException.getMessage());

			this.getRequest().setAttribute(AuthenticatorListener.PARAM_VISIBLE_DIALOG, Boolean.TRUE);

		} else {

			this.getRequest().setAttribute(AuthenticatorListener.PARAM_VISIBLE_DIALOG, Boolean.FALSE);
		}
	}

	/**
	 * Método responsável por mapear qual etapa do ciclo de vida do JSF será manipulada por esta classe.
	 * 
	 * @author Wilker Machado
	 * 
	 * @return <code>PhaseId</code>
	 */
	@Override
	public PhaseId getPhaseId() {

		return PhaseId.RENDER_RESPONSE;
	}

	/**
	 * Método responsável por obter a solicitação vínculada a uma chamada cliente-servidor.
	 * 
	 * @author Wilker Machado
	 * 
	 * @return <code>HttpServeletRequest</code>
	 */
	private HttpServletRequest getRequest() {

		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}
}
