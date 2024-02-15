 package com.ProdutosECompras.Project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProdutosECompras.Project.profiel.Brand;
import com.ProdutosECompras.Project.services.BrandService;

@RestController
@RequestMapping(value = "/api/brands")
public class BrandHTTP {

	@Autowired
	private BrandService brandService;

	// Retornando todas as marcas
	@GetMapping
	public ResponseEntity<List<Brand>> findAll(){ // ReponseEntity representa o chamado HTTPS
		List<Brand> obj = brandService.findAll();
		return ResponseEntity.ok().body(obj);
	}

	//Adiciona uma nova marca
	@PostMapping
	public ResponseEntity<Brand> CreatNewObj(@RequestBody Brand obj) {
		obj = brandService.CreatObj(obj);
		return ResponseEntity.ok().body(obj);
	}

	// Alterando a marca de acordo com o ID
	@PutMapping(value = "/{idBrand}")
	public ResponseEntity<Brand> UpdateObj(@PathVariable Long idBrand, @RequestBody Brand objNew) {
		objNew = brandService.updateDataBase(idBrand, objNew);
		return ResponseEntity.ok().body(objNew);
	}
}
