package com.example.demo.service.impl;

import com.example.demo.exception.ResourceAlreadyExistsException;
import com.example.demo.model.dto.BusinessRetailDto;
import com.example.demo.model.entity.BusinessRetail;
import com.example.demo.model.entity.Customer;
import com.example.demo.model.entity.MediaPost;
import com.example.demo.repository.BusinessRetailRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.BusinessRetailService;
import com.example.demo.service.FileService;
import com.example.demo.utils.EntityCheckerUtils;
import com.example.demo.utils.PublicIdGeneratorUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class BusinessRetailServiceImpl implements BusinessRetailService {

    private CustomerRepository customerRepository;
    private BusinessRetailRepository businessRetailRepository;
    private ModelMapper modelMapper;
    private EntityCheckerUtils entityCheckerUtils;
    private FileService fileService;

    public BusinessRetailServiceImpl(
            CustomerRepository customerRepository,
            BusinessRetailRepository businessRetailRepository,
            ModelMapper modelMapper,
            EntityCheckerUtils entityCheckerUtils,
            FileService fileService) {

        this.customerRepository = customerRepository;
        this.businessRetailRepository = businessRetailRepository;
        this.modelMapper = modelMapper;
        this.entityCheckerUtils = entityCheckerUtils;
        this.fileService = fileService;
    }

    @Override
    public void registerBusiness(String customerId,
                                 BusinessRetailDto businessRetailDto,
                                 MultipartFile multipartFile) throws ResourceAlreadyExistsException {

        Customer customer = entityCheckerUtils.checkIfCustomerExists(customerId);

        if (customer.getBusiness() == null) {

            String businessId = PublicIdGeneratorUtils.generatePublicId(30);

            BusinessRetail businessRetail = modelMapper.map(businessRetailDto, BusinessRetail.class);
            businessRetail.setBusinessId(businessId);
            customer.setBusiness(businessRetail);

            MediaPost mediaPost = null;

            try {

                mediaPost = fileService.uploadBusinessLogo(businessId, multipartFile);

                businessRetail.setBusinessLogo(mediaPost.getMediaUrl());

                customerRepository.save(customer);
            }
            catch (Exception ex) {
                customerRepository.save(customer);
            }
        }
        else {
            throw new ResourceAlreadyExistsException("You have already created a business with name: "
                    + businessRetailDto.getBusinessName());
        }

    }
}
