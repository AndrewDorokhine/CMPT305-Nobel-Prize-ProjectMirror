package API.laureate;

import com.google.gson.internal.LinkedTreeMap;
import java.util.ArrayList;
import java.util.List;

/**
 * Prize to be used with the laureate class, has more information than the Prize
 * object in API.prize
 * 
 * @author Nemi R, Andrew D, Seth T, Sitharthan E
 */
public class PrizePlus {
    /**
     * Class attribute variables
     */
    private final String year;
    private final String category;
    private final String share;
    private final String motivation;
    private final List<LinkedTreeMap<String, String>> affiliations;
    /**
     * Deep copy constructor.
     * @param o object to be copied
     */
    public PrizePlus(PrizePlus o) {
        year         = o.getYear();
        category     = o.getCategory();
        share        = o.getShare();
        motivation   = o.getMotivation();
        affiliations = o.getAffiliations();
    }
    /**
     * Getter for the year.
     * @return String
     */
    public String getYear() {
        return year + "";
    }
    /**
     * Getter for the category.
     * @return String
     */
    public String getCategory() {
        return category + "";
    }
    /**
     * Getter for the share.
     * @return String
     */
    public String getShare() {
        return share + "";
    }
    /**
     * Getter for the motivation
     * @return String
     */
    public String getMotivation() {
        return motivation + "";
    }
    /**
     * Getter for the affiliations.
     * @return List
     */
    public List getAffiliations() {
        List<LinkedTreeMap> copy = new ArrayList();
        if (affiliations == null) {
            copy.add(new LinkedTreeMap());
            return copy;
        }
        for (LinkedTreeMap m : affiliations) {
            LinkedTreeMap curr = new LinkedTreeMap();
            for (Object key : m.keySet()) {
                key   = (String) key;
                String value = (String) m.get(key);
                curr.put(key, value);
            }
            copy.add(curr);
        }
        return copy;
    }
    /**
     * For getting a string representation of the object.
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        // JAD ADD CODE HERE
        return builder.toString();
    }
}
