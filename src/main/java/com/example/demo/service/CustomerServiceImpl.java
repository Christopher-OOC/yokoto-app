package com.example.demo.service;

import java.util.Arrays;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.exception.NoResourceFoundException;
import com.example.demo.model.constant.Roles;
import com.example.demo.model.dto.CustomerDto;
import com.example.demo.model.dto.EventCeremonyDto;
import com.example.demo.model.entity.Ceremony;
import com.example.demo.model.entity.Customer;
import com.example.demo.model.entity.EventCeremony;
import com.example.demo.model.entity.User;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.utils.EntityCheckerUtils;
import com.example.demo.utils.PublicIdGeneratorUtils;
import com.example.demo.utils.TokenGenerators;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	private CustomerRepository customerRepository;
	
	private EmailService emailService;
	
	private RoleRepository roleRepository;
	
	private EntityCheckerUtils entityCheckerUtils;
	
	private BCryptPasswordEncoder passwordEncoder;
	
	private ModelMapper modelMapper;
	
	public CustomerServiceImpl(CustomerRepository customerRepository, EmailService emailService,
			RoleRepository roleRepository, EntityCheckerUtils entityCheckerUtils,
			BCryptPasswordEncoder passwordEncoder, ModelMapper modelMapper
			) {
		this.customerRepository = customerRepository;
		this.emailService = emailService;
		this.roleRepository = roleRepository;
		this.entityCheckerUtils = entityCheckerUtils;
		this.passwordEncoder = passwordEncoder;
		this.modelMapper = modelMapper;
	}

	@Override
	public void save(CustomerDto customerDto) {
		
		System.out.println("HERE 3");
		
		Customer customerEntity = modelMapper.map(customerDto, Customer.class);
		customerEntity.setCustomerId(PublicIdGeneratorUtils.generatePublicId(30));
		
		User user = new User();
		user.setEmail(customerDto.getEmail());
		user.setPassword(passwordEncoder.encode(customerDto.getPassword()));
		user.setEmailVerificationStatus(false);
		user.setEmailVerificationToken(TokenGenerators.generateEmailVerificationToken(customerDto.getEmail()));
		user.setPasswordResetToken(null);
		user.setRoles(Arrays.asList(roleRepository.findByRoleName(Roles.CUSTOMER.name())));
		
		customerEntity.setUser(user);
		
		customerRepository.save(customerEntity);
		
		//emailService.sendEmailVerification(user);
	}

	@Override
	public CustomerDto findByEmail(String email) {
		Customer customer = customerRepository.findByEmail(email);
		
		if (customer == null) {
			throw new NoResourceFoundException("No such customer");
		}
		
		return modelMapper.map(customer, CustomerDto.class);
	}

	@Override
	public CustomerDto findByCustomerId(String customerId) {
		
		Customer customer = entityCheckerUtils.checkIfCustomerExists(customerId);
		
		return modelMapper.map(customer, CustomerDto.class);
	}

	@Override
	public void postEventCeremony(String customerId, EventCeremonyDto dto, String ceremonyName) {
		
		Customer customer = entityCheckerUtils.checkIfCustomerExists(customerId);
		
		Ceremony ceremony = entityCheckerUtils.checkIfCeremonyExistsByName(ceremonyName);
		
		
		EventCeremony eventCeremony = modelMapper.map(dto, EventCeremony.class);
		eventCeremony.setCustomer(customer);
		eventCeremony.setCeremony(ceremony);
		eventCeremony.setDateCreated(new Date());
	}

}
