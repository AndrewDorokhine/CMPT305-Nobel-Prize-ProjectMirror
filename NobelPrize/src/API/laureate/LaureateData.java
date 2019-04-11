package API.laureate;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Database for the information retrieved from the "laureate" section of the
 * Nobel Prize API at "http://api.nobelprize.org/v1/laureate.json?". When this 
 * object is created it consults the API and parses the result using the 
 * LaureateResult object.
 * 
 * @author Nemi R, Andrew D, Seth T, Sitharthan E
 */
public class LaureateData {
    /**
     * Class attribute variables
     */
    private final HashMap<String, Laureate> data;
    private final HashMap<String, Laureate> IDMap;
    private final HashMap<String, Integer>  countriesInUse;
    private final HashMap<String, String>   laureateInfo;
    /**
     * Class constructor. Consults the Nobel Prize API and parses the JSON result.
     */
    public LaureateData() {
        data           = new HashMap();
        IDMap          = new HashMap();
        laureateInfo   = new HashMap();
        countriesInUse = new HashMap();
        System.out.println(">>> Parsing laureate Data...");
        parseData();
    }
    /**
     * Getter for the data Map.
     * @return a deep copy map of the data variable
     */
    public Map<String, Laureate> getData() {
        HashMap<String, Laureate> copy = new HashMap();
        for (String laureateName : data.keySet()) {
            copy.put(laureateName, new Laureate(data.get(laureateName)));
        }
        return copy;
    }
    /**
     * Getter for the countries in use Map.
     * @return a shallow copy of the countries in use
     */
    public HashMap<String, Integer> getCountriesInUse() {
        return countriesInUse;
    }
    /**
     * A getter for the Map containing all information about the laureate in
     * one string for searching.
     * @return a deep copy map of the laureateInfo variable
     */
    public Map<String, String> getLaureateInfo() {
        return laureateInfo;
    }
    /**
     * Gets laureate information from the Nobel Prize API and uses GSON to parse
     * the JSON into the LaureateData object.
     */
    private void parseData() {
        // Get laureate JSON form the API
        String url = "	http://api.nobelprize.org/v1/laureate.json?";
        String json = getJson(url);
        // Parse with GSON
        Gson gson = new Gson();
        LaureateResult result = gson.fromJson(json, LaureateResult.class);
        // Put the list into the data hashmap
        for (Laureate l : result.laureates) {
            String name = l.getFirstname() + " " + l.getSurname();
            data.put(name, l);
            IDMap.put(l.getID(), l);
            // Add data to the info HashMap for the search bar to search
            laureateInfo.put(name, getLaureateInfo(l));
            /**
             * Get all countries that are actually in use. Check if the 
             * countriesInUse Map already contains the country, if so then add 
             * 1 to the value in the Map. If the country does not exist in the
             * Map, add it with a value of 1.
             */ 
            int current = 0;
            if (countriesInUse.containsKey(l.getBornCountry())) {
                countriesInUse.put(l.getBornCountry(), (countriesInUse.get(l.getBornCountry()) + 1));
            } else countriesInUse.put(l.getBornCountry(), 1);
            
            if (countriesInUse.containsKey(l.getDiedCountry())) {
                countriesInUse.put(l.getDiedCountry(), (countriesInUse.get(l.getDiedCountry()) + 1));
            } else countriesInUse.put(l.getDiedCountry(), 1);
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
        } catch (MalformedURLException ex) {
            Logger.getLogger(LaureateData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LaureateData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    /**
     * For getting a Laureate from the data map.
     * @param name Name to be searched
     * @return Laureate object result
     */
    public String getLaureate(String name) {
        Laureate result = data.get(name);
        if (result == null) {
            return "";
        }
        StringBuilder b = new StringBuilder();
        b.append("Name: ");
        b.append(result.getFirstname());
        b.append(" ");
        b.append(result.getSurname());
        b.append("\n");
        b.append("Prize: ");
        b.append(result.getPrizes().get(0).getCategory());
        b.append("\n");
        b.append("Year: ");
        b.append(result.getPrizes().get(0).getYear());
        
        return b.toString();
    }
    /**
     * Gets a laureate by ID by using the IDMap
     * @param id the laureate id to be searched for
     * @return the laureate object result of the search
     */
    public Laureate getLaureatebyID(String id) {
        return IDMap.get(id);
    }
    /**
     * Gets information as a string about a laureate
     * @param l the laureate to create a string for
     * @return string with the fields of the laureate
     */
    private String getLaureateInfo(Laureate l) {
        StringBuilder builder = new StringBuilder();
        builder.append(l.getID().toLowerCase());
        builder.append(" ");
        builder.append(l.getFirstname().toLowerCase());
        builder.append(" ");
        builder.append(l.getSurname().toLowerCase());
        builder.append(" ");
        builder.append(l.getBorn().toLowerCase());
        builder.append(" ");
        builder.append(l.getDied().toLowerCase());
        builder.append(" ");
        builder.append(l.getBornCountry().toLowerCase());
        builder.append(" ");
        builder.append(l.getBornCountryCode().toLowerCase());
        builder.append(" ");
        builder.append(l.getBornCity().toLowerCase());
        builder.append(" ");
        builder.append(l.getDiedCountry().toLowerCase());
        builder.append(" ");
        builder.append(l.getDiedCountryCode().toLowerCase());
        builder.append(" ");
        builder.append(l.getDiedCity().toLowerCase());
        builder.append(" ");
        builder.append(l.getGender().toLowerCase());
        builder.append(" ");
        for (PrizePlus p : l.getPrizes()) {
            builder.append(p.getYear().toLowerCase());
            builder.append(" ");
            builder.append(p.getCategory().toLowerCase());
            builder.append(" ");
            builder.append(p.getMotivation().toLowerCase());
            builder.append(" ");
            // need to add the affiliations here
        }
        builder.append(" ");
        return builder.toString();
    }
}
