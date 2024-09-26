package com.example.demo.service.impl;

import com.example.demo.exception.ResourceAlreadyExistsException;
import com.example.demo.model.dto.BusinessRetailDto;
import com.example.demo.model.entity.BusinessRetail;
import com.example.demo.model.entity.Customer;
import com.example.demo.repository.BusinessRetailRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.BusinessRetailService;
import com.example.demo.utils.EntityCheckerUtils;
import org.modelmapper.ModelMapper;

public class BusinessRetailServiceImpl implements BusinessRetailService {

    private CustomerRepository customerRepository;

    private BusinessRetailRepository businessRetailRepository;

    private ModelMapper modelMapper;

    private EntityCheckerUtils entityCheckerUtils;

    public BusinessRetailServiceImpl(
            CustomerRepository customerRepository,
            BusinessRetailRepository businessRetailRepository,
            ModelMapper modelMapper,
            EntityCheckerUtils entityCheckerUtils) {

        this.customerRepository = customerRepository;
        this.businessRetailRepository = businessRetailRepository;
        this.modelMapper = modelMapper;
        this.entityCheckerUtils = entityCheckerUtils;
    }

    @Override
    public void registerBusiness(String customerId, BusinessRetailDto businessRetailDto) {

        Customer customer = entityCheckerUtils.checkIfCustomerExists(customerId);

        if (customer.getBusiness() == null) {

            BusinessRetail businessRetail = modelMapper.map(businessRetailDto, BusinessRetail.class);
            customer.setBusiness(businessRetail);

            customerRepository.save(customer);
        }
        else {
            throw new ResourceAlreadyExistsException("You have already created a business with name: "
                    + businessRetailDto.getBusinessName());
        }

    }
}
