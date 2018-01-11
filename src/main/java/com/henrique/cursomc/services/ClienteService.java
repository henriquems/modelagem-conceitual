package com.henrique.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henrique.cursomc.domain.Cliente;
import com.henrique.cursomc.respositories.ClienteRepository;
import com.henrique.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	public Cliente buscar(Integer id) {
		Cliente cliente = repository.findOne(id);
		if(cliente == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: "+id+ ", Tipo: "+Cliente.class.getName());
		}
		return cliente;
	} 

}
