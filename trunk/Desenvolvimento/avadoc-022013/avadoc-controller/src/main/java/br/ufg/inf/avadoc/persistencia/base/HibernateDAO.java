package br.ufg.inf.avadoc.persistencia.base;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.impl.CriteriaImpl;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;
import br.ufg.inf.avadoc.modelo.AbstractEntity;
import br.ufg.inf.avadoc.util.UtilColecao;
import br.ufg.inf.avadoc.util.UtilObjeto;
import br.ufg.inf.avadoc.util.UtilString;

/**
 * <p>
 * <b>Title:</b> HibernateDAO
 * </p>
 * 
 * <p>
 * <b>Description:</b> Classe responsável pela persistência usando Hibernate.
 * </p>
 * 
 * @author Wilker Machado
 * 
 * @version 1.0
 * 
 * @param <T>
 *            Entidade que será manipulada.
 */
@SuppressWarnings({ "unchecked", "restriction" })
public abstract class HibernateDAO<T extends AbstractEntity> extends HibernateDaoSupport implements DAO<T> {

	/**
	 * Construtor
	 */
	public HibernateDAO(SessionFactory sessionFactory) {

		setSessionFactory(sessionFactory);

	}

	/**
	 * @see DAO#alterar(Entidade)
	 */
	public void alterar(final T entidade) {

		if (this.isReferencia(entidade)) {

			this.getTemplate().update(entidade);

			this.getTemplate().flush();

		}
	}

	/**
	 * @see DAO#consultar()
	 */
	public Collection<T> consultar() {

		final Criteria criteria = this.novoCriteria();

		return this.consultar(criteria);
	}

	/**
	 * @see DAO#listar()
	 */
	public Collection<T> listar() {

		final Criteria criteria = this.novoCriteria();

		return criteria.list();
	}

	/**
	 * @see DAO#refresh(Entidade)
	 */
	public void refresh(final T entidade) {

		this.getTemplate().refresh(entidade);
	}

	/**
	 * @see DAO#consultar(Entidade)
	 */
	public Collection<T> consultar(final T entidade) {

		final Criteria criteria = this.novoCriteria();

		if (this.isReferencia(entidade)) {

			final Example example = Example.create(entidade);

			example.enableLike(MatchMode.START);

			example.excludeZeroes();

			criteria.add(example);
		}

		return this.consultar(criteria);
	}

	/**
	 * @see DAO#inserir(Entidade)
	 */
	public Serializable inserir(final T entidade) {

		Serializable resultado = null;

		if (this.isReferencia(entidade)) {

			resultado = this.getTemplate().save(entidade);

			this.getTemplate().flush();
		}

		return resultado;
	}

	/**
	 * @see DAO#obter(Serializable)
	 */
	public T obter(final Serializable id) {

		T resultado = null;

		if (this.isReferencia(id)) {

			final Class<T> tipo = this.getTipoDaEntidade();

			resultado = (T) this.getTemplate().get(tipo, id);
		}

		return resultado;
	}

	/**
	 * @see DAO#remover(Entidade)
	 */
	public void remover(final T entidade) {

		if (this.isReferencia(entidade)) {

			this.carregarEntidadePersistente(entidade);

			this.getTemplate().delete(entidade);

			this.getTemplate().flush();
		}
	}

	/**
	 * @see DAO#removerTodos(Collection)
	 */
	public void removerTodos(final Collection<T> entidades) {

		if (this.isReferencia(entidades)) {

			for (final T entidade : entidades) {

				this.remover(entidade);
			}
		}
	}

	/**
	 * @see DAO#salvar(Entidade)
	 */
	public void salvar(final T entidade) {

		if (this.isReferencia(entidade)) {

			this.getTemplate().saveOrUpdate(entidade);

			this.getTemplate().flush();
		}
	}

	/**
	 * @see DAO#mesclar(Entidade)
	 */
	public void mesclar(final T entidade) {

		if (this.isReferencia(entidade)) {

			this.getTemplate().merge(entidade);

			this.getTemplate().flush();
		}
	}

	/**
	 * Efetua o load da entidade caso esta não seja uma entidade persistente.
	 * 
	 * @param entidade
	 *            Entidade que será carregada como persistente.
	 */
	protected void carregarEntidadePersistente(final T entidade) {

		if (this.isReferencia(entidade) && !this.isPersistente(entidade)) {

			final Serializable id = entidade.getId();

			this.getTemplate().load(entidade, id);
		}
	}

	/**
	 * Retorna true se a entidade for persistente.
	 * 
	 * @param entidade
	 *            Entidade validada.
	 * 
	 * @return true se a entidade for persistente.
	 */
	protected boolean isPersistente(final T entidade) {

		return (this.getTemplate().contains(entidade));
	}

	/**
	 * Efetua a consulta de um criteria.
	 * 
	 * @param criteria
	 *            Critéria que será executada.
	 * 
	 * @return Coleção de entidades.
	 */
	protected Collection<T> consultar(final Criteria criteria) {

		final Collection<T> colecao = criteria.list();

		return colecao;
	}

