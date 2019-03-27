package sk.itsovy.projectKaufland;

import sk.itsovy.Exception.BillException;
import sk.itsovy.items.Food.Food;
import sk.itsovy.items.Food.Pastry;

public class Main {
    public static void main(String[] args) throws BillException
    {
        //Pastry bread = new Pastry("bread", 1.50, 900, 1);
        ///Application app = new Application();
        //app.example();
        Internet getreq = new Internet();
        getreq.sendGet();
    }
}
