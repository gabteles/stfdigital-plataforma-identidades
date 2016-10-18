package br.jus.stf.plataforma.identidades;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.jus.stf.plataforma.identidades.domain.model.validation.CPFUtils;

/**
 * Teste unitário do utilitário de CPF.
 * 
 * @author Anderson.Araujo
 * @since 22.03.2016
 *
 */
public class CPFUtilsUnitTest {
	
	@Test
	public void criarCPFInvalidoComCaracteresAlfanumericos(){
		String numeroCPF = "123hfh";
		Boolean cpfValido = CPFUtils.isValido(numeroCPF);
		
		assertFalse(cpfValido);
	}
	
	@Test
	public void criarCPFInvalidoComMenos11Digitos(){
		String numeroCPF = "85462536";
		Boolean cpfValido = CPFUtils.isValido(numeroCPF);
		
		assertFalse(cpfValido);
	}
	
	@Test
	public void criarCPFInvalidoComMais11Digitos(){
		String numeroCPF = "8546253654857";
		Boolean cpfValido = CPFUtils.isValido(numeroCPF);
		
		assertFalse(cpfValido);
	}
	
	@Test
	public void criarCPFInvalidoCom11Digitos(){
		String numeroCPF = "49862486181";
		Boolean cpfValido = CPFUtils.isValido(numeroCPF);
		
		assertFalse(cpfValido);
	}
	
	@Test
	public void criarCPFValido(){
		String numeroCPF = "49862486180";
		Boolean cpfValido = CPFUtils.isValido(numeroCPF);
		
		assertTrue(cpfValido);
	}
	
	@Test
	public void criarCPFValidoComCaracteresEspeciais(){
		String numeroCPF = "498.624.861-80";
		Boolean cpfValido = CPFUtils.isValido(numeroCPF);
		
		assertTrue(cpfValido);
	}
	
	@Test
	public void criarCPFValidoComEspacosBranco(){
		String numeroCPF = "498 624 861 80";
		Boolean cpfValido = CPFUtils.isValido(numeroCPF);
		
		assertTrue(cpfValido);
	}
	
	@Test
	public void aplicaMascaraEmCPFValido(){
		String numeroCPF = "49862486180";
		String cpfComMascara = CPFUtils.aplicarMascara(numeroCPF);
		
		assertEquals("498.624.861-80",cpfComMascara);
	}
	
	@Test
	public void naoAplicaMascaraEmCPFInvalido(){
		String numeroCPF = "49862486181";
		String cpfComMascara = CPFUtils.aplicarMascara(numeroCPF);
		
		assertEquals("",cpfComMascara);
	}
	
	@Test
	public void removeMascaraCPFValido(){
		String numeroCPF = "498.624.861-80";
		String cpfComMascara = CPFUtils.retirarMascara(numeroCPF);
		
		assertEquals("49862486180",cpfComMascara);
	}
	
	@Test
	public void naoRemoveMascaraEmCPFInvalido(){
		String numeroCPF = "49862486181";
		String cpfComMascara = CPFUtils.aplicarMascara(numeroCPF);
		
		assertEquals("",cpfComMascara);
	}
	
}
