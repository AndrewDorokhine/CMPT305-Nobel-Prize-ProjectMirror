package API.prize;

import API.prize.PrizeLaureate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A category of Nobel Prize (physics, chemistry, medicine, peace, literature
 * or economics).
 * 
 * @author Nemi
 */
public class Category {
    private Map<String, List<PrizeLaureate>> data;
    /**
     * Constructor.
     */
    public Category() {
        data = new HashMap();
    }
    /**
     * Deep copy constructor.
     * @param o 
     */
    public Category(Category o) {
        data = (HashMap) o.getData();
    }
    /**
     * Gets a copy of the data map.
     * @return Map
     */
    public Map getData() {
        Map<String, List<PrizeLaureate>> mapCopy = new HashMap();
        for(String key : data.keySet()) {
            List<PrizeLaureate> listCopy = new ArrayList();
            for (PrizeLaureate p : data.get(key)) {
                listCopy.add(new PrizeLaureate(p));
            }
            mapCopy.put(key, listCopy);
        }
        return mapCopy;
    }
    /**
     * Adds a new year of prizes to the data.
     * @param year
     * @param laureates 
     */
    public void add(String year, List laureates) {
        data.put(year, (ArrayList) laureates);
    }
    /**
     * For getting a string representation of the data.
     * @return String
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (String key : data.keySet()) {
            builder.append("\t");
            builder.append(key);
            builder.append("\n");
            for (PrizeLaureate p : data.get(key)) {
                builder.append(p.toString());
            }
        }
        return builder.toString();
    }
}
