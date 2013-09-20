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
			final int ano = 2009;
			final int dia = 12;
			cal.set(ano, Calendar.DECEMBER, dia);
			docente.setClasse("B");
			docente.setDataIngresso(cal);			
			docente.setLotacao("Faculdade de Letras - UFG");
			docente.setMatriculaSIAP("123" + matricula);			
			docente.setNivel("I");
		} else if (matricula.equals("2")){
			docente.setNome("Joaquim das Quintas");
			final int ano = 2010;
			final int dia = 1;
			cal.set(ano, Calendar.JANUARY, dia);
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
