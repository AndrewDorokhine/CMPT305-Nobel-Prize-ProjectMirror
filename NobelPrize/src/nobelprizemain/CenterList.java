package nobelprizemain;

import API.laureate.Laureate;
import API.laureate.PrizePlus;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * Contains the one of the GridPane objects that can be displayed in the center
 * of the root BorderPane. The results are self updating, given that the
 * attribute variables {countryCode, prize, year, gender} are updated through
 * their setter methods.
 * @author Nemi
 */
public class CenterList {
    /** 
     * Attribute variables.
     */
    GridPane                      center;
    private ListView              listView;
    private final BorderPane      root;
    private Map<String, Laureate> laureateData;
    private List<String>          sortedKeys;
    private String                countryCode;
    private String                prize;
    private int                   year;
    private String                gender;
    /**
     * Class constructor.
     * @param r root BorderPane
     * @param l laureate Map
     */
    public CenterList(BorderPane r, Map l) {
        // Initialize the GridPane
        center = new GridPane();
        center.setPrefWidth(500);
        center.setPrefHeight(700);
        center.setPadding(new Insets(10,10,10,10));
        
        listView     = new ListView();
        root         = r;
        laureateData = l;
        sortedKeys   = getKeysInOrder();
        countryCode  = "";
        prize        = "";
        year         = 0;
        gender       = "";
    }
    /**
     * Getter for the center GridPane.
     * @return GridPane containing the information to display
     */
    public GridPane getCenter() {
        return center;
    }
    /**
     * Gets a list of keys from the laureateData Map and sorts them.
     * @return List of the sorted keys
     */
    private List<String> getKeysInOrder() {
        List<String> list = new ArrayList(laureateData.keySet());
        Collections.sort(list);
        return list;
    }
    /**
     * Updates the center GridPane according to the {country, prize, year, 
     * gender} variables.
     * @throws IOException 
     */
    public void updateDisplay() throws IOException{
        ListView<HBox> newDisplay = new ListView();
        newDisplay.setPrefWidth(600);
        newDisplay.setPrefHeight(700);
        /**
         * Traverse all the keys in the laureate data and check for matches. If
         * the laureate matches, create a ListNode and add it to the ListView.
         */
        for (String key : sortedKeys) {
            Laureate current  = (Laureate) laureateData.get(key);
            Boolean toAdd     = false;
            Boolean breakLoop = false;
            // Check for country
            if (countryCode.equals("") || countryCode.equals(current.getBornCountryCode())) {
                toAdd = true;
            } else {
                continue;
            }
            // Check for prize category
            for (PrizePlus p : current.getPrizes()) {
                if (prize.equals("") || p.getCategory().equals(prize)) {
                    toAdd = true;
                    breakLoop = false;
                    continue;
                } else {
                    toAdd = false;
                    breakLoop = true;
                }
            }
            // move onto the next key without adding this laureate
            if (breakLoop) {
                continue;
            }
            // Check for date, currently only year
            int startYear = Integer.parseInt(current.getBorn().substring(0,4));
            int endYear   = Integer.parseInt(current.getDied().substring(0,4));
            if (year == 0 || ((year > startYear) && (year < endYear))) {
                toAdd = true;
            } else {
                continue;
            }
            // Check gender
            if (gender.equals("") || gender.equals(current.getGender())) {
                toAdd = true;
            } else { 
                continue;
            }
            // Add the laureate if the qualify
            if (toAdd == true) {
                ListNode listItem = new ListNode(current);
                newDisplay.getItems().add(listItem.getNode());
            }
        }
        // All laureates have been searched, update center node in the window
        center.getChildren().clear();
        center.getChildren().add(newDisplay);
        root.setCenter(center);
    }
    /**
     * Updates the countryCode search field.
     * @param c String of the country's code
     * @throws IOException 
     */
    public void updateCountryCode(String c) throws IOException {
        countryCode = c;
        updateDisplay();
    }
    /**
     * Updates the prize search field.
     * @param p String of the prize
     * @throws IOException 
     */
    public void updatePrize(String p) throws IOException {
        prize = p;
        updateDisplay();
    }
    /**
     * Updates the year search field
     * @param y String of the year
     * @throws IOException 
     */
    public void updateYear(int y) throws IOException {
        year= y;
        updateDisplay();
    }
    /**
     * Updates the gender search field.
     * @param g String of the gender
     * @throws IOException 
     */
    public void updateGender(String g) throws IOException {
        gender = g;
        updateDisplay();
    }
}
