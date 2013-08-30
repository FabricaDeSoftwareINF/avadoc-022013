package br.ufg.inf.avadoc.listener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import br.ufg.inf.avadoc.enums.EnumTipoUsuario;
import br.ufg.inf.avadoc.modelo.Usuario;
import br.ufg.inf.avadoc.util.UtilObjeto;
import br.ufg.inf.avadoc.util.UtilString;

/**
 * 
 * @author Wilker Machado
 * 
 * @version 1.0.0
 */
public class Authenticator implements AuthenticationProvider, Serializable {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -3130729921629518201L;

	/** Atributo usuario. */
	private Usuario usuarioLogado;

	/** Atributo mockUsuario. */
	private final Map<String, Usuario> mockUsuarioPerfil;
	

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public Authenticator() {

		this.mockUsuarioPerfil = new HashMap<String, Usuario>();

		this.mockUsuarioPerfil.put("admin", new Usuario("Administrador", "admin", "admin", Arrays.asList(EnumTipoUsuario.ADMINISTRADOR)));

		System.out.println("MOCK DE USUÁRIOS CRIADO COM SUCESSO.");
	}

	/**
	 * Método responsável por autenticar usuários do sistema.
	 * 
	 * @author Wilker Machado
	 * 
	 * @return <code>Authentication</code>
	 */
	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {

		final String login = (String) authentication.getPrincipal();

		final String senha = (String) authentication.getCredentials();

		String campos = null;

		if (!UtilString.isVazio(( campos = this.isCamposObrigatoriosNaoInformados(login, senha) ))) {

			throw new BadCredentialsException("Preencha Campos Obrigatorios, "+ campos);
		}

		this.usuarioLogado = this.mockUsuarioPerfil.get(login);

		if (!UtilObjeto.isReferencia(this.usuarioLogado) || !UtilString.isStringsIguais(this.usuarioLogado.getSenha(), senha)) {

			throw new BadCredentialsException("Usuario e Senha Incorretos");
		}

		return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), this.getAuthorities());
	}

	

	/**
	 * Método responsável por validar campos obrigatórios não informados.
	 * 
	 * @author Wilker Machado
	 * 
	 * @param login
	 * 
	 * @param senha
	 * 
	 * @return <code>String</code>
	 */
	private String isCamposObrigatoriosNaoInformados(final String login, final String senha) {

		final StringBuilder builderFields = new StringBuilder();

		builderFields.append(UtilString.isVazio(login) ? "Login" : "");

		builderFields.append(UtilString.isVazio(senha) && UtilString.isVazio(login) ? ", " : "");

		builderFields.append(UtilString.isVazio(senha) ? "Senha": "");

		return builderFields.toString();
	}

	/**
	 * Método responsável por obter os perfis de um usuario.
	 * 
	 * @author Wilker Machado
	 * 
	 * @return <code>Collection</code>
	 */
	public Collection<GrantedAuthority> getAuthorities() {

		final Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (final EnumTipoUsuario perfil : this.usuarioLogado.getTipoUsuario()) {

			authorities.add(new GrantedAuthorityImpl(perfil.getRole()));
		}

		return authorities;
	}

	/**
	 * Método responsável por fornecer suporte a autenticação de usuários através do framework
	 * Spring-security.
	 * 
	 * @author Wilker Machado
	 * 
	 * @return <code>boolean</code>
	 */
	@Override
	public boolean supports(final Class<? extends Object> authentication) {

		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	/**
	 * Retorna o valor do atributo <code>usuarioLogado</code>
	 * 
	 * @return <code>Usuario</code>
	 */
	public Usuario getUsuarioLogado() {

		return this.usuarioLogado;
	}

}
