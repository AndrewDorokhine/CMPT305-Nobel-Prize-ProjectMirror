package API.laureate;

import java.util.ArrayList;
import java.util.List;

/**
 * Prize to be used with the laureate class, has more information than the Prize
 * object in API.prize
 * 
 * @author Nemi R, Andrew D, Jad A, Seth T, Sitharthan E
 */
public class PrizePlus {
    String year;
    String category;
    String share;
    String motivation;
    List<Object> affiliations;
    /**
     * Deep copy constructor.
     * @param o object to be copied
     */
    public PrizePlus(PrizePlus o) {
        year         = o.getYear();
        category     = o.getCategory();
        share        = o.getShare();
        motivation   = o.getMotivation();
        affiliations = (ArrayList) getAffiliations();
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
        List<Affiliations> copy = new ArrayList();
        for (Object o : affiliations) {
            Affiliations current = (Affiliations) o;
            copy.add(new Affiliations(current));
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
