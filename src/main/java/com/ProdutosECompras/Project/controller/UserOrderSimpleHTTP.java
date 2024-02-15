package com.ProdutosECompras.Project.controller;

import java.util.List;

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

import com.ProdutosECompras.Project.profiel.UserOrderSimple;
import com.ProdutosECompras.Project.services.UserOrderSimpleService;

@RestController
@RequestMapping(value = "/api/user")
public class UserOrderSimpleHTTP {
	@Autowired
	private UserOrderSimpleService userOrderSimpleService;
	
	// Retornando TODOS
	@GetMapping
	public ResponseEntity<List<UserOrderSimple>> findAll(){ // ReponseEntity representa o chamado HTTPS
		List<UserOrderSimple> obj = userOrderSimpleService.findAll();
		return ResponseEntity.ok().body(obj);
	}
	// Adicionando um novo usuário
	@PostMapping
	public ResponseEntity<UserOrderSimple> CreatNewObj(@RequestBody UserOrderSimple obj) {
		obj = userOrderSimpleService.CreatObj(obj);
		return ResponseEntity.ok().body(obj);
	}
	// Atualizando usuário
	@PutMapping("/updateUser/{idUser}")
	public ResponseEntity<UserOrderSimple> UpdateObjUser(@PathVariable Long idUser, @RequestBody UserOrderSimple oldObjUser) {
		UserOrderSimple obj = userOrderSimpleService.UpdateNewUser(idUser, oldObjUser);
		return ResponseEntity.ok().body(obj);
	}
	// Deletando usuário
	@DeleteMapping("/deleteUser/{idUser}")
	public ResponseEntity<UserOrderSimple> deleteById(@PathVariable Long idUser) {
		userOrderSimpleService.deleteByIdUser(idUser);
		return ResponseEntity.noContent().build();
	}
}
