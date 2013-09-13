package br.ufg.inf.avadoc.managedbean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("session")
@Component(AvaliacaoManagedBean.KEY_COMPONENT)
public class AvaliacaoManagedBean extends AbstractManagedBean {

	public static final String KEY_COMPONENT = "avaliacaoManagedBean";

	private static final long serialVersionUID = -7047823076987573930L;

	public void metodo() {

	}

}
