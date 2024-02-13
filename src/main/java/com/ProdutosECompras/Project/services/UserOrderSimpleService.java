package com.ProdutosECompras.Project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProdutosECompras.Project.profiel.UserOrderSimple;
import com.ProdutosECompras.Project.repositories.ShoppingCartRepository;
import com.ProdutosECompras.Project.repositories.UserOrderSimpleRepository;

@Service
public class UserOrderSimpleService {

	@Autowired
	private UserOrderSimpleRepository Uservice;
	
	@Autowired
	private ShoppingCartRepository Sservice;
	
	@Autowired

	// Retorna todos.
	public List<UserOrderSimple> findAll() {
		return Uservice.findAll();
	}

	// Retornando por ID
	public Optional<UserOrderSimple> findById(Long id) {
		Optional<UserOrderSimple> obj = Uservice.findById(id);
		if (obj.isPresent()) {
			return obj;
		} else {
			throw new RuntimeException("Id não encontrado");
		}
	}

	// Inserção do usuário:
	public UserOrderSimple CreatObj(UserOrderSimple obj) {
		return Uservice.save(obj);
	}

	// Deletar Usuário
	public void deleteByIdUser(Long idUser) {
		Optional<UserOrderSimple> obj = Uservice.findById(idUser);
		
		if(obj != null) {
			 Uservice.deleteById(idUser);
		} 
		else {
			throw new RuntimeException("Id não encontrado");
		}
	}

	// Update Usuário
	public UserOrderSimple UpdateNewUser(Long idUser, UserOrderSimple oldObjUser) {
		UserOrderSimple newUser = Uservice.getReferenceById(idUser);
		
		UpdateData(newUser, oldObjUser);
		return Uservice.save(newUser);
	}

	private void UpdateData(UserOrderSimple newUser, UserOrderSimple oldObjUser) {
		newUser.setName(oldObjUser.getName());
		newUser.setDateTime(oldObjUser.getDateTime());
		
	}

}
