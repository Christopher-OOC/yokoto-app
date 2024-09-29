package com.example.demo.model.generictype;

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
