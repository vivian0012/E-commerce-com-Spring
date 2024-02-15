package com.ProdutosECompras.Project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ProdutosECompras.Project.profiel.AddProductToCart;
import com.ProdutosECompras.Project.profiel.ShoppingCart;
import com.ProdutosECompras.Project.services.ShoppingCartService;

@RestController
@RequestMapping(value = "/api/shoppingCart")
public class ShoppingCartHTTP {
	@Autowired
	private ShoppingCartService shoppingCartService;

	// Returnando apenas todo mundo.
	@GetMapping("/{id}")
	public ResponseEntity<ShoppingCart> findById(@PathVariable Long id) {
		ShoppingCart obj = shoppingCartService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	// Cria um carrinho (Sem produtos) e obrigando o navegador a associar com o usuário que deseja através do ID.
	@PostMapping("/UserID/{idUser}")
	public ResponseEntity<ShoppingCart> creatNewObj(@PathVariable Long idUser) {
		ShoppingCart obj = shoppingCartService.CreatObj(idUser);
		return ResponseEntity.ok().body(obj);
	}

	// Associando PRODUTOS dentro do CARRINHO (Adicionando)
	@PutMapping(value = "/addProductCart")
	public ResponseEntity<ShoppingCart> AddProductsShopping(@RequestBody AddProductToCart GetObj) {

		ShoppingCart request = shoppingCartService.AddNewProductTESTE(GetObj);
		return ResponseEntity.ok().body(request);
	}

	// Associando valores
	@PutMapping(value = "/{idCart}/product/{idProduct}")
	public ShoppingCart AssociationById(@PathVariable Long idCart, @PathVariable Long idProduct) {

		return shoppingCartService.AssociationData(idCart, idProduct);
	}

	// Deleção de carrinho
	@DeleteMapping("/delete/cartId/{idCart}/product/{idProduct}")
	public void DeleteProductsInCart(@PathVariable Long idCart, @PathVariable Long idProduct) {
		shoppingCartService.DeleteByIdProduct(idCart, idProduct);
	}
}
