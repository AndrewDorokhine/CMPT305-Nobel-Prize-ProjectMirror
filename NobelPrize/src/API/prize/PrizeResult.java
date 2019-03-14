package API.prize;

import java.util.ArrayList;
import java.util.List;

/**
 * Database for use with the Prizes method from the Nobel Prize API.
 * 
 * @author Nemi R, Andrew D, Jad A, Seth T, Sitharthan E
 */
public class PrizeResult {
    private List<Prize> prizes;
    /**
     * Constructor.
     */
    public PrizeResult(){}
    /**
     * Getter for the prizes list.
     * @return ArrayList copy of the prizes list
     */
    public List getPrizes() {
        List<Prize> copy = new ArrayList();
        for (Prize p : prizes) {
            copy.add(new Prize(p));
        }
        return copy;
    }
    /**
     * Get the list of all prizes as a string for printing.
     * @return string representation of the prizes list
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Object p : getPrizes()) {
            p = (Prize) p;
            builder.append(p.toString());
        }
        return builder.toString();
    }
}
