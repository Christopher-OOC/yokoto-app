package com.example.demo.model.request;

import com.example.demo.model.entity.BusinessType;
import com.example.demo.model.entity.Location;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
public class BusinessRetailRequestModel {

    private String businessName;

    private String businessDescription;

    private BusinessType businessType;

    private Location location;

    private Date dateCreated;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusinessRetailRequestModel that = (BusinessRetailRequestModel) o;
        return Objects.equals(businessName, that.businessName) && Objects.equals(businessDescription, that.businessDescription) && businessType == that.businessType && Objects.equals(location, that.location) && Objects.equals(dateCreated, that.dateCreated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(businessName, businessDescription, businessType, location, dateCreated);
    }
}
