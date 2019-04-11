package Window;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Right panel of the root BorderPane.
 * 
 * @author Nemi R, Andrew D, Seth T, Sitharthan E
 */
public class RightPanel {
    /**
     * Class attribute variables.
     */
    private final BorderPane  root;
    private final VBox        right;
    private TextField         textField;
    private Button            searchButton;
    private final HBox        search;
    private final CenterPanel center;
    private final LeftPanel   left;
    /**
     * Class constructor.
     * @param r the root BorderPane
     */
    public RightPanel(BorderPane r, CenterPanel c, LeftPanel l) {
       center = c;
       left = l;
       root         = r;
       right        = initVBox(new Insets(10, 10, 10, 10), 300, 700);
       search       = initSearchHBox();
       right.getChildren().add(search);
       root.setRight(right);
    }
    /**
     * Initializes the HBox that contains the TextField and search button.
     * @return HBox
     */
    private HBox initSearchHBox() {
        HBox newBox  = new HBox();
        textField    = (TextField) createTextField("Enter laureate.", 175);
        searchButton = (Button) createButton("Search");
        newBox.getChildren().addAll(textField, searchButton);
        return newBox;
    }
    /**
     * Initializes the VBox with padding and dimensions.
     * @param padding padding of the VBox
     * @param width   width of the VBox
     * @param height  height of the VBox
     * @return VBox
     */
    private VBox initVBox (Insets padding, int width, int height) {
        VBox newBox = new VBox();
        newBox.setPadding(padding);
        newBox.setPrefWidth(width);
        newBox.setPrefHeight(height);
        
        return newBox;
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
    private Object createButton(String prompt) {
        Button button = new Button(prompt);
        return button;
    }
}
