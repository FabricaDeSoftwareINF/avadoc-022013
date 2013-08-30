package br.ufg.inf.avadoc.modelo.pontuacao;

import br.ufg.inf.avadoc.enums.EnumJornadaTrabalho;
import br.ufg.inf.avadoc.modelo.Nota;

/**
 * Classe que verifica as exigências para aprovação do docente que está em
 * Estágio probatório e/ou Progressão Horizontal. Tais exigências são descritas
 * no <b>Art.27</b> e <b>Art.16</b> da <b>CONSUNI 21/2009</b>, disponível em:
 * <br>
 * <a href="http://cavi.prodirh.ufg.br/uploads/65/original_Resolucao_CONSUNI_2009_0021.pdf">CONSUNI 21/2009</a>
 *
 */
public final class VerificaExigencias {

    /**
     * Verifica as exigências do docente a qual está em estágio probatório.
     */
    public static final class EstagioProbatorio {

        /**
         * Média final a qual o docente deverá atingir ao final de 36 meses em
         * estágio probatório.
         */
        public static final double MEDIA_FINAL = 7.5;

        /**
         * Média final da diretoria ou chefia de gabinete ao final de 36 meses
         * em estágio probatório.
         */
        public static final double MEDIA_N2 = 5.0;

        /**
         * Média final da nota do corpo discente  ao final de 36 meses em
         * estágio probatório.
         */
        public static final double MEDIA_N3 = 5.0;

        /**
         * Verifica a aprovação do docente a qual está em estágio probatório
         * conforme a CONSUNI 21/2009.
         * @param notaFinal nota final do docente ao longo de 36 meses.
         * @param ocupaDirecao flag para verificar se o docente ocupa cargo de
         * direção/administrativa
         * @param participaPrograma flag para verificar se o docente realizou
         * as atividades da PROGRAD e PRODIRH, Art. 8° da CONSUNI 21/2009.
         * @param mediaN2 média das notas da chefia de gabinete ou da diretoria
         * no decorrer de 36 meses
         * @param mediaN3 média das notas do corpo discente no decorrer de 36
         * meses
         * @param medAtvInt média das notas das atividades
         * intelecutais produzidas
         * @param medAtvEns médida média das notas das atividades de ensino
         * realizadas
         * @param jorTra especifica qual a jornada de trabalho do
         * docente
         * @return verdadeiro para aprovado e falso para reprovado
         */
        public static boolean verificaAprovacao(final Nota notaFinal,
                final boolean ocupaDirecao, final boolean participaPrograma,
                final double mediaN2, final double mediaN3,
                final double medAtvInt, final double medAtvEns,
                final EnumJornadaTrabalho jorTra) {

            boolean exigI = verificaExigenciaI(medAtvEns, ocupaDirecao);
            boolean exigII = verificaExigenciaII(medAtvInt, jorTra, medAtvEns);
            boolean exigIII = verificaExigenciaIII(mediaN2, mediaN3);
            boolean exigIV = verificaExigenciaIV(participaPrograma);
            boolean exigV = verificaExigenciaV(notaFinal);

            return exigI && exigII && exigIII && exigIV && exigV;

        }

        /**
         * Método que verifica a exigência do Art. 16 do CONSUNI 21/2009 no item
         * I Atividades de Ensino.
         * @param mediaAtvEns média aritmética de pontos (E) no item I
         * Atividades de Ensino
         * @param ocupaCargoDirecao flag que verifica se o docente ocupa cargo
         * na direção. Se for verdadeiro, a regra será desconsiderada, senão
         * será considerada
         * @return verdadeiro (aprovado) caso a média seja igual ou maior que
         * 80.0 no item I Atividades de Ensino, caso contrário será falso
         * (reprovado)
         */
        private static boolean verificaExigenciaI(final double mediaAtvEns,
                final boolean ocupaCargoDirecao) {
            return ocupaCargoDirecao || (mediaAtvEns >= 80.0);
        }

