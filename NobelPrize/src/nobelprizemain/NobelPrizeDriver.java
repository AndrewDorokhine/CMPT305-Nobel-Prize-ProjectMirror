package nobelprizemain;

import Window.TopPanel;
import Window.CenterPanel;
import Window.LeftPanel;
import API.APISearcher;
import java.io.IOException;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * Driver for the NobelPrize program & GUI, creates all the nodes to be placed
 * into the  root BorderPane.
 * 
 * @author Nemi R, Andrew D, Jad A, Seth T, Sitharthan E
 */
public class NobelPrizeDriver {
    private static APISearcher api;
    private static BorderPane  root;
    private static TopPanel    top;
    private static CenterPanel centerPanel;
    private static LeftPanel   left;
    /**
     * Class constructor.
     * @param r the root BorderPane
     * @throws java.io.IOException
     */
    public NobelPrizeDriver (BorderPane r) throws IOException{
        api         = new APISearcher();
        root        = r;
        
        top         = new TopPanel   (root);
        centerPanel = new CenterPanel(root, api.getLaureateData());
        left        = new LeftPanel  (root, centerPanel, api);
        
        VBox right = new VBox();
        root.setRight(right);
    }
    /**
     * Runs the GUI.
     * @param stage the main stage
     * @throws java.io.IOException 
     */
    public void runGUI(Stage stage) throws IOException {
        // Show the stage
        Scene scene = new Scene(root, 1024, 768);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show(); 
    }
}
