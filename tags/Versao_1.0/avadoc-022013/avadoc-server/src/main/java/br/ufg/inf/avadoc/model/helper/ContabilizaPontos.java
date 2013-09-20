package br.ufg.inf.avadoc.model.helper;

import br.ufg.inf.avadoc.entity.AtividadeAdministrativaRepresentacao;

/**
 * Calcula os pontos de cada atividade ou produ&ccedil;&atilde;o.
 * @author Ciro Anacleto
 * */
public class ContabilizaPontos {
	
	private AtividadeAdministrativaRepresentacao atividadeAdmRepresent;
	
	
	public ContabilizaPontos(){
		this.atividadeAdmRepresent = new AtividadeAdministrativaRepresentacao();
		
	}
	
	/**
	 * Calcula a pontua&ccedil;&atilde;o de uma atividade adminstrativa de representa&ccedil;&atilde;o
	 * 
	 * @param pontos
	 * @return resultado do somat&oacute;rio de cada atividade de representa&ccedil;&atilde;o
	 * */
	public double calculaPontosAtividadeAdminstrativaRepresentacao(){
		double 	resultado = atividadeAdmRepresent.getDirecaoFuncaoGratificada().getPontos()
				+ atividadeAdmRepresent.getAdministrativa().getPontos() 
				+ atividadeAdmRepresent.getOutraAdministrativa().getPontos()
				+ atividadeAdmRepresent.getRepresentacaoFora().getPontos();
		return resultado;
	}

}
