package br.ufg.inf.avadoc.persistencia.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import br.ufg.inf.avadoc.modelo.Usuario;
import br.ufg.inf.avadoc.persistencia.dao.UsuarioDAO;

@Repository
public class UsuarioDAOImpl extends GenericDAOImpl<Usuario> implements
		UsuarioDAO {

	private static final long serialVersionUID = 6517998201359048719L;

	@Autowired
	public UsuarioDAOImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public HibernateTemplate getTemplate() {
		return this.getHibernateTemplate();
	}

}
