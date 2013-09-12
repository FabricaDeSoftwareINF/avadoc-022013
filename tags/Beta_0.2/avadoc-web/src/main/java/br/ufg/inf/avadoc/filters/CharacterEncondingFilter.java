package br.ufg.inf.avadoc.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 
 * @author Wilker Machado
 * 
 * @version 1.0.0
 */
public class CharacterEncondingFilter implements Filter {

	/** Constante CHARSET_SISTEMA. */
	private static final String CHARSET_SISTEMA = "UTF-8";

	/**
	 * Método responsável por inicializar o filtro.
	 * 
	 * @author Wilker Machado
	 * 
	 * @param filterConfig
	 * 
	 * @throws ServletException
	 */
	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {

		return;
	}

	/**
	 * Método responsável por definir o charset do sistema para UTF-8.
	 * 
	 * @author Wilker Machado
	 * 
	 * @param request
	 * 
	 * @param response
	 * 
	 * @param chain
	 * 
	 * @throws IOException
	 * 
	 * @throws ServletException
	 */
	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {

		request.setCharacterEncoding(CharacterEncondingFilter.CHARSET_SISTEMA);

		response.setCharacterEncoding(CharacterEncondingFilter.CHARSET_SISTEMA);

		chain.doFilter(request, response);
	}

	/**
	 * Método responsável por finalizar o filtro.
	 * 
	 * @author Wilker Machado
	 */
	@Override
	public void destroy() {

		return;
	}
}
