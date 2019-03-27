package sk.itsovy.items.Drink;

import sk.itsovy.items.Item;

public abstract class Drink extends Item {
    private boolean sweet;

    public Drink(String name, double price, boolean sweet) {
        super(name, price);
        this.sweet=sweet;
    }

    public boolean isSweet() {
        return sweet;
    }


}
