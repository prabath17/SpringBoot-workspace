package com.example.SPRING_DEMO_1.controllers;

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

import com.example.SPRING_DEMO_1.entity.UserEntity;
import com.example.SPRING_DEMO_1.exceptions.ResourceNotFoundException;
import com.example.SPRING_DEMO_1.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserContoller {
	@Autowired
	private UserRepository userRepository;
	//@GetMapping
	//public String getUsers() {
	//	return "Hello ";
	//}
	@GetMapping
    public List<UserEntity> getUsers() {
        //return Arrays.asList(
        //    new user(1L, "Roy", "roy@gmail.com"),
        //    new user(2L, "John", "john@gmail.com"),
        //    new user(3L,"Alice","alice@hotmail.com")
        //);
		return userRepository.findAll();
    }
	@PostMapping
	public UserEntity createUser(@RequestBody UserEntity user) {
		//System.out.print("User name:"+ user.getName()+", "+user.getEmail());
		return userRepository.save(user);
		//return null;
	}
//	@GetMapping("/{id}")
//	public Optional<UserEntity> getUserById(@PathVariable Long id) {
//		return userRepository.findById(id);
//	}
	@GetMapping("/{id}")
	public UserEntity getUserById(@PathVariable Long id) {
	    return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User ID not found: " + id));
	}
	@PutMapping("/{id}")
	public UserEntity updateUser(@PathVariable Long id,@RequestBody UserEntity user) {
	    UserEntity userdata= userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User ID not found: " + id));
	    userdata.setEmail(user.getEmail());
	    userdata.setName(user.getName());
	    return userRepository.save(userdata);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteuser(@PathVariable Long id) {
		UserEntity userdata=userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User ID not found: " + id));
		userRepository.delete(userdata);
		return ResponseEntity.ok().build();
	}
}
