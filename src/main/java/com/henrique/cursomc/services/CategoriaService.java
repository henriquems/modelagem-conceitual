package com.henrique.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henrique.cursomc.domain.Categoria;
import com.henrique.cursomc.respositories.CategoriaRepository;
import com.henrique.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	
	public Categoria buscar(Integer id) {
		Categoria categoria = repository.findOne(id);
		if(categoria == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: "+id+ ", Tipo: "+Categoria.class.getName());
		}
		return categoria;
	} 
	
}
