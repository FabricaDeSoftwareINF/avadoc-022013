package br.ufg.inf.avadoc.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("session")
@Component(AvaliacaoController.KEY_COMPONENT)
public class AvaliacaoController extends Controller {

	public static final String KEY_COMPONENT = "avaliacaoController";

	private static final long serialVersionUID = -7047823076987573930L;

	public void metodo() {

	}

}
