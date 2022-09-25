package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
@Service
public class SaleService { 
	
	@Autowired
	private SaleRepository repository;

	public Page<Sale> findSales(String minDate, String maxDate, Pageable pageable) {
		
		//Estamos pegando a data atual;
		LocalDate taday = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		
		// LocalDate.parse(minDate): Irá converter de String para LocalDate;
		LocalDate min = minDate.equals("") ? taday.minusDays(365) : LocalDate.parse(minDate);
		
		//Expressa condicional ternaria;
		LocalDate max = maxDate.equals("") ? taday : LocalDate.parse(maxDate);
		
		return repository.findSales(min, max, pageable);
	}
}
