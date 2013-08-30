package br.ufg.inf.avadoc.xml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.ufg.inf.avadoc.modelo.atividade.Produto;

/**
 * XmlAtividades
 * 
 * Auxilia na leitura das atividades e pontuações contidas no arquivo
 * 'atividadesPontuacao.xml'.
 * 
 */
public class XmlAtividades {
	private XmlAtividades() {
	}

	/**
	 * Retorna produtos de uma subatividade.
	 * 
	 * @param nomeSubAtividade
	 *            a ser procurada
	 * @return produtos da subatividade
	 */
	public static List<Produto> getProdutos(String nomeSubAtividade) {
		try {
			InputStream xmlFile = ClassLoader
					.getSystemResourceAsStream("atividades.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("atividade");

			List<Produto> produtos = new ArrayList<Produto>();

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					if (eElement.getElementsByTagName("subatividade").item(0)
							.getTextContent().equals(nomeSubAtividade)) {

						Produto p = new Produto();
						p.setCodigo(eElement.getAttribute("id"));
						p.setPontos(Integer.parseInt(eElement
								.getElementsByTagName("pontos").item(0)
								.getTextContent()));
						p.setPontuacaoMaxima(Integer.parseInt(eElement
								.getElementsByTagName("maximo").item(0)
								.getTextContent()));
						produtos.add(p);
					}
				}
			}
			return produtos;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	/**
	 * Retorna um produto de uma subatividade através de seu código.
	 * 
	 * @param codigo
	 *            do produto
	 * @param nomeSubAtividade
	 *            nome da subAtividade
	 * @return <code>produto</code> se encontrado <code>false</code> caso
	 *         contrário.
	 */
	public static Produto getProduto(String codigo, String nomeSubAtividade) {
		List<Produto> produtos = getProdutos(nomeSubAtividade);
		for (Produto p : produtos) {
			if (p.getCodigo().equals(codigo)) {
				return p;
			}
		}
		return null;
	}

}
