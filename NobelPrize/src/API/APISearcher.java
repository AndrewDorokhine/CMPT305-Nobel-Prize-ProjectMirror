package API;

import API.prize.PrizeData;
import API.country.CountryData;
import API.laureate.Laureate;
import API.laureate.LaureateData;
import API.prize.Category;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
    /**
     * Class attribute variables.
     */
    private final CountryData  countryData;
    private final PrizeData    prizeData;
    private final LaureateData laureateData;
    
    public final Map<String, String>   countryDataCopy;  
    public final Map<String, Category> prizeDataCopy;
    public final Map<String, Laureate> laureateDataCopy;   
    /**
     * Class constructor, builds the databases.
     * @throws java.io.IOException
     */
    public APISearcher() throws IOException {
        countryData  = new CountryData();
        prizeData    = new PrizeData();
        laureateData = new LaureateData();
        
        countryDataCopy  = countryData.getData();
        prizeDataCopy    = prizeData.getData();
        laureateDataCopy = laureateData.getData();
    }
    /**
     * Checks if a country is in use by a laureate for either born/died fields
     * @param c country to check
     * @return boolean of whether the country is in use
     */
    public boolean checkIfCountryInUse(String c) {
        return laureateData.countriesInUse.containsKey(c);
    }
    /**
     * Gets the deep copy Map of data from the Country section of the API.
     * @return 
     */
    public Map<String, String> getCountryData() {
        return countryDataCopy;
    }
    /**
     * Gets the deep copy Map of data from the Prize section of the API.
     * @return 
     */
    public Map<String, Category> getPrizeData() {
        return prizeDataCopy;
    }
    /**
     * Gets the deep copy Map of data from the Laureate section of the API.
     * @return 
     */
    public Map<String, Laureate> getLaureateData() {
        return laureateDataCopy;
    }
    /**
     * Gets the keys in order from the countryData.
     * @return 
     */
    public List<String> getCountryKeysInOrder() {
        HashMap<String, String> copy = (HashMap) getCountryData();
        List<String> keys = new ArrayList<>(copy.size());
        keys.addAll(copy.keySet());
        Collections.sort(keys);
        return keys;
    }
    /**
     * Gets the keys in order from the prizeData.
     * @return 
     */
    public List<String> getPrizeKeysInOrder() {
        HashMap<String, Category> copy = (HashMap) getPrizeData();
        List<String> keys = new ArrayList<>(copy.size());
        keys.addAll(copy.keySet());
        Collections.sort(keys);
        return keys;
    }
    /**
     * Gets the keys in order from the laureateData.
     * @return 
     */
    public List<String> getLaureateKeysInOrder() {
        HashMap<String, String> copy = (HashMap) getLaureateData();
        List<String> keys = new ArrayList<>(copy.size());
        keys.addAll(copy.keySet());
        Collections.sort(keys);
        return keys;
    }
}
