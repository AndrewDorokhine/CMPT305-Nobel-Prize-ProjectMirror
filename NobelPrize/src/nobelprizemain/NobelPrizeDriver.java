package nobelprizemain;

import Window.TopPanel;
import Window.CenterPanel;
import Window.LeftPanel;
import API.APISearcher;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
/**
 * Driver for the NobelPrize program & GUI, creates all the nodes to be placed
 * into the root BorderPane.
 * 
 * @author Nemi R, Andrew D, Seth T, Sitharthan E
 */
public class NobelPrizeDriver {
    /**
     * Class attribute variables.
     */
    private static BorderPane  root;
    private static APISearcher api;
    private static TopPanel    top;
    private static CenterPanel centerPanel;
    private static LeftPanel   left;
    /**
     * Class constructor. Creates the databases by getting data from the Nobel
     * Prize API and parsing it into Maps. The nodes for the root BorderPane
     * are then created and filled out with the previously retrieved data.
     * @param r the root BorderPane
     */
    public NobelPrizeDriver (BorderPane r) {
        root        = r;
        api         = new APISearcher();
        top         = new TopPanel   (root);
        centerPanel = new CenterPanel(root, api.getLaureateData());
        left        = new LeftPanel  (root, api, centerPanel);
    }
    /**
     * Runs the GUI by setting a newly created scene and showing the stage.
     * @param stage the main stage
     */
    public void runGUI(Stage stage) {
        Scene scene = new Scene(root, 1024, 768);
        stage.setScene(scene);
        stage.setResizable(false);
        System.out.println(">>> Running program...");
        stage.show(); 
    }
}
