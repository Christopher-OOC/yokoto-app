package com.example.demo.repository;

import com.example.demo.model.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void testInsertItem() {

        Meat meat = new Meat();
        meat.setName("Fresh Beef");
        meat.setMeatType(MeatType.BEEF);
        meat.setCategory(Category.MEAT);
        meat.setPrice(10000.00);

        ItemWeight itemWeight = new ItemWeight();
        itemWeight.setWeightUnit(Unit.KG);
        itemWeight.setWeightValue(5);

        meat.setItemWeight(itemWeight);

        BusinessRetail businessRetail = new BusinessRetail();
        businessRetail.setId(1L);

        meat.setBusinessRetail(businessRetail);

        itemRepository.save(meat);
    }
}
