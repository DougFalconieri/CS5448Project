package application.models;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String email;
	private String password;
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name="user_role", 
				joinColumns={@JoinColumn(name="user_id")}, 
				inverseJoinColumns={@JoinColumn(name="role_id")})
	private Set<Role> roles = new HashSet<Role>();
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRolesDescription() {
		if (roles.size() == 0) return "Employee";
		return roles.stream()
			  .map(Role::getName)
			  .collect(Collectors.joining(", "));
	}
	
	public boolean hasRole(String roleName) {
		return roles.stream()
			  .anyMatch(role -> role.getName().equals(roleName));
	}
}
