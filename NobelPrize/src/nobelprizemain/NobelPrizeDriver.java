package nobelprizemain;

import API.APISearcher;
import API.laureate.Laureate;
import API.picture.ImageData;
import API.prize.Category;
import API.prize.PrizeLaureate;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
     * Runs the GUI.
     * @param program
     * @param stage
     * @param grid 
     */
    public void runGUI(NobelPrizeDriver program, Stage stage, BorderPane root) throws IOException {
        // Add all components of the BorderPane Layout.
        
        // Add Top
        ImageView top = (ImageView) addTop(root);
        // Add center
        //GridPane center = (GridPane) addCenter(root);
        
        CenterList center = new CenterList(root, api.laureateData.getData());
        center.updateDisplay();
        root.setCenter(center.getCenter());
        // Add left column
        VBox left = (VBox) addLeft(root, center.getCenter());
        // Add right column
        GridPane right = (GridPane) addRight(root, center.getCenter());
        
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
    private Object addTop(BorderPane root) {
        ImageView titleBanner = new ImageView(new Image("file:title.png"));
        root.setTop(titleBanner);
        return titleBanner;
    }
    /**
     * Creates the left part of the BorderPane, holds the TreeView object in a 
     * GridPane.
     * @param root   BorderPane container.
     * @param center for updating the view in the middle of the window
     */
    private Object addLeft(BorderPane root, GridPane center) {
        VBox leftCol = new VBox();
        leftCol.setPrefWidth(200);
        TreeItem<String> prizeTree = new TreeItem<String> ("Prizes");
        /**
         * Traverse data and populate the tree, sort map keys before traversing.
         */
        // Add categories
        List<String> categoryKeys = api.getPrizeKeysInOrder();
        for (String category : categoryKeys) {
            TreeItem treeItem = new TreeItem<String>(category);
            prizeTree.getChildren().add(treeItem);
            HashMap<String, Category> copy = (HashMap) api.getPrizeData();
            Category current = copy.get(category);
            
            
            // Add years
            List<String> yearKeys = new ArrayList<String>(current.getData().size());
            yearKeys.addAll(current.getData().keySet());
            Collections.sort(yearKeys, Collections.reverseOrder());
            for (String year : yearKeys) {
                Hyperlink hyperlink = new Hyperlink(year);
                treeItem.getChildren().add(new TreeItem<Hyperlink>(hyperlink));
                
                hyperlink.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        populateCenter(root, center, (List) current.getData().get(year));
                    }
                    
                });
            }
            // Add more to the tree here
        }
        // Create TreeView and add to the BorderPane
        TreeView<String> tree = new TreeView<>(prizeTree);
        leftCol.getChildren().add(tree);
        leftCol.setPadding(new Insets(10,10,10,10));
        root.setLeft(leftCol);
        
        return leftCol;
    }
    /**
     * 
     * @param root
     * @param center
     * @param laureates 
     */
    private void populateCenter(BorderPane root, GridPane center, List laureates) {
        ListView main = new ListView();
        main.setStyle("-fx-background-color: transparent");
        for(Object p : laureates) {
            PrizeLaureate current = (PrizeLaureate) p;
            Laureate laureate = api.laureateData.getLaureatebyID(current.getID());
            Text person = new Text(current.toString());
            main.getItems().add(person);
        }
        center.getChildren().clear();
        main.setPrefWidth(600);
        center.getChildren().add(main);
        root.setCenter(center);
    }
    /**
     * Creates the right part for the BorderPane, currently just the search
     * field.
     * @param root BorderPane container.
     */
    private Object addRight(BorderPane root, GridPane center) {
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
                    
                    try {
                        Image image = (Image) getImage(name);
                        ImageView imageView = (ImageView) setImage(image, 200, 200);
                        Text info = new Text(laureateInfo);
                        center.getChildren().clear();
                        center.add(imageView, 0, 0);
                        center.add(info, 200, 0);
                        root.setCenter(center);
                    } catch (IOException ex) {
                        Logger.getLogger(NobelPrizeDriver.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    // Print to console
                    System.out.println(laureateInfo);
                } else {
                    searchText.setPromptText("Invalid entry");
                }
            }
        });
        
        return right;
    }
    /**
     * Creates the center part of the BorderPane, currently an empty StackPane.
     * @param root BorderPane container
     */
    private Object addCenter(BorderPane root) {
        GridPane center = new GridPane();
        center.setPrefWidth(400);
        center.setPadding(new Insets(10,10,10,10));
        root.setCenter(center);
        return center;
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
