package br.ufg.inf.avadoc.persistencia.base;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.orm.hibernate3.HibernateTemplate;

import br.ufg.inf.avadoc.modelo.AbstractEntity;


/**
 * <p>
 * <b>Title:</b> DAO
 * </p>
 * 
 * <p>
 * <b>Description:</b> Interface para acesso a base de dados já com métodos de manutenção (CRUD) pré-definidos.
 * </p>
 * 
 * @author Wilker Machado
 * 
 * @version 1.0
 * 
 * @param <E>
 *            Entidade que será manipulada.
 */
public interface DAO<E extends AbstractEntity> {

	/**
	 * Busca a entidade pelo seu identificador.
	 * 
	 * @param identificador
	 *            O identificador da entidade
	 * 
	 * @return Entidade pesquisada
	 */
	E obter(final Serializable identificador);

	/**
	 * Insere o objeto informado como parametro na entidade gerenciada por esta classe.
	 * 
	 * @param entidade
	 *            Objeto da entidade
	 * 
	 * @return Chave da entidade inserida na base de dados, podendo ser um objeto com mais de um atributo representando a chave
	 */
	Serializable inserir(final E entidade);

	/**
	 * Altera a entidade na base de dados.
	 * 
	 * @param entidade
	 *            Objeto da entidade
	 */
	void alterar(final E entidade);

	/**
	 * Insere o objeto caso ele não exista, senão, atualiza o objeto na entidade.
	 * 
	 * @param entidade
	 *            Objeto da entidade
	 */
	void salvar(final E entidade);

	/**
	 * Altera a entidade passada por parâmetro fazendo um "merge" com a entidade existente na sessão do Hibernate (caso exista).
	 * 
	 * @param entidade
	 *            Entidade a ser salva.
	 */
	void mesclar(final E entidade);

	/**
	 * Remove o objeto da entidade gerenciada por esta classe.
	 * 
	 * @param entidade
	 *            Objeto da entidade
	 */
	void remover(final E entidade);

	/**
	 * Remove todos os objetos informados como parametro da entidade gerenciada por esta classe.
	 * 
	 * @param entidades
	 *            Objetos da entidade
	 */
	void removerTodos(final Collection<E> entidades);

	/**
	 * Consulta os objetos que possuirem os valores dos atributos do objeto informado.
	 * 
	 * @param entidade
	 *            Objeto que será utilizado como parâmetro na consulta.
	 * 
	 * @return Lista das entidades encontradas.
	 */
	Collection<E> consultar(final E entidade);

	/**
	 * Lista todos os objetos da entidade sem efetuar controle de paginacao.
	 * 
	 * @return Lista de todas objetos da entidade
	 */
	Collection<E> listar();

	/**
	 * Sincroniza entidade com a base de dados.
	 * 
	 * @param entidade
	 *            Entidade que será atualizada.
	 */
	void refresh(final E entidade);
	
	
	/**
	 * Método responsável por retornar instancia do hibernate
	 *
	 * @author Wilker Machado
	 * 
	 * @return <code>HibernateTemplate</code>
	 */
	HibernateTemplate getTemplate();
}
