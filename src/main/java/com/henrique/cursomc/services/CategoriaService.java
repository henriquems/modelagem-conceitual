package com.henrique.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henrique.cursomc.domain.Categoria;
import com.henrique.cursomc.respositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	
	public Categoria buscar(Integer id) {
		return repository.findOne(id);
	}
	
}
