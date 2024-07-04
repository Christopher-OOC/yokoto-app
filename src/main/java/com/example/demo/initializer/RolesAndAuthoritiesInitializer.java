package com.example.demo.initializer;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.model.constant.Roles;
import com.example.demo.model.entity.Authority;
import com.example.demo.model.entity.Customer;
import com.example.demo.model.entity.Location;
import com.example.demo.model.entity.Role;
import com.example.demo.model.entity.User;
import com.example.demo.repository.AuthorityRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@Component
public class RolesAndAuthoritiesInitializer {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@EventListener
	public void initializeRolesAndAuthorities(ApplicationReadyEvent event) {
		
		Authority createAdminAuthority = createAuthority("CREATE_ADMIN");
		
		Authority deleteAdminAuthority = createAuthority("DELETE_ADMIN");
		
		Authority updateAdminAuthority = createAuthority("UPDATE_ADMIN");
		
		Role customerRole = createRole("CUSTOMER", Collections.emptyList());
		
		Role superAdminRole = createRole("SUPER_ADMIN", Arrays.asList(createAdminAuthority,
				deleteAdminAuthority, updateAdminAuthority));
		
		createSuperAdmin(Arrays.asList(superAdminRole));
		
	}

	private Authority createAuthority(String authorityName) {
		
		Authority authority = authorityRepository.findByAuthorityName(authorityName);
		
		if (authority == null) {
			authority = new Authority();
			authority.setAuthorityName(authorityName);
			
			Authority save = authorityRepository.save(authority);
			
			return save;
		}
		
		return authority;
	}
	
	private Role createRole(String roleName, List<Authority> authorities) {
		
		Role role = roleRepository.findByRoleName(roleName);
		
		List<Authority> authority = authorities;
		
		if (role == null) {
			role = new Role();
			role.setRoleName(roleName);
			
			for (Authority auth : authorities) {
				auth.getRoles().add(role);
			}
			
			role.setAuthorities(authorities);
			
			Role save = roleRepository.save(role);
			
			return save;
		}
		
		return role;
	}

	private void createSuperAdmin(List<Role> roles) {
		String superAdminEmail = "test@test.com";
		
		Customer customer = customerRepository.findByEmail(superAdminEmail);
		
		if (customer != null) {
			return;
		}

		Customer newCustomer = new Customer();
		newCustomer.setEmail(superAdminEmail);
		newCustomer.setFirstName("Christopher");
		newCustomer.setLastName("Olojede");
		newCustomer.setDateOfBirth(new Date());
		newCustomer.setCustomerId("kuyjjjskdhsadiadlpoi");
		
		Location location = new Location();
		location.setCityName("Lagos");
		location.setCountry("Nigeria");
		location.setState("Ogun");
		
		newCustomer.setLocation(location);
		
		User user = new User();
		user.setEmail(newCustomer.getEmail());
		user.setEmailVerificationStatus(true);
		user.setEmailVerificationToken(null);
		user.setPassword(passwordEncoder.encode("chris"));
		user.setPasswordResetToken(null);
		
		roles.forEach(role-> {
			user.getRoles().add(role);
		});
		
		newCustomer.setUser(user);
		
		customerRepository.save(newCustomer);
	}
	
}
