package API.picture;

import com.google.gson.Gson;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.StringTokenizer;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

/**
 * Data structure for getting only 1 (currently) image from the Mediawiki API 
 * at "https://www.mediawiki.org/wiki/MediaWiki".
 * 
 * @author Nemi R, Andrew D, Jad A, Seth T, Sitharthan E
 */
public class ImageData {
    /**
     * Class attribute variables.
     */
    private Image  image;
    private int    width;
    private int    height;
    private String URL;
    /**
     * Class constructor.c
     */
    public ImageData() {
        image  = null;
        width  = 0;
        height = 0;
        URL    = "";
    }
    /**
     * Getter for the Image.
     * @return Image
     */
    public Image getImage() {
        return image;
    }
    /**
     * Getter for both the width and height
     * @return int[]
     */
    public int[] getDimensions() {
        int[] dimensions = {width, height};
        return dimensions;
    }
    /**
     * Gets an image information from the MediaWiki API, as well as other 
     * information like dimensions, name, and page id. Uses GSON to parse the 
     * resulting JSON into the ImageData object.
     * @throws IOException 
     */
    public void search(String name) throws IOException {
        String json = formatName(name);
        // Parse with GSON
        Gson gson = new Gson();
        ImageResult result = gson.fromJson(json, ImageResult.class);
        
        for (String key : result.query.pages.keySet()) {
            if (key.equals("-1") || result.query.pages.get(key).thumbnail == null) {
                width = 0;
                height = 0;
                image = new Image("file:no-image-found.jpg");
                URL = "";
                return;
            }
            Page p = result.query.pages.get(key);
            String address = p.thumbnail.source;
            width  = p.thumbnail.width;
            height = p.thumbnail.height;
            image = new Image(address);
            
            try {
                // Copy to a file.
                BufferedImage bufferedImage = new BufferedImage (200, 300, BufferedImage.TYPE_INT_RGB);
                bufferedImage = ImageIO.read(new URL(address));
                BufferedImage croppedimage = cropImage(bufferedImage, new Rectangle(200, 300));
                String newName = name.replaceAll(" ", "");
                String fname = "C://Users/nemir/Desktop/smallImages/" + name.replaceAll(" ", "") + ".jpg";
                ImageIO.write(croppedimage, "jpg", new File(fname));
            } catch (Exception ex){
                System.out.println("Unable to get image!");
            }
        }
    }
    /**
     * Formats a name by removing all initials, periods, commas, "Sir", and
     * all spaces and return a JSON string
     * @param name the name to be searched
     * @return JSON string
     * @throws IOException 
     */
    private String formatName (String name) throws IOException {
        String current     = "";
        StringBuilder url  = new StringBuilder();
        StringTokenizer st = new StringTokenizer(name);
        
        // Append the base URL and the first part of the query
        url.append("https://en.wikipedia.org");
        url.append("/w/api.php?action=query&format=json&prop=pageimages&titles=");
        while(st.countTokens() > 1) {
            current = st.nextToken();
            // Replace all periods and commas with an empty string
            current = current.replaceAll("\\.", "");
            current = current.replaceAll(",", "");
            // Do not append if the token is an initial or "Sir"
            if (current.length() > 1 && !current.equals("Sir")) {
                System.out.print(current + " ");
                url.append(current);
                url.append("%20");
            }
        }
        // Append the last token after the same checks as above are performed
        current = st.nextToken();
        current = current.replaceAll("\\.", "");
        current = current.replaceAll(",", "");
        if (current.length() > 1) {
            url.append(current);
        }
        System.out.print(current + " ");
        System.out.println();
        // Append the last bit of the URL, get the JSON from online and return it
        url.append("&pithumbsize=325");
        return getJson (url.toString());
    }
    /**
     * Crops an Image NOT IDEAL because it uses a Rectangle.
     * @param src  Image to be cropped
     * @param rect cropped region
     * @return the cropped Image
     */
    private BufferedImage cropImage(BufferedImage src, Rectangle rect) {
      //BufferedImage dest = src.getSubimage(0, 0, rect.width, rect.height);
      //return dest;
      
        BufferedImage otherImage = src;
        BufferedImage newImage = new BufferedImage(200, 300, BufferedImage.TYPE_INT_RGB);

        Graphics g = newImage.createGraphics();
        g.drawImage(otherImage, 0, 0, 200, 300, null);
        g.dispose();
        return otherImage;
    }
    /**
     * Gets JSON string from a URL string that is passed in.
     * @param u the URL as a string
     * @return JSON string
     * @throws IOException 
     */
    public static String getJson (String u) throws IOException {
        URL url            = new URL(u);
        BufferedReader br  = new BufferedReader
                                (new InputStreamReader(url.openStream()));
        StringBuilder json = new StringBuilder();
        String line        = "";
        // Read each line from the buffer and append to the JSON string.
        while ((line = br.readLine()) != null) {
            json.append(line);
        }
        br.close();
        return json.toString();
    }
}
