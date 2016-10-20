package br.jus.stf.plataforma.identidades.domain.model.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * Validador de CPF.
 * 
 * @author Anderson.Araujo
 * @since 22.03.2016
 *
 */
public class CNPJUtils extends DocumentoUtils {

    @Override
    public boolean isValido(String numeroCNPJ) {
        String cnpj = removerCaracteresEspeciais(StringUtils.deleteWhitespace(numeroCNPJ));
        int[] pesoCNPJ = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

        if (cnpj == null || cnpj.length() != 14)
            return false;

        Integer digito1 = calcularDigito(cnpj.substring(0, 12), pesoCNPJ);
        Integer digito2 = calcularDigito(cnpj.substring(0, 12) + digito1, pesoCNPJ);

        return cnpj.equals(cnpj.substring(0, 12) + digito1.toString() + digito2.toString());
    }

    @Override
    public String retirarMascara(String cnpj) {
        String novoCNPJ = "";

        if (isValido(cnpj)) {
            novoCNPJ = removerCaracteresEspeciais(StringUtils.deleteWhitespace(cnpj));
        }

        return novoCNPJ;
    }

    @Override
    public String aplicarMascara(String cnpj) {
        Pattern pattern = Pattern.compile("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})");
        Matcher matcher = pattern.matcher(cnpj);
        String novoCNPJ = "";

        if (matcher.matches() && isValido(cnpj)) {
            novoCNPJ = matcher.replaceAll("$1.$2.$3/$4-$5");
        }

        return novoCNPJ;
    }

}
