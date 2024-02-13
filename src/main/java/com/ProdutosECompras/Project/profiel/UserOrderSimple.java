package com.ProdutosECompras.Project.profiel;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

// Classe apenas para demonstrativo de usuário

@Entity
@Table(name = "tb_user")
@JsonPropertyOrder({ "idUser", "name", "dateTime", "quantity", "valueOrder", "idCart" })
public class UserOrderSimple implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUser;
	
	private String name;
	private LocalDateTime dateTime;

	@OneToOne(mappedBy = "UserInfo", cascade = CascadeType.ALL)
	@JsonIgnore
	private ShoppingCart shoppingCart;

	// private List<ShoppingCart> produtos = new ArrayList<>();

	public UserOrderSimple() {

	}

	public UserOrderSimple(Long idUser, String name, LocalDateTime dateTime) {
		super();
		this.idUser = idUser;
		this.name = name;
		this.dateTime = dateTime;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	// Tentando pegar os cados do Carrinho
	public Integer getQuantity() {
		if (shoppingCart != null) {
			return shoppingCart.getQuantity();
		} else {
			return null;
		}

	}

	// Carrega do valor do carrinho
	public Double getValueOrder() {

		if (shoppingCart != null) {
			return shoppingCart.getTotalValue();
		} else {
			return null;
		}

	}

	public Long getIdCart() {

		if (shoppingCart != null) {
			return shoppingCart.getIdCart();
		} else {
			return null;
		}
	}

}
