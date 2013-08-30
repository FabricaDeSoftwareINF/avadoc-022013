package br.ufg.inf.avadoc.listener;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.WebApplicationContextUtils;

import br.ufg.inf.avadoc.modelo.Usuario;
import br.ufg.inf.avadoc.util.UtilObjeto;

/**
 * 
 * @author Wilker Machado
 * 
 * @version 1.0.0
 */
public class UsuarioLogoutListener implements PhaseListener {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = -908096912539732701L;

	/**
	 * Método executado antes da ação ter sido realizada.
	 * 
	 * @author Wilker Machado
	 */
	@Override
	public void beforePhase(final PhaseEvent event) {

		final FacesContext facesContext = event.getFacesContext();

		if (facesContext.isPostback()) {

			if (!UtilObjeto.isReferencia(this.getUsuarioLogado())) {

				try {

					this.redirecionar(this.getRequest(facesContext).getContextPath().concat("/login.jsf"), facesContext);

					facesContext.responseComplete();

				} catch (final IOException e) {

					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Método responsável por redirecionar a solicitação.
	 * 
	 * @author Wilker Machado
	 * 
	 * @param destino
	 * 
	 * @throws IOException
	 */
	private void redirecionar(final String destino, final FacesContext facesContext) throws IOException {

		this.getResponse(facesContext).sendRedirect(destino);
	}

	/**
	 * Método responsável por obter a resposta dada ao cliente.
	 * 
	 * @author Wilker Machado
	 * 
	 * @param facesContext
	 * 
	 * @return <code>HttpServletResponse</code>
	 */
	private HttpServletResponse getResponse(final FacesContext facesContext) {

		return (HttpServletResponse) facesContext.getExternalContext().getResponse();
	}

	/**
	 * Método responsável por obter a requisção feita pelo usuário.
	 * 
	 * @author Wilker Machado
	 * 
	 * @param facesContext
	 * 
	 * @return <code>HttpServletRequest</code>
	 */
	private HttpServletRequest getRequest(final FacesContext facesContext) {

		return (HttpServletRequest) facesContext.getExternalContext().getRequest();
	}

	/**
	 * Método executado após a ação ter sido realizada.
	 * 
	 * @author Wilker Machado
	 */
	@Override
	public void afterPhase(final PhaseEvent event) {

		return;
	}

	/**
	 * Método retorna a fase do JSF que será interceptada.
	 * 
	 * @author Wilker Machado
	 */
	@Override
	public PhaseId getPhaseId() {

		return PhaseId.INVOKE_APPLICATION;
	}

	/**
	 * Método responsável por obter o usuário logado do sistema.
	 * 
	 * @author Wilker Machado
	 * 
	 * @param facesContext
	 * 
	 * @return <code>Usuario</code>
	 */
	private Usuario getUsuarioLogado() {

		return WebApplicationContextUtils.getWebApplicationContext(UsuarioLogoutListener.getApplicationScope()).getBean("secAuth", Authenticator.class).getUsuarioLogado();
	}
	/**
	 * Método responsável por obter uma referência ao escopo <code>context/application</code>
	 * 
	 * @author Wilker Machado
	 * 
	 * @return <code>ServletContext</code>
	 */
	public static ServletContext getApplicationScope() {

		return (ServletContext) UsuarioLogoutListener.getExternalContext().getContext();
	}

	/**
	 * Método responsável por obter o contexto externo ao JSF.
	 * 
	 * @author Wilker Machado
	 * 
	 * @return <code>ExternalContext</code>
	 */
	private static ExternalContext getExternalContext() {

		return FacesContext.getCurrentInstance().getExternalContext();
	}
}
