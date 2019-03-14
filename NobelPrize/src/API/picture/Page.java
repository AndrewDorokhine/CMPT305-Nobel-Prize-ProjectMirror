package API.picture;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Class which has some information about the picture, the actual picture is 
 * retrieved from the Picture class.
 * @author Andrew D, Jad A, Nemi R, Seth T, Sitharthan E
 */
public class Page {
    private int pageid;
    private int ns;
    private String title;
    private String pageimage;
    Picture thumbnail = null;
    /**
     * Getter for the pageid
     * @return int
     */
    public String getPageId() {
        return pageid + "";
    }
    /**
     * getter for the ns (# of shares?)
     * @return int
     */
    public int getNs() {
        return ns;
    }
    /**
     * Getter for the title.
     * @return String
     */
    public String getTitle() {
        return title + "";
    }
    /**
     * Getter for the pageimage
     * @return String
     */
    public String getPageImage() {
        return pageimage + "";
    }
    /**
     * Getter for the Image we want from the Picture class.
     * @return Buffered Image
     * @throws IOException 
     */
    public BufferedImage getImage() throws IOException {
        return thumbnail.getImage();    
    }
    /**
     * Getter for the dimensions from the Picture class.
     * @return int[] WxH
     */
    public int[] getDimensions() {
        int [] arr = {thumbnail.getWidth(), thumbnail.getHeight()};
        return arr;
    }
    /**
     * For getting a string representation of the data.
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Page ID: ");
        builder.append(getPageId());
        builder.append("\n");
        builder.append("Title: ");
        builder.append(getTitle());
        builder.append("\n");
        builder.append(thumbnail.toString());
        
        return builder.toString();
    } 
    
}
