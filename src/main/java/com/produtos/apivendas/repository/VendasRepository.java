package com.produtos.apivendas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.produtos.apivendas.model.Vendas;


public interface VendasRepository extends JpaRepository<Vendas, Long>{
	
	//		
	
	@Query("select v from Vendas v where v.nomeProduto like %?1%  ")
	List<Vendas> findVendasByName(String nomeProduto);
	
	
	@Query("select nomeProduto  from Vendas v group by v.nomeProduto")		
	List<Vendas> findVendasBySomaQtd(String nomeProduto);
	

	
}
