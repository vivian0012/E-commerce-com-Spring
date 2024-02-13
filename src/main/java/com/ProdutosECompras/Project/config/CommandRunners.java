package com.ProdutosECompras.Project.config;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.ProdutosECompras.Project.profiel.Brand;
import com.ProdutosECompras.Project.profiel.Products;
import com.ProdutosECompras.Project.profiel.ShoppingCart;
import com.ProdutosECompras.Project.profiel.UserOrderSimple;
import com.ProdutosECompras.Project.repositories.BrandRepository;
import com.ProdutosECompras.Project.repositories.ProductRepository;
import com.ProdutosECompras.Project.repositories.ShoppingCartRepository;
import com.ProdutosECompras.Project.repositories.UserOrderSimpleRepository;

// INSTANCIAÇÃO DAS CLASSES

@Configuration
public class CommandRunners implements CommandLineRunner{
	
	@Autowired
	private ProductRepository Pservice;
	
	@Autowired
	private BrandRepository Bservice;
	
	@Autowired
	private ShoppingCartRepository Sservice;
	
	@Autowired
	private UserOrderSimpleRepository Uservice;
	
	
	@Override
	public void run(String... args) throws Exception {
	
		
	/*	// MARCA =============================================================
		Brand b1 = new Brand(null, "Kingston");
		Brand b2 = new Brand(null, "XPG");

		// PRODUTOS =============================================================
		Products p1 = new Products(null, "Memória Kingston Fury Beast RGB, 8GB, 3200MHz", "DDR4 e CL16", 2, 189.99, "https://www.kabum.com.br/produto/480526/memoria-kingston-fury-beast-rgb-8gb-3200mhz-ddr4-cl16-preto-kf432c16bb2a-8");
		Products p2 = new Products(null, "Memória XPG Spectrix D50 RGB, 8GB, 3600MHz,", "DDR4, CL18 e Cinza", 1, 185.99, "https://www.kabum.com.br/produto/201507/memoria-xpg-spectrix-d50-rgb-8gb-3600mhz-ddr4-cl18-cinza-ax4u36008g18i-st50");
		
	
		Bservice.saveAll(Arrays.asList(b1, b2));
		
		p1.getBrandL().add(b1);
		p2.getBrandL().add(b2);
		
		Pservice.saveAll(Arrays.asList(p1, p2));

		// SALVANDO USUÁRIO =============================================================
		
		UserOrderSimple u1 = new UserOrderSimple(null, "Teste1", LocalDateTime.parse("2023-05-05T14:05:23"));
		Uservice.save(u1);
		// Redefinindo o usuário no ShoppingCart para que os dados seja os mesmos de UserOrderSimple
		ShoppingCart s1 = new ShoppingCart();


		s1.setUserInfo(u1); 

		
		// Colocando os produtos p1 e p2 dentro do carrinho de compra s1.
		s1.getProducts().add(p1);
		s1.getProducts().add(p2);
		
	
		// Colocando os produtos p1 e p2 dentro do carrinho de compra s1.
		p1.setShoppingcart(s1);
		p2.setShoppingcart(s1);

		
		// Salvando os itens 
		Sservice.save (s1);
	    Pservice.saveAll(Arrays.asList(p1, p2)); */
		
	}
}
