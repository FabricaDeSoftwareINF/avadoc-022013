package br.ufg.inf.avadoc.model.enums;

/**
 *
 * Tipo de Avaliação
 * @author Guilherme
 */
public enum EnumTipoAvaliacao {

    PROGRESSAO_HORIZONTAL("ProgressaoHorizontal"),
    ESTAGIO_PROBATORIO("EstagioProbatorio");
    
    private String tipo;
    
    private EnumTipoAvaliacao(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
