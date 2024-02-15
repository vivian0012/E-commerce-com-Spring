package com.ProdutosECompras.Project.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import com.ProdutosECompras.Project.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProdutosECompras.Project.profiel.Brand;
import com.ProdutosECompras.Project.profiel.Products;
import com.ProdutosECompras.Project.repositories.BrandRepository;
import com.ProdutosECompras.Project.repositories.ProductRepository;

// Classe para serviços básicos como CRUD - Create, Read, Update, Delete

@Service
public class ProductService {

	// Me dará todos os comandos de persistência de dados.
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private BrandRepository brandRepository;

	// Retorna todos (Não precisa)
	public List<Products> findAll() {
		return productRepository.findAll();
	}
	
	// Retornando por ID (Exceção finalizada)
	public Products findById(Long idProduct) {
		try {
			Products obj = productRepository.findById(idProduct).get();
			return obj;
		}catch (NoSuchElementException e) {
			throw new ResourceNotFoundException(idProduct);
		}

	}
	
	// Adicionando um novo produto (Não precisa pq retorna um erro 400 caso o usuário digite errado)
	public Products CreatObj(Products obj) {
		return productRepository.save(obj);
	}

	// Update Produto (Exceção tratada)
	public Products UpdateObjProduct(Long idProduct, Products objOld) {
		try {
			Products objNew = productRepository.getReferenceById(idProduct);
			UpdateData(objNew, objOld);
			return productRepository.save(objNew);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(idProduct);
		}
	}
	private void UpdateData(Products objNew, Products objOld) {
		objNew.setName(objOld.getName());
		objNew.setDescription(objOld.getDescription());
		objNew.setQuantity(objOld.getQuantity());
		objNew.setValue(objOld.getValue());
		objNew.setLink(objOld.getLink());
	}
	
	// Associação com Brand (Exceção finalizada!)
	// Aqui eu capturo uma exceção personalizada e uma do próprio Java
	public Products AssociationData(Long idProduct, Long idBrand) {
		try {
			Set<Brand> brandSet = null;
			Products products = productRepository.findById(idProduct).get();
			Brand brand = brandRepository.findById(idBrand).get();
			// Cria uma coleção valendo nula primeiro e depois coloca a coleção de produtos dentro dela.
			brandSet = products.getBrandList();
			// Aqui ele então adicionará as valores de brand obtidos pelo ID e colocará dentro da coleção de produtos.
			brandSet.add(brand);
			// Com a coleção de Brands atualizada, você passará para o produto. Dessa forma fará com que produtos tenha uma nova coleção.
			products.setBrandList(brandSet);
			return productRepository.save(products);
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException(idProduct);
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException(idBrand);
		}
	}

	//Deleção de produto (Exceção finalizada)
	public void DeleteProduct(Long idProduct) {
		try {
			Products obj = productRepository.findById(idProduct).get();
			productRepository.deleteById(obj.getIdProduct());
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException(idProduct);
		}
	}
}	

