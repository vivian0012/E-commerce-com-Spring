package com.ProdutosECompras.Project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ProdutosECompras.Project.profiel.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long>{

}
