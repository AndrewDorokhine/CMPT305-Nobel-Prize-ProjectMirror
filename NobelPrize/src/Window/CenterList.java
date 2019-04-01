/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.text.Text;

/**
 * List node to be put in the CenterPanel, displays laureates based on search 
 * results.
 * 
 * @author Nemi R, Andrew D, Jad A, Seth T, Sitharthan E
 */
public class CenterList {
    /**
     * Class attribute variables.
     */
    private final Map<String, Laureate> laureateData;
    private final List<String> laureateKeys;
    private final GridPane     center;
    private final ListView     listView;
    private final BorderPane   root;
    private String             country;
    private String             prize;
    private int                year;
    private String             gender;
    private int                numberDisplayed;
    /**
     * Class Constructor.
     * @param r root BorderPane
     * @param l LaureateData Map
     * @param c CenterPanel GridPane
     * @throws IOException 
     */
    public CenterList(BorderPane r, Map l, GridPane c) throws IOException {
        listView     = new ListView();
        root         = r;
        center       = c;
        laureateData = l;
        laureateKeys = getLaureateKeysInOrder();
        country      = "";
        prize        = "";
        year         = 0;
        gender       = "";
        numberDisplayed = 0;
        
        updateDisplay();
        root.setCenter(listView);
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
     * @param bornSet boolean value of whether the born box is checked
     * @param diedSet boolean value of whether the died box is checked
     * @throws IOException 
     */
    public void updateCountry(String c) throws IOException {
        country = c;
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
/**
     * Updates the center GridPane according to the {country, prize, year, 
     * gender} variables.
     * @throws IOException 
     */
    public void updateDisplay() throws IOException{
        numberDisplayed = 0;
        ListView<HBox> newDisplay = new ListView();
        newDisplay.setPrefWidth(600);
        newDisplay.setPrefHeight(700);
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
                ++numberDisplayed;
            }
        }
        // All laureates have been searched, update center node in the window
        center.getChildren().clear();
        center.getChildren().add(newDisplay);
        center.getChildren().add(new Text(Integer.toString(numberDisplayed)));
        root.setCenter(center);
    }
}
