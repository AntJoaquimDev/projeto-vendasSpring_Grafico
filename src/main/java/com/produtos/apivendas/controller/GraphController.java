/*

package com.produtos.apivendas.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.produtos.apivendas.model.Vendas;
import com.produtos.apivendas.repository.VendasRepository;

@Controller
public class GraphController {
	
	
	@Autowired
	VendasRepository vendasRepository;


	
	@PostMapping("/buscarVendas") // culsatar por busca
	public ModelAndView pesquisar(@RequestParam("buscarVendas") String nomeProduto) {

		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("vendas", vendasRepository.findVendasByName(nomeProduto));
		modelAndView.addObject("vendasobj", new Vendas());
		
		return modelAndView;

	}
	
	
	@GetMapping("/barChart")
	public String barChart(Model model) {
		Map<String, Integer> data = new LinkedHashMap<String, Integer>();

		ModelAndView modelAndView = new ModelAndView("index");
		Iterable<Vendas> vendas = vendasRepository.findAll();
		//modelAndView.addObject("vendas", vendas);
		modelAndView.addObject("vendas", vendas);
		

		data.put("Sapato couro ", 35);
		data.put("Tênis Adidas", 65);
		data.put("Sapato Napa", 90);
		data.put("Tênes Rainha", 25);

		model.addAttribute("keySet", data.keySet());
		model.addAttribute("values", data.values());
		return "barChart";

	}

	@GetMapping("/pieChart")
	public String pieChart(Model model) {
		model.addAttribute("pass", 90);
		model.addAttribute("fail", 10);
		return "pieChart";

	}

}





/*Vendas vendas = new Vendas();
List<Vendas> vendasList = vendasRepository.findVendasByName(nomeProduto);

int somaQtd = 0;
Vendas maiorQtd;
String maisVendido = "";
for (Vendas vendas2 : vendasList) {
	somaQtd += vendas2.getQuantidade();
}

System.out.println(nomeProduto + "==>" + somaQtd);
*/
