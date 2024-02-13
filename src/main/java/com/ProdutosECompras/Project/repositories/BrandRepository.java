package com.ProdutosECompras.Project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ProdutosECompras.Project.profiel.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long>{

}
