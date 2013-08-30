package br.ufg.inf.avadoc.bean;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufg.inf.avadoc.persistencia.dao.DocenteDAO;

@Service
public class DocenteBean implements Serializable {

	private static final long serialVersionUID = 6805046318065943014L;
	
	@Autowired
	private DocenteDAO dao;

	public DocenteDAO getDao() {

		return this.dao;
	}
}
