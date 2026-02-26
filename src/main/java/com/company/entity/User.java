

	
	
	package com.company.entity;

	import jakarta.persistence.*;

	@Entity
	@Table(name = "users")
	public class User {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(unique = true, nullable = false)
	    private String email;

	    @Column(nullable = false)
	    private String password;

	    private String name;

	    private String role;  
	    
	    
	    @Column(unique = true, nullable = false)
	    private String username;

	    // getter and setter
	    public String getUsername() { return username; }
	    public void setUsername(String username) { this.username = username; }
// ROLE_USER / ROLE_ADMIN

	    // constructors
	    public User() {}

	    public User(String email, String password, String name, String role) {
	        this.email = email;
	        this.password = password;
	        this.name = name;
	        this.role = role;
	    }

	    // getters setters
	    public Long getId() { return id; }

	    public String getEmail() { return email; }
	    public void setEmail(String email) { this.email = email; }

	    public String getPassword() { return password; }
	    public void setPassword(String password) { this.password = password; }

	    public String getName() { return name; }
	    public void setName(String name) { this.name = name; }

	    public String getRole() { return role; }
	    public void setRole(String role) { this.role = role; }
	}

	
	
	
	

