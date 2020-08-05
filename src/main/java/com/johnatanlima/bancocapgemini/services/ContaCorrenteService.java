package com.johnatanlima.bancocapgemini.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnatanlima.bancocapgemini.domain.ContaCorrente;
import com.johnatanlima.bancocapgemini.dto.ContaCorrenteDTO;
import com.johnatanlima.bancocapgemini.repositories.ContaCorrenteRepository;
import com.johnatanlima.bancocapgemini.services.exceptions.AuthorizationException;
import com.johnatanlima.bancocapgemini.services.exceptions.ObjectNotFoundException;

@Service
public class ContaCorrenteService {

	@Autowired
	private ContaCorrenteRepository repo;
	
	public ContaCorrente find(Integer id) {
		Optional<ContaCorrente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + ContaCorrente.class.getName()));
	}
	
	public ContaCorrente update(ContaCorrente obj) {
		if(obj.getEstado().toString() == "ENCERRADA") {
			throw new AuthorizationException("Conta encerrada. Essa operação não é autorizada!");
		}
		
		ContaCorrente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public ContaCorrente fromDTO(ContaCorrenteDTO objDto) {
		return new ContaCorrente(objDto.getId(), objDto.getEstado(), objDto.getSaldo());
	}
	
	private void updateData(ContaCorrente newObj, ContaCorrente obj) {
		newObj.setSaldo(obj.getSaldo());
	}
}
