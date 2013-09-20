package br.ufg.inf.avadoc.managedbean;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.ufg.inf.avadoc.xml.XmlExtratoAtividades;

@Scope("session")
@Component(ImportacaoManagedBean.KEY_COMPONENT)
public class ImportacaoManagedBean extends AbstractManagedBean {

	private static final long serialVersionUID = 7444607033271769749L;

	public static final String KEY_COMPONENT = "importacaoManagedBean";

	private Boolean importacao = Boolean.FALSE;

	private Boolean renderedDocente = Boolean.TRUE;

	private UploadedFile arquivo;

	public void upload() {
		if (this.importacao && this.arquivo != null) {
			this.arquivo.getFileName();
			final byte[] bytes = this.arquivo.getContents();
			final InputStream is = new ByteArrayInputStream(bytes);
			String xmlStr = "";

			try {
				xmlStr = IOUtils.toString(is);
			} catch (final IOException e) {
				Logger.getLogger("IOException");
			}

			XmlExtratoAtividades.getExtrato(xmlStr);

			final FacesMessage msg = new FacesMessage("Succesful",
					this.arquivo.getFileName() + " is uploaded.");

			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void mudaRendered() {
		if (this.importacao) {
			this.renderedDocente = Boolean.FALSE;
		} else {
			this.renderedDocente = Boolean.TRUE;
		}
	}

	public Boolean getRenderedDocente() {
		return this.renderedDocente;
	}

	public void setRenderedDocente(final Boolean renderedDocente) {
		this.renderedDocente = renderedDocente;
	}

	public Boolean getImportacao() {
		return this.importacao;
	}

	public void setImportacao(final Boolean importacao) {
		this.importacao = importacao;
	}

	public UploadedFile getArquivo() {
		return this.arquivo;
	}

	public void setArquivo(final UploadedFile arquivo) {
		this.arquivo = arquivo;
	}

}
