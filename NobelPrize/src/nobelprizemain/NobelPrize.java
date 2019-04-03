package nobelprizemain;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
/**
 * MAIN
 *
 * @author Nemi R, Andrew D, Jad A, Seth T, Sitharthan E
 */
public class NobelPrize extends Application{
    /**
     * Class constructor. 
     */
    public NobelPrize(){
    }
    /**
     * Main call: checks internet connection, then runs program.
     * @param args 
     * @throws java.io.IOException 
     */
    public static void main(String[] args) throws IOException {
        checkInternetConnection();
        launch(args);
    }
    /**
     * Starts the JavaFX application.
     * @param stage
     * @throws Exception 
     */
    @Override
    public void start(Stage stage) throws Exception {
        /**
         * Create new root BorderPane and pass it to a new driver, then
         * run the driver.
         */
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
