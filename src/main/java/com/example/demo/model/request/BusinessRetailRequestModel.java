package com.example.demo.model.request;

import com.example.demo.model.entity.BusinessType;
import com.example.demo.model.entity.Location;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
public class BusinessRetailRequestModel implements Serializable {

    @JsonProperty("business_name")
    private String businessName;

    @JsonProperty("business_description")
    private String businessDescription;

    private BusinessType businessType;

    private LocationRequestModel location;

    @JsonFormat(pattern = "yyyy-MM-dd")
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

    @Override
    public String toString() {
        return "BusinessRetailRequestModel{" +
                "businessName='" + businessName + '\'' +
                ", businessDescription='" + businessDescription + '\'' +
                ", businessType=" + businessType +
                ", location=" + location +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
