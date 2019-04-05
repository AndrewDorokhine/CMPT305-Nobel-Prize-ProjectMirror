package Window;

import API.laureate.Laureate;
import API.laureate.PrizePlus;
import API.picture.ImageData;
import java.io.File;
import java.io.IOException;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * An item for the CenterList ListView, contains an image and some text.
 * 
 * @author Nemi R, Andrew D, Jad A, Seth T, Sitharthan E
 */
public class ListNode {
    /** 
     * Class attribute variables.
     */
    private final HBox     node;
    private final Laureate laureate;
    private CenterList     centerList;
    private CenterPanel    centerPanel;
    /**
     * Class constructor.
     * @param l laureate to display 
     * @throws java.io.IOException 
     */
    public ListNode (Laureate l, CenterList c, CenterPanel p) throws IOException {
        centerList = c;
        centerPanel = p;
        laureate = new Laureate(l);
        node     = new HBox();
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
     * a some text(WILL BE LINKS LATER).
     */
    private void initNode() throws IOException {
        node.setPrefWidth(700);
        //node.setStyle("-fx-padding: 2px;");
        node.getChildren().add(getImageFromFile());
        node.getChildren().add(new Text(createInfoString()));
        
        node.setOnMouseClicked(new EventHandler<MouseEvent>(){
 
          @Override
          public void handle(MouseEvent arg0) {
             CenterIndividual current = new CenterIndividual(laureate, centerList, centerPanel);
             current.setShow();
          }
 
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
        info.append(" ");
        info.append(laureate.getSurname());
        info.append("\n");
        // Add born/died
        info.append("Born: ");
        info.append(laureate.getBorn());
        info.append(", ");
        info.append(laureate.getBornCountry());
        info.append("\n");
        if (!(laureate.getDiedCountry().equals("null"))) {
            info.append("Died: ");
            info.append(laureate.getDied());
            info.append(", ");
            info.append(laureate.getDiedCountry());
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
     * Gets an image from file, or uses the default no image found.
     * @return ImageView of the image.
     */
    private ImageView getImageFromFile() {
        ImageView imageView;
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
        //imageView.setPreserveRatio(true);
        return imageView;
    }
    /**
     * Gets a laureate image from the MediaWiki API online.
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
