package com.example.demo.model.dto;

import com.example.demo.model.entity.BusinessType;
import com.example.demo.model.entity.Customer;
import com.example.demo.model.entity.Location;
import com.example.demo.model.entity.MediaPost;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
public class BusinessRetailDto {

    private Long id;

    private String businessId;

    private String businessName;

    private String businessLogo;

    private String businessDescription;

    private BusinessType businessType;

    private Location location;

    private Customer customer;

    private Date dateCreated;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusinessRetailDto that = (BusinessRetailDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