        /**
         * Método que verifica a média aritmética de pontos por ano sobre os
         * itens II-1, II-2 e II-3 (Produção Intelectual) e V-1
         * (Outras Atividades).
         * @param mediaProdIntel média aritmética de pontos nos
         * itens II-1, II-2 e II-3 (Produção Intelectual) e V-1
         * (Outras Atividades)
         * @param jorTra flag que verifica se o docente é de regime 40
         * horas ou Dedicação exclusiva, se for verdadeiro, a regra se aplica,
         * caso contrário o mesmo é de regime de 20 horas e tal regra não será
         * aplicada
         * @param mediaAtvEns valor da média nota do item I em
         * atividades de ensino
         * @return verdadeiro (aprovado) para caso a média seja igual ou maior
         * que 20 por ano, caso contrário será falso (reprovado)
         */
        private static boolean verificaExigenciaII(final double mediaProdIntel,
                final EnumJornadaTrabalho jorTra, final double mediaAtvEns) {
            double mediaRequerida = 20;
            if (((mediaAtvEns >= 160)
                    && !jorTra.equals(EnumJornadaTrabalho.jornada20))
                    || jorTra.equals(EnumJornadaTrabalho.jornada20)) {
                return true;
            } else if ((mediaAtvEns > 80) && (mediaAtvEns < 160)) {
                mediaRequerida = (40 - (mediaAtvEns / 4));
                if (mediaProdIntel >= mediaRequerida) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

        /**
         * Método que verifica a média final das avaliações de chefia e corpo
         * discente.
         * @param mediaN2 média final da nota da diretoria ou chefia de
         * gabinete
         * @param mediaN3 média final da nota do corpo discente
         * @return verdadeiro (aprovado) para caso a ambas as médias sejam
         * maiores ou iguais a 5.0, caso contrário será falso (reprovado)
         */
        private static boolean verificaExigenciaIII(final double mediaN2,
                final double mediaN3) {
            return (mediaN2 >= MEDIA_N2) && (mediaN3 >= MEDIA_N3);
        }

        /**
         * Método que verifica se o docente participou do programa de
         * atividades de responsabilidade da PROGRAD e PRODIRH, Art. 8°
         * da CONSUNI 21/2009.
         * @param partPrograma flag que armazena se o docente participou
         * ou não dos programas
         * @return verdadeiro (aprovado) para caso tenha participado ou falso
         * (reprovado) para caso não tenha participado
         */
        private static boolean verificaExigenciaIV(final boolean partPrograma) {
            if (partPrograma) {
                return partPrograma;
            } else {
                return partPrograma;
            }
        }

        /**
         * Método que verifica a nota global do docente no período de 36 meses,
         * de acordo com a CONSUNI 21/2009.
         * @param notaFinal nota global do docente.
         * @return verdadeiro (aprovado) para caso essa média seja maior ou
         * igual a 7.5, caso contrário será falso (reprovado)
         */
        private static boolean verificaExigenciaV(final Nota notaFinal) {
            return notaFinal.getNota() >= MEDIA_FINAL;
        }

    }

    /**
     * Verifica as exigências do docente a qual está em progressão horizontal.
     */
    public static final class ProgressaoHorizontal {

        /**
         * Média global mínima a ser atingida pelo docente em progressão
         * horizontal para ser aprovado.
         */
        public static final double MEDIA_GLOBAL = 7.5;

        /**
         * Verifica a aprovação do docente a qual está em progressão horizontal
         * conforme a CONSUNI 21/2009.
         * @param ng nota global do docente que está em progressão horizontal
         * @param medAtvEns média das atividades de ensino (item I)
         * @param medAtvInt média dos itens II-1, II-2 e II-3 da Produção
         * Intelectual
         * @param pontosAtvAcad pontos do docente nos subitens 1, 2, 3 e 4 do
         * item V - Atividades Acadêmicas - Orientação
         * @param desenvPosGrad flag que informa se o docente desenvolveu
         * atividades de orientação de pós graduação <i>stricto sensu</i>
         * @param afastado flag que informa se o docente está afastado para a
         * realização de curso de pós graduação <i>stricto sensu</i>, estágio
         * pós-doutoral ou estágio sênior
         * @param cargoDirecao flag que indica se o docente ocupa algum cargo
         * de direção na UFG
         * @param jorTra jornada de trabalho do docente
         * @return retorna verdadeiro (aprovado) caso todas as exigências sejam
         * satisfeitas, caso contrários será falso (reprovado)
         */
        public static boolean verificaAprovacao(final Nota ng,
                final double medAtvEns, final double medAtvInt,
                final double pontosAtvAcad, final boolean desenvPosGrad,
                final boolean afastado, final boolean cargoDirecao,
                final EnumJornadaTrabalho jorTra) {

            boolean exigI = verificaExigenciaI(medAtvEns, desenvPosGrad,
                    pontosAtvAcad, cargoDirecao, afastado);
            boolean exigII = verificaExigenciaII(medAtvEns, medAtvInt,
                    cargoDirecao, jorTra, afastado);
            boolean exigIII = verificaExigenciaIII(ng);

            return exigI && exigII && exigIII;

        }

        /**
         * Conforme <b>Art.27</b> da <b>CONSUNI 21/2009</b>, verifica se o
         * docente tem uma média aritmética igual ou superior a 80 (ou 60 caso
         * o mesmo tenha desenvolvido atividades de orientação na pós-Graduação
         * <i>stricto sensu</i> e que tenham vinte ou mais pontos nos subitens
         * 1, 2, 3 e 4 do item V - Atividades Acadêmicas - Orientação) para ser
         * aprovado. Caso o mesmo ocupe cargo de direção na UFG (conforme item
         * IV - 1) ou que esteja oficialmente afastado para a realização de
         * curso de pós-graduação <i>stricto sensu</i>, estágio pós-doutoral ou
         * estágio sênior, serão desconsideradas no período em que o docente
         * atuarará.
         * @param medAtvEns média das atividades de ensino do item I -
         * Atividades de Ensino
         * @param desenvPosGrad flag que informa se o docente desenvolveu
         * atividades de orientação de pós graduação <i>stricto sensu</i>
         * @param pontosAtvAcad pontos do docente nos subitens 1, 2, 3 e 4 do
         * item V - Atividades Acadêmicas - Orientação
         * @param cargoDirecao flag que informa se o docente ocupa um cargo de
         * direção na UFG
         * @param afastado flag que informa se o docente está afastado para a
         * realização de curso de pós graduação <i>stricto sensu</i>, estágio
         * pós-doutoral ou estágio sênior
         * @return aprovado (verdadeiro) para quando o docente ocupar cargo de
         * direção, estiver afastado ou obter uma média de atividades de ensino
         * maior ou igual à média estabelecida acima. Caso contrário será
         * reprovado (falso)
         */
        private static boolean verificaExigenciaI(final double medAtvEns,
                final boolean desenvPosGrad, final double pontosAtvAcad,
                final boolean cargoDirecao, final boolean afastado) {

            double media = 80;

            if (desenvPosGrad && (pontosAtvAcad >= 20)) {
                media = 60;
            }

            return cargoDirecao || afastado || (medAtvEns >= media);
        }

        /**
         * Conforme <b>Art.27</b> da <b>CONSUNI 21/2009</b>, verifica se o
         * docente obter uma média maior ou igual a 20 pontos nos itens II-1,
         * II-2e II-3 da Produção Intelectual e do item V-1 de Outras
         * atividades, se possui cargo de direção na UFG, se participa de algum
         * curso de pós-graduação <i>stricto sensu</i>, estágio pós-doutoral ou
         * estágio sênior e orientações realizadas.
         * @param medAtvEns média das atividades de ensino (item I)
         * @param medAtvInt média dos itens II-1, II-2 e II-3 da Produção
         * Intelectual
         * @param cargoDirecao flag que indica se o docente estiver ocupando
         * um cargo de direção na UFG
         * @param jorTra especifica a jornada de trabalho do docente
         * @param afastado flag que indica
         * @return aprovado (verdadeiro) para quando o docente ocupar cargo de
         * direção, estiver afastado, ter uma jornada de trabalho de 20 horas,
         * ter a média de atividades de ensino igual a 160 ou a média de
         * atividades intelectuais maior ou igual a 20 pontos (ou pela fórmula
         * (40 - E / 4) onde "E" é a pontuação média obtida no item I -
         * Atividades de Ensino caso a mesma tenha pontuação média maior que 80
         * e menor que 160
         */
        private static boolean verificaExigenciaII(final double medAtvEns,
                final double medAtvInt, final boolean cargoDirecao,
                final EnumJornadaTrabalho jorTra, final boolean afastado) {

            double pontos = 20;

            if ((medAtvEns > 80) && (medAtvEns < 160)) {
                pontos = (40 - (medAtvEns / 4));
            }

            return afastado || cargoDirecao || (medAtvEns == 160)
                    || (medAtvInt >= pontos)
                    || jorTra.equals(EnumJornadaTrabalho.jornada20);
        }

        /**
         * Verifica se a nota global obtida pelo docente é maior ou igual a
         * 7,5 na avaliação definida no Art. 26 da CONSUNI 21/2009.
         * @param notaGlobal nota global do docente em progressão horizontal
         * @return verdadeiro para aprovado e falso para reprovado
         */
        private static boolean verificaExigenciaIII(final Nota notaGlobal) {
            return notaGlobal.getNota() >= MEDIA_GLOBAL;
        }

    }

}
