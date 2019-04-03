package Window;

import API.APISearcher;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Contains one of the VBox object to be used as a node in the left panel of 
 * the root BorderPane. Once an item is selected in each of the children of the
 * VBox, it will update the center portion of the root BorderPane. Ex: selecting
 * "Physics" from the "Prizes" ComboBox will display all Physics prize winners.
 * @author Nemi
 */
public class LeftPanel {
    /**
     * Attribute variables.
     */
    private final BorderPane      root;
    private final APISearcher     api;
    private final CenterPanel     centerPanel;
    private final ComboBox        prizeComboBox;
    private final ComboBox        countryComboBox;
    private final TabPane         tabPane;
    private final TextField       searchField;
    private final VBox            advancedSearch;
    private final VBox            basicSearch;
    /**
     * Class constructor.
     * @param r root BorderPane
     * @param c the CenterPanel
     * @param a the api data
     */
    public LeftPanel(BorderPane r, CenterPanel c, APISearcher a) {
        root            = r;
        api             = a;
        centerPanel     = c;
        prizeComboBox   = createComboBox(200);
        countryComboBox = createComboBox(200);
        tabPane         = initTabPane();
        searchField     = createTextField("Search", 125);
        basicSearch     = initBasicSearch(new Insets(10,10,10,10), 10, 200, 700);
        advancedSearch  = initAdvancedSearch(new Insets(10,10,10,10), 10, 200, 700);
        
        
        updateDisplay();
    }
    /**
     * Updates the TabPane node in the BorderPane.
     */
    private void updateDisplay() {
        root.setLeft(tabPane);
    }
    /**
     * Creates a ComboBox with the given width.
     * @param width the horizontal length of the ComboBox
     * @return a new ComboBox Object
     */
    private ComboBox createComboBox(int width) {
        ComboBox comboBox = new ComboBox();
        comboBox.setPrefWidth(width);
        return comboBox;
    }
    /**
     * Creates a TabPane.
     * @return a new TabPane Object
     */
    private TabPane initTabPane () {
        TabPane tabPane = new TabPane();
        return tabPane;
    }
    /**
     * Create a TextField with a given prompt and size.
     * @param prompt the prompt to display in the text field
     * @param size   length of the TextField
     * @return       a new TextField Object
     */
    private TextField createTextField(String prompt, double size) {
        // Create search field
        TextField newField = new TextField();
        newField.setPromptText(prompt);
        newField.setPrefWidth(size);
        newField.getText();
        return newField;
    }
    /**
     * Initializes the advancedSearch VBox with ComboBox selections.
     * @param padding padding of the VBox
     * @param width   width of the VBox
     * @param height  height of the VBox
     * @return VBox
     */
    private VBox initAdvancedSearch (Insets padding, int spacing, int width, int height) {
        // Create the VBox
        VBox vBox = new VBox();
        vBox.setPadding(padding);
        vBox.setPrefWidth(width);
        vBox.setPrefHeight(height);
        vBox.setSpacing(spacing);
        // Create the ComboBox selections and search button
        createPrizeSelection();
        createCountrySelection();
        // Add more ComboBoxes here;
        Button button = createAdvancedSearchButton();
        // Add the ComboBoxes to the VBox
        vBox.getChildren().addAll(prizeComboBox, countryComboBox, button);
        // Create the tab and add the VBox to it and add the tab to the TabPane
        Tab advanced    = new Tab("Advanced");
        advanced.setClosable(false);
        advanced.setContent(vBox);
        tabPane.getTabs().add(advanced);
        return vBox;
    }
    /**
     * 
     * @param padding
     * @param spacing
     * @param width
     * @param height
     * @return 
     */
    private VBox initBasicSearch(Insets padding, int spacing, int width, int height) {
        // Create the VBox
        VBox vBox = new VBox();
        vBox.setPadding(padding);
        vBox.setPrefWidth(width);
        vBox.setPrefHeight(height);
        vBox.setSpacing(spacing);
        // Create the HBox that holds the TextField and search button
        HBox searchBox  = new HBox();
        Button searchButton = createButton("Go!", searchField);
        searchBox.getChildren().addAll(searchField, searchButton);
        vBox.getChildren().add(searchBox);
        // Create the tab and add the VBox to it and add the tab to the TabPane
        Tab basic = new Tab("Basic");
        basic.setClosable(false);
        basic.setContent(vBox);
        tabPane.getTabs().add(basic);
        return vBox;
    }
    /**
     * Creates a button with an ActionEvent that searches the laureate data.
     * @param prompt text to display on the button
     * @return new Button Object
     */
    private Button createButton(String prompt, TextField text) {
        Button button = new Button(prompt);
        
        button.setOnAction((ActionEvent event) -> {
            Map results = (HashMap) api.search(searchField.getText());
            try {
                centerPanel.getCenterList().updateBasicSearchDisplay(results);
            } catch (IOException ex) {
                Logger.getLogger(LeftPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        return button;
    }
    /**
     * Creates the prize selection ComboBox. Has an event that updates the
     * center display with new prize parameters.
     */
    private void createPrizeSelection() {
        prizeComboBox.getItems().add("Prizes");
        prizeComboBox.getSelectionModel().selectFirst();
        // Get the keys in order to display in the ComboBox, and add them
        List<String> categoryKeys = api.getPrizeKeysInOrder();
        for (String category : categoryKeys) {
            prizeComboBox.getItems().add(category);
            /**
             * Update the prize parameter in the CenterPanel.
             */
            prizeComboBox.setOnAction((Event e) -> {
                try {
                    if (prizeComboBox.getValue().equals("Prizes")) {
                        centerPanel.updatePrize("");
                    } else {
                        centerPanel.updatePrize((String)prizeComboBox.getValue());
                    }
                } catch (IOException ex) {
                    Logger.getLogger(LeftPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
        }
    }
    /**
     * Creates the country selection comboBox. Hash an event that updates the
     * center display with new country parameters.
     */
    private void createCountrySelection() {
        countryComboBox.getItems().add("Countries");
        countryComboBox.getSelectionModel().selectFirst();
        // Get the keys in order to display in the ComboBox, and add them
        List<String> countryKeys = api.getCountryKeysInOrder();
        for (String country : countryKeys) {
            if (api.checkIfCountryInUse(country)) {
                countryComboBox.getItems().add(country);
            }
            /**
             * Update the country parameter in the CenterPanel.
             */
            countryComboBox.setOnAction((Event e) -> {
                try {
                    if (countryComboBox.getValue().equals("Countries")) {
                        centerPanel.updateCountry("");
                    } else {
                        centerPanel.updateCountry((String)countryComboBox.getValue());
                    }
                } catch (IOException ex) {
                    Logger.getLogger(LeftPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
    }
    /**
     * Creates a button for the advancedSearch VBox.
     * @return a new Button Object
     */
    private Button createAdvancedSearchButton() {
        Button button = new Button("Go!");
        button.setOnAction((ActionEvent event) -> {
            try {
                centerPanel.updateDisplay();
            } catch (IOException ex) {
                Logger.getLogger(LeftPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        return button;
    }
}
   