package br.ufg.inf.avadoc.managedbean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.primefaces.context.RequestContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.ufg.inf.avadoc.entity.Docente;
import br.ufg.inf.avadoc.web.util.Mocks;

@Scope("session")
@Component(AvaliacaoManagedBean.KEY_COMPONENT)
public class AvaliacaoManagedBean extends AbstractManagedBean implements Serializable {

    public static final String KEY_COMPONENT = "avaliacaoManagedBean";
    private static final long serialVersionUID = -7047823076987573930L;
    private Docente docente;
    private String matriculaSelecionada;
    private String tipoAvaliacao;

    public String getMatriculaSelecionada() {
        return matriculaSelecionada;
    }

    public void setMatriculaSelecionada(String matriculaSelecionada) {
        this.matriculaSelecionada = matriculaSelecionada;
    }
    
    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public void setTipoAvaliacao(String tipoAvaliacao) {
        this.tipoAvaliacao = tipoAvaliacao;
    }

    public String getTipoAvaliacao() {
        return tipoAvaliacao;
    }
    
    public void metodo() {
    }
    
    /**
     * Obtém os dados do docente a partir da matrícula informada na tela.
     */
    public void getDadosDocente() {	
    	docente = Mocks.obtenhaDocentePorMatricula(getMatriculaSelecionada());
    	
    	if (docente == null && getMatriculaSelecionada().equals("")) {
			RequestContext context = RequestContext.getCurrentInstance();
    		context.execute("caixaDialogo.show()");
    	}
    }

    /**
     * Converte um Calendar para uma string em formato de data brasileiro.
     * @param data Data do tipo calendar.
     * @return Data formatada como string em formato brasileiro.
     */
    public String convertaDataParaString(Calendar data) {
    	if (data == null) {
    		return "";
    	}
    	
    	SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");  
        return s.format(data.getTime());
    }
}
