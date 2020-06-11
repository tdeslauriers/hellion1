package world.deslauriers.hellion.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

import world.deslauriers.hellion.model.audit.DateAudit;

@Entity
@Table(name="user",
		uniqueConstraints=@UniqueConstraint(columnNames="email")
		)
public class User extends DateAudit {
	
	private static final long serialVersionUID = 3512550283017130240L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(name="firstname")
	private String firstname; 
	
	@NotBlank
	@Column(name="lastname")
	private String lastname;
	
	@NotBlank
	@Column(name="username")
	private String username;
	
	@NaturalId
	@NotBlank
	@Size(max=40)
	@Email
	@Column(name="email")
	private String email;
	
	@NotBlank
	@Column(name="password")
	private String password;
	 
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="user_role", 
				joinColumns=@JoinColumn(name="user_id"), 
				inverseJoinColumns=@JoinColumn(name="role_id"))
	private Set<Role> roles;
	
	public User() {}

	public User(String firstname, String lastname, String username, String email, String password) {
		
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
