package nobelprizemain;

import API.laureate.Laureate;
import API.laureate.PrizePlus;
import java.io.File;
import java.io.IOException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 *
 * @author Nemi
 */
public class ListNode {
    // Attribute variables
    private HBox node;
    private Laureate laureate;
    /**
     * Constructor class.
     * @param l laureate to display 
     */
    public ListNode (Laureate l) throws IOException {
        laureate = new Laureate(l);
        node = new HBox();
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
    private void initNode() {
        node.setPrefWidth(600);
        node.getChildren().add(getImageFromFile());
        node.getChildren().add(new Text(createInfoString()));
    }
    
    private String createInfoString() {
        StringBuilder info = new StringBuilder();
        info.append("Name: ");
        info.append(laureate.getFirstname());
        info.append(" ");
        info.append(laureate.getSurname());
        info.append("\n");
        info.append("Prize(s): ");
        for (PrizePlus p : laureate.getPrizes()) {
            info.append(p.getCategory());
            info.append(" ");
        }
        info.append("\n");
        
        return info.toString();
    }
    

    
    private String getIDForImage() {
       
        return (laureate.getID());
    }
    
    private ImageView getImageFromFile() {
        ImageView imageView;
        String fName = "images/" + getIDForImage() + ".jpg";
        System.out.println(fName);
        File file = new File(fName);
        if (file.exists()) {
            fName = "file:" + fName;
            imageView = new ImageView(new Image(fName));
        } else {
            imageView = new ImageView(new Image("file:no-image-found.jpg"));
        }
                
           
        imageView.setX(0); 
        imageView.setY(0); 
        imageView.setFitHeight(200); 
        imageView.setFitWidth(200); 
        imageView.setPreserveRatio(true);
        return imageView;
    }
}
