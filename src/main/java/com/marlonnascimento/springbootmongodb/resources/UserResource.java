package com.marlonnascimento.springbootmongodb.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marlonnascimento.springbootmongodb.domain.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		User maria = new User("1", "Maria", "maria@gmail.com");
		User maria2 = new User("2", "Maria2", "maria2gmail.com");

		List<User> list = new ArrayList<>();
		list.addAll(Arrays.asList(maria, maria2));

		return ResponseEntity.ok().body(list);
	}
}
