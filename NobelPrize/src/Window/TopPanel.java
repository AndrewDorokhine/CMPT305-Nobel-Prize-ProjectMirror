package Window;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

/**
 * Top panel of the root BorderPane, holds the title Image.
 * 
 * @author Nemi R, Andrew D, Jad A, Seth T, Sitharthan E
 */
public class TopPanel {
    /**
     * Class attribute variables.
     */
    private final ImageView  titleBanner;
    private final BorderPane root;
    /**
     * Class constructor.
     * @param r root BorderPane
     */
    public TopPanel(BorderPane r) {
        titleBanner = new ImageView(new Image("file:title.png"));
        root        = r;
        updateDisplay();
    }
    /**
     * Updates the root BorderPane.
     */
    private void updateDisplay() {
        root.setTop(titleBanner);
    }
}
