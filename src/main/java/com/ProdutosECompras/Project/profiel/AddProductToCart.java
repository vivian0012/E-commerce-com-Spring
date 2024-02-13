package com.ProdutosECompras.Project.profiel;

import java.io.Serializable;

// CLASS USADA APENAS PARA GUARDAR O ID DO PRODUTO

public class AddProductToCart implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idCart;
	private Long idProduct;
	
	public Long getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}
	public Long getIdCart() {
		return idCart;
	}
	public void setIdCart(Long idCart) {
		this.idCart = idCart;
	}

}
