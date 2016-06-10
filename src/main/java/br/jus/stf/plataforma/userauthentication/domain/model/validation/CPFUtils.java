package br.jus.stf.plataforma.userauthentication.domain.model.validation;

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
public class CPFUtils {
	
	public static boolean isValido(String numeroCPF) {
		String cpf = removerCaracteresEspeciais(StringUtils.deleteWhitespace(numeroCPF));
		int[] pesoCPF = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };
		
		if (!StringUtils.isNumeric(cpf))
			return false;
		
		if (cpf == null || cpf.length() != 11)
			return false;

		Integer digito1 = calcularDigito(cpf.substring(0, 9), pesoCPF);
		Integer digito2 = calcularDigito(cpf.substring(0, 9) + digito1, pesoCPF);
		
		return cpf.equals(cpf.substring(0, 9) + digito1.toString() + digito2.toString());
	}
	
	public static String retirarMascara(String cpf) {
		String novoCPF = "";

		if (isValido(cpf)) {
			novoCPF = removerCaracteresEspeciais(StringUtils.deleteWhitespace(cpf));
		}

		return novoCPF;
	}
	
	public static String aplicarMascara(String cpf) {
		Pattern pattern = Pattern.compile("(\\d{3})(\\d{3})(\\d{3})(\\d{2})");
		Matcher matcher = pattern.matcher(cpf);
		String novoCPF = "";

		if (matcher.matches() && isValido(cpf)) {
			novoCPF = matcher.replaceAll("$1.$2.$3-$4");
		}

		return novoCPF;
	}
	
	private static String removerCaracteresEspeciais(String str) {
		if (str != null)
			return str.replaceAll("\\p{Punct}", "");
		
		return null;
	}
	
	private static int calcularDigito(String str, int[] peso) {
		int soma = 0;
		
		for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
			digito = Integer.parseInt(str.substring(indice, indice + 1));
			soma += digito * peso[peso.length - str.length() + indice];
		}
		soma = 11 - soma % 11;
		
		return soma > 9 ? 0 : soma;
	}
	
}
