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
    private final BorderPane            root;
    private final Map<String, Laureate> laureateData;
    private final GridPane              center;
    private final CenterList            centerList;

    /**
     * Class constructor.
     * @param r root BorderPane
     * @param l laureate Map
     * @throws java.io.IOException
     */
    public CenterPanel(BorderPane r, Map l) throws IOException {
        root         = r;
        laureateData = l;
        center       = createGridPane(700, 700);
        centerList   = new CenterList(root, laureateData, center, this);
    }
    /**
     * Creates a new GridPane Object with a specified size 
     * @param width  the width of the GridPane
     * @param height the height of the GridPane
     * @return a new GridPane Object
     */
    private GridPane createGridPane(int width, int height) {
        GridPane gridPane = new GridPane();
        gridPane.setPrefWidth(width);
        gridPane.setPrefHeight(height);
        gridPane.setPadding(new Insets(10,10,10,10));
        return gridPane;
    }
    /**
     * Getter for the center GridPane.
     * @return GridPane containing the information to display
     */
    public GridPane getCenter() {
        return center;
    }
    /**
     * Getter for the centerList, containing the search results.
     * @return the current displayed CenterList
     */
    public CenterList getCenterList() {
        return centerList;
    }
    /**
     * Updates the country search field in the centerList.
     * @param c String of the prize
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
     * @throws java.io.IOException
     */
    public void updateDisplay() throws IOException{
        centerList.updateAdvancedDisplay();
    }
    /**
     * Updates the centerList.
     */
    public void update() {
        centerList.update();
    }
}
