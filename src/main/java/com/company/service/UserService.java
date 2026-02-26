
	package com.company.service;

	import java.util.Collection;
	import java.util.Collections;

	import org.springframework.security.core.GrantedAuthority;
	import org.springframework.security.core.authority.SimpleGrantedAuthority;
	import org.springframework.security.core.userdetails.*;
	import org.springframework.stereotype.Service;

	import lombok.RequiredArgsConstructor;

import com.company.dto.RegisterRequest;
import com.company.entity.User;
	import com.company.repository.UserRepository;

	@Service

	public class UserService implements UserDetailsService {

	    private final UserRepository userRepository;
	    
	    
	    public void registerUser(RegisterRequest request) {
	        // Example: convert request to User entity and save
	        User user = new User();
	        user.setEmail(request.getEmail());
	        user.setPassword(request.getPassword()); // hash in real app
	        userRepository.save(user);
	    }

	    
	    
	    
	    
	    public UserService(UserRepository userRepository) {
	        this.userRepository = userRepository;
	    }

	    // find user by email (used by JWT login)
	    public User findByEmail(String email) {
	        return userRepository.findByEmail(email).orElse(null);
	    }

	    // This method is called automatically by Spring Security during login
	    @Override
	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

	        User user = userRepository.findByEmail(email)
	                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
                   
	        System.out.println("Trying login for username: " + email);
	        System.out.println("Stored password: " + user.getPassword());
	        
	        return new org.springframework.security.core.userdetails.User(
	                user.getEmail(),
	                user.getPassword(),
	                getAuthorities(user)
	        );
	    }

	    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
	        return Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));
	    }
	}



