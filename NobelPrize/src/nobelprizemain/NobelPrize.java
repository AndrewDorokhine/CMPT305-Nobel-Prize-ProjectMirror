package nobelprizemain;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * MAIN PROGRAM
 *
 * @author Nemi R, Andrew D, Seth T, Sitharthan E
 */
public class NobelPrize extends Application{
    /**
     * Class constructor. 
     */
    public NobelPrize(){
    }
    /**
     * Main call: checks internet connection, then runs program.
     * @param args the command line arguments
     */
    public static void main(String[] args){
        checkInternetConnection();
        launch(args);
    }
    /**
     * Starts the JavaFX application, creates a root BorderPane and passes
     * it to a driver, then runs the driver.
     * @param stage the stage to display the GUI on
     */
    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        NobelPrizeDriver program = new NobelPrizeDriver(root);
        stage.setTitle("Nobel Prize Information");
        root.setStyle("-fx-background-color: grey;");
        program.runGUI(stage);
    }
    /**
     * Checks internet connection. Prints an error message to the console if
     * there's no internet.
     */
    private static void checkInternetConnection() {
        System.out.println(">>> Checking internet connection...");
        boolean status = false;
        while (!status) {
            try {
                URL url = new URL("https://www.macewan.ca/");
                URLConnection connection = url.openConnection();
                connection.connect();
                status =  true;
            }
            catch(IOException e) {
               status =  false;
               System.out.println(">>> Check internet connection.");
            }
        }
    }
}
