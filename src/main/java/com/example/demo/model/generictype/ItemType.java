package com.example.demo.model.generictype;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemType<I> {

    I item;

    public ItemType(I item) {
        this.item = item;
    }

    public I getItem() {
        return item;
    }

    public void setItem(I item) {
        this.item = item;
    }
}
