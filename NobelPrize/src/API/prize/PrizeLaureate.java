package API.prize;

/**
 * Resembles a laureate from the prize result from the Nobel Prize API.
 * 
 * @author Nemi R, Andrew D, Jad A, Seth T, Sitharthan E
 */
public class PrizeLaureate {
    private final String id;
    private final  String firstname;
    private final  String motivation;
    private final  String share;
    /**
     * Deep copy constructor.
     * @param o PrizeLaureate to copy
     */
    public PrizeLaureate(PrizeLaureate o) {
        id         = o.getID();
        firstname  = o.getFirstName();
        motivation = o.getMotivation();
        share      = o.getShare();
    }
    /**
     * Getter for the id
     * @return String
     */
    private String getID() {
        return id + "";
    }
    /**
     * Getter fort eh firstname.
     * @return String
     */
    private String getFirstName() {
        return firstname + "";
    }
    /**
     * Getter for the motivation.
     * @return String
     */
    private String getMotivation() {
        return motivation + "";
    }
    /**
     * Getter for the share.
     * @return String
     */
    private String getShare() {
        return share + "";
    }
    /**
     * For getting a string representation of the object for printing.
     * @return String
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n\tID: ");
        builder.append(getID());
        builder.append("\n");
        builder.append("\tFirst Name: ");
        builder.append(getFirstName());
        builder.append("\n");
        builder.append("\tMotivation: ");
        builder.append(getMotivation());
        builder.append("\n");
        builder.append("\tShare: ");
        builder.append(getShare());
        builder.append("\n\n");
        return builder.toString();
    }
}
