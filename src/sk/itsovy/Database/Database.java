package sk.itsovy.Database;

import sk.itsovy.Items.Drink.DraftInterface;
import sk.itsovy.Items.Food.Fruit;
import sk.itsovy.Items.Item;
import sk.itsovy.Items.Pce;
import sk.itsovy.projectKaufland.Bill;
import sk.itsovy.projectKaufland.Globals;

import java.sql.*;

public class Database {

    private static Database db = new Database();

    private Database() {
    }

    public static Database getInstanceDB(){
        return db;
    }

    private Connection getConnection(){
        Connection connection;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(Globals.host, Globals.userName,Globals.password);
            System.out.println("Connecting");
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;
    }

    public void insertNewBill(Bill bill) throws SQLException {
        Connection con = getConnection();
        String query = "insert into Bill(TotalPrice, Date, Time) values(?,?,?)";
        String query2 = "insert into Item(OrderID, Name, Price, Count, Unit) values(?,?,?,?,?)";
        try
        {
            con.setAutoCommit(false);
            PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setDouble(1, bill.getFinalPrice());
            statement.setDate(2, new java.sql.Date(bill.getDate().getTime()));
            statement.setTime(3, new java.sql.Time(bill.getDate().getTime()));

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0)
            {
                throw new SQLException("Failed, no rows affected");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys())
            {
                if (generatedKeys.next())
                {
                    for (Item item : bill.getList())
                    {
                        statement = con.prepareStatement(query2, Statement.RETURN_GENERATED_KEYS);
                        statement.setDouble(1, generatedKeys.getLong(1));
                        statement.setString(2, item.getName());
                        statement.setDouble(3, item.getPrice());

                        if (item instanceof DraftInterface)
                        {
                            statement.setDouble(4, ((DraftInterface) item).getVolume());
                            statement.setString(5, "l");
                        } else if (item instanceof Fruit)
                        {
                            statement.setDouble(4, ((Fruit) item).getWeight());
                            statement.setString(5, "kg");
                        } else if (item instanceof Pce)
                        {
                            statement.setDouble(4, ((Pce) item).getAmount());
                            statement.setString(5, "pcs");
                        }
                        statement.executeUpdate();
                    }
                }
                else
                    {
                    throw new SQLException("Creating bill failed");
                    }

            }
            con.commit();
            con.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            con.rollback();
        }

    }

}