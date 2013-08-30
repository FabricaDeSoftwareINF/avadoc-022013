package br.ufg.inf.avadoc.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.ufg.inf.avadoc.modelo.atividade.ExtratoAtividades;
import br.ufg.inf.avadoc.xml.XmlExtratoAtividades;

@Scope("session")
@Component(ImportacaoController.KEY_COMPONENT)
public class ImportacaoController extends Controller {

	private static final long serialVersionUID = 7444607033271769749L;

	public static final String KEY_COMPONENT = "importacaoController";

	private Boolean importacao = Boolean.FALSE;

	private Boolean renderedDocente = Boolean.TRUE;

	private UploadedFile arquivo;

	public void upload() {

		if (importacao) {

			if (arquivo != null) {
				
				String fileName = arquivo.getFileName();

				byte[] bytes = arquivo.getContents();

				InputStream is = new ByteArrayInputStream(bytes);
				
				String xmlStr = "";

				try {
					
					xmlStr = IOUtils.toString(is);
					
				} catch (IOException e) {
					
					e.printStackTrace();
					
				}
				
				ExtratoAtividades extrato = XmlExtratoAtividades.getExtrato(xmlStr);

				// XmlExtratoAtividades xml = new XmlExtratoAtividades(xmlStr);
				//
				// Thread th = new Thread(xml);
				//
				// th.start();
				
				FacesMessage msg = new FacesMessage("Succesful",arquivo.getFileName() + " is uploaded.");

				FacesContext.getCurrentInstance().addMessage(null, msg);

			}
		}
	}

	public void mudaRendered() {
		if (importacao) {
			renderedDocente = Boolean.FALSE;
		} else {
			renderedDocente = Boolean.TRUE;
		}
	}

	public Boolean getRenderedDocente() {
		return renderedDocente;
	}

	public void setRenderedDocente(Boolean renderedDocente) {
		this.renderedDocente = renderedDocente;
	}

	public Boolean getImportacao() {
		return importacao;
	}

	public void setImportacao(Boolean importacao) {
		this.importacao = importacao;
	}

	public UploadedFile getArquivo() {
		return arquivo;
	}

	public void setArquivo(UploadedFile arquivo) {
		this.arquivo = arquivo;
	}

}
