package API.picture;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import javafx.scene.image.Image;

/**
 * Data structure for getting only 1 (currently) image from the Mediawiki API 
 * at "https://www.mediawiki.org/wiki/MediaWiki".
 * @author Nemi R, Andrew D, Jad A, Seth T, Sitharthan E
 */
public class ImageData {
    Image image;
    int width;
    int height;
    /**
     * Constructor;
     */
    public ImageData() {}
    /**
     * getter for the Image.
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
    public void search(String first, String last) throws IOException {
        // Only seems to work with a first and last name without a middle name.
        StringBuilder url = new StringBuilder();
        url.append("https://en.wikipedia.org");
        url.append("/w/api.php?action=query&format=json&prop=pageimages&titles=");
        url.append(first);
        url.append("%20");
        url.append(last);
        url.append("&pithumbsize=300");
        String json = getJson (url.toString());
        // Parse with GSON
        Gson gson = new Gson();
        ImageResult result = gson.fromJson(json, ImageResult.class);
        
        // Bit iffy making a new image everytime, but there will only be 1 anyway.
        for (String key : result.query.pages.keySet()) {
            if (key.equals("-1") || result.query.pages.get(key).thumbnail == null) {
                width = 0;
                height = 0;
                image = new Image("file:no-image-found.jpg");
                return;
            }
            Page p = result.query.pages.get(key);
            String address = p.thumbnail.source;
            width  = p.thumbnail.width;
            height = p.thumbnail.height;
            image = new Image(address);
            
        }
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
