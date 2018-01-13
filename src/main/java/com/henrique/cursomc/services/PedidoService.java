package com.henrique.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henrique.cursomc.domain.Pedido;
import com.henrique.cursomc.respositories.PedidoRepository;
import com.henrique.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repository;
	
	public Pedido buscar(Integer id) {
		Pedido pedido = repository.findOne(id);
		if(pedido == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: "+id+ ", Tipo: "+Pedido.class.getName());
		}
		return pedido;
	} 
	
}
