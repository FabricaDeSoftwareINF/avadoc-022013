package br.ufg.inf.avadoc.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Wilker Machado
 * 
 */
@Scope("session")
@Component(NavegacaoController.KEY_COMPONENT)
public class NavegacaoController extends Controller {

	private static final long serialVersionUID = -2655198545850791277L;

	public static final String KEY_COMPONENT = "navegacaoController";

	private String paginaSelecionada;

	public void navegacaoIndex() {
		this.paginaSelecionada = "index.xhtml";
	}

	public void navegacaoAjuda() {
		this.paginaSelecionada = "ajuda.xhtml";
	}

	public void navegacaoAvaliacao() {
		this.paginaSelecionada = "avaliacaoDocente.xhtml";
	}

	public void navegacaoConfiguracao() {
		this.paginaSelecionada = "configuracao.xhtml";
	}

	public void navegacaoHistorico() {
		this.paginaSelecionada = "historicoDocente.xhtml";
	}

	public void navegacaoImportacao() {
		this.paginaSelecionada = "importarDados.xhtml";
	}

	public void navegacaoInformacao() {
		this.paginaSelecionada = "informacaoDocente.xhtml";
	}

	public String getPaginaSelecionada() {
		return paginaSelecionada;
	}

	public void setPaginaSelecionada(String paginaSelecionada) {
		this.paginaSelecionada = paginaSelecionada;
	}

}
