package sk.itsovy.Items.Food;

import sk.itsovy.Items.Pce;

public class Sweets extends Food implements Pce {
    private int amount;

    public Sweets(String name, double price, int callories, int amount) {
        super(name, price, callories);
        this.amount = amount;
    }

    public Sweets(String name, double price, int amount) {
        this(name, price, -1, amount);
    }

    @Override
    public double getTotalPrice() {
        return amount*getPrice();
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}