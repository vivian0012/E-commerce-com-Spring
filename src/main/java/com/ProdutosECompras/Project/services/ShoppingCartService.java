package com.ProdutosECompras.Project.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.ProdutosECompras.Project.services.exceptions.ResourceNotFoundException;
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
	private ShoppingCartRepository shoppingCartRepository;

	@Autowired
	private UserOrderSimpleService userOrderSimpleRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private EntityManager entityManager;

	// RETORNANDO TODOS (Não precisa)
	public List<ShoppingCart> findAll() {
		return shoppingCartRepository.findAll();
	}

	// RETORNANDO UM VALOR POR ID (Exceção tratada)
	public ShoppingCart findById(Long id) {
		try {
			ShoppingCart obj = shoppingCartRepository.findById(id).get();
			return obj;
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	// ADICIONANDO UM NOVO VALOR (Exceção tratada)
	public ShoppingCart CreatObj(Long idUser) {
			ShoppingCart spCart = new ShoppingCart();
			UserOrderSimple findUserObj = userOrderSimpleRepository.findById(idUser); // Chama a função e localiza o ID
			spCart.setUserInfo(findUserObj);
			return shoppingCartRepository.save(spCart);
	}

	// COLOCANDO O PRODUTO DENTRO DO CARRINHO DE COMPRA USANDO O CORPO DO JSON (Exceção tratada)
	@Transactional
	public ShoppingCart AddNewProductTESTE(AddProductToCart request) {
		// Colocando os IDs dentro do findById para encontrar os valores correspondente
		try {
			// Pego o ID que vem da requisição
			Long idCart = request.getIdCart();
			Long idProduct = request.getIdProduct();
			ShoppingCart Cart = shoppingCartRepository.findById(idCart).get();
			Products products = productRepository.findById(idProduct).get();
			List<Products> productList = Cart.getProductList();
			products.setShoppingcart(Cart);
			productList.add(products);
			Cart.setProductList(productList);
			entityManager.flush();
			return shoppingCartRepository.save(Cart);

		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException(request);
		}
	}

	// DELEÇÃO DE PRODUTOS DENTRO DO CARRINHO (Exceção tratada)
	public void DeleteByIdProduct(Long idCart, Long idProduct) {
		Optional<ShoppingCart> cartOptional = shoppingCartRepository.findById(idCart);

		// CASO TUDO DER CERTO
		if (cartOptional.isPresent()) {
			ShoppingCart cart = cartOptional.get(); // Pegando o Id do carrinho
			boolean isProductInCart = false;
			for (Products product : cart.getProductList()) {
				if (product.getIdProduct().equals(idProduct)) {
					isProductInCart = true;
					break;
				}
			}
			// CASO O ID DO PRODUTO ESTEJA ERRADO.
			if(isProductInCart) {
				productRepository.deleteById(idProduct);
			} else {
				throw new ResourceNotFoundException(idProduct);
			}

		} else {
			// CASO O ID DO CARRINHO ESTEJA ERRADO.
			throw new ResourceNotFoundException(idCart);
		}
	}

	// Atualização por URL (Não precisa)
	public ShoppingCart AssociationData(Long idCart, Long idProduct) {

		ShoppingCart Cart = shoppingCartRepository.findById(idCart).get(); // Instanciado
		Products products = productRepository.findById(idProduct).get();

		List<Products> productList = Cart.getProductList();

		products.setShoppingcart(Cart);
		productList.add(products);
		Cart.setProductList(productList);

		return shoppingCartRepository.save(Cart);

	}


}
