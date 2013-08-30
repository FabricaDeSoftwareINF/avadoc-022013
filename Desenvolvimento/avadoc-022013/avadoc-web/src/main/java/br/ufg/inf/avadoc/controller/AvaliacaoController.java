package br.ufg.inf.avadoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.ufg.inf.avadoc.bean.DocenteBean;

@Scope("session")
@Component(AvaliacaoController.KEY_COMPONENT)
public class AvaliacaoController extends Controller {

	public static final String KEY_COMPONENT = "avaliacaoController";

	private static final long serialVersionUID = -7047823076987573930L;

	@Autowired
	private DocenteBean docenteService;

	public void metodo() {
		

		
	}

	public DocenteBean getDocenteService() {
		return docenteService;
	}

}
