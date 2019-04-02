package Window;

import API.APISearcher;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
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
    private final VBox            left;
    private final ComboBox        prizeComboBox;
    private final ComboBox        countryComboBox;
    private final RightPanel      right;
    /**
     * Class constructor.
     * @param r root BorderPane
     * @param c the CenterPanel
     * @param a the api data
     */
    public LeftPanel(BorderPane r, CenterPanel c, RightPanel rt, APISearcher a) {
        // Create the VBox and configure
        left = initVBox(new Insets(10,10,10,10), 10, 200, 700);
        
        prizeComboBox   = new ComboBox();
        prizeComboBox.setPrefWidth(200);
        countryComboBox = new ComboBox();
        countryComboBox.setPrefWidth(200);
        
        root     = r;
        center   = c;
        api      = a;
        
        right = rt;
        
        updateDisplay();
    }
    /**
     * Initializes the VBox with padding and dimensions.
     * @param padding padding of the VBox
     * @param width   width of the VBox
     * @param height  height of the VBox
     * @return VBox
     */
    private VBox initVBox (Insets padding, int spacing, int width, int height) {
        VBox newBox = new VBox();
        newBox.setPadding(padding);
        newBox.setPrefWidth(width);
        newBox.setPrefHeight(height);
        newBox.setSpacing(spacing);
        
        return newBox;
    }
    /**
     * Getter for the VBox node.
     * @return VBox
     */
    public VBox getLeft() {
        return left;
    }
    /**
     * Updates the left node in the BorderPane.
     */
    private void updateDisplay() {
        // Create the ComboBoxes
        createPrizeSelection();
        createCountrySelection();
        
        left.getChildren().addAll(prizeComboBox, countryComboBox);
        left.setPadding(new Insets(10,10,10,10));
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
   