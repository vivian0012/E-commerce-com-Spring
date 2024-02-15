package com.ProdutosECompras.Project.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.ProdutosECompras.Project.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProdutosECompras.Project.profiel.UserOrderSimple;
import com.ProdutosECompras.Project.repositories.ShoppingCartRepository;
import com.ProdutosECompras.Project.repositories.UserOrderSimpleRepository;

@Service
public class UserOrderSimpleService {

	@Autowired
	private UserOrderSimpleRepository userOrderSimpleRepository;

	// Retorna todos (Não precisa)
	public List<UserOrderSimple> findAll() {
		return userOrderSimpleRepository.findAll();
	}

	// Retornando por ID. SENDO USADO PELO ShoppingCartSERVICE (Exceção tratada)
	public UserOrderSimple findById(Long id) {
		try{
			UserOrderSimple obj = userOrderSimpleRepository.findById(id).get();
			return obj;
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	// Inserção do usuário (Não precisa)
	public UserOrderSimple CreatObj(UserOrderSimple obj) {
		return userOrderSimpleRepository.save(obj);

		// Automaticamente o JSON retorna um 400 informando que o usuário digitou errado
	}

	// Deletar Usuário (Exceção tratada)
	public void deleteByIdUser(Long idUser) {
		try {
		UserOrderSimple obj = userOrderSimpleRepository.findById(idUser).get();
		userOrderSimpleRepository.deleteById(obj.getIdUser());
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException(idUser);
		}
	}

	// Update Usuário (Exceção tratada)
	public UserOrderSimple UpdateNewUser(Long idUser, UserOrderSimple oldObjUser) {
		try {
			UserOrderSimple newUser = userOrderSimpleRepository.getReferenceById(idUser);
			UpdateData(newUser, oldObjUser);
			return userOrderSimpleRepository.save(newUser);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(idUser);
		}
	}
	private void UpdateData(UserOrderSimple newUser, UserOrderSimple oldObjUser) {
		newUser.setName(oldObjUser.getName());
		newUser.setDateTime(oldObjUser.getDateTime());
		
	}

}
