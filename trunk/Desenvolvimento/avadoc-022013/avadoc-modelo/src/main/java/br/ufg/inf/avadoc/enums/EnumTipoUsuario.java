package br.ufg.inf.avadoc.enums;

public enum EnumTipoUsuario {

	ADMINISTRADOR("ROLE_ADMIN"),

	DENTISTA("ROLE_DENTISTA"),

	USUARIO("ROLE_USUARIO");

	/** Atributo role. */
	private String role;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param role
	 * 
	 */
	private EnumTipoUsuario( final String role ) {

		this.role = role;

	}

	/**
	 * Retorna o valor do atributo <code>role</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getRole() {

		return this.role;
	}

	/**
	 * Define o valor do atributo <code>role</code>.
	 * 
	 * @param role
	 */
	public void setRole(final String role) {

		this.role = role;
	}

}
