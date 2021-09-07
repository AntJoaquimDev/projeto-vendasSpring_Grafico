package com.produtos.apivendas.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.produtos.apivendas.model.Vendas;
import com.produtos.apivendas.repository.VendasRepository;
import com.produtos.apivendas.service.ServiceVendas;

@Controller
public class ControllerVendas {

	@Autowired
	VendasRepository vendasRepository;
	@Autowired
	private ServiceVendas serviceVendas;


		@RequestMapping(value = "/formVendas", method = RequestMethod.GET)
		public String formVendas() {
			return "formVendas";
		}
		
		
		@RequestMapping(value = "/formVendas",method = RequestMethod.POST)
		public String salvarVendas (Vendas vendas) {
			
			vendasRepository.save(vendas);
			
			return "redirect:/formVendas";
		}

		
		 @RequestMapping("/index")
		    public ModelAndView listarVEndas() {
			 ModelAndView modelAndView = new ModelAndView("index");
			 Iterable<Vendas> vendas = vendasRepository.findAll();
			 modelAndView.addObject("vendas",vendas);
			 
			 return modelAndView;
			 
		 }
		 
		 @PostMapping("/buscarVendas") // culsatar por busca
			public ModelAndView pesquisar(@RequestParam("buscarVendas") String nomeProduto) {

				ModelAndView modelAndView = new ModelAndView("index");
				modelAndView.addObject("vendas", vendasRepository.findVendasByName(nomeProduto));
				modelAndView.addObject("vendasobj", new Vendas());
				
				return modelAndView;

			}
		 
		 
		 
		 //-----------------------------------------retornar Obj Json----------------------------------
		 @PostMapping("index")
		 public String addVendas(@RequestBody Vendas vendas) {
			 return serviceVendas.salvarVendas(vendas);
		 }
		 
		 @GetMapping("/barChart")
		 public String getAllVendas(Model model) {
	
			 List<String> nameProdutoList = serviceVendas.getAllVendas().stream().map(x-> x.getNomeProduto()).collect(Collectors.toList());			 
			 List<Integer> quantidadeList = serviceVendas.getAllVendas().stream().map(x-> x.getQuantidade()).collect(Collectors.toList());
			 List<String> listDate = serviceVendas.getAllVendas().stream().map(x-> x.getDataVenda()).collect(Collectors.toList());
			 model.addAttribute("name",nameProdutoList);
			 model.addAttribute("values",quantidadeList);
			 model.addAttribute("date",listDate);
			 
			 
			 return "barChart";
		 
			 
		 }
		 

	}

//*/
