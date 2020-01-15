package world.deslauriers.hellion.controller;

import java.net.URI;
import java.util.Collections;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import world.deslauriers.hellion.exception.AppException;
import world.deslauriers.hellion.model.Role;
import world.deslauriers.hellion.model.RoleName;
import world.deslauriers.hellion.model.User;
import world.deslauriers.hellion.payload.ApiResponse;
import world.deslauriers.hellion.payload.JwtAuthenticationResponse;
import world.deslauriers.hellion.payload.LoginRequest;
import world.deslauriers.hellion.payload.SignUpRequest;
import world.deslauriers.hellion.repository.RoleRepository;
import world.deslauriers.hellion.repository.UserRepository;
import world.deslauriers.hellion.security.JwtTokenProvider;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	@Autowired 
	private JwtTokenProvider tokenProvider;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginRequest.getUserNameOrEmail(),
						loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = tokenProvider.generateToken(authentication);
		
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest){
		
		if(userRepository.existsByUsername(signUpRequest.getUsername())) {
			
			return new ResponseEntity<>(
					new ApiResponse(
							false, "User name already taken!"), HttpStatus.BAD_REQUEST);
		}
		
		if(userRepository.existsByEmail(signUpRequest.getEmail())) {
			
			return new ResponseEntity<>(
					new ApiResponse(
							false, "Email Address is already in use!"), HttpStatus.BAD_REQUEST);
		}
		
		User user = new User(
							signUpRequest.getFirstname(),
							signUpRequest.getLastname(),
							signUpRequest.getUsername(),
							signUpRequest.getEmail(),
							signUpRequest.getPassword());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		Role userRole = roleRepository.findByRole(RoleName.USER)
				.orElseThrow(() -> new AppException("User Role not set."));
		
		user.setRoles(Collections.singleton(userRole));
		
		User result = userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder
						.fromCurrentContextPath().path("/users/{username}")
						.buildAndExpand(result.getUsername()).toUri();
		
		return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully."));
	}
}