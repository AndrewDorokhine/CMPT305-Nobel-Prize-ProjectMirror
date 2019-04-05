package Window;

import API.laureate.Laureate;
import API.laureate.PrizePlus;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * List node to be put in the CenterPanel, displays laureates based on search 
 * results.
 * 
 * @author Nemi R, Andrew D, Jad A, Seth T, Sitharthan E
 */
public final class CenterList {
    /**
     * Class attribute variables.
     */
    private final BorderPane            root;
    private final Map<String, Laureate> laureateData;
    private final List<String>          laureateKeys;
    private final GridPane              center;
    private final CenterPanel           centerPanel;
    private       ListView              listView;
    private       String                country;
    private       String                prize;
    private       int                   minYear;
    private       int                   maxYear;
    private       String                gender;
    private       int                   numberDisplayed;
    /**
     * Class Constructor.
     * @param r root BorderPane
     * @param l LaureateData Map
     * @param c CenterPanel GridPane
     * @param p the CenterPanel object
     */
    public CenterList(BorderPane r, Map l, GridPane c, CenterPanel p) {
        root         = r;
        laureateData = l;
        laureateKeys = getLaureateKeysInOrder();
        center       = c;
        centerPanel  = p;
        listView     = new ListView();
        country      = "";
        prize        = "";
        minYear      = 1950;
        maxYear      = 2000;
        gender       = "gender";
        numberDisplayed = 0;
        
        System.out.println(">>> Updating the display...");
        updateAdvancedDisplay(); 
    }
    /**
     * Getter for the listView.
     * @return the listView class object
     */
    public ListView getListView() {
        return listView;
    }
    /**
     * Gets a list of keys from the laureateData Map and sorts them.
     * @return List of the sorted keys
     */
    private List<String> getLaureateKeysInOrder() {
        List<String> list = new ArrayList(laureateData.keySet());
        Collections.sort(list);
        return list;
    }
    /**
     * Updates the countryCode search field.
     * @param c String of the country's code
     */
    public void updateCountry(String c) {
        country = c;
    }
    /**
     * Updates the prize search field.
     * @param p String of the prize
     */
    public void updatePrize(String p) {
        prize = p;
    }
    /**
     * Updates the minimum year search field
     * @param y String of the year
     */
    public void updateMinYear(int y){
        minYear = y;
    }
    /**
     * Updates the maximum year search field
     * @param y String of the year
     */
    public void updateMaxYear(int y) {
        maxYear = y;
    }
    /**
     * Updates the gender search field.
     * @param g String of the gender
     */
    public void updateGender(String g) {
        gender = g;
    }
    /**
     * Update methods for the search-bar.
     */
    public void update() {
        root.setCenter(center);
    }
    /**
     * Creates a ListView for the search results from the search-bar.
     * @param results a map with the keys being the results of a search
     */
    public void updateBasicSearchDisplay(Map<String, String> results) {
        List<String> laureatesInOrder = new ArrayList(results.keySet());
        Collections.sort(laureatesInOrder);
        ListView<HBox> newDisplay = new ListView();
        
        for (String key : laureatesInOrder) {
            Laureate current  = (Laureate) laureateData.get(key);
            ListNode listItem = new ListNode(current, this, centerPanel);
            newDisplay.getItems().add(listItem.getNode());
        }
        newDisplay.setPrefWidth(836);
        newDisplay.setPrefHeight(800);
        listView = newDisplay;
        root.setCenter(listView);
        center.getChildren().clear();
        center.getChildren().add(newDisplay);
        root.setCenter(center);
    }
    /**
     * Updates the center GridPane according to the {country, prize, year, 
     * gender} variables.
     */
    public void updateAdvancedDisplay() {
        numberDisplayed = 0;
        ListView<HBox> newDisplay = new ListView();
        /**
         * Traverse all the keys in the laureate data and check for matches. If
         * the laureate matches, create a ListNode and add it to the ListView.
         */
        for (String key : laureateKeys) {
            Laureate current  = (Laureate) laureateData.get(key);
            Boolean toAdd     = false;
            Boolean breakLoop = false;
            // Check for country
            if (country.equals("")) {
                toAdd = true;
            } else if (country.equals(current.getBornCountry())) {
                toAdd = true;
            } else if (country.equals(current.getDiedCountry())) {
                toAdd = true;
            } else {
                continue;
            }
            // Check for prize category
            for (PrizePlus p : current.getPrizes()) {
                if (prize.equals("") || p.getCategory().equals(prize)) {
                    toAdd = true;
                    breakLoop = false;
                } else {
                    toAdd = false;
                    breakLoop = true;
                }
            }
            // move onto the next key without adding this laureate
            if (breakLoop) {
                continue;
            }
            for (PrizePlus p : current.getPrizes()) {
                if (!p.getYear().equals("null")) {
                    int year = Integer.parseInt(p.getYear());
                    if (year >= minYear && year <= maxYear) {
                        toAdd = true;
                        breakLoop = false;
                    } else {
                        toAdd = false;
                        breakLoop = true;
                    }
                }
            }
            // move onto the next key without adding this laureate
            if (breakLoop) {
                continue;
            }
            // Check gender
            if (gender.equals(current.getGender()) || gender.equals("gender")) {
                toAdd = true;
            } else { 
                continue;
            }
            // Add the laureate if the qualify
            if (toAdd == true) {
                ListNode listItem = new ListNode(current, this, centerPanel);
                
                newDisplay.getItems().add(listItem.getNode());
                ++numberDisplayed;
            }
        }
        newDisplay.setPrefWidth(836);
        newDisplay.setPrefHeight(800);
        listView = newDisplay;
        // All laureates have been searched, update center node in the window
        center.getChildren().clear();
        center.getChildren().add(newDisplay);
        root.setCenter(center);
    }
}
