package com.ProdutosECompras.Project.profiel;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_product")
@JsonPropertyOrder({"idProduct", "name", "description", "quantity", "value", "link", "brandList"})
public class Products implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // O banco de dados organizará o id
	private Long idProduct;
	private String name;
	private String description;
	private Integer quantity;
	private Double value;
	private String link;
	//================================================================

	@ManyToMany
	@JoinTable(name = "tb_product_brand", 
	joinColumns = @JoinColumn(name = "product_id"), 
	inverseJoinColumns = @JoinColumn(name = "brand_id"))
	@JsonIgnore
	private Set<Brand> brandList = new HashSet<>();

	//================================================================
	@ManyToOne
	@JsonIgnore
	private ShoppingCart shoppingcart;

	public Products() {
	}
	
	// CONSTRUCTOR
	public Products(Long idProduct, String name, String description, Integer quantity, Double value, String link) {
		super();
		this.idProduct = idProduct;
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.value = value;
		this.link = link;

	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	// GETTER AND SETTER
	public Long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public ShoppingCart getShoppingcart() {
		return shoppingcart;
	}

	public void setShoppingcart(ShoppingCart shoppingcart) {
		this.shoppingcart = shoppingcart;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Set<Brand> getBrandList() {
		return brandList;
	}

	public void setBrandList(Set<Brand> brandList) {
		this.brandList = brandList;
	}
	
}
