package com.ProdutosECompras.Project.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProdutosECompras.Project.profiel.Brand;
import com.ProdutosECompras.Project.profiel.Products;
import com.ProdutosECompras.Project.repositories.BrandRepository;
import com.ProdutosECompras.Project.repositories.ProductRepository;

// Classe para serviços básicos como CRUD - Create, Read, Update, Delete

@Service
public class ProductService {

	// Me dará todos os comandos de persistencia de dados.
	@Autowired
	private ProductRepository Pservice;
	
	@Autowired
	private BrandRepository Bservice;

	// Retorna todos.
	public List<Products> findAll() {
		return Pservice.findAll();
	}
	
	// Retornando por ID
	
	public Optional<Products> findById(Long idProduct) {
		Optional<Products> obj = Pservice.findById(idProduct);
		if(obj.isPresent()) {
			return obj;	
		} else {
			throw new RuntimeException("Id não encontrado");
		}
	}
	
	// Adicionando um novo produto
	public Products CreatObj(Products obj) {
		return Pservice.save(obj);
	}
	
	
	// Update Produto
	public Products UpdateObjProduct(Long idProduct, Products objOld) {
		Products objNew = Pservice.getReferenceById(idProduct);
		UpdateData(objNew, objOld);
		return Pservice.save(objNew);
	}

	// Update
	private void UpdateData(Products objNew, Products objOld) {
		objNew.setName(objOld.getName());
		objNew.setDiscription(objOld.getDiscription());
		objNew.setQuantity(objOld.getQuantity());
		objNew.setValue(objOld.getValue());
		objNew.setLink(objOld.getLink());
	}
	
	// Associação com Brand
	public Products AssociationData(Long idProduct, Long idBrand) {
		Set<Brand> brandSet = null;
		
		Products products = Pservice.findById(idProduct).get();
		Brand brand = Bservice.findById(idBrand).get();
		
		// Cria uma coleção valendo nula primeiro e depois coloca a coleção de produtos dentro dela.
		brandSet = products.getBrandL();
		
		// Aqui ele então adicionará as valores de brand optidos pelo ID e colocará dentro da coleção de produtos.
		brandSet.add(brand);
		
		// Com a coleção de Brands atualizada, você passará para o produto. Dessa forma fará com que produtos tenha uma nova coleção.
		products.setBrandL(brandSet);
		
		return Pservice.save(products);
	}

	//Deleção de produto
	public void DeleteProduct(Long idProduct) {
		Optional<Products> obj = Pservice.findById(idProduct);
		
		if(obj != null) {
			Pservice.deleteById(idProduct);
		} else {
			throw new RuntimeException("Id não identificado");
		}
	}
}	

