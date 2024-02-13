package com.ProdutosECompras.Project.profiel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_shopping_cart")
@JsonPropertyOrder({"id", "userInfo", "products", "totalValue", "quantity"})
public class ShoppingCart implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCart;
	
	@OneToOne
	@JoinColumn(name = "id_user")
	private UserOrderSimple UserInfo;
	
	
	@OneToMany(mappedBy = "shoppingcart")
	private List<Products> products = new ArrayList<>();
	
	public ShoppingCart() {

	}
	
	public Long getIdCart() {
		return idCart;
	}
	

	public void setIdCart(Long idCart) {
		this.idCart = idCart;
	}
	
	public UserOrderSimple getUserInfo() {
		return UserInfo;
	}


	public void setUserInfo(UserOrderSimple UserInfo) {
		this.UserInfo = UserInfo;
	}


	// Tentando pegar todos os produtos
	public Integer getQuantity() {
		Integer getQuantityTotal = 0;
		for (Products x : products) {
			getQuantityTotal += x.getQuantity();
		}
		return getQuantityTotal;
	}

	// Tentando pegar o VALOR TOTAL
	public Double getTotalValue() {
		Double sum = 0.0;
		for(Products x: products) {
			Double count = x.getValue() * x.getQuantity();
			sum += count;
		}
		return sum;
	}

	public List<Products> getProducts() {
		return products;
	}
	
	public void setProducts(List<Products> products) {
		this.products = products;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	


	
	
}
