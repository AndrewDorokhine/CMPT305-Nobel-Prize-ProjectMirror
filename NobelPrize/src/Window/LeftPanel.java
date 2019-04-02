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
import javafx.event.EventHandler;
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
    private final CenterPanel     center;
    private final TabPane         left;
    private VBox                  advancedSearch;
    private VBox                  basicSearch;
    private final ComboBox        prizeComboBox;
    private final ComboBox        countryComboBox;
    private TextField textField;
    private final RightPanel      right;
    /**
     * Class constructor.
     * @param r root BorderPane
     * @param c the CenterPanel
     * @param rt
     * @param a the api data
     */
    public LeftPanel(BorderPane r, CenterPanel c, RightPanel rt, APISearcher a) {
        api      = a;
        center   = c;
        root     = r;
        right = rt;
        prizeComboBox   = new ComboBox();
        prizeComboBox.setPrefWidth(200);
        countryComboBox = new ComboBox();
        countryComboBox.setPrefWidth(200);
        
        textField    = (TextField) createTextField("Search", 125);
        
        left            = initTabPane(200, 700);
        initAdvancedSearch(new Insets(10,10,10,10), 10, 200, 700);
        initBasicSearch(new Insets(10,10,10,10), 10, 200, 700);
        
        updateDisplay();
    }
    /**
     * Initializes the TabPane.
     * @param padding padding of the VBox
     * @param width   width of the VBox
     * @param height  height of the VBox
     * @return VBox
     */
    private TabPane initTabPane (int width, int height) {
        TabPane tabPane = new TabPane();
        return tabPane;
    }
    private void initBasicSearch(Insets padding, int spacing, int width, int height) {
        basicSearch = new VBox();
        basicSearch.setPadding(padding);
        basicSearch.setPrefWidth(width);
        basicSearch.setPrefHeight(height);
        basicSearch.setSpacing(spacing);

        
        HBox searchBox  = new HBox();
        Button searchButton = (Button) createButton("Go!", textField);
        searchBox.getChildren().addAll(textField, searchButton);
        
        basicSearch.getChildren().add(searchBox);
        
        Tab basic = new Tab("Basic");
        basic.setClosable(false);
        basic.setContent(basicSearch);
        
        left.getTabs().add(basic);
        
    }
    /**
     * Create a TextField and returns the object
     * @param prompt the prompt to display in the text field
     * @param col    column count
     * @return       Object type
     */
    private Object createTextField(String prompt, double size) {
        // Create search field
        TextField newField = new TextField();
        newField.setPromptText(prompt);
        newField.setPrefWidth(size);
        newField.getText();
        
        return newField;
    }
    /**
     * Creates a button
     * @param prompt text to display on the button
     * @return Object type
     */
    private Object createButton(String prompt, TextField text) {
        Button button = new Button(prompt);
        
        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Map results = (HashMap) api.search(textField.getText());
                try {
                    center.getCenterList().updateBasicSearchDisplay(results);
                } catch (IOException ex) {
                    Logger.getLogger(LeftPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return button;
    }
    /**
     * Initializes the VBox with padding and dimensions.
     * @param padding padding of the VBox
     * @param width   width of the VBox
     * @param height  height of the VBox
     * @return VBox
     */
    private void initAdvancedSearch (Insets padding, int spacing, int width, int height) {
        advancedSearch = new VBox();
        advancedSearch.setPadding(padding);
        advancedSearch.setPrefWidth(width);
        advancedSearch.setPrefHeight(height);
        advancedSearch.setSpacing(spacing);
        createPrizeSelection();
        createCountrySelection();
        
        advancedSearch.getChildren().addAll(prizeComboBox, countryComboBox);
        
        Tab advanced    = new Tab("Advanced");
        advanced.setClosable(false);
        advanced.setContent(advancedSearch);
        
        left.getTabs().add(advanced);
        
    }
    /**
     * Getter for the VBox node.
     * @return VBox
     */
    public TabPane getLeft() {
        return left;
    }
    /**
     * Updates the left node in the BorderPane.
     */
    private void updateDisplay() {
        root.setLeft(left);
    }
    /**
     * Creates the prize selection ComboBox. Has an event that updates the
     * center display with new prize parameters.
     */
    private void createPrizeSelection() {
        prizeComboBox.getItems().add("Prizes");
        prizeComboBox.getSelectionModel().selectFirst();
        List<String> categoryKeys = api.getPrizeKeysInOrder();
        for (String category : categoryKeys) {
            prizeComboBox.getItems().add(category);
            
            prizeComboBox.setOnAction((Event e) -> {
                try {
                    if (prizeComboBox.getValue().equals("Prizes")) {
                        center.updatePrize("");
                    } else {
                        center.updatePrize((String)prizeComboBox.getValue());
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
        List<String> countryKeys = api.getCountryKeysInOrder();
        
        for (String country : countryKeys) {
            if (api.checkIfCountryInUse(country)) {
                countryComboBox.getItems().add(country);
            }
            
            countryComboBox.setOnAction((Event e) -> {
                try {
                    if (countryComboBox.getValue().equals("Countries")) {
                        center.updateCountry("");
                    } else {
                        center.updateCountry((String)countryComboBox.getValue());
                    }
                } catch (IOException ex) {
                    Logger.getLogger(LeftPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
    }
}
   