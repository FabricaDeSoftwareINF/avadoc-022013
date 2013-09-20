package br.ufg.inf.avadoc.managedbean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("session")
@Component(NavegacaoManagedBean.KEY_COMPONENT)
public class NavegacaoManagedBean extends AbstractManagedBean {

	private static final long serialVersionUID = -2655198545850791277L;

	public static final String KEY_COMPONENT = "navegacaoManagedBean";

	private String paginaSelecionada;

	public void navegacaoIndex() {
		this.paginaSelecionada = "index.xhtml";
	}

	public void navegacaoAjuda() {
		this.paginaSelecionada = "ajuda.xhtml";
	}

	public void navegacaoAvaliacao() {
		this.paginaSelecionada = "avaliacaoPassoUm.xhtml";
	}
        
        public void navegacaoAvaliacaoPassoDois() {
		this.paginaSelecionada = "avaliacaoPassoDois.xhtml";
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
		return this.paginaSelecionada;
	}

	public void setPaginaSelecionada(final String paginaSelecionada) {
		this.paginaSelecionada = paginaSelecionada;
	}

}
