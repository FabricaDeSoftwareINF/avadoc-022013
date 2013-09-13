package br.ufg.inf.avadoc.managedbean;

import br.ufg.inf.avadoc.entity.Docente;
import br.ufg.inf.avadoc.model.enums.EnumTipoAvaliacao;
import java.io.Serializable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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
}
