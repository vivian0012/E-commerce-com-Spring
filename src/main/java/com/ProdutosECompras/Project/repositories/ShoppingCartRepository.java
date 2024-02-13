package com.ProdutosECompras.Project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProdutosECompras.Project.profiel.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long>{

}
