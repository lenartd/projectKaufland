package sk.itsovy.Database;

import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.w3c.dom.Document;
import sk.itsovy.Items.Item;
import sk.itsovy.projectKaufland.Bill;

import java.sql.Time;
import java.util.Date;

import static sk.itsovy.projectKaufland.Globals.*;

public class Mongo {

    private static Mongo mongodb = new Mongo();
}
