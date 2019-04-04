package Window;

import API.laureate.Laureate;
import API.laureate.PrizePlus;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author Nemi
 */
public class CenterIndividual {
    private final Laureate laureate;
    private CenterList previous;
    private CenterPanel centerPanel;
    private VBox      center;
    private HBox      top;
    private ImageView picture;
    private Text      description;
    private Text      bottom;
    
    
    public CenterIndividual(Laureate l, CenterList p, CenterPanel c) {
        laureate = l;
        previous = p;
        centerPanel = c;
        center  = new VBox();
        top     = new HBox();
        picture = getImageFromFile();
        description = getDescription();
        bottom = getBottom();
    }
    
    public void setShow() {
        Button back = new Button("Back");
        
        back.setOnMouseClicked(new EventHandler<MouseEvent>(){
 
          @Override
          public void handle(MouseEvent arg0) {
              centerPanel.getCenter().getChildren().clear();
              centerPanel.getCenter().getChildren().add(previous.getListView());
              centerPanel.update();
              //centerPanel.updateDisplay();
          }
 
      });
        
        center.setPrefWidth(600);
        top.getChildren().addAll(picture, description);
        center.getChildren().addAll(top, bottom, back);
        
        centerPanel.getCenter().getChildren().clear();
        centerPanel.getCenter().getChildren().add(center);
    }
    private Text getDescription() {
        StringBuilder builder = new StringBuilder();
        builder.append("Name: ");
        builder.append(laureate.getFirstname());
        builder.append(" ");
        builder.append(laureate.getSurname());
        builder.append("\n");
        
        return new Text(builder.toString());
    }
    /**
     * 
     * @return 
     */
    private Text getBottom () {
        StringBuilder builder = new StringBuilder();
        
        for (PrizePlus p : laureate.getPrizes()) {
            builder.append("Motivation: ");
            builder.append(p.getMotivation());
            builder.append("\n");
        }
        
        return new Text(builder.toString());
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
}
