package com.example.demo.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="packaging_items")
public class PackagingItem extends Item {

    {
        category = Category.PACKAGING_ITEM;
    }
}