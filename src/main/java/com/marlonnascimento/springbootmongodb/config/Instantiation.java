package com.marlonnascimento.springbootmongodb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.marlonnascimento.springbootmongodb.domain.Post;
import com.marlonnascimento.springbootmongodb.domain.User;
import com.marlonnascimento.springbootmongodb.dto.AuthorDto;
import com.marlonnascimento.springbootmongodb.dto.CommentDto;
import com.marlonnascimento.springbootmongodb.repositories.PostRepository;
import com.marlonnascimento.springbootmongodb.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... arg0) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		userRepository.deleteAll();
		postRepository.deleteAll();

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");

		userRepository.saveAll(Arrays.asList(maria, alex, bob));

		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu Viagem!", "Vou viajar para SP.",
				new AuthorDto(maria));
		Post post2 = new Post(null, sdf.parse("21/03/2012"), "Partiu Viagem! 2", "Vou viajar para SP. 2",
				new AuthorDto(maria));

		CommentDto c1 = new CommentDto("Boa viagem, mano!", sdf.parse("21/03/2012"), new AuthorDto(alex));
		CommentDto c2 = new CommentDto("Boa viagem, mano!", sdf.parse("21/03/2012"), new AuthorDto(maria));
		CommentDto c3 = new CommentDto("Boa viagem, mano!", sdf.parse("21/03/2012"), new AuthorDto(bob));

		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().add(c3);

		postRepository.saveAll(Arrays.asList(post1, post2));

		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}

}
