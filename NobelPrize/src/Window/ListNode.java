package Window;

import API.laureate.Laureate;
import API.laureate.PrizePlus;
import API.picture.ImageData;
import java.io.File;
import java.io.IOException;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * An item for the CenterList ListView, contains an image and some text
 * information about the laureate.
 * 
 * @author Nemi R, Andrew D, Seth T, Sitharthan E
 */
public class ListNode {
    /** 
     * Class attribute variables.
     */
    private final HBox        node;
    private final Laureate    laureate;
    private final CenterList  centerList;
    private final CenterPanel centerPanel;
    /**
     * Class constructor. initializes the node.
     * @param l laureate to display 
     * @param c the list of results in the list view, which can have many
     *          laureates displayed
     * @param p used to format the data to be shown when the user clicks on a 
     *          laureate. Detailed view doesn't work without this.
     */
    public ListNode (Laureate l, CenterList c, CenterPanel p) {
        centerList  = c;
        centerPanel = p;
        laureate    = new Laureate(l);
        node        = new HBox();
        
        node.setStyle("-fx-background-color: lightgrey;");
        initNode();
    }
    /**
     * Getter for the node.
     * @return HBox node
     */
    public HBox getNode() {
        return node;
    }
    /**
     * Initializes the HBox node by setting a width, adding a thumbnail, and
     * some text.
     */
    private void initNode() {
        node.setPrefWidth(700);
        node.getChildren().add(getImageFromFile());
        Text info = new Text(createInfoString());
        info.setFont(Font.font("Times New Roman", 17));
        node.getChildren().add(info);
        
        node.setOnMouseClicked((MouseEvent arg0) -> {
            CenterIndividual current = new CenterIndividual(laureate, centerList, centerPanel);
            current.setShow();
        });
    }
    /**
     * Creates a String from the given laureate to be displayed in the HBox.
     * @return String
     */
    private String createInfoString() {
        StringBuilder info = new StringBuilder();
        // Add name
        info.append("Name: ");
        info.append(laureate.getFirstname());
        /**
         * condition to omit display if there is no last name (common with
         * associations like amnesty international)
         */
        if(!laureate.getSurname().equals("null")) {
            info.append(" ");
            info.append(laureate.getSurname());
        }  
        info.append("\n");
        // Add born/died     
        // condition to omit display if there is no birth data
        if(!laureate.getBorn().equals("0000-00-00")) {
            info.append("Born: ");
            info.append(laureate.getBorn());
            if(!laureate.getBornCountry().equals("null")) {
                info.append(", ");
                info.append(laureate.getBornCountry());                
            }
            info.append("\n");
        }
        // condition to omit display if there is no death data
        if(!laureate.getDied().equals("0000-00-00")) {
            info.append("Died: ");
            info.append(laureate.getDied());
            if (!(laureate.getDiedCountry().equals("null"))) {
                info.append(", ");
                info.append(laureate.getDiedCountry());
            }
            info.append("\n");
        }
        // Add awarded prizes
        info.append("Prize(s): ");
        for (PrizePlus p : laureate.getPrizes()) {
            info.append(p.getCategory());
            info.append(" (");
            info.append(p.getYear());
            info.append(") ");
        }
        info.append("\n");
        return info.toString();
    }
    /**
     * Gets a name from the laureate to be used for either searching from file
     * or online. Strips all spaces.
     * @return String of the laureates firstname and surname
     */
    private String getNameForImage() {
        return (laureate.getFirstname() + laureate.getSurname()).replaceAll(" ", "");
    }
    /**
     * Gets a small image from file, or uses the default no image found.
     * @return ImageView of the image.
     */
    private ImageView getImageFromFile() {
        ImageView imageView;
        // Try to get the image from 2 directories. (backup is from Google API)
        String fName = "smallImages/" + getNameForImage() + ".jpg";
        String backupFName = "backupImages/" + getNameForImage().toLowerCase() + ".jpg";
        File file = new File(fName);
        File backupFile = new File(backupFName);
        
        if (file.exists()) {
            fName = "file:" + fName;
            imageView = new ImageView(new Image(fName));
        } else if (backupFile.exists()){
            backupFName = "file:" + backupFName;
            imageView = new ImageView(new Image(backupFName));
        } else {
            imageView = new ImageView(new Image("file:no-image-found.jpg"));
        }
        imageView.setX(0); 
        imageView.setY(0); 
        imageView.setFitHeight(150); 
        imageView.setFitWidth(100); 
        return imageView;
    }
    /**
     * Gets a laureate image from the MediaWiki API online. Not used in actually
     * running the program.
     * @return ImageView
     * @throws IOException 
     */
    private ImageView getImageFromOnline() throws IOException {
        ImageData imageData = new ImageData();
        String name = laureate.getFirstname() + " " + laureate.getSurname();
        imageData.search(name);

        return new ImageView(imageData.getImage());
    }
}
