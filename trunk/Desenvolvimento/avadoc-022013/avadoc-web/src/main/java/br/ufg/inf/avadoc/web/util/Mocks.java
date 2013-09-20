package br.ufg.inf.avadoc.web.util;

import java.util.Calendar;
import br.ufg.inf.avadoc.entity.Docente;

public final class Mocks {
	
	private Mocks() {		
	}
	
	public static Docente obtenhaDocentePorMatricula(String matricula) {
		Docente docente = new Docente();
		Calendar cal = Calendar.getInstance();
		
		if (matricula.equals("1")) {
			docente.setNome("Fulano de Tal");
			cal.set(2009, Calendar.DECEMBER, 12);
			docente.setClasse("B");
			docente.setDataIngresso(cal);			
			docente.setLotacao("Faculdade de Letras - UFG");
			docente.setMatriculaSIAP("123" + matricula);			
			docente.setNivel("I");
		} else if (matricula.equals("2")){
			docente.setNome("Joaquim das Quintas");
			cal.set(2010, Calendar.JANUARY, 1);
			docente.setClasse("A");
			docente.setDataIngresso(cal);
			docente.setLotacao("Instituto de Informática - UFG");
			docente.setMatriculaSIAP("321" + matricula);
			docente.setNivel("V");
		} else {
			return null;
		}		
		
		return docente;
	}
}
