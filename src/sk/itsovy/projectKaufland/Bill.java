package sk.itsovy.projectKaufland;

import sk.itsovy.Database.Database;
import sk.itsovy.Exception.BillException;
import sk.itsovy.Items.Drink.DraftInterface;
import sk.itsovy.Items.Food.Fruit;
import sk.itsovy.Items.Item;
import sk.itsovy.Items.Pce;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Bill {
    private List<Item> list;
    boolean open;
    Date date;
    private double sum;

    public Bill() {
        this.list = new ArrayList<>();
        open = true;
    }

    public void addItem(Item item) throws BillException{

        if(item != null)
        {
            if(getItemCount() == Globals.MAXITEMS)
            {
                throw new BillException("Bill is full, max "+Globals.MAXITEMS+" Items");
            }
            else if(open == false)
            {
                System.out.println("bill is closed");
            }
            else
                {
                    list.add(item);
                }
        }
    }

    public List<Item> getList() {
        return list;
    }

    public Date getDate() {
        return date;
    }

    public void end()
    {
        if(open == true)
        {
            try
            {
                Database db = Database.getInstanceDB();
                db.insertNewBill(this);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            open = false;
        }
        else
            {
                System.out.println("Bill is already closed");
            }
    }

    public void removeItem(Item item){
        if (list.contains(item))
        {
            list.remove(item);
        }
    }

    public double getFinalPrice(){
        sum=0;
        for(Item item: list)
        {
            sum+=item.getTotalPrice();
        }
        return sum;
    }

    public int getItemCount()
    {
        return list.size();
    }

    public double getTotalPriceUSD() throws IOException
    {
        double totalPrice = getFinalPrice();
        double sum = totalPrice * Internet.getUSDrate();
        return sum;
    }

    public void printItems()
    {
        if(getItemCount() == 0)
        {
            System.out.println("Bill is empty");
        }
        else
            {
                for(Item item : list)
                {
                    if(item instanceof DraftInterface)
                    {
                        System.out.print(item.getName() + " " + ((DraftInterface) item).getVolume() + " ");
                        System.out.println(item.getPrice() + " " + item.getTotalPrice());
                    }
                    else if(item instanceof Fruit)
                    {
                        System.out.print(item.getName() + " " + ((Fruit) item).getWeight() + " ");
                        System.out.println(item.getPrice() + " " + item.getTotalPrice());
                    }
                    else
                        {
                            System.out.print(item.getName() + " " + ((Pce)item).getAmount() + " ");
                            System.out.println(item.getPrice() + " " + item.getTotalPrice());
                        }
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Date date1 = Calendar.getInstance().getTime();
                    String currdate = dateFormat.format(date1);
                    date = date1;
                    System.out.println(currdate);
                }
            }
    }
}