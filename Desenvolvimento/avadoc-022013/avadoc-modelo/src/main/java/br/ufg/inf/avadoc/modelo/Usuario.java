package br.ufg.inf.avadoc.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

import br.ufg.inf.avadoc.enums.EnumTipoUsuario;

@Entity
public class Usuario extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -4634824486948432121L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Long id;

	private String nome;

	private String login;

	private String senha;

	@ElementCollection(targetClass = EnumTipoUsuario.class)
	@JoinTable(name = "tb_roles_usuario", joinColumns = @JoinColumn(name = "id_usuario"))
	@Enumerated(EnumType.STRING)
	@Column(name = "tp_role", nullable = false)
	private List<EnumTipoUsuario> tipoUsuario;

	public Usuario() {

		super();
	}

	public Usuario(String nome, String login, String senha,
			List<EnumTipoUsuario> tipoUsuario) {

		this.nome = nome;

		this.login = login;

		this.senha = senha;

		this.tipoUsuario = tipoUsuario;
	}

	/**
	 * Retorna o valor do atributo <code>login</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getLogin() {

		return login;
	}

	/**
	 * Define o valor do atributo <code>login</code>.
	 * 
	 * @param login
	 */
	public void setLogin(String login) {

		this.login = login;
	}

	/**
	 * Retorna o valor do atributo <code>senha</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getSenha() {

		return senha;
	}

	/**
	 * Define o valor do atributo <code>senha</code>.
	 * 
	 * @param senha
	 */
	public void setSenha(String senha) {

		this.senha = senha;
	}

	/**
	 * Retorna o valor do atributo <code>tipoUsuario</code>
	 * 
	 * @return <code>List<EnumTipoUsuario></code>
	 */
	public List<EnumTipoUsuario> getTipoUsuario() {

		return tipoUsuario;
	}

	/**
	 * Define o valor do atributo <code>tipoUsuario</code>.
	 * 
	 * @param tipoUsuario
	 */
	public void setTipoUsuario(List<EnumTipoUsuario> tipoUsuario) {

		this.tipoUsuario = tipoUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
