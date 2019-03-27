package sk.itsovy.projectKaufland;

import sk.itsovy.Exception.BillException;
import sk.itsovy.items.Drink.Bottle;
import sk.itsovy.items.Drink.Draft;
import sk.itsovy.items.Drink.Drink;
import sk.itsovy.items.Food.Fruit;
import sk.itsovy.items.Food.Pastry;
import sk.itsovy.items.Goods;
import sk.itsovy.items.Item;

public class Application {
    /*
    private static Application app = new Application();

    private Application()
    {

    }

    public static Application getInstance()
    {
        return app;
    }
     */
    public void example() throws BillException
    {

        Bill bill = new Bill();
        Bottle milk = new Bottle("Milk 1.5%", 0.59, 2);
        bill.addItem(milk);
        Item pizza = new Pastry("Gazdovska", 1.10, 280, 2);
        bill.addItem(pizza);
        Fruit apple = new Fruit("Red apple", 59, 0.370);
        bill.addItem(apple);
        Goods pencil = new Goods("Pencil", 0.50, 1, Category.school);
        bill.addItem(pencil);
        Draft vinea = new Draft("Vinea", 1.50, true, 0.3);
        bill.addItem(vinea);
        Bottle kozel = new Bottle("Kozel 10", 0.80, false, 1);
        bill.addItem(kozel);
        bill.removeItem(kozel);
        bill.printItems();
        bill.end();

        Bill bill2 = new Bill();
        bill2.addItem(new Bottle("Coca Cola", 0.7, 1));
        bill2.end();
        bill2.addItem(new Bottle("Fanta", 0.5, 2));
        bill2.printItems();
    }
}
