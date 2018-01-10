package com.henrique.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.henrique.cursomc.domain.Categoria;
import com.henrique.cursomc.respositories.CategoriaRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		Categoria c1 = new Categoria(1, "Informática");
		Categoria c2 = new Categoria(2, "Financeiro");
		
		repository.save(Arrays.asList(c1, c2));		
	}
}
