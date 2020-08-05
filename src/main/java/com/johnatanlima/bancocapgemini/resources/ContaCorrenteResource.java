package com.johnatanlima.bancocapgemini.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.johnatanlima.bancocapgemini.domain.ContaCorrente;
import com.johnatanlima.bancocapgemini.dto.ContaCorrenteDTO;
import com.johnatanlima.bancocapgemini.services.ContaCorrenteService;

@RestController
@RequestMapping(value = "/contas")
public class ContaCorrenteResource {
	
	@Autowired
	private ContaCorrenteService service;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<ContaCorrente> listar(@PathVariable Integer id) {
		ContaCorrente obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ContaCorrenteDTO objDto, @PathVariable Integer id) {
		ContaCorrente obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

}
