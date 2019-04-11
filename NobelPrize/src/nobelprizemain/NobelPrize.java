package nobelprizemain;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * MAIN PROGRAM. Checks for an internet connection before running the program.
 * When an internet connection is confirmed, it builds hashmaps of GSON data
 * retrieved from the Nobel Prize API (https://nobelprize.readme.io/) and shows
 * the window. Images are stored locally and have been retrieved from the
 * WikiMedia API (https://www.mediawiki.org/wiki/MediaWiki), backup images have
 * been retrieved from the Google API.
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
     * Main call, checks internet connection, builds hashmaps, and launches the
     * window.
     * @param args the command line arguments
     */
    public static void main(String[] args){
        checkInternetConnection();
        launch(args);
        System.out.println(">>> Program terminated successfully.");
    }
    /**
     * Starts the JavaFX application. Creates the NobelPrizeDriver, and runs it.
     * @param stage the stage to display the GUI on
     */
    @Override
    public void start(Stage stage) {
        NobelPrizeDriver program = new NobelPrizeDriver();
        stage.setTitle("Nobel Prize Information");
        program.runGUI(stage);
    }
    /**
     * Checks internet connection. Prints an error message to the console if
     * there's no connection.
     */
    private static void checkInternetConnection() {
        System.out.println(">>> Checking internet connection...");
        boolean status = false;
        while (!status) {
            try {
                URL url = new URL("https://nobelprize.readme.io/");
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