	/**
	 * Efetua a consulta de uma query.
	 * 
	 * @param query
	 *            Query que será executada.
	 * 
	 * @return Coleção de entidades.
	 */
	protected Collection<T> consultar(final Query query) {

		final Collection<T> colecao = query.list();

		return colecao;
	}

	/**
	 * Efetua a consulta de um hql.
	 * 
	 * @param hql
	 *            HQL que será executado.
	 * @return Coleção de entidades.
	 */
	protected Collection<T> consultar(final String hql) {

		final Query query = this.novaQuery(hql);

		return this.consultar(query);
	}

	/**
	 * Obtem a entidade solicitada.
	 * 
	 * @param entidade
	 *            Entidade
	 * @return entidade
	 */
	protected T obter(final T entidade) {

		T resultado = null;

		if (this.isReferencia(entidade)) {

			resultado = this.obter(entidade.getId());
		}

		return resultado;
	}

	/**
	 * Consulta uma entidade do criteria passado por parâmetro.
	 * 
	 * @param criteria
	 *            Criteria que será executado.
	 * 
	 * @return Entidade
	 */
	protected T obter(final Criteria criteria) {

		final Collection<T> colecao = this.consultar(criteria);

		return UtilColecao.getElementoDoIndice(colecao, 0);
	}

	/**
	 * Consulta uma entidade da query passado por parâmetro.
	 * 
	 * @param query
	 *            Query que será executado.
	 * 
	 * @return Entidade
	 */
	protected T obter(final Query query) {

		final T resultado = (T) query.uniqueResult();

		return resultado;
	}

	/**
	 * Consulta uma entidade da query passado por parâmetro.
	 * 
	 * @param hql
	 *            HQL que será executado.
	 * 
	 * @return Entidade
	 */
	protected T obter(final String hql) {

		final Collection<T> colecao = this.consultar(hql);

		return UtilColecao.getElementoDoIndice(colecao, 0);
	}

	/**
	 * Retorna uma alias da consulta atual, caso o alias não exista ele será
	 * criado.
	 * 
	 * @param query
	 *            Query
	 * @param alias
	 *            Alias
	 * 
	 * @return alias da consulta atual
	 */
	protected Criteria getAlias(final Criteria query, final String alias) {

		Criteria resultado = this.recuperarSubCriteria(query, alias);

		if (!this.isReferencia(resultado)) {

			resultado = query.createAlias(alias, alias);
		}

		return resultado;
	}

	/**
	 * Retorna uma sub criteria da consulta atual, caso a sub criteira não
	 * exista ela será criada.
	 * 
	 * @param query
	 *            Query
	 * @param alias
	 *            Alias
	 * 
	 * @return sub criteria da consulta atual
	 */
	protected Criteria getCriteria(final Criteria query, final String alias) {

		Criteria resultado = this.recuperarSubCriteria(query, alias);

		if (!this.isReferencia(resultado)) {

			resultado = query.createCriteria(alias, alias);
		}

		return resultado;
	}

	/**
	 * Retorna o tipo da entidade. O tipo é recuperado a partir do generics.
	 * 
	 * @return Classe da entidade.
	 */
	protected Class<T> getTipoDaEntidade() {

		final Type type[] = ((ParameterizedTypeImpl) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments();

		return (Class<T>) type[0];
	}

	/**
	 * @param objetos
	 *            Objetos que serão validados.
	 * 
	 * @return true se o objeto tiver referência.
	 * 
	 * @see UtilObjeto#isReferencia(Object)
	 */
	protected boolean isReferencia(final Object... objetos) {

		return UtilObjeto.isReferenciaTodos(objetos);
	}

	/**
	 * @param strings
	 *            Strings que serão validadas.
	 * 
	 * @return true se a string for vazia.
	 * 
	 * @see UtilString#isVazio(String)
	 */
	protected boolean isVazio(final String... strings) {

		return UtilString.isVazioTodos(strings);
	}

	/**
	 * Retorna true se a coleção estiver vazia.
	 * 
	 * @param colecao
	 *            Coleção validada
	 * 
	 * @return true se a coleção estiver vazia.
	 */
	protected boolean isVazio(final Collection<?> colecao) {

		return UtilColecao.isVazio(colecao);
	}

	/**
	 * Retorna true se o valor passado for zero ou nulo.
	 * 
	 * @param valor
	 *            Valor que será validado
	 * 
	 * @return true se o valor passado for zero ou nulo.
	 */
	@SuppressWarnings("boxing")
	protected boolean isZero(final Number valor) {

		return !this.isReferencia(valor) || (valor.intValue() == 0);
	}

	/**
	 * Retorna nova ordenação ASC.
	 * 
	 * @param propriedade
	 *            propriedade que será ordenada.
	 * 
	 * @return ordenação ASC
	 */
	protected Order novaOrdenacaoASC(final String propriedade) {

		return Order.asc(propriedade);
	}

	/**
	 * Retorna nova ordenação DESC.
	 * 
	 * @param propriedade
	 *            propriedade que será ordenada.
	 * 
	 * @return ordenação DESC
	 */
	protected Order novaOrdenacaoDESC(final String propriedade) {

		return Order.desc(propriedade);
	}

	/**
	 * Retorna nova query.
	 * 
	 * @param hql
	 *            HQL
	 * 
	 * @return nova query.
	 */
	protected Query novaQuery(final String hql) {

		return this.getSession().createQuery(hql);
	}

	/**
	 * Retorna novo criteria para entidade.
	 * 
	 * @return novo criteria para entidade.
	 */
	protected Criteria novoCriteria() {

		final Class<T> tipo = this.getTipoDaEntidade();

		return this.getSession().createCriteria(tipo);
	}

	/**
	 * Retorna novo criteria.
	 * 
	 * @param propriedade
	 *            Propriedade
	 * @param objeto1
	 *            Argumento
	 * @param objeto2
	 *            Argumento
	 * 
	 * @return criteria
	 * 
	 * @see Restrictions#between(String, Object, Object)
	 */
	protected Criterion novoCriterioBetween(final String propriedade,
			final Object objeto1, final Object objeto2) {

		return Restrictions.between(propriedade, objeto1, objeto2);
	}

	/**
	 * Retorna novo criteria.
	 * 
	 * @param propriedade
	 *            Propriedade
	 * @param objeto1
	 *            Argumento
	 * 
	 * @return criteria
	 * 
	 * @see Restrictions#eq(String, Object)
	 */
	protected Criterion novoCriterioEQ(final String propriedade,
			final Object objeto1) {

		return Restrictions.eq(propriedade, objeto1);
	}

	/**
	 * Retorna novo criteria.
	 * 
	 * @param propriedade
	 *            Propriedade
	 * @param objeto1
	 *            Argumento
	 * 
	 * @return criterion
	 * 
	 * @see Restrictions#eq(String, Object)
	 */
	protected Criterion novoCriterioEQIgnoreCase(final String propriedade,
			final Object objeto1) {

		final SimpleExpression se = Restrictions.eq(propriedade, objeto1);

		se.ignoreCase();

		return se;
	}

	/**
	 * Retorna novo criteria.
	 * 
	 * @param propriedade
	 *            Propriedade
	 * @param str
	 *            Argumento
	 * 
	 * @return criteria
	 * 
	 * @see Restrictions#like(String, Object)
	 */
	protected Criterion novoCriterioLike(final String propriedade,
			final String str) {

		final SimpleExpression se = Restrictions.like(propriedade, str,
				MatchMode.ANYWHERE);

		se.ignoreCase();

		return se;
	}

	/**
	 * Retorna novo criteria.
	 * 
	 * @param propriedade
	 *            Propriedade
	 * @param str
	 *            Argumento
	 * 
	 * @return criteria
	 * 
	 * @see Restrictions#like(String, Object)
	 */
	protected Criterion novoCriterioLikeDireita(final String propriedade,
			final String str) {

		final SimpleExpression se = Restrictions.like(propriedade, str,
				MatchMode.START);

		se.ignoreCase();

		return se;
	}

	/**
	 * Retorna novo criteria.
	 * 
	 * @param propriedade
	 *            Propriedade
	 * @param str
	 *            Argumento
	 * 
	 * @return criteria
	 * 
	 * @see Restrictions#ne(String, Object)
	 */
	protected Criterion novoCriterioNE(final String propriedade,
			final Object str) {

		return Restrictions.ne(propriedade, str);
	}

	/**
	 * Remove a entidade pela chave primária.
	 * 
	 * @param id
	 *            Chave primária da entidade.
	 */
	protected void remover(final Serializable id) {

		if (this.isReferencia(id)) {

			final T entidade = this.obter(id);

			this.remover(entidade);
		}
	}

	/**
	 * Retorna o criterio principal do criterio informado, caso o criterio
	 * informado seja uma subcriteria, será recuperado o criterio pai até chegar
	 * ao criterio principal
	 * 
	 * @param query
	 *            Criterio
	 * 
	 * @return criterio principal.
	 */
	private Criteria getCriteriaPrincipal(final Criteria query) {

		Criteria resultado = query;

		final Class<?> subcriteria = CriteriaImpl.Subcriteria.class;

		while (UtilObjeto.isObjetoDoTipo(resultado, subcriteria)) {

			resultado = ((CriteriaImpl.Subcriteria) resultado).getParent();
		}

		return resultado;
	}

	/**
	 * Recupera uma sub criteria da query passada por parâmetro.
	 * 
	 * @param query
	 *            Query
	 * @param alias
	 *            Alias
	 * 
	 * @return sub criteria da query passada por parâmetro.
	 */
	private Criteria recuperarSubCriteria(final Criteria query,
			final String alias) {

		Criteria resultado = null;

		final Criteria principal = this.getCriteriaPrincipal(query);

		final Iterator<Criteria> it = ((CriteriaImpl) principal)
				.iterateSubcriteria();

		while (it.hasNext() && !this.isReferencia(resultado)) {

			final Criteria criteria = it.next();

			if (UtilString.isStringsIguais(criteria.getAlias(), alias)) {

				resultado = criteria;
			}
		}

		return resultado;
	}

}
