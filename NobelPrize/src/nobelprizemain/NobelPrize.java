package nobelprizemain;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;

/**
 * MAIN
 *
 * @author Nemi R, Andrew D, Jad A, Seth T, Sitharthan E
 */
public class NobelPrize extends Application{
    NobelPrizeDriver program; 
    /**
     * Constructor.
     * @throws IOException 
     */
    public NobelPrize() throws IOException {
        this.program = new NobelPrizeDriver();
    }
    /**
     * Main call: currently runs a test to get and display information from
     *            the Nobel Prize API.
     * @param args 
     * @throws java.io.IOException 
     */
    public static void main(String[] args) throws IOException {
        while (!checkInternetConnection()) {
            System.out.println("Internet not connected.");
        }
        launch(args);
    }
    /**
     * Checks internet connection.
     * @return boolean
     */
    public static boolean checkInternetConnection() {
        try {
            URL url = new URL("https://www.macewan.ca/");
            URLConnection connection = url.openConnection();
            connection.connect();
            return true;
        }
        catch(Exception e) {
           return false;
        }
    }
    @Override
    public void start(Stage stage) throws Exception {
        // Create grid and run test
        GridPane grid = new GridPane();
        program.test(program, stage, grid);
    }
}
