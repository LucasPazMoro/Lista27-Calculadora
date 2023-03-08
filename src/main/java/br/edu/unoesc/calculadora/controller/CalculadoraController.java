package br.edu.unoesc.calculadora.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Attr;

import br.edu.unoesc.calculadora.model.Calculadora;

@Controller
public class CalculadoraController {
	@GetMapping("/soma-query")
    public String somaQuery(@RequestParam(value = "n1", defaultValue = "0") String n1,
    						@RequestParam(value = "n2", defaultValue = "0") String n2,
    						RedirectAttributes atributos) {	
		atributos.addAttribute("v1", "40"); //addAtribute mostra na URL
		atributos.addFlashAttribute("v2", "42"); //addFlashAttribute mostra apenas no servidor
		
    	return "redirect:/soma-path/" + n1 + "/" + n2; //Aqui está montando  URL
    }
	
    @GetMapping("/soma-path/{numero1}/{numero2}")
    public String somaPath(@PathVariable String numero1, 
    					   @PathVariable String numero2,
    					   @ModelAttribute("v1") Double v1,
    					   @ModelAttribute("v2") Double v2,
    					   ModelMap model) {
    	System.out.println("Impressão v1: " + v1 + " " + v2);
    	System.out.println("Impressão v2: " + model.getAttribute("v1") + " " + model.getAttribute("v2"));
    	//A impressão do v2 não está convertendo em Double (42.0) igual o v1,
    	//devido no addFlashAtribute ter passado uma String e ele não converte igual o addAtribute
    	
    	model.addAttribute("resultado", Calculadora.somar(numero1, numero2));
    	
    	return "resultado"; 
    }
    //----------------
    @GetMapping("/soma-forward-query")
    public String somaForwardQuery(@RequestParam(value = "n1", defaultValue = "0") String n1,
    						       @RequestParam(value = "n2", defaultValue = "0") String n2,
    						     //não usa o redirectAttributes, pode usar o ModelMap ou HttpServletRequest. Aqui foi usado os dois 
    						       ModelMap model, // eles fazem o redirecionamento automatico
    						       HttpServletRequest requisicao) {	
		model.addAttribute("v1", "40");
    	requisicao.setAttribute("v2", "2");
		
    	return "forward:/soma-forward-path/" + n1 + "/" + n2;
    }
    
    @GetMapping("/soma-forward-path/{numero1}/{numero2}") //Forward não altera a URL no navegador(lado cliente)
                                                           //igual o redirect faz
    public String somaForwardPath(@PathVariable String numero1, 
    							  @PathVariable String numero2,
    							  Model model,
    							  HttpServletRequest requisicao) {
    	System.out.println(requisicao.getAttribute("v1"));
    	System.out.println(requisicao.getAttribute("v2"));

    	model.addAttribute("resultado", Calculadora.somar(numero1, numero2));
    	
    	return "resultado"; // quando a classe é um controlador normal @Controller e não um controlador Rest @RestController
    	                    // retorna a página resultado.html    
    }
    //-------------------------
    @GetMapping("/subtrai-query")
    public String subtraiQuery (@RequestParam(value = "n1", defaultValue = "0") String n1,
    		                    @RequestParam(value = "n2", defaultValue = "0") String n2,
    		                    RedirectAttributes atributos) {
    	atributos.addAttribute("valor1", 11);
    	atributos.addAttribute("valor2", 21);
    	
    	return "redirect:/subtrai-path/" + n1 + "/" + n2;    
    
    }
    
    @GetMapping("/subtrai-path/{n1}/{n2}")
    public String subtraiPath(@PathVariable String n1,
    		                  @PathVariable String n2,
                              @ModelAttribute("valor1") Double valor1,
                              @ModelAttribute("valor2") Double valor2,
	                          ModelMap model) {
//    	System.out.println(model.getAttribute("valor1"));
//    	System.out.println(model.getAttribute("valor2"));
    	
//    	System.out.println("Impressão n1 e n2: " + n1 + " " + n2);
    	
    	System.out.println("Impressão valor 1: " + model.getAttribute("valor1"));
    	System.out.println("Impressão valor 2: " + model.getAttribute("valor2"));
    	
    	model.addAttribute("resultado", Calculadora.subtrair(n1, n2));
    	
    	return "resultado";
    	
    }
    
    @GetMapping("/multiplica-forward-query")
    public String multiplicaForwardQuery(@RequestParam (value="n1", defaultValue = "0" ) String n1,
    		                             @RequestParam (value="n2", defaultValue = "0" ) String n2,
    		                             ModelMap model,
    		                             HttpServletRequest requisicao
    		                             ) {
    	
//    	requisicao.setAttribute("valor1", 12);
//    	requisicao.setAttribute("valor2", "4"); //aspas são opcionais nos números?
    	model.addAttribute("valor1", "123");
    	model.addAttribute("valor2", 3); //aspas são opcionais nos números?
    	return "forward:/multiplica-forward-path/" + n1 + "/" + n2;
    }
    
    @GetMapping("/multiplica-forward-path/{n1}/{n2}")
    public String multiplicaForwardPath(@PathVariable String n1,
    		                            @PathVariable String n2,    		                      
    		                            ModelMap model,
  		                            HttpServletRequest requisicao
    		                            ) {
    	System.out.println("Impressão do valor1: " + requisicao.getAttribute("valor1")); //A impressão não funciona com o model?
    	System.out.println("Impressão do valor2: " + requisicao.getAttribute("valor2"));
    	
//    	requisicao.setAttribute("resultado", Calculadora.multiplicar(n1, n2));
    	model.addAttribute("resultado", Calculadora.multiplicar(n1, n2));
    	
    	return "resultado";
    }
    
    		 		
  
}