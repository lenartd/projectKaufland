package sk.itsovy.projectKaufland;

import sk.itsovy.Exception.BillException;
import sk.itsovy.Items.Drink.Bottle;
import sk.itsovy.Items.Drink.Draft;
import sk.itsovy.Items.Food.Fruit;
import sk.itsovy.Items.Food.Pastry;
import sk.itsovy.Items.Goods;
import sk.itsovy.Items.Item;

public class Application {

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

        bill.addItem(new Bottle("Coca Cola", 0.7, 1));
        bill.addItem(new Bottle("Fanta", 0.5, 2));

        bill.printItems();

        System.out.println("Total price: "+bill.getFinalPrice());

        try
        {
            System.out.println("Price in USD: " + bill.getTotalPriceUSD());

            XML totalBill = new XML();
            totalBill.createXML(bill);
            bill.end();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Something went wrong");
        }

    }
}
