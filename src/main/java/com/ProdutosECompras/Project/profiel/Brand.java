package com.ProdutosECompras.Project.profiel;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_brands")
public class Brand implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idBrand;
	private String label;
	
	@ManyToMany (mappedBy = "brandList")
	Set<Products> ProdutosL = new HashSet<>();

	public Brand() {
	}

	public Brand(Long idBrand, String label) {
		super();
		this.idBrand = idBrand;
		this.label = label;
	}
	public Long getIdBrand() {
		return idBrand;
	}

	public void setIdBrand(Long idBrand) {
		this.idBrand = idBrand;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public Set<Products> getProdutosL() {
		return ProdutosL;
	}

	public void setProdutosL(Set<Products> produtosL) {
		ProdutosL = produtosL;
	}

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
