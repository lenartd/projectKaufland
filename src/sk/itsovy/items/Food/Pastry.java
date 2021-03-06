package sk.itsovy.Items.Food;

import sk.itsovy.Items.Pce;

public class Pastry extends Food implements Pce {
    private int amount;

    public Pastry(String name, double price, int callories, int amount) {
        super(name, price, callories);
        this.amount=amount;
    }

    public Pastry(String name, double price, int amount) {
        this(name, price,-1,amount);
    }

    @Override
    public double getTotalPrice() {
        return amount*getPrice();
    }

    @Override
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
