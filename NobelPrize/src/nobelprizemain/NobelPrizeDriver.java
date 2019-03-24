package nobelprizemain;

import API.APISearcher;
import API.picture.ImageData;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Driver for the NobelPrize program.
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
    public void test(NobelPrizeDriver program, Stage stage, GridPane grid) {
        
        // Create search field
        TextField searchText = (TextField) createTextField("Enter laureate.", 10);
        GridPane.setConstraints(searchText, 50, 0);
        grid.getChildren().add(searchText);
        
        // Create search button
        Button search = (Button) createButton("Search");
        GridPane.setConstraints(search,51,0);
        grid.getChildren().add(search);
        
        // Show the stage
        Scene scene = new Scene(grid, 1024, 768);
        stage.setScene(scene);
        stage.show(); 
        
        // Add a label for testing
        Label label = new Label();
        GridPane.setConstraints(label, 100, 100);
        grid.getChildren().add(label);
        
        // Set an action for the search button
        search.setOnAction(new EventHandler<ActionEvent>() {
        @Override
            public void handle(ActionEvent e) {
                if (searchText.getText() != null && !searchText.getText().isEmpty()) {
                    try {
                        String name = searchText.getText();
                        String laureateInfo = searchByName(name);
                        label.setText(laureateInfo);
                        Image image = (Image) getImage(name);
                        ImageView imageView = (ImageView) setImage(image, 455, 500); 
                        GridPane.setConstraints(imageView, 25, 25);
                        grid.getChildren().add(imageView);
                    } catch (IOException ex) {
                        Logger.getLogger(NobelPrize.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    label.setText("Invalid entry");
                }
            }
        });
    }
    /**
     * Create a TextField and returns the object
     * @param prompt the prompt to display in the text field
     * @param col    column count
     * @return       Object type
     */
    private Object createTextField(String prompt, int col) {
        // Create search field
        TextField textField = new TextField();
        textField.setPromptText(prompt);
        textField.setPrefColumnCount(col);
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
    /**
     * Searches the laureateData by name
     * @param name String to be searched
     * @return String information about the laureate
     */
    private String searchByName(String name) {
        return api.laureateData.getLaureate(name);
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
    
    private Object setImage(Image image, int height, int width) {
        ImageView imageView = new ImageView(image); 
        imageView.setX(0); 
        imageView.setY(0); 
        imageView.setFitHeight(height); 
        imageView.setFitWidth(width); 
        imageView.setPreserveRatio(true);
        return imageView;
    }
    
    

}
