package API.laureate;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Database for the information retrieved from the "laureate" section of the
 * Nobel Prize API at "http://api.nobelprize.org/v1/laureate.json?". When this 
 * object is created it consults the API and parses the result using the 
 * LaureateResult object.
 * @author Nemi R, Andrew D, Jad A, Seth T, Sitharthan E
 */
public class LaureateData {
    private final HashMap<String, Laureate> data;
    public final HashMap<String, Laureate> IDMap;
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
        IDMap = new HashMap();
        parseData();
    }
    
    /***************************************************************************
     * GETTERS 
     **************************************************************************/
    
    public Map getData() {
        HashMap<String, Laureate> copy = new HashMap();
        for (String laureateName : data.keySet()) {
            copy.put(laureateName, new Laureate(data.get(laureateName)));
        }
        
        return copy;
    }
    
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
            String name = l.firstname + " " + l.surname;
            data.put(name, l);
            IDMap.put(l.getID(), l);
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
    
    /**
     * Test for getting a Laureate from the data map.
     * @param name Name to be searched
     * @return Laureate object
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
    
    public Laureate getLaureatebyID(String id) {
        return IDMap.get(id);
    }
}
