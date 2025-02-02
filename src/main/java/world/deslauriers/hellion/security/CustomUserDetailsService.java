package world.deslauriers.hellion.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import world.deslauriers.hellion.exception.ResourceNotFoundException;
import world.deslauriers.hellion.model.User;
import world.deslauriers.hellion.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String usernameOrEmail) 
			throws UsernameNotFoundException {
		
		User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
				.orElseThrow(
						() -> new UsernameNotFoundException(
								"Username not found with username or email: " + usernameOrEmail));
		
		return UserPrincipal.create(user);
	}
		
		@Transactional
		public UserDetails loadUserById(Long id) {
			
			User user = userRepository.findById(id).orElseThrow(
					() -> new ResourceNotFoundException("User", "id", id));
			
			return UserPrincipal.create(user);
		}
	
	
}
