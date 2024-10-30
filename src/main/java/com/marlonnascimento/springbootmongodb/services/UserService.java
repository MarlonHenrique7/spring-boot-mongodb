package com.marlonnascimento.springbootmongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marlonnascimento.springbootmongodb.domain.User;
import com.marlonnascimento.springbootmongodb.dto.UserDto;
import com.marlonnascimento.springbootmongodb.repositories.UserRepository;
import com.marlonnascimento.springbootmongodb.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	public List<User> findAll() {
		return repo.findAll();
	}

	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public User insert(User obj) {
		return repo.insert(obj);
	}

	public User fromDTO(UserDto objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}

	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}

}
