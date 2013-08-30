package br.ufg.inf.avadoc.ods;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

import br.ufg.inf.avadoc.modelo.atividade.ExtratoAtividades;

/**
 * OdsGerador
 * 
 * Responsável por gerar planilha .ODS do quadro sumário da avaliação.
 * 
 */
public class OdsGerador {

	/**
	 * Gera a planilha a partir de um template. <BR>
	 * Retorna planilha com as pontuações obtidas do extrato de atividades
	 * (RADOC).
	 * 
	 * @param extrato
	 *            extrato de atividades (RADOC)
	 * @return planilha com avaliação parcial
	 */
	public static SpreadSheet gerarPontuacaoOds(ExtratoAtividades extrato) {
		Sheet sheet = null;
		try {
			URL filePath = ClassLoader
					.getSystemResource("template_sumario.ods");
			File file = new File(filePath.toURI());
			sheet = SpreadSheet.createFromFile(file).getSheet(0);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}

		sheet.getCellAt("B1").setValue(extrato.getDocente().getNome());
		sheet.getCellAt("B2").setValue(extrato.getDocente().getMatricula());

		DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
		String periodo = sourceFormat.format(extrato.getDataInicial()) + " a "
				+ sourceFormat.format(extrato.getDataFinal());
		sheet.getCellAt("B4").setValue(periodo);

		sheet.getCellAt("C8").setValue(
				extrato.getAtividadeEnsino().getPontosGraduacao());
		sheet.getCellAt("C9").setValue(
				extrato.getAtividadeEnsino().getPontosPosGraduacao());
		sheet.getCellAt("C10").setValue(
				extrato.getAtividadeEnsino().getPontos());

		sheet.getCellAt("C13").setValue(
				extrato.getProducaoIntelectual().getProducaoCientifica()
						.getPontos());
		sheet.getCellAt("C14").setValue(
				extrato.getProducaoIntelectual().getProducaoArtisticaCultural()
						.getPontos());
		sheet.getCellAt("C15").setValue(
				extrato.getProducaoIntelectual()
						.getProducaoTecnicaTecnologica().getPontos());
		sheet.getCellAt("C16")
				.setValue(
						extrato.getProducaoIntelectual().getProducaoOutra()
								.getPontos());
		sheet.getCellAt("C17").setValue(
				extrato.getProducaoIntelectual().getPontos());

		sheet.getCellAt("C20").setValue(
				extrato.getAtividadePesquisaExtensao().getPesquisa()
						.getPontos());
		sheet.getCellAt("C21").setValue(
				extrato.getAtividadePesquisaExtensao().getExtensao()
						.getPontos());
		sheet.getCellAt("C22").setValue(
				extrato.getAtividadePesquisaExtensao().getPontos());

		sheet.getCellAt("C25").setValue(
				extrato.getAtividadeAdministrativaRepresentacao()
						.getDirecaoFuncaoGratificada().getPontos());
		sheet.getCellAt("C26").setValue(
				extrato.getAtividadeAdministrativaRepresentacao()
						.getAdministrativa().getPontos());
		sheet.getCellAt("C27").setValue(
				extrato.getAtividadeAdministrativaRepresentacao()
						.getOutraAdministrativa().getPontos());
		sheet.getCellAt("C28").setValue(
				extrato.getAtividadeAdministrativaRepresentacao()
						.getRepresentacaoFora().getPontos());
		sheet.getCellAt("C29").setValue(
				extrato.getAtividadeAdministrativaRepresentacao().getPontos());

		sheet.getCellAt("C32").setValue(
				extrato.getAtividadeOutra().getOrientacao().getPontos());
		sheet.getCellAt("C33").setValue(
				extrato.getAtividadeOutra().getBancaCurso().getPontos());
		sheet.getCellAt("C34").setValue(
				extrato.getAtividadeOutra().getAprendizadoAperfeicoamento()
						.getPontos());
		sheet.getCellAt("C35")
				.setValue(extrato.getAtividadeOutra().getPontos());

		sheet.getCellAt("C37").setValue(extrato.getPontos());

		return sheet.getSpreadSheet();
	}

}
