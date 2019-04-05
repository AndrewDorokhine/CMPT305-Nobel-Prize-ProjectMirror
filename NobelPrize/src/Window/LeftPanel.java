package Window;

import API.APISearcher;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.controlsfx.control.RangeSlider;

/**
 * Contains one of the VBox object to be used as a node in the left panel of 
 * the root BorderPane. Once an item is selected in each of the children of the
 * VBox, it will update the center portion of the root BorderPane. Ex: selecting
 * "Physics" from the "Prizes" ComboBox will display all Physics prize winners.
 * 
 * @author Nemi R, Andrew D, Jad A, Seth T, Sitharthan E
 */
public class LeftPanel {
    /**
     * Class attribute variables.
     */
    private final BorderPane      root;
    private final APISearcher     api;
    private final CenterPanel     centerPanel;
    private final ComboBox        prizeComboBox;
    private final ComboBox        countryComboBox;
    private final ComboBox        genderComboBox;
    private final RangeSlider     yearSlider;
    private final Label           sliderLabel;
    private final Label           minYearResult;
    private final Label           maxYearResult;
    private final TabPane         tabPane;
    private final TextField       searchField;
    private final CheckBox        anyTerm;
    private final CheckBox        oneTerm;
    private final VBox            basicSearch;
    private final VBox            advancedSearch;
    /**
     * Class constructor. Creates the ComboBoxes and Slider then when all the 
     * nodes have been initializes, it set's the left side of the root
     * BorderPane.
     * @param r the root BorderPane
     * @param c the CenterPanel
     * @param a all the api data
     */
    public LeftPanel(BorderPane r,APISearcher a, CenterPanel c) {
        root            = r;
        api             = a;
        centerPanel     = c;
        prizeComboBox   = createComboBox(200);
        countryComboBox = createComboBox(200);
        genderComboBox  = createComboBox(200);
        sliderLabel     = new Label("Year range");
        minYearResult   = new Label("Low: ");
        maxYearResult   = new Label("High: 2000");
        yearSlider      = createYearSlider();
        tabPane         = initTabPane();
        searchField     = createTextField("Search", 125);
        anyTerm         = new CheckBox("Match any words");
        oneTerm         = new CheckBox("Match term exactly");
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
     * Creates a RangeSlider starting at 1901 and going until 2018. The slider
     * is initialized to 1950/2000 for the low/high values respectively.
     * @return the RangedSlider object that the function creates
     */
    private RangeSlider createYearSlider() {
        RangeSlider slider = new RangeSlider(1901, 2018, 1950, 2000);
        slider.setShowTickMarks(true); 
        slider.setShowTickLabels(true); 
        /**
         * Listener for the 'low' thumb.
         */
        slider.lowValueProperty().addListener(o -> {
            int lowValue = (int) slider.getLowValue();
            minYearResult.setText("Low: " + lowValue);
            try {
                centerPanel.updateMinYear(lowValue);
            } catch (IOException ex) {
                Logger.getLogger(LeftPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        /**
         * Listener for the 'high' thumb.
         */
        slider.highValueProperty().addListener(o -> {
            int highValue = (int) slider.getHighValue();
            maxYearResult.setText("High: " + highValue);
            try {
                centerPanel.updateMaxYear(highValue);
            } catch (IOException ex) {
                Logger.getLogger(LeftPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        slider.setHighValue(2000);
        slider.setLowValue(1950);
        return slider;
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
        
        newField.setOnKeyPressed((KeyEvent ke) -> {
            if (ke.getCode().equals(KeyCode.ENTER))
            {
                Map results = (HashMap) api.searchAll(searchField.getText());
                centerPanel.getCenterList().updateBasicSearchDisplay(results);
            }
            });
        return newField;
    }
    /**
     * Initializes the check boxes and adds listeners to them. Only one can be 
     * selected at a time.
     */
    private void initCheckBoxes() {
        anyTerm.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                oneTerm.setSelected(!anyTerm.isSelected());
            }
        });
        oneTerm.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                anyTerm.setSelected(!oneTerm.isSelected());
            }
        });
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
        createGenderSelection();
        // Add more ComboBoxes here;
        Button button = createAdvancedSearchButton();
        // Add the ComboBoxes to the VBox
        vBox.getChildren().addAll(prizeComboBox, countryComboBox, genderComboBox,
                                  sliderLabel, yearSlider, minYearResult, maxYearResult,
                                  button);
        // Create the tab and add the VBox to it and add the tab to the TabPane
        Tab advanced    = new Tab("Advanced");
        advanced.setClosable(false);
        advanced.setContent(vBox);
        tabPane.getTabs().add(advanced);
        return vBox;
    }
    /**
     * Initializes the basic search by creating the TextField and search Button.
     * @param padding the padding for the VBox container
     * @param spacing the spacing of the VBox container
     * @param width   the width of the VBox container
     * @param height  the height of the VBox container
     * @return the newly created VBox
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
        // Initialize the checkboxes
        initCheckBoxes();
        oneTerm.setSelected(true);
        anyTerm.setSelected(false);
        searchBox.getChildren().addAll(searchField, searchButton);
        vBox.getChildren().addAll(searchBox, oneTerm, anyTerm);
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
            Map<String, String> results = null;
            if (anyTerm.isSelected()) {
                results = (HashMap) api.searchAll(searchField.getText());
            } else {
                results = (HashMap) api.searchOne(searchField.getText());
            }
            centerPanel.getCenterList().updateBasicSearchDisplay(results);
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
     * Creates the country selection comboBox. Has an event that updates the
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
     * Creates the gender selection comboBox. Has an event that updates the
     * center display with new country parameters.
     */
    private void createGenderSelection() {
        genderComboBox.getItems().add("Gender");
        genderComboBox.getSelectionModel().selectFirst();
        genderComboBox.getItems().add("female");
        genderComboBox.getItems().add("male");
        /**
         * Update the gender parameter in the CenterPanel.
         */
            genderComboBox.setOnAction((Event e) -> {
                try {
                    // DOES NOT WORK!!!! Will always go to the "else"
                    if (countryComboBox.getValue().equals("Gender")) {
                        centerPanel.updateGender("");
                    } else {
                        String updateValue = (String)genderComboBox.getValue();
                        centerPanel.updateGender(updateValue.toLowerCase());
                    }
                } catch (IOException ex) {
                    Logger.getLogger(LeftPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
        }); 
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
   