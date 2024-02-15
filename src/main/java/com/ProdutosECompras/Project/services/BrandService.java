package com.ProdutosECompras.Project.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.ProdutosECompras.Project.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProdutosECompras.Project.profiel.Brand;
import com.ProdutosECompras.Project.repositories.BrandRepository;

// Classe para serviços básicos como CRUD - Create, Read, Update, Delete

@Service
public class BrandService {

	// Me dará todos os comandos de persistência de dados.
	
	@Autowired
	private BrandRepository brandRepository;

	// Retorna todos.
	public List<Brand> findAll() {
		return brandRepository.findAll();
	}
	
	// Retornando por ID
	public Brand findById(Long idBrand) {
		try {
			Brand obj = brandRepository.findById(idBrand).get();
			return obj;
		}catch (NoSuchElementException e) {
			throw new ResourceNotFoundException(idBrand);
		}
	}
	
	// Inserção de marca
	public Brand CreatObj(Brand obj) {
		return brandRepository.save(obj);
	}
	
	// Atualização de dados
	public Brand updateDataBase(Long idBrand, Brand objOld) {
		Brand objNew = brandRepository.getReferenceById(idBrand); // Pegará o ID e permanecerá em observação
		updateData(objNew, objOld); // Troca um pelo outro.
		return brandRepository.save(objNew);
	}

	private void updateData(Brand objNew, Brand objOld) {
		objNew.setLabel(objOld.getLabel());

	}
}
