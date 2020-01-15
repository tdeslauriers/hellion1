package world.deslauriers.hellion.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import world.deslauriers.hellion.exception.ResourceNotFoundException;
import world.deslauriers.hellion.model.User;
import world.deslauriers.hellion.payload.UserIdentityAvailability;
import world.deslauriers.hellion.payload.UserProfile;
import world.deslauriers.hellion.payload.UserSummary;
import world.deslauriers.hellion.repository.UserRepository;
import world.deslauriers.hellion.security.CurrentUser;
import world.deslauriers.hellion.security.UserPrincipal;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@GetMapping("/user/me")
//	@PreAuthorize("hasRole('USER')")
	public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
		
		UserSummary userSummary = new UserSummary(
												currentUser.getId(),
												currentUser.getUsername(),
												currentUser.getFirstName(),
												currentUser.getLastName());
		
		return userSummary;
	}
	
	@GetMapping("/users/{username}")
	public UserProfile getUserProfile(@PathVariable(value="username") String username) {
		
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
		UserProfile userProfile = new UserProfile(
												user.getId(), 
												user.getUsername(), 
												user.getFirstname(), 
												user.getLastname(), 
												user.getCreatedAt());
		return userProfile;
	}
	
    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        
    	Boolean isAvailable = !userRepository.existsByUsername(username);
        
    	return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        
    	Boolean isAvailable = !userRepository.existsByEmail(email);
        
    	return new UserIdentityAvailability(isAvailable);
    }
} 
