package nobelprize;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Driver for the NobelPrize program.
 * 
 * @author Nemi R, Andrew D, Jad A, Seth T, Sitharthan E
 */
public class NobelPrizeDriver {
    
    /**
     * Constructor.
     */
    public NobelPrizeDriver () {}
    
    /**
     * A test that displays the first 5 entries from both the prize and country
     * data.
     * @throws IOException 
     */
    public static void runTest() throws IOException {
        StringBuilder builder = new StringBuilder();
        builder.append(".....GETTING PRIZE DATA.....\n\n");
        builder.append(prizeTest());
        builder.append(".....GETTING COUNTRY DATA.....\n\n");
        builder.append(countryTest());
        
        System.out.println(builder.toString());     
    }
       
    /**
     * Gets prize information (JSON) form the Nobel Prize API and uses GSON
     * to parse into the PrizeData object.
     * @return
     * @throws IOException 
     */
    public static String prizeTest() throws IOException {
        // Get prize JSON from the API
        String url = "http://api.nobelprize.org/v1/prize.json?";
        String json = getJson(url);
        // Parse with GSON
        Gson gson = new Gson();
        PrizeData result = gson.fromJson(json, PrizeData.class);
        
        return result.getHead();
    }
    
    /**
     * Gets country information (JSON) from the Nobel Prize API and uses GSON
     * to parse into the CountryData object.
     * @throws IOException 
     */
    public static String countryTest() throws IOException {
        // Get country JSON from the API
        String url = "http://api.nobelprize.org/v1/country.json";
        String json = getJson(url);
        // Parse with GSON
        Gson gson = new Gson();
        CountryData result = gson.fromJson(json, CountryData.class);
        
        return result.getHead();
    } 
    
    /**
     * Gets JSON string from a URL string that is passed in.
     * @param u the URL as a string
     * @return JSON string
     * @throws IOException 
     */
    public static String getJson (String u) throws IOException {
        URL url            = new URL(u);
        BufferedReader br  = new BufferedReader
                                (new InputStreamReader(url.openStream()));
        StringBuilder json = new StringBuilder();
        String line        = "";
        // Read each line from the buffer and append to the JSON string.
        while ((line = br.readLine()) != null) {
            json.append(line);
        }
        br.close();
        return json.toString();
    }
}
