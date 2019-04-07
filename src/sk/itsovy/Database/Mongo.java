package sk.itsovy.Database;

import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import sk.itsovy.Items.Drink.DraftInterface;
import sk.itsovy.Items.Food.Fruit;
import sk.itsovy.Items.Item;
import sk.itsovy.Items.Pce;
import sk.itsovy.projectKaufland.Bill;
import sk.itsovy.projectKaufland.Globals;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

import static sk.itsovy.projectKaufland.Globals.*;

public class Mongo {

    private static Mongo mongodb = new Mongo();

    private Mongo()
    {
    }

    public static Mongo getMongoInstance()
    {
        return mongodb;
    }

    public MongoDatabase mongoConnection()
    {
        MongoDatabase database = null;
        try
        {
            com.mongodb.MongoClient client = new com.mongodb.MongoClient(new MongoClientURI(hostM));
            MongoCredential credential = MongoCredential.createCredential(userNameM, dbnameM, passwordM.toCharArray());

            database = client.getDatabase(dbnameM);

            return database;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return database;
    }

    public MongoCollection <org.bson.Document> getBillCollection(){

        MongoDatabase database = mongoConnection();
        try
        {
            MongoCollection<org.bson.Document> billCollection = database.getCollection("bill");
            return billCollection;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public MongoCollection <org.bson.Document> getItemCollection(){

        MongoDatabase database = mongoConnection();
        try
        {
            MongoCollection<org.bson.Document> billCollection = database.getCollection("item");
            return billCollection;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public void insertBill(Bill bill){
        MongoCollection<org.bson.Document> billCollection = getBillCollection();
        MongoCollection<org.bson.Document> itemCollection = getItemCollection();

        Date date = new java.sql.Date(bill.getDate().getTime());
        Date time = new Time(bill.getDate().getTime());

        Document billDoc;
        Document itemDoc;

        billDoc = new Document("Date", date).append("Time", time).append("TotalPrice", bill.getFinalPrice());
        billCollection.insertOne(billDoc);

        for (Item item : bill.getList())
        {
            itemDoc = new Document("name",item.getName()).append("price",item.getPrice());

            if (item instanceof DraftInterface)
            {
                itemDoc.append("count",((DraftInterface) item).getVolume()).append("unit","l");
            }
            else if(item instanceof Fruit)
            {
                itemDoc.append("count",((Fruit) item).getWeight()).append("unit","kg");
            }
            else if(item instanceof Pce)
            {
                itemDoc.append("count",((Pce) item).getAmount()).append("unit","pcs");
            }
            itemCollection.insertOne(itemDoc);
        }
    }
}
