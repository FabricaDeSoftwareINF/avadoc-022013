package br.ufg.inf.avadoc.persistencia.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import br.ufg.inf.avadoc.modelo.Docente;
import br.ufg.inf.avadoc.modelo.atividade.ExtratoAtividades;
import br.ufg.inf.avadoc.persistencia.dao.DocenteDAO;
import br.ufg.inf.avadoc.persistencia.dao.ExtratoAtividadesDAO;

@Repository
public class ExtratoAtividadesDAOImpl extends GenericDAOImpl<ExtratoAtividades>
		implements ExtratoAtividadesDAO {

	private static final long serialVersionUID = 6517998201359048719L;

	@Autowired
	public ExtratoAtividadesDAOImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	@Override
	public Serializable inserir(ExtratoAtividades entidade) {
		DocenteDAO dao = new DocenteDAOImpl(getSessionFactory());
		
		Docente docentePorMatricula = new Docente();
		docentePorMatricula.setMatricula(new String(entidade.getDocente().getMatricula()));
		
		List<Docente> docentes = (List<Docente>) dao.consultar(docentePorMatricula);
		for(Docente d : docentes) {
			if (d.getMatricula().equals(entidade.getDocente().getMatricula())) {
				entidade.setDocente(d);
				return super.inserir(entidade);
			}
		}
		
		dao.inserir(entidade.getDocente());
		return super.inserir(entidade);
	}
	
	@Override
	public void alterar(ExtratoAtividades entidade) {
		DocenteDAO dao = new DocenteDAOImpl(getSessionFactory());
		
		Docente docentePorMatricula = new Docente();
		docentePorMatricula.setMatricula(entidade.getDocente().getMatricula());
		
		List<Docente> docentes = (List<Docente>) dao.consultar(docentePorMatricula);
		for(Docente d : docentes) {
			if (d.getMatricula().equals(entidade.getDocente().getMatricula())) {
				entidade.setDocente(d);
				super.alterar(entidade);
			}
		}
		super.alterar(entidade);
	}

	@Override
	public HibernateTemplate getTemplate() {
		return this.getHibernateTemplate();
	}

}
