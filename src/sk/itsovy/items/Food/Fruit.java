package sk.itsovy.Items.Food;

public class Fruit extends Food {
    private double weight;

    public Fruit(String name, double price, int callories, double weight) {
        super(name, price, callories);
        this.weight=weight;
    }

    public Fruit(String name, double price, double weight) {
        this(name,price,-1,weight);
    }

    @Override
    public double getTotalPrice() {
        return weight*getPrice();
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
