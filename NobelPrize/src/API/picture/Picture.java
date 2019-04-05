package API.picture;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 * Class modeling a picture, has a source URL and dimensions.
 * 
 * @author Nemi R, Andrew D, Jad A, Seth T, Sitharthan E
 */
class Picture {
    /**
     * Class attribute variables.
     */
    String source;
    int width;
    int height;
    /**
     * Getter for the source.
     * @return String
     */
    public String getSource() {
        return source + "";
    }
    /**
     * Getter for the width
     * @return int
     */
    public int getWidth() {
        return width;
    }
    /**
     * Getter for the height.
     * @return int
     */
    public int getHeight() {
        return height;
    }
    /**
     * Gets the image from the API using the source string.
     * @return BufferedImage
     * @throws IOException 
     */
    public BufferedImage getImage() throws IOException {
        URL url = new URL(getSource());
        BufferedImage image = ImageIO.read(url);
        return image;  
    }
    /**
     * For getting a string representation of the data.
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("URL: ");
        builder.append(getSource());
        builder.append("\n");
        builder.append("Width/height: ");
        builder.append(getWidth());
        builder.append("/");
        builder.append(getHeight());
        builder.append("\n");
        
        return builder.toString();
    } 
}
