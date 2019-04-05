package API.prize;

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
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Data structure for the information describing prizes (physics, chemistry,
 * medicine, peace, literature or economics), and the years the laureates 
 * received them. Some basic information about the laureates is also stored such
 * as name and id.
 * 
 * @author Nemi R, Andrew D, Jad A, Seth T, Sitharthan E
 */
public class PrizeData {
    private final HashMap<String, Category> data;
    /**
     * Constructor. Gets information from the Nobel Prize API and parses it.
     */
    public PrizeData() {
        data = new HashMap();
        System.out.println(">>> Parsing prize Data...");
        parseData();
    }
    /**
     * Gets a copy of the data hashmap.
     * @return HashMap
     */
    public HashMap<String, Category> getData() {
        HashMap<String, Category> copy = new HashMap();
        for (String key : data.keySet()) {
            copy.put(key, new Category(data.get(key)));
        }
        return copy;
    }
    /**
     * Gets the keys in order as a list
     * @return List<String>
     */
    public List<String> getKeysInOrder() {
        HashMap<String, Category> copy = getData();
        List<String> keys = new ArrayList<String>(copy.size());
        keys.addAll(copy.keySet());
        Collections.sort(keys);
        return keys;
    }
    /**
     * Gets prize information from the Nobel Prize API and uses GSON
     * to parse the JSON into the PrizeData object.
     * @return
     */
    private void parseData() {
        // Get prize JSON from the API
        String url = "http://api.nobelprize.org/v1/prize.json?";
        String json = getJson(url);
        // Parse with GSON
        Gson gson = new Gson();
        PrizeResult result = gson.fromJson(json, PrizeResult.class);
        // Put the list into the data hashmap
        for (Object prize : result.getPrizes()) {
            addPrize((Prize)prize);
        }
    }
    /**
     * Adds a new prize category to the data hashmap, or adds to the list 
     * contained within the key if it already exists.
     * @param p Prize to add
     */
    private void addPrize(Prize p) {
        if (!data.containsKey(p.getCategory())) {
            data.put(p.getCategory(), new Category());
        }
        Category current = data.get(p.getCategory());
        current.add(p.getYear(), p.getLaureates()); 
    }
    /**
     * Gets JSON string from a URL string that is passed in.
     * @param u the URL as a string
     * @return JSON string 
     */
    private static String getJson (String u) {
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
            Logger.getLogger(PrizeData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PrizeData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    /**
     * For getting a string representation of the data.
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Object c : getData().keySet()) {
            String category = (String) c;
            builder.append(category);
            builder.append("\n");
            builder.append(data.get(category).toString());
        }
        return builder.toString();
    }
}
