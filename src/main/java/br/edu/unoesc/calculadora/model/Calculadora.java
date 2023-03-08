package br.edu.unoesc.calculadora.model;

import java.util.logging.Logger;

import br.edu.unoesc.calculadora.exceptions.RaizNegativaException;
import br.edu.unoesc.calculadora.utils.ConversorNumerico;

public class Calculadora {
	private static Logger logger = Logger.getLogger(Calculadora.class.getName()); 
	
	public static Double somar(String n1, String n2) {
		if (!ConversorNumerico.ehNumerico(n1) || !ConversorNumerico.ehNumerico(n2)) {
    		return 0d;
    	}
		
		Double numero1 = ConversorNumerico.converterParaDouble(n1);
		Double numero2 = ConversorNumerico.converterParaDouble(n2);
		Double resultado = numero1 + numero2;
		
		logger.info("Calculando... " + numero1 + " + " + numero2 + " = " + resultado);
		
		return resultado;
	}
	
    // Método sobrecarregado (mesmo nome mas passando variáveis diferentes)
    public static Double subtrair(Double n1, Double n2) {
		return subtrair(n1.toString(), n2.toString());
	}
		
	public static Double subtrair(String n1, String n2) {
		Double numero1 = ConversorNumerico.converterParaDouble(n1);
		Double numero2 = ConversorNumerico.converterParaDouble(n2);
		Double resultado = numero1 - numero2;
		
		logger.info("Calculando... " + numero1 + " - " + numero2 + " = " + resultado);
		
		return resultado;
	}
	
	public static Double multiplicar(String n1, String n2) {
		Double numero1 = ConversorNumerico.converterParaDouble(n1);
		Double numero2 = ConversorNumerico.converterParaDouble(n2);
		Double resultado = numero1 * numero2;
		
		logger.info("Calculando... " + numero1 + " * " + numero2 + " = " + resultado);
		
		return resultado;
	}
	
	public static Double dividir(String n1, String n2) {
		String erro = "Erro de divisão por zero!";
		
		Double numero1 = ConversorNumerico.converterParaDouble(n1);
		Double numero2 = ConversorNumerico.converterParaDouble(n2);
		
		if (numero2 == 0) {
			logger.info(erro);
			throw new ArithmeticException(erro);
		}
		
		Double resultado = numero1 / numero2;
		
		logger.info("Calculando... " + numero1 + " / " + numero2 + " = " + resultado);
		
		return resultado;
	}
	
	public static Double calcularMedia(String n1, String n2) {
		Double numero1 = ConversorNumerico.converterParaDouble(n1);
		Double numero2 = ConversorNumerico.converterParaDouble(n2);
		Double resultado = (numero1 + numero2) / 2.0;
		
		logger.info("Calculando... (" + numero1 + " + " + numero2 + ")/2 = " + resultado);
		
		return resultado;
	}
	
	public static Double calcularPotencia(String n1, String n2) {
		Double base = ConversorNumerico.converterParaDouble(n1);
		Double expoente = ConversorNumerico.converterParaDouble(n2);
		Double resultado = Math.pow(base, expoente);
		
		logger.info("Calculando... " + base + " ^ " + expoente + " = " + resultado);
		
		return resultado;
	}
	
	public static Double calcularRaizQuadradra(String num) {
		String erro = "Erro! Não existe raiz quadrada de um número negativo!";
		
		Double numero = ConversorNumerico.converterParaDouble(num);

		if (numero < 0) {
			logger.info(erro);
			throw new RaizNegativaException(erro); // Aqui está enviando a String erro pra variavel MSG
			                                       //  da classe RaizNegativaException
		}
		
		Double resultado = Math.sqrt(numero);
		
		logger.info("Calculando... raiz quadrada de " + numero + " = " + resultado);
		
		return resultado;	
	}
}