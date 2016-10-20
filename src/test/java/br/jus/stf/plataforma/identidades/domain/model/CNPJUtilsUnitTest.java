package br.jus.stf.plataforma.identidades.domain.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.jus.stf.plataforma.identidades.domain.model.validation.CNPJUtils;

/**
 * Teste unitário do validador de CNPJ.
 * 
 * @author Anderson.Araujo
 * @since 22.03.2016
 *
 */
public class CNPJUtilsUnitTest {

    @Test
    public void criarCNPJInvalidoComCaracteresAlfanumericos() {
        String numeroCNPJ = "123hfh";
        Boolean cnpjValido = cnpjUtils().isValido(numeroCNPJ);

        assertFalse(cnpjValido);
    }

    @Test
    public void criarCNPJInvalidoComMenos14Digitos() {
        String numeroCNPJ = "85462536";
        Boolean cnpjValido = cnpjUtils().isValido(numeroCNPJ);

        assertFalse(cnpjValido);
    }

    @Test
    public void criarCNPJInvalidoComMais14Digitos() {
        String numeroCNPJ = "8210131700018910";
        Boolean cnpjValido = cnpjUtils().isValido(numeroCNPJ);

        assertFalse(cnpjValido);
    }

    @Test
    public void criarCNPJInvalidoCom14Digitos() {
        String numeroCNPJ = "82101317000188";
        Boolean cnpjValido = cnpjUtils().isValido(numeroCNPJ);

        assertFalse(cnpjValido);
    }

    @Test
    public void criarCNPJValido() {
        String numeroCNPJ = "82101317000189";
        Boolean cnpjValido = cnpjUtils().isValido(numeroCNPJ);

        assertTrue(cnpjValido);
    }

    @Test
    public void criarCNPJValidoComCaracteresEspeciais() {
        String numeroCNPJ = "82.101.317/0001-89";
        Boolean cnpjValido = cnpjUtils().isValido(numeroCNPJ);

        assertTrue(cnpjValido);
    }

    @Test
    public void criarCNPJValidoComEspacosBranco() {
        String numeroCNPJ = "82  101 317 0001 89";
        Boolean cnpjValido = cnpjUtils().isValido(numeroCNPJ);

        assertTrue(cnpjValido);
    }

    @Test
    public void aplicaMascaraEmCNPJValido() {
        String numeroCNPJ = "82101317000189";
        String cnpjComMascara = cnpjUtils().aplicarMascara(numeroCNPJ);

        assertEquals("82.101.317/0001-89", cnpjComMascara);
    }

    @Test
    public void naoAplicaMascaraEmCNPJInvalido() {
        String numeroCPF = "82101317000181";
        String cpfComMascara = cnpjUtils().aplicarMascara(numeroCPF);

        assertEquals("", cpfComMascara);
    }

    @Test
    public void removeMascaraCNPJValido() {
        String numeroCPF = "82.101.317/0001-89";
        String cpfComMascara = cnpjUtils().retirarMascara(numeroCPF);

        assertEquals("82101317000189", cpfComMascara);
    }

    @Test
    public void naoRemoveMascaraEmCNPJInvalido() {
        String numeroCPF = "82101317000181";
        String cpfComMascara = cnpjUtils().aplicarMascara(numeroCPF);

        assertEquals("", cpfComMascara);
    }

    private CNPJUtils cnpjUtils() {
        return new CNPJUtils();
    }
}