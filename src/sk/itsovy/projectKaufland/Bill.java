package sk.itsovy.projectKaufland;

import sk.itsovy.Exception.BillException;
import sk.itsovy.items.Drink.DraftInterface;
import sk.itsovy.items.Food.Fruit;
import sk.itsovy.items.Item;
import sk.itsovy.items.Pce;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Bill {
    private List<Item> list;
    boolean open;

    public Bill() {
        this.list = new ArrayList<>();
        open = true;
    }

    public void addItem(Item item) throws BillException{

        if(item != null)
        {
            if(getItemCount() == Globals.MAXITEMS)
            {
                throw new BillException("Bill is full, max "+Globals.MAXITEMS+" items");
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

    public void end()
    {
        if(open == true)
        {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = Calendar.getInstance().getTime();
            String currdate = dateFormat.format(date);

            System.out.println(currdate);
            //addItem(currdate);
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
        throw new UnsupportedOperationException("Method does not exists yet");
    }

    public int getItemCount()
    {
        return list.size();
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
                }
            }
    }
}