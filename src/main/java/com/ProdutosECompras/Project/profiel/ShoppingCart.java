package com.ProdutosECompras.Project.profiel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_shopping_cart")
@JsonPropertyOrder({"idCart", "userInfo", "products", "totalValue", "quantity"})
public class ShoppingCart implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCart;
	
	@OneToOne
	@JoinColumn(name = "id_user")
	private UserOrderSimple userInfo;
	
	
	@OneToMany(mappedBy = "shoppingcart")
	private List<Products> productList = new ArrayList<>();
	
	public ShoppingCart() {

	}
	
	public Long getIdCart() {
		return idCart;
	}
	

	public void setIdCart(Long idCart) {
		this.idCart = idCart;
	}
	
	public UserOrderSimple getUserInfo() {
		return userInfo;
	}


	public void setUserInfo(UserOrderSimple userInfo) {
		this.userInfo = userInfo;
	}


	// Tentando pegar todos os produtos
	public Integer getQuantity() {
		Integer getQuantityTotal = 0;
		for (Products x : productList) {
			getQuantityTotal += x.getQuantity();
		}
		return getQuantityTotal;
	}

	// Tentando pegar o VALOR TOTAL
	public Double getTotalValue() {
		Double sum = 0.0;
		for(Products x: productList) {
			Double count = x.getValue() * x.getQuantity();
			sum += count;
		}
		return sum;
	}

	public List<Products> getProductList() {
		return productList;
	}
	
	public void setProductList(List<Products> productList) {
		this.productList = productList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	


	
	
}
