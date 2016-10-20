package br.jus.stf.plataforma.identidades.domain.model.validation;

/**
 * Base para validação de CPF e CNPJ.
 * 
 * @author Rafael Alencar
 * @since 20.10.2016
 *
 */
public abstract class DocumentoUtils {

    public abstract boolean isValido(String numeroDocumento);

    public abstract String retirarMascara(String numeroDocumento);

    public abstract String aplicarMascara(String numeroDocumento);

    protected String removerCaracteresEspeciais(String numeroDocumento) {
        if (numeroDocumento != null)
            return numeroDocumento.replaceAll("\\p{Punct}", "");

        return null;
    }

    protected int calcularDigito(String numeroDocumento, int[] peso) {
        int soma = 0;

        for (int indice = numeroDocumento.length() - 1, digito; indice >= 0; indice--) {
            digito = Integer.parseInt(numeroDocumento.substring(indice, indice + 1));
            soma += digito * peso[peso.length - numeroDocumento.length() + indice];
        }
        soma = 11 - soma % 11;

        return soma > 9 ? 0 : soma;
    }

}
