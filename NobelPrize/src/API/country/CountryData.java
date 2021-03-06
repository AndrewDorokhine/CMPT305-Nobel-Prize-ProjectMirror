 package API.country;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contains the data from the Country section of the Nobel Prize API.
 * 
 * @author Nemi R, Andrew D, Seth T, Sitharthan E
 */
public class CountryData {
    /**
     * Class attribute variables.
     */
    private final Map<String, String> data;
    private final List<String>        countriesInOrder;
    /**
     * Constructor, fills the data hashmap with information retrieved from the 
     * Nobel Prize API.
     */
    public CountryData() {
        data = new HashMap<>();
        System.out.println(">>> Parsing country Data...");
        parseData();
        countriesInOrder = new ArrayList((data.keySet()));
        Collections.sort(countriesInOrder);
    }
    /**
     * Gets a deep copy of the data.
     * @return a map deep copy of the data
     */
    public Map<String, String> getData() {
        HashMap<String, String> deepCopy = new HashMap();
        for (String key : data.keySet()) {
            deepCopy.put(key, data.get(key));
        }
        return deepCopy;
    }
    /**
     * Deep copy for the list of countries.
     * @return a list deep copy of the countries in order
     */
    private List<String> getCountriesInOrder() {
        List<String> deepCopy = new ArrayList();
        for (String country : countriesInOrder) {
            deepCopy.add(country);
        }
        return deepCopy;
    }
    /**
     * Gets JSON data from the Nobel Prize API, uses GSON to parse and puts 
     * the information into a hashmap for faster searching.
     */
    private void parseData() {
        // Get country JSON from the API
        String url = "http://api.nobelprize.org/v1/country.json";
        String json = getJson(url);
        // Parse with GSON
        Gson gson = new Gson();
        CountryResult result = gson.fromJson(json, CountryResult.class);
        // Put the list into the data hashmap
        for (Country c : result.getCountries()) {
            data.put(c.getName(), c.getCode());
        }
    }
    
    /**
     * Gets JSON string from a URL string that is passed in.
     * @param u the URL as a string
     * @return JSON string
     */
    public static String getJson (String u) {
        try {
            URL url            = new URL(u);
            BufferedReader br  = new BufferedReader (new InputStreamReader(url.openStream()));
            StringBuilder json = new StringBuilder();
            String line        = "";
            // Read each line from the buffer and append to the JSON string.
            while ((line = br.readLine()) != null) {
                json.append(line);
            }
            br.close();
            return json.toString();
        } catch (MalformedURLException ex) {
            Logger.getLogger(CountryData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CountryData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    /**
     * For getting a string representation of the object.
     * @return String with info about the object
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (String c :data.keySet()) {
            builder.append("\tCountry: ");
            builder.append(c);
            builder.append("\n");
            builder.append("\tCode: ");
            builder.append(data.get(c));
            builder.append("\n");
        }
        return builder.toString();
    }
}
