package com.example.demo.repository;

import com.example.demo.model.entity.BusinessRetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRetailRepository extends JpaRepository<BusinessRetail, Long> {

    BusinessRetail findByBusinessId(String businessId);

}
