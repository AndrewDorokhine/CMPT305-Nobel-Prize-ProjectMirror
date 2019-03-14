package nobelprizemain;

import API.APISearcher;
import API.picture.ImageData;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
        stage.setTitle("Nobel Prize Information");
        // Create grid
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(1);
        grid.setHgap(1);
        
        // Create search field
        TextField name = new TextField();
        name.setPromptText("Enter laureate.");
        name.setPrefColumnCount(10);
        name.getText();
        GridPane.setConstraints(name, 50, 0);
        grid.getChildren().add(name);
        
        // Create search button
        Button search = new Button("search");
        GridPane.setConstraints(search,51,0);
        grid.getChildren().add(search);
        
        // Show the stage
        Scene scene = new Scene(grid, 720, 600);
        stage.setScene(scene);
        stage.show(); 
        
        // Add a label for testing
        Label label = new Label();
        GridPane.setConstraints(label, 50, 50);
        //GridPane.setColumnSpan(label, 2);
        grid.getChildren().add(label);
        
        // Set an action for the search button
        search.setOnAction(new EventHandler<ActionEvent>() {
        @Override
            public void handle(ActionEvent e) {
                if (name.getText() != null && !name.getText().isEmpty()) {
                    try {
                        String input = name.getText();
                        label.setText(input);
                        Image image = imageTest(input);
                        System.out.print("");
                        if (image == null) {
                            Image noImage = new Image("no-image-found.jpg");
                        }
                        ImageView imageView = new ImageView(image); 
                        imageView.setX(50); 
                        imageView.setY(25); 
                        imageView.setFitHeight(455); 
                        imageView.setFitWidth(500); 
                        imageView.setPreserveRatio(true);
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
     * Given a string with a first and last name, gets a picture.
     * @param searchTerm name to be searched for
     * @return Image
     * @throws IOException 
     */
    public Image imageTest(String searchTerm) throws IOException {
        StringTokenizer st = new StringTokenizer(searchTerm);
        String first = st.nextToken();
        String last = st.nextToken();
        ImageData testImageData = api.getImage(first, last);
        return testImageData.getImage();
    }
    
    

}
