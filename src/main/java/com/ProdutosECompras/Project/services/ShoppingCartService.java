package com.ProdutosECompras.Project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProdutosECompras.Project.profiel.AddProductToCart;
import com.ProdutosECompras.Project.profiel.Products;
import com.ProdutosECompras.Project.profiel.ShoppingCart;
import com.ProdutosECompras.Project.profiel.UserOrderSimple;
import com.ProdutosECompras.Project.repositories.ProductRepository;
import com.ProdutosECompras.Project.repositories.ShoppingCartRepository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
public class ShoppingCartService {

	@Autowired
	private ShoppingCartRepository Sservice;

	@Autowired
	private UserOrderSimpleService Uservice;

	@Autowired
	private ProductRepository Pservice;

	@Autowired
	private EntityManager entityManager;

	// Retorna todos.
	public List<ShoppingCart> findAll() {
		return Sservice.findAll();
	}

	// Retornando por ID
	public Optional<ShoppingCart> findById(Long id) {
		Optional<ShoppingCart> obj = Sservice.findById(id);
		if (obj.isPresent()) {
			return obj;
		} else {
			throw new RuntimeException("Id não encontrado");
		}
	}

	// Inserção de um novo produto
	public ShoppingCart CreatObj(Long idUser) {

		ShoppingCart spCart = new ShoppingCart();

		// Encontrando o ID do usuário
		UserOrderSimple findUserObj = Uservice.findById(idUser).get();

		// Associando o Usuário ao carrinho
		spCart.setUserInfo(findUserObj);

		return Sservice.save(spCart);
	}

	// Atualização por JSON
	@Transactional
	public ShoppingCart AddNewProductTESTE(AddProductToCart request) {
		// Pego o ID que vem da requisição
		Long idCart = request.getIdCart();
		Long idProduct = request.getIdProduct();

		// Colocando os IDs dentro do findById para encontrar os valores correspondente
		ShoppingCart Cart = Sservice.findById(idCart).get(); // Instanciado
		Products products = Pservice.findById(idProduct).get();

		List<Products> productList = Cart.getProducts();

		products.setShoppingcart(Cart);
		productList.add(products);

		Cart.setProducts(productList);
		entityManager.flush();

		return Sservice.save(Cart);

	}

	// Atualização por URL
	public ShoppingCart AssociationData(Long idCart, Long idProduct) {

		ShoppingCart Cart = Sservice.findById(idCart).get(); // Instanciado
		Products products = Pservice.findById(idProduct).get();

		List<Products> productList = Cart.getProducts();

		products.setShoppingcart(Cart);
		productList.add(products);
		Cart.setProducts(productList);

		return Sservice.save(Cart);

	}

	// Deleção de Produtos
	public void DeleteByIdProduct(Long idCart, Long idProduct) {
		Optional<ShoppingCart> cartOptional = Sservice.findById(idCart);

		if (cartOptional.isPresent()) {
			ShoppingCart cart = cartOptional.get(); // Pegando o Id do carrinho

			Boolean isProductInCart = false;
			
			for (Products product : cart.getProducts()) {
				if (product.getIdProduct().equals(idProduct)) {
					isProductInCart = true;
					break;
				} else {
					throw new RuntimeException("Erro ao encontraro o produto");
				}
			}
			if(isProductInCart == true) {
				Pservice.deleteById(idProduct);
			} else {
				throw new RuntimeException("Produto não encontrado no carrinho");
			}
			
		} else {
			throw new RuntimeException("Carrinho não encontrado");
		}
	}
}
