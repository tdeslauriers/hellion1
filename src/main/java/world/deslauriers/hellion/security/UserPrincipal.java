package world.deslauriers.hellion.security;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import world.deslauriers.hellion.model.User;

public class UserPrincipal implements UserDetails {
	
	private static final long serialVersionUID = -582634152766878554L;
	
	private Long id;
	private String firstname;
	private String lastname;
	private String username;
	
	@JsonIgnore
	private String email;
	
	@JsonIgnore 
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserPrincipal(Long id, String firstname, String lastname, String username, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserPrincipal create(User user) {
		
		List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
				
				new SimpleGrantedAuthority(role.getRole().name())
			).collect(Collectors.toList());
		
		return new UserPrincipal(
						user.getId(), 
						user.getFirstname(), 
						user.getLastname(), 
						user.getUsername(),
						user.getEmail(), 
						user.getPassword(), 
						authorities
		);
	}
	
	public Long getId() {
		return id;
	}
	
	public String getFirstName() {
		return firstname;
	}
	
	public String getLastName() {
		return lastname;
	}
	public String getEmail() {
		return email;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public boolean equals(Object o) {
		
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		UserPrincipal that = (UserPrincipal) o;
		return Objects.equals(id,  that.id);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String getUsername() {
		return username;
	}

}
