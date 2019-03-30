package nobelprizemain;

import API.APISearcher;
import API.laureate.Laureate;
import API.prize.Category;
import API.prize.PrizeLaureate;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

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
    VBox                          left;
    private ComboBox              prizeComboBox;
    private final BorderPane      root;
    APISearcher                   api;
    CenterList                    center;
    /**
     * Class constructor.
     * @param r root BorderPane
     * @param a the api data
     */
    public LeftPanel(BorderPane r, CenterList c, APISearcher a) {
        // Create the VBox and configure
        left = new VBox();
        left.setPrefWidth(200);
        left.setPrefHeight(700);
        left.setPadding(new Insets(10,10,10,10));
        
        prizeComboBox = new ComboBox();
        root     = r;
        api      = a;
        center   = c;
        
        updateDisplay();
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
    public void updateDisplay() {
        createPrizeSelection();
        
        left.getChildren().add(prizeComboBox);
        left.setPadding(new Insets(10,10,10,10));
        root.setLeft(left);
    }
    
    private void createPrizeSelection() {
        prizeComboBox.getItems().add("Prizes");
        prizeComboBox.getSelectionModel().selectFirst();
        List<String> categoryKeys = api.getPrizeKeysInOrder();
        for (String category : categoryKeys) {
            prizeComboBox.getItems().add(category);
            
            prizeComboBox.setOnAction(new EventHandler() {
                @Override
                public void handle(Event e) {
                    System.out.println(prizeComboBox.getValue());
                    try {
                        if (prizeComboBox.getValue().equals("Prizes")) {
                            center.updatePrize("");
                        } else {
                            center.updatePrize((String) prizeComboBox.getValue());
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(LeftPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            
        }
    }
}
   