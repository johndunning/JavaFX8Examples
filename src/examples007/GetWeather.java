/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples007;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 *
 * @author johndunning
 */
public class GetWeather
{
    // rest of the class code omitted

    public static void main(String[] args)
    {
        System.out.println(
                jsonGetRequest("http://myserver.org/current-weather?q=Pasadena,MD&format=json"));
    }

    /**
     * Quick one liner that delimits on the end of file character and returning
     * the whole input stream as a String.
     *
     * @param inputStream byte input stream.
     * @return String contents of the text file
     */
    private static String streamToString(InputStream inputStream)
    {
        String text = new Scanner(inputStream, "UTF-8")
                .useDelimiter("\\Z")
                .next();
        return text;
    }

    public static String jsonGetRequest(String urlQueryString)
    {
        String json = null;
        try
        {
            URL url = new URL(urlQueryString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("charset", "utf-8");
            connection.connect();
            InputStream inStream = connection.getInputStream();
            json = streamToString(inStream); // input stream to string
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return json;
    }
}
