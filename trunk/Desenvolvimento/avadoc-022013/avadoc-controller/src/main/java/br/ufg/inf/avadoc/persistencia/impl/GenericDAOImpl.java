package br.ufg.inf.avadoc.persistencia.impl;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Map;

import javax.persistence.Transient;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.ufg.inf.avadoc.modelo.AbstractEntity;
import br.ufg.inf.avadoc.persistencia.base.DAO;
import br.ufg.inf.avadoc.persistencia.base.HibernateDAO;
import br.ufg.inf.avadoc.util.UtilObjeto;
import br.ufg.inf.avadoc.util.UtilString;

/**
 * <p>
 * <b>Title:</b> GenericDAOImpl
 * </p>
 * 
 * <p>
 * <b>Description:</b> Classe responsável pela integração com a base de dados mantida pelo sistema.
 * </p>
 * 
 * @author Wilker Machado
 * 
 * @version 1.0.0
 */
@SuppressWarnings("unchecked")
public abstract class GenericDAOImpl<E extends AbstractEntity> extends	HibernateDAO<E> implements DAO<E>, Serializable {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 780997552374026613L;

	public GenericDAOImpl(SessionFactory sessionFactory) {

		super(sessionFactory);

	}

	/**
	 * Método responsável por listar registros ativos do sistema.
	 * 
	 * @author Wilker Machado
	 * 
	 * @return <code>Collection</code>
	 */
	@Override
	public Collection<E> listar() {

		final Criteria criteria = this.novoCriteria();

		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

		return super.consultar(criteria);
	}

	/**
	 * Método responsável por verificar se uma propriedade é transient.
	 * 
	 * @author Wilker Machado
	 * 
	 * @param property
	 * 
	 * @return <code>boolean</code>
	 */
	protected boolean isTransient(final String property) {

		for (final Field field : this.getTipoDaEntidade().getDeclaredFields()) {

			if (field.getName().equals(property)
					&& (Modifier.isTransient(field.getModifiers()) || this
							.isTransient(field))) {

				return true;
			}
		}

		return false;
	}

	/**
	 * Método responsável por verificar se uma propriedade é transient.
	 * 
	 * @author Wilker Machado
	 * 
	 * @param field
	 *            - Propriedade da classe em questão.
	 * 
	 * @return <code>boolean</code>
	 */
	protected boolean isTransient(final Field field) {

		final Annotation[] annotations = field.getAnnotations();

		if (UtilObjeto.isReferencia(annotations)) {

			for (final Annotation annot : annotations) {

				if (annot instanceof Transient) {

					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Método responsável por definir os parametros da consulta.
	 * 
	 * @author Wilker Machado
	 * 
	 * @param query
	 * 
	 * @param filters
	 */
	protected void definirParametros(final Query query,
			final Map<String, String> filters) {

		int paramFilters = 1;

		if (UtilObjeto.isReferencia(filters) && !filters.isEmpty()) {

			for (final String property : filters.keySet()) {

				if (!this.isTransient(property)) {

					query.setString("var" + paramFilters++,
							filters.get(property).concat("%").toLowerCase());
				}
			}
		}
	}

	/**
	 * Método responsável por obter a quantidade de dados retornados.
	 * 
	 * @author Wilker Machado
	 * 
	 * @return <code>Long</code>
	 */
	public Long getQuantidadeDados() {

		final Criteria criteria = this.novoCriteria();

		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

		criteria.setProjection(Projections.count("id"));

		return (Long) criteria.uniqueResult();
	}

	/**
	 * @see DAO#novoCriteria()
	 */
	@Override
	protected Criteria novoCriteria() {

		final Class<E> clazz = this.getTipoDaEntidade();

		return this.getSession().createCriteria(clazz);
	}

	/**
	 * @see DAO#obter(Serializable)
	 */
	@Override
	public E obter(final Serializable id) {

		E resultado = null;

		if (this.isReferencia(id)) {

			final Class<E> tipo = this.getTipoDaEntidade();

			Session sessao = this.getSessionFactory().openSession();

			resultado = (E) sessao.get(tipo, id);

			sessao.close();

		}

		return resultado;
	}

	/**
	 * @see DAO#alterar(Entidade)
	 */
	@Override
	public void alterar(final E entidade) {

		if (this.isReferencia(entidade)) {

			this.getTemplate().update(entidade);

			this.getTemplate().flush();
		}
	}

	/**
	 * @see DAO#inserir(Entidade)
	 */
	@Override
	public Serializable inserir(final E entidade) {

		Serializable resultado = null;

		if (this.isReferencia(entidade)) {

			resultado = this.getTemplate().save(entidade);

			this.getTemplate().flush();
		}

		return resultado;
	}

	/**
	 * @see DAO#remover(Entidade)
	 */
	@Override
	public void remover(final E entidade) {

		if (this.isReferencia(entidade)) {

			this.carregarEntidadePersistente(entidade);

			this.getTemplate().delete(entidade);

			this.getTemplate().flush();
		}
	}

	/**
	 * @see DAO#salvar(Entidade)
	 */
	@Override
	public void salvar(final E entidade) {

		if (this.isReferencia(entidade)) {

			this.getTemplate().saveOrUpdate(entidade);

			this.getTemplate().flush();
		}
	}

	/**
	 * @see DAO#mesclar(Entidade)
	 */
	@Override
	public void mesclar(final E entidade) {

		if (this.isReferencia(entidade)) {

			this.getTemplate().merge(entidade);

			this.getTemplate().flush();
		}
	}

	/**
	 * Efetua o load da entidade caso esta não seja uma entidade persistente.
	 * 
	 * @param entidade
	 */
	@Override
	protected void carregarEntidadePersistente(final E entidade) {

		if (this.isReferencia(entidade) && !this.isPersistente(entidade)) {

			final Serializable id = entidade.getId();

			this.getTemplate().load(entidade, id);
		}
	}

	/**
	 * Retorna true se a entidade for persistente.
	 * 
	 * @param entidade
	 * 
	 * @return true se a entidade for persistente.
	 */
	@Override
	protected boolean isPersistente(final E entidade) {

		return (this.getTemplate().contains(entidade));
	}

	/**
	 * Método responsável por adicionar restrições ao <code>Criteria</code>
	 * 
	 * @author Silvânio Júnior
	 * 
	 * @param criteria
	 * 
	 * @param property
	 * 
	 * @param value
	 */
	public void addCriteria(final Criteria criteria, final String property,
			final Object value) {

		if (this.isReferencia(value)) {

			criteria.add(Restrictions.eq(property, value));
		}
	}

	/**
	 * Método responsável por adicionar restrições ao <code>Criteria</code>
	 * 
	 * @author Silvânio Júnior
	 * 
	 * @param criteria
	 * 
	 * @param property
	 * 
	 * @param value
	 */
	public void addCriteria(final Criteria criteria, final String property,
			final String value) {

		if (!UtilString.isVazio(value)) {

			criteria.add(Restrictions.eq(property, value));
		}
	}

}
