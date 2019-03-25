package API;

import API.prize.PrizeData;
import API.country.CountryData;
import API.laureate.Laureate;
import API.laureate.LaureateData;
import API.picture.ImageData;
import API.prize.Category;
import API.prize.PrizeLaureate;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Contains methods for getting information about Nobel Prize laureates from the
 * Nobel Prize API (https://nobelprize.readme.io/) and Images for the 
 * MediaWiki API (https://www.mediawiki.org/wiki/MediaWiki).
 * 
 * @author Nemi R, Andrew D, Jad A, Seth T, Sitharthan E
 */
public class APISearcher {
    public final PrizeData    prizeData;
    public final CountryData  countryData;
    public final LaureateData laureateData;
    /**
     * Constructor.
     * @throws java.io.IOException
     */
    public APISearcher() throws IOException {
        prizeData   = new PrizeData();
        countryData = new CountryData();
        laureateData = new LaureateData();
        HashMap test = (HashMap) laureateData.getData();
        System.out.println();
    }
    /**
     * Searches the prizeData by category.
     * @param searchTerm String to be searched
     * @return Category object
     */
    public Category searchPrizeByCategory(String searchTerm) {
        Map<String, Category> copy = (HashMap) prizeData.getData();
        return copy.get(searchTerm);
    }
    /**
     * Searches the prizeData by year.
     * @param searchTerm String to be searched
     * @return Map categories as keys and a list of laureates as the values
     */
    public Map searchPrizeByYear (String searchTerm) {
        Map<String, Category> copy   = (HashMap) prizeData.getData();
        Map<String, List> result = new HashMap();
        
        for (String c : copy.keySet()) {
            Category category = copy.get(c); 
            List<PrizeLaureate> laureates = new ArrayList((List) category.getData().get(searchTerm));
            result.put(c, laureates);
        }
        
        return result;
    }
    /**
     * Gets an image from the MediaWiki API.
     * @param first first name
     * @param last last name
     * @return ImageData object
     * @throws IOException 
     */
    public ImageData getImage(String first, String last) throws IOException {
        ImageData picture = new ImageData();
        picture.search(first, last);
        return picture;
    }
}
