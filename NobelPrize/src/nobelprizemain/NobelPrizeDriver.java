package nobelprizemain;

import API.APISearcher;
import API.picture.ImageData;
import API.prize.Category;
import java.io.IOException;
import java.util.StringTokenizer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Driver for the NobelPrize program & GUI.
 * 
 * @author Nemi R, Andrew D, Jad A, Seth T, Sitharthan E
 */
public class NobelPrizeDriver {
    private static APISearcher api;
    /**
     * Constructor.
     * @throws java.io.IOException
     */
    public NobelPrizeDriver () throws IOException{
        api = new APISearcher();
    }
    /**
     * Test
     * @param program
     * @param stage
     * @param grid 
     */
    public void runGUI(NobelPrizeDriver program, Stage stage, BorderPane root) {
        // Add all components of the BorderPane Layout.
        
        // Add Top
        addTop(root);
        // Add left column
        addLeft(root);
        // Add tight column
        addRight(root);
        // Add center
        addCenter(root);
        
        // Show the stage
        Scene scene = new Scene(root, 1024, 768);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show(); 
    }
    /**
     * Creates the top part for the BorderPane, currently just the title image.
     * @param root BorderPane container
     */
    private void addTop(BorderPane root) {
        ImageView titleBanner = new ImageView(new Image("file:title.png"));
        root.setTop(titleBanner);
    }
    /**
     * Creates the left part of the BorderPane, holds the TreeView object.
     * @param root BorderPane container.
     */
    private void addLeft(BorderPane root) {
        VBox leftCol = new VBox();
        TreeItem<String> prizeTree = new TreeItem<String> ("Prizes");
        // Traverse data and populate the tree
        for (String category : api.prizeData.data.keySet()) {
            TreeItem temp = new TreeItem<String>(category);
            prizeTree.getChildren().add(temp);
            Category current = api.prizeData.data.get(category);
            for (Object year : current.getData().keySet()) {
                temp.getChildren().add(new TreeItem<String>((String)year));
            }
            // Add more to the tree here
        }
        // Create TreeView and add to the BorderPane
        TreeView<String> tree = new TreeView<>(prizeTree);
        leftCol.getChildren().add(tree);
        leftCol.setPadding(new Insets(10,10,10,10));
        root.setLeft(leftCol);
    }
    /**
     * Creates the right part for the BorderPane, currently just the search
     * field.
     * @param root BorderPane container.
     */
    private void addRight(BorderPane root) {
        // Create search field, and search button
        TextField searchText = (TextField) createTextField("Enter laureate.", 175);
        Button searchButton = (Button) createButton("Search");
        
        // Create Gridpane container and add the components
        GridPane right = new GridPane();
        right.setPadding(new Insets(10, 10, 10, 10));
        right.setPrefWidth(300);
        right.add(searchText, 0, 0);
        right.add(searchButton, 4, 0);
        root.setRight(right);
        
        // Set an action for the search button
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
        @Override
            public void handle(ActionEvent e) {
                if (searchText.getText() != null && !searchText.getText().isEmpty()) {
                    String name = searchText.getText();
                    String laureateInfo = searchByName(name);
                    // Print to console
                    System.out.println(laureateInfo);
                } else {
                    searchText.setPromptText("Invalid entry");
                }
            }
        });
    }
    /**
     * Creates the center part of the BorderPane, currently an empty StackPane.
     * @param root BorderPane container
     */
    private void addCenter(BorderPane root) {
        StackPane center = new StackPane();     
        root.setCenter(center);
    }
    /**
     * Gets an image from the wikiMedia API
     * @param name name to be searched
     * @return Object type
     * @throws IOException 
     */
    private Object getImage(String name) throws IOException {
        StringTokenizer st = new StringTokenizer(name);
        String first = st.nextToken();
        String last = st.nextToken();
        ImageData testImageData = api.getImage(first, last);        
        Image image = testImageData.getImage();
        
        if (image == null) {
            image = new Image("no-image-found.jpg");
        }
        return image;
    }
    /**
     * Creates an ImageView of an image for displaying.
     * @param image
     * @param height
     * @param width
     * @return 
     */
    private Object setImage(Image image, int height, int width) {
        ImageView imageView = new ImageView(image); 
        imageView.setX(0); 
        imageView.setY(0); 
        imageView.setFitHeight(height); 
        imageView.setFitWidth(width); 
        imageView.setPreserveRatio(true);
        return imageView;
    }
    /**
     * Searches the laureateData by name
     * @param name String to be searched
     * @return String information about the laureate
     */
    private String searchByName(String name) {
        return api.laureateData.getLaureate(name);
    }
    /**
     * Create a TextField and returns the object
     * @param prompt the prompt to display in the text field
     * @param col    column count
     * @return       Object type
     */
    private Object createTextField(String prompt, double size) {
        // Create search field
        TextField textField = new TextField();
        textField.setPromptText(prompt);
        textField.setPrefWidth(size);
        textField.getText();
        
        return textField;
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
