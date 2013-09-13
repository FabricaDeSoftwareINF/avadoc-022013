/**
 * Author: Wilker Machado
 * 
 * Data de Criação: 11.07.2013
 */

	

function removerMensagemInfo() {

	$('.ui-messages-info').click(

	function() {

		$('.ui-messages-info').hide();
	});

}

/**
 * Define o input <p:calendar /> para ser exibido em Portugues.
 */
PrimeFaces.locales['pt_BR'] = {
	closeText : 'Fechar',
	prevText : 'Anterior',
	nextText : 'Próximo',
	currentText : 'Começo',
	monthNames : [ 'Janeiro', 'Fevereiro', 'Marco', 'Abril', 'Maio', 'Junho',
			'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro' ],
	monthNamesShort : [ 'Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago',
			'Set', 'Out', 'Nov', 'Dez' ],
	dayNames : [ 'Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta',
			'Sábado' ],
	dayNamesShort : [ 'Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sab' ],
	dayNamesMin : [ 'D', 'S', 'T', 'Q', 'Q', 'S', 'S' ],
	weekHeader : 'Semana',
	firstDay : 1,
	isRTL : false,
	showMonthAfterYear : false,
	yearSuffix : '',
	timeOnlyTitle : 'Horas',
	timeText : 'Tempo',
	hourText : 'Hora',
	minuteText : 'Minuto',
	secondText : 'Segundo',
	currentText : 'Data Atual',
	ampm : false,
	month : 'Mes',
	week : 'Semana',
	day : 'Dia',
	allDayText : 'Todo Dia'
};

var NULL = "";

/**
 * Variavel global responsavel por representar se o status dialog sera exibido
 * ou nao.
 */
var STATUS_DIALOG_SHOW = true;



/**
 * Desabilita a exibição do status dialog
 */
function disabilitarStatusDialog() {

	STATUS_DIALOG_SHOW = false;
}

/**
 * Habilita a exibição do status dialog
 */
function habilitarStatusDialog() {

	STATUS_DIALOG_SHOW = true;
}


