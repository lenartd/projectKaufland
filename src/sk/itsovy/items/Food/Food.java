package sk.itsovy.Items.Food;

import sk.itsovy.Items.Item;

public abstract class Food extends Item {
    private  int callories;

    public Food(String name, double price, int callories) {
        super(name, price);
        this.callories= callories;
    }
}