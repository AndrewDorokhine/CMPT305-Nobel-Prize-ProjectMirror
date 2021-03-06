package Window;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

/**
 * Top panel of the root BorderPane, only contains the title Image.
 * 
 * @author Nemi R, Andrew D, Seth T, Sitharthan E
 */
public class TopPanel {
    /**
     * Class attribute variables.
     */
    private final ImageView  titleBanner;
    private final BorderPane root;
    /**
     * Class constructor.
     * @param r root BorderPane to add the ImageView node to
     */
    public TopPanel(BorderPane r) {
        titleBanner = new ImageView(new Image("file:title.png"));
        root        = r;
        updateDisplay();
    }
    /**
     * Updates the root BorderPane.
     * @return void
     */
    private void updateDisplay() {
        root.setTop(titleBanner);
    }
}
