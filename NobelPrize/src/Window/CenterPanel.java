package Window;

import API.laureate.Laureate;
import java.io.IOException;
import java.util.Map;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * Contains the one of the GridPane objects that can be displayed in the center
 * of the root BorderPane. The results are self updating, given that the
 * attribute variables {countryCode, prize, year, gender} are updated through
 * their setter methods.
 * 
 * @author Nemi R, Andrew D, Jad A, Seth T, Sitharthan E
 */
public final class CenterPanel {
    /** 
     * Class attribute variables.
     */
    private final CenterList            centerList;
    private final GridPane              center;
    private final BorderPane            root;
    private final Map<String, Laureate> laureateData;
    private final LeftPanel             left;
    private final RightPanel            right;

    /**
     * Class constructor.
     * @param r root BorderPane
     * @param l laureate Map
     * @throws java.io.IOException
     */
    public CenterPanel(BorderPane r, LeftPanel lt, RightPanel rt, Map l) throws IOException {
        // Initialize the GridPane
        center = new GridPane();
        center.setPrefWidth(500);
        center.setPrefHeight(700);
        center.setPadding(new Insets(10,10,10,10));
        
        root         = r;
        laureateData = l;
        centerList = new CenterList(root, laureateData, center, this);
        left = lt;
        right = rt;
    }
    /**
     * Getter for the center GridPane.
     * @return GridPane containing the information to display
     */
    public GridPane getCenter() {
        return center;
    }
    /**
     * Updates the country search field in the centerList.
     * @param c String of the prize
     * @param bornSet boolean for the checkbox
     * @param diedSet boolean for the checkbox
     * @throws IOException 
     */
    public void updateCountry(String c) throws IOException {
        centerList.updateCountry(c);
    }
    /**
     * Updates the prize search field in the centerList.
     * @param p String of the prize
     * @throws IOException 
     */
    public void updatePrize(String p) throws IOException {
        centerList.updatePrize(p);
    }
    /**
     * Updates the year search field in the centerList.
     * @param y String of the year
     * @throws IOException 
     */
    public void updateYear(int y) throws IOException {
        centerList.updateYear(y);
    }
    /**
     * Updates the gender search field in the centerList.
     * @param g String of the gender
     * @throws IOException 
     */
    public void updateGender(String g) throws IOException {
        centerList.updateGender(g);
    }
    /**
     * Updates the center GridPane according to the node selected. 
     */
    public void updateDisplay() throws IOException{
        centerList.updateDisplay();
    }
}
