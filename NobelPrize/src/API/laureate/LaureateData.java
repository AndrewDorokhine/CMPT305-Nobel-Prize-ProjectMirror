package API.laureate;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;

/**
 * Database for the information retrieved from the "laureate" section of the
 * Nobel Prize API at "http://api.nobelprize.org/v1/laureate.json?". When this 
 * object is created it consults the API and parses the result using the 
 * LaureateResult object.
 * @author Nemi R, Andrew D, Jad A, Seth T, Sitharthan E
 */
public class LaureateData {
    private final HashMap<String, Laureate> data;
    private final String name;
    private final String info;
    /**
     * Constructor. Consults the Nobel Prize API and parses the JSON result.
     * @throws IOException 
     */
    public LaureateData() throws IOException {
        name = "Laureate data.\n";
        info = "HashMap of laureates, and their information.\n";
        data = new HashMap();
        parseData();
    }
    
    /***************************************************************************
     * GETTERS 
     **************************************************************************/
    
    // JAD ADD CODE HERE FOR THE GETTERS. DO THIS LAST.
    
    /***************************************************************************
     * PARSING FUNCTIONS
     **************************************************************************/
    /**
     * Gets laureate information from the Nobel Prize API and uses GSON to parse
     * the JSON into the LaureateData object.
     * @throws IOException 
     */
    private void parseData() throws IOException {
        // Get laureate JSON form the API
        String url = "	http://api.nobelprize.org/v1/laureate.json?";
        String json = getJson(url);
        // Parse with GSON
        Gson gson = new Gson();
        LaureateResult result = gson.fromJson(json, LaureateResult.class);
        // Put the list into the data hashmap
        for (Laureate l : result.laureates) {
            String key = l.firstname + " " + l.surname;
            data.put(key, l);
        }
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
    
    // JAD CREATE A TOSTRING METHOD HERE
}
