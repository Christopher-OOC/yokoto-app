package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.example.demo.repository.UserRepository;
import com.example.demo.service.CustomerService;
import com.example.demo.service.EmailService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.constant.Roles;
import com.example.demo.model.dto.CustomerDto;
import com.example.demo.model.entity.Ceremony;
import com.example.demo.model.entity.Customer;
import com.example.demo.model.entity.Dish;
import com.example.demo.model.entity.EventCeremony;
import com.example.demo.model.entity.User;
import com.example.demo.model.request.EventCeremonyRequestModel;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.EventCeremonyRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.utils.EntityCheckerUtils;
import com.example.demo.utils.PublicIdGeneratorUtils;
import com.example.demo.utils.TokenGenerators;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	private CustomerRepository customerRepository;

	private UserRepository userRepository;
	
	private EmailService emailService;
	
	private EventCeremonyRepository eventCeremonyRepository;
	
	private RoleRepository roleRepository;
	
	private EntityCheckerUtils entityCheckerUtils;
	
	private BCryptPasswordEncoder passwordEncoder;
	
	private ModelMapper modelMapper;
	
	public CustomerServiceImpl(CustomerRepository customerRepository, EmailService emailService,
			RoleRepository roleRepository, EntityCheckerUtils entityCheckerUtils,
			BCryptPasswordEncoder passwordEncoder, EventCeremonyRepository eventCeremonyRepository,
			ModelMapper modelMapper, UserRepository userRepository
			) {
		this.customerRepository = customerRepository;
		this.emailService = emailService;
		this.roleRepository = roleRepository;
		this.entityCheckerUtils = entityCheckerUtils;
		this.eventCeremonyRepository = eventCeremonyRepository;
		this.passwordEncoder = passwordEncoder;
		this.modelMapper = modelMapper;
		this.userRepository = userRepository;
	}

	@Override
	public void createCustomer(CustomerDto customerDto) {
		
		Customer customerEntity = modelMapper.map(customerDto, Customer.class);
		customerEntity.setCustomerId(PublicIdGeneratorUtils.generatePublicId(30));

		customerRepository.save(customerEntity);

		User user = new User();
		user.setCustomerId(customerEntity.getCustomerId());
		user.setEmail(customerDto.getEmail());
		user.setPassword(passwordEncoder.encode(customerDto.getPassword()));
		user.setEmailVerificationStatus(true);
		user.setEmailVerificationToken(TokenGenerators.generateEmailVerificationToken(customerDto.getEmail()));
		user.setPasswordResetToken(null);
		user.setRoles(Arrays.asList(roleRepository.findByRoleName(Roles.CUSTOMER.name())));

		userRepository.save(user);
		
		//emailService.sendEmailVerification(user);
	}

	@Override
	public CustomerDto findByCustomerId(String customerId) {
		
		Customer customer = entityCheckerUtils.checkIfCustomerExists(customerId);
		
		return modelMapper.map(customer, CustomerDto.class);
	}

	@Override
	public void postEventCeremony(String customerId, EventCeremonyRequestModel requestModel) {
		
		Customer customer = entityCheckerUtils.checkIfCustomerExists(customerId);
		
		Ceremony ceremony = entityCheckerUtils.checkIfCeremonyExistsByName(requestModel.getCeremonyName());
		
		List<Dish> dishesToBePrepared = new ArrayList<>();
		
		requestModel.getIdOfDishesToBePrepared().forEach(dishId -> {
			Dish dish = entityCheckerUtils.checkIfDishExists(dishId);
			
			dishesToBePrepared.add(dish);
		});
		
		
		EventCeremony eventCeremony = modelMapper.map(requestModel, EventCeremony.class);
		eventCeremony.setEventCeremonyId(PublicIdGeneratorUtils.generatePublicId(30));
		eventCeremony.setCustomer(customer);
		eventCeremony.setCeremony(ceremony);
		eventCeremony.setDishesToBePrepared(dishesToBePrepared);
		eventCeremony.setDateCreated(new Date());
		
		eventCeremonyRepository.save(eventCeremony);
	}

}
