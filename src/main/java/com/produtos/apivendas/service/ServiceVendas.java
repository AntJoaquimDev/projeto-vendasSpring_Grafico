package com.produtos.apivendas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.produtos.apivendas.model.Vendas;
import com.produtos.apivendas.repository.VendasRepository;

@Service
public class ServiceVendas {

	@Autowired
	private VendasRepository vendasRepository;
	
	public String salvarVendas(Vendas vendas) {
		
		vendasRepository.save(vendas);
		return "saved Vendas Reource";
	}
	
	
	public List<Vendas> getAllVendas(){
		
		return vendasRepository.findAll();
	}
	
}
