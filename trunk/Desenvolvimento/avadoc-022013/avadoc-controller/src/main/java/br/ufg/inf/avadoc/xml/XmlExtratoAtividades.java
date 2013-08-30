package br.ufg.inf.avadoc.xml;

import java.io.ByteArrayInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jopendocument.dom.spreadsheet.SpreadSheet;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.ufg.inf.avadoc.enums.EnumTipoProducao;
import br.ufg.inf.avadoc.modelo.Docente;
import br.ufg.inf.avadoc.modelo.atividade.AtividadeAdministrativaRepresentacao;
import br.ufg.inf.avadoc.modelo.atividade.AtividadeEnsino;
import br.ufg.inf.avadoc.modelo.atividade.AtividadeOutra;
import br.ufg.inf.avadoc.modelo.atividade.AtividadePesquisaExtensao;
import br.ufg.inf.avadoc.modelo.atividade.ExtratoAtividades;
import br.ufg.inf.avadoc.modelo.atividade.Producao;
import br.ufg.inf.avadoc.modelo.atividade.ProducaoIntelectual;
import br.ufg.inf.avadoc.modelo.atividade.Produto;
import br.ufg.inf.avadoc.modelo.atividade.ProdutoPontosAnuais;
import br.ufg.inf.avadoc.modelo.atividade.ProdutoPontosHora;
import br.ufg.inf.avadoc.modelo.atividade.ProdutoPontosMensais;
import br.ufg.inf.avadoc.ods.OdsGerador;

/**
 * XmlExtratoAtividades
 * 
 * Auxilia na leitura do extrato de atividades contido no xml recebido do SICAD.
 * 
 */
public class XmlExtratoAtividades implements Runnable {

	private String arquivoXml;

	private ExtratoAtividades extrato;

	private SpreadSheet odsExport;

	public XmlExtratoAtividades(String xml) {
		this.arquivoXml = xml;
		extrato = new ExtratoAtividades();
	}

	/**
	 * Retorna extrato de atividades (RADOC) já pontuado.
	 * 
	 * @param xml
	 *            recebido do SICAD
	 * @return extrato pontuado
	 */
	public static ExtratoAtividades getExtrato(String xml) {
		ExtratoAtividades extrato = null;
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new ByteArrayInputStream(xml
					.getBytes("UTF-8")));

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("Extrato");

			extrato = new ExtratoAtividades();

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					Docente docente = new Docente();
					docente.setMatricula(eElement
							.getElementsByTagName("MatriculaDocente").item(0)
							.getTextContent());
					extrato.setDocente(docente);

					DateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd");
					String dateAsString = eElement
							.getElementsByTagName("DataInicial").item(0)
							.getTextContent();
					Date dataInicial = sourceFormat.parse(dateAsString);
					dateAsString = eElement.getElementsByTagName("DataFinal")
							.item(0).getTextContent();
					Date dataFinal = sourceFormat.parse(dateAsString);

					extrato.setDataInicial(dataInicial);
					extrato.setDataFinal(dataFinal);

					AtividadeEnsino atividadeEnsino = getAtividadeEnsino((Element) eElement
							.getElementsByTagName("AtividadeEnsino").item(0));
					extrato.setAtividadeEnsino(atividadeEnsino);

					ProducaoIntelectual producaoIntelectual = getProducaoIntelectual((Element) eElement
							.getElementsByTagName("ProducaoIntelectual")
							.item(0));
					extrato.setProducaoIntelectual(producaoIntelectual);

					AtividadePesquisaExtensao atividadePesquisaExtensao = getAtividadePesquisaExtensao((Element) eElement
							.getElementsByTagName("AtividadePesquisaExtensao")
							.item(0));
					extrato.setAtividadePesquisaExtensao(atividadePesquisaExtensao);

					AtividadeAdministrativaRepresentacao atividadeAdministrativaRepresentacao = getAtividadeAdministrativaRepresentacao((Element) eElement
							.getElementsByTagName(
									"AtividadeAdministrativaRepresentacao")
							.item(0));
					extrato.setAtividadeAdministrativaRepresentacao(atividadeAdministrativaRepresentacao);

					AtividadeOutra atividadeOutra = getAtividadeOutra((Element) eElement
							.getElementsByTagName("AtividadeOutra").item(0));
					extrato.setAtividadeOutra(atividadeOutra);
				}
			}
			return extrato;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return extrato;
	}

	/**
	 * Retorna atividades de ensino
	 * 
	 * @param element
	 *            parte do xml que contém as atividades de ensino
	 * @return Atividade de ensino
	 */
	private static AtividadeEnsino getAtividadeEnsino(Element element) {
		int horasGraduacaoPresencial = Integer.parseInt(element
				.getElementsByTagName("HorasSemanaisGraduacaoPresencial")
				.item(0).getTextContent());
		int horasGraduacaoDistancia = Integer.parseInt(element
				.getElementsByTagName("HorasSemanaisGraduacaoDistancia")
				.item(0).getTextContent());
		int horasPosPresencial = Integer.parseInt(element
				.getElementsByTagName("HorasSemanaisPosPresencial").item(0)
				.getTextContent());
		int horasPosDistancia = Integer.parseInt(element
				.getElementsByTagName("HorasSemanaisPosDistancia").item(0)
				.getTextContent());

		AtividadeEnsino atividade = new AtividadeEnsino();
		atividade
				.setGraduacaoHorasAulaSemanaisPresenciais(horasGraduacaoPresencial);
		atividade
				.setGraduacaoHorasAulaSemanaisDistancia(horasGraduacaoDistancia);
		atividade.setPosGradHorasAulaSemanaisPresenciais(horasPosPresencial);
		atividade.setPosGradHorasAulaSemanaisDistancia(horasPosDistancia);
		return atividade;
	}

	/**
	 * Retorna atividades de produção intelectual
	 * 
	 * @param element
	 *            parte do xml que contém as atividades de produção intelectual
	 * @return ProducaoIntelectual
	 */
	private static ProducaoIntelectual getProducaoIntelectual(Element element) {
		ProducaoIntelectual producaoIntelectual = new ProducaoIntelectual();
		Element e = (Element) element.getElementsByTagName("Cientifica")
				.item(0);
		producaoIntelectual.setProducaoCientifica(getProducaoCientifica(e));

		e = (Element) element.getElementsByTagName("ArtisticaCultural").item(0);
		producaoIntelectual
				.setProducaoArtisticaCultural(getProducaoArtisticaCultural(e));

		e = (Element) element.getElementsByTagName("TecnicaTecnologica")
				.item(0);
		producaoIntelectual
				.setProducaoTecnicaTecnologica(getProducaoTecnicaTecnologica(e));

		e = (Element) element.getElementsByTagName("Outro").item(0);
		producaoIntelectual.setProducaoOutra(getProducaoOutro(e));

		return producaoIntelectual;
	}

	/**
	 * Retorna atividades de pesquisa e extensão
	 * 
	 * @param element
	 *            parte do xml que contém as atividades de pesquisa e extensão
	 * @return AtividadePesquisaExtensao
	 */
	private static AtividadePesquisaExtensao getAtividadePesquisaExtensao(
			Element e) {
		AtividadePesquisaExtensao atividadePesquisaExtensao = new AtividadePesquisaExtensao();
		Element element = (Element) e.getElementsByTagName("Pesquisa").item(0);
		atividadePesquisaExtensao.setPesquisa(getAtividadePesquisa(element));

		element = (Element) e.getElementsByTagName("Extensao").item(0);
		atividadePesquisaExtensao.setExtensao(getAtividadeExtensao(element));

		return atividadePesquisaExtensao;
	}

	/**
	 * Retorna atividades administrativas e de representação
	 * 
	 * @param element
	 *            parte do xml que contém as atividades administrativas e de
	 *            representação
	 * @return AtividadeAdministrativaRepresentacao
	 */
	private static AtividadeAdministrativaRepresentacao getAtividadeAdministrativaRepresentacao(
			Element e) {
		AtividadeAdministrativaRepresentacao atividadeAdministrativaRepresentacao = new AtividadeAdministrativaRepresentacao();
		Element element = (Element) e.getElementsByTagName(
				"DirecaoFuncaoGratificada").item(0);
		atividadeAdministrativaRepresentacao
				.setDirecaoFuncaoGratificada(getDirecaoFuncaoGratificada(element));

		element = (Element) e.getElementsByTagName("Administrativa").item(0);
		atividadeAdministrativaRepresentacao
				.setAdministrativa(getAdministrativa(element));

		element = (Element) e.getElementsByTagName("OutraAdministrativa").item(
				0);
		atividadeAdministrativaRepresentacao
				.setOutraAdministrativa(getOutraAdministrativa(element));

		element = (Element) e.getElementsByTagName("RepresentacaoFora").item(0);
		atividadeAdministrativaRepresentacao
				.setRepresentacaoFora(getRepresentacaoFora(element));

		return atividadeAdministrativaRepresentacao;
	}

	/**
	 * Retorna outras atividades
	 * 
	 * @param element
	 *            parte do xml que contém as outras atividades
	 * @return AtividadeOutra
	 */
	private static AtividadeOutra getAtividadeOutra(Element e) {
		AtividadeOutra atividadeOutra = new AtividadeOutra();
		Element element = (Element) e.getElementsByTagName("Orientacao")
				.item(0);
		atividadeOutra.setOrientacao(getOrientacao(element));

		element = (Element) e.getElementsByTagName("BancaCurso").item(0);
		atividadeOutra.setBancaCurso(getBancaCurso(element));

		element = (Element) e
				.getElementsByTagName("AprendizadoAperfeicoamento").item(0);
		atividadeOutra
				.setAprendizadoAperfeicoamento(getAprendizadoAperfeicoamento(element));

		return atividadeOutra;
	}

	private static Producao getProducaoCientifica(Element e) {
		return getProducao(e, EnumTipoProducao.PRODUCAO_CIENTIFICA.getKey());
	}

	private static Producao getProducaoArtisticaCultural(Element e) {
		return getProducao(e,
				EnumTipoProducao.PRODUCAO_ARTISTICA_CULTURAL.getKey());
	}

	private static Producao getProducaoTecnicaTecnologica(Element e) {
		return getProducao(e,
				EnumTipoProducao.PRODUCAO_TECNICA_TECNOLOGICA.getKey());
	}

	private static Producao getProducaoOutro(Element e) {
		return getProducao(e, EnumTipoProducao.PRODUCAO_OUTRO.getKey());
	}

	private static Producao getAtividadePesquisa(Element e) {
		return getProducao(e, EnumTipoProducao.ATIVIDADE_PESQUISA.getKey());
	}

	private static Producao getAtividadeExtensao(Element e) {
		return getProducao(e, EnumTipoProducao.ATIVIDADE_EXTENSAO.getKey());
	}

	private static Producao getDirecaoFuncaoGratificada(Element element) {
		return getProducao(element,
				EnumTipoProducao.DIRECAO_FUNCAO_GRATIFICADA.getKey());
	}

	private static Producao getAdministrativa(Element element) {
		return getProducao(element,
				EnumTipoProducao.ATIVIDADE_ADMINISTRATIVA.getKey());
	}

	private static Producao getOutraAdministrativa(Element element) {
		return getProducao(element,
				EnumTipoProducao.ATIVIDADE_ADMINISTRATIVA_OUTRAS.getKey());
	}

	private static Producao getRepresentacaoFora(Element element) {
		return getProducao(element,
				EnumTipoProducao.ATIVIDADE_REPRESENTACAO.getKey());
	}

	private static Producao getOrientacao(Element element) {
		return getProducao(element,
				EnumTipoProducao.ATIVIDADE_ACADEMICA_ORIENTACAO.getKey());
	}

	private static Producao getBancaCurso(Element element) {
		return getProducao(element,
				EnumTipoProducao.ATIVIDADE_ACADEMICA_BANCAS_CURSOS.getKey());
	}

	private static Producao getAprendizadoAperfeicoamento(Element element) {
		return getProducao(element,
				EnumTipoProducao.ATIVIDADE_APRENDIZADO_APERFEICOAMENTO.getKey());
	}

	/**
	 * Atividades/Produtos que possam ser pontuados.
	 * 
	 * @param element
	 *            parte do Xml com atividades/produtos da sub-atividade passada
	 *            por parâmetro
	 * @param subAtividade
	 *            sub-atividades contidas no ANEXO I DA RESOLUÇÃO - CONSUNI Nº
	 *            21/2009
	 * @return producao atividades/produtos de uma sub-atividade
	 */
	private static Producao getProducao(Element element, String subAtividade) {
		Producao producao = new Producao();

		NodeList atividades = element.getElementsByTagName("Atividade");
		for (int i = 0; i < atividades.getLength(); i++) {
			Element atividade = (Element) atividades.item(i);
			String codigo = atividade.getElementsByTagName("Id").item(0)
					.getTextContent();

			Produto produto = XmlAtividades.getProduto(codigo, subAtividade);

			if (atividade.getElementsByTagName("Anos").getLength() > 0
					&& !atividade.getElementsByTagName("Anos").item(0)
							.getTextContent().isEmpty()) {
				int anos = Integer.parseInt(atividade
						.getElementsByTagName("Anos").item(0).getTextContent());

				ProdutoPontosAnuais produtoAno = new ProdutoPontosAnuais();
				produtoAno.setCodigo(produto.getCodigo());
				produtoAno.setPontos(produto.getPontos());
				produtoAno.setAnos(anos);
				produto.setPontos(produtoAno.getPontos());
			}

			if (atividade.getElementsByTagName("Meses").getLength() > 0
					&& !atividade.getElementsByTagName("Meses").item(0)
							.getTextContent().isEmpty()) {
				int meses = Integer
						.parseInt(atividade.getElementsByTagName("Meses")
								.item(0).getTextContent());

				ProdutoPontosMensais produtoMes = new ProdutoPontosMensais();
				produtoMes.setCodigo(produto.getCodigo());
				produtoMes.setPontos(produto.getPontos());
				produtoMes.setMeses(meses);
				produto.setPontos(produtoMes.getPontos());
			}

			if (atividade.getElementsByTagName("Horas").getLength() > 0
					&& !atividade.getElementsByTagName("Horas").item(0)
							.getTextContent().isEmpty()) {
				int horas = Integer
						.parseInt(atividade.getElementsByTagName("Horas")
								.item(0).getTextContent());

				ProdutoPontosHora produtoHora = new ProdutoPontosHora();
				produtoHora.setCodigo(produto.getCodigo());
				produtoHora.setPontos(produto.getPontos());
				produtoHora.setHoras(horas);
				produto.setPontos(produtoHora.getPontos());
			}
			producao.addProduto(produto);
		}

		return producao;
	}

	@SuppressWarnings("static-access")
	@Override
	public void run() {
		
		extrato = this.getExtrato(this.arquivoXml);
		
		odsExport = OdsGerador.gerarPontuacaoOds(extrato);
		
	}

	public String getArquivoXml() {
		return arquivoXml;
	}

	public void setArquivoXml(String arquivoXml) {
		this.arquivoXml = arquivoXml;
	}

	public ExtratoAtividades getExtrato() {
		return extrato;
	}

	public void setExtrato(ExtratoAtividades extrato) {
		this.extrato = extrato;
	}

	public SpreadSheet getOdsExport() {
		return odsExport;
	}

	public void setOdsExport(SpreadSheet odsExport) {
		this.odsExport = odsExport;
	}

}
