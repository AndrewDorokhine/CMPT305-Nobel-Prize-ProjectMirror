package Window;

import API.laureate.Laureate;
import API.laureate.PrizePlus;
import java.io.File;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Contains functions regarding the detailed view of a laureate when their
 * search result is clicked/selected by the user. This includes birth data,
 * death data, related countries, prizes won and dates, and formatting with
 * the use of StringBuilder.
 * 
 * @author Nemi R, Andrew D, Seth T, Sitharthan E
 */
public class CenterIndividual {
    /**
     * Class attribute variables.
     */
    private final Laureate laureate;
    private final CenterList     previous;
    private final CenterPanel    centerPanel;
    private final VBox           center;
    private final HBox           top;
    private final ImageView      picture;
    private final Text           description;
    private final Text           bottom;
    
    /**
     * Constructor. Creates the detailed information page when a laureate
     * is clicked on in the search results.
     * @param l The laureate's information, used to populate CenterPanel
     * @param p Search results from the previous page, to be re-accessed when
     * the user clicks the "back" button
     * @param c Recreates the page formatting for the previous page, accessed
     * by previous when the user clicks the back button.
     */
    public CenterIndividual(Laureate l, CenterList p, CenterPanel c) {
        laureate    = l;
        previous    = p;
        centerPanel = c;
        center      = new VBox();
        top         = new HBox();
        top.setPadding(new Insets(10,10,10,10));
        picture     = getImageFromFile();
        description = getDescription();
        bottom      = getBottom();
    }
    /**
     * Creates the back button and implements the mouse event handler.
     */
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
    /**
     * Uses laureate information and getter methods to populate basic laureate
     * information, such as birth/death dates, countries of birth/death,
     * and which prizes were won in which years.
     * @return JavaFX Text object
     */
    private Text getDescription() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n\t");
        builder.append("Name: ");
        builder.append(laureate.getFirstname());
        /**
         * condition to omit display if there is no last name (common with
         * associations like amnesty international)
         */
        if(!laureate.getSurname().equals("null")) {
            builder.append(" ");
            builder.append(laureate.getSurname());
        }        
        builder.append("\n\n\t");
        // Condition to omit display if there is no birthdate
        if(!laureate.getBorn().equals("0000-00-00")) {
            builder.append("Born in: ");
            builder.append(laureate.getBorn());
            if(!laureate.getBornCountry().equals("null")) {
                builder.append(" in ");
                builder.append(laureate.getBornCountry());
            }    
            builder.append("\n\t");
        } 
        // Condition to omit display if there is no death date
        if(!laureate.getDied().equals("0000-00-00")) {
            builder.append("Died in: ");
            builder.append(laureate.getDied());
            if(!laureate.getDiedCountry().equals("null")) {
                builder.append(" in ");
                builder.append(laureate.getDiedCountry());
            }        
            builder.append("\n\t");
        }
        builder.append("\n\t");
  
        builder.append("Prize won: ");
        builder.append(laureate.getPrizes().get(0).getCategory());
        builder.append(" in ");
        builder.append(laureate.getPrizes().get(0).getYear());
        /**
         * In the few cases of laureates who've won more than one prize
         * (e.x. John Bardeen) this segment displays them by connecting them 
         * with "and"
         */        
        if(laureate.getPrizes().size() != 1) {
            for(int i = 0; i<laureate.getPrizes().size()-1; i++) {
                builder.append(" and ");
                builder.append(laureate.getPrizes().get(i+1).getCategory());
                builder.append(" in ");
                builder.append(laureate.getPrizes().get(i+1).getYear());
            }
            
        }
                  
        Text end = new Text(builder.toString());
        end.setFont(Font.font("Times New Roman", 20));
        end.setWrappingWidth(525);
        return end;
    }
    /**
     * Prints out the motivation of the laureate selected.
     * @return JavaFX Text object
     */
    private Text getBottom () {
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        for (PrizePlus p : laureate.getPrizes()) {
            // condition to omit display if there is no motivation
            if(!p.getMotivation().equals("null")) {                
                builder.append("Motivation: ");
                builder.append(p.getMotivation());
                builder.append("\n");
            }
        }
        
        Text end = new Text(builder.toString());
        end.setFont(Font.font("Times New Roman", 18));
        end.setWrappingWidth(600);
        return end;
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
        imageView.setFitHeight(400); 
        imageView.setFitWidth(300); 
        //imageView.setPreserveRatio(true);
        return imageView;
    }
}
