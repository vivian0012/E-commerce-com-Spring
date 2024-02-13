package com.ProdutosECompras.Project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProdutosECompras.Project.profiel.Products;
import com.ProdutosECompras.Project.services.ProductService;

@RestController
@RequestMapping(value = "/product")
public class ProductsHTTP {
	
	@Autowired
	private ProductService Pservice;
	
	// Retornando TODOS
	@GetMapping
	public ResponseEntity<List<Products>> findAll(){ // ReponseEntity representa o chamado HTTPS
		List<Products> obj = Pservice.findAll();
		return ResponseEntity.ok().body(obj);
	}
	
	// Adicionando valor
	@PostMapping
	public ResponseEntity<Products> CreatNewObj(@RequestBody Products obj) {
		obj = Pservice.CreatObj(obj);
		return ResponseEntity.ok().body(obj);
	}
	
	// Alterando valores
	@PutMapping(value = "/{idProduct}")
	public ResponseEntity<Products> UpdateObj(@PathVariable Long idProduct, @RequestBody Products objNew)
	{
		objNew = Pservice.UpdateObjProduct(idProduct, objNew);
		return ResponseEntity.ok().body(objNew);
	}
	
	
	// Associando valores
	@PutMapping(value = "/{idProduct}/brand/{idBrand}")
	public Products products(
			@PathVariable Long idProduct, 
			@PathVariable Long idBrand) {
		
		return Pservice.AssociationData(idProduct, idBrand);
	}
	
	// Delete produtos (FUNCIONANDO)
	@DeleteMapping("/deleteProduct/{idProduct}")
	public void deleteProduct(@PathVariable Long idProduct) {
		Pservice.DeleteProduct(idProduct);
	}
}
