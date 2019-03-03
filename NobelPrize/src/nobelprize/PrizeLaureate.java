package nobelprize;

/**
 *
 * @author Nemi R, Andrew D, Jad A, Seth T, Sitharthan E
 */
public class PrizeLaureate {
    private String id;
    private String firstname;
    private String motivation;
    private String share;
    
    public String toString() {
        StringBuilder builder = new StringBuilder();
        
        builder.append("\n\tID: ");
        builder.append(id);
        builder.append("\n");
        builder.append("\tFirst Name: ");
        builder.append(firstname);
        builder.append("\n");
        builder.append("\tMotivation: ");
        builder.append(motivation);
        builder.append("\n");
        builder.append("\tShare: ");
        builder.append(share);
        builder.append("\n\n");
        
        return builder.toString();
    }
}
