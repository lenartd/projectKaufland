package sk.itsovy.projectKaufland;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Internet {
    public void sendGet()
    {
        try
        {
            URL urlForGetRequest = new URL("https://api.exchangeratesapi.io/latest?symbols=USD");
            String readLine = null;
            HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
            conection.setRequestMethod("GET");
            int responseCode = conection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK)
            {

                BufferedReader in;
                in = new BufferedReader(new InputStreamReader(conection.getInputStream()));

                StringBuffer response = new StringBuffer();

                while ((readLine = in .readLine()) != null)
                {
                    response.append(readLine);

                }
                in .close();
                System.out.println("JSON String Result " + response.toString());;

                //Gson gson = new Gson();
                //myJson json  =gson.fromJson(response.toString(), Internet);

            }
            else
            {

                System.out.println("GET NOT WORKED");

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }



    }
}
class myJson{}
