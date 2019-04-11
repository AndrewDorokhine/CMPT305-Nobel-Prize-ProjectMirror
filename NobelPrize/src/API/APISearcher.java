package API;

import API.prize.PrizeData;
import API.country.CountryData;
import API.laureate.Laureate;
import API.laureate.LaureateData;
import API.prize.Category;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Contains methods for getting information about Nobel Prize laureates from the
 * Nobel Prize API (https://nobelprize.readme.io/) and Images for the 
 * MediaWiki API (https://www.mediawiki.org/wiki/MediaWiki).
 * 
 * @author Nemi R, Andrew D, Seth T, Sitharthan E
 */
public class APISearcher {
    /**
     * Class attribute variables.
     */
    private final CountryData           countryData;
    private final PrizeData             prizeData;
    private final LaureateData          laureateData;
    
    public  final Map<String, String>   countryDataCopy;  
    public  final Map<String, Category> prizeDataCopy;
    public  final Map<String, Laureate> laureateDataCopy;   
    /**
     * Class constructor, gets JSON from the Nobel Prize API and then parses
     * the data into Maps.
     */
    public APISearcher() {
        countryData      = new CountryData();
        prizeData        = new PrizeData();
        laureateData     = new LaureateData();
        
        countryDataCopy  = countryData.getData();
        prizeDataCopy    = prizeData.getData();
        laureateDataCopy = laureateData.getData();
    }
    /**
     * Searches the laureateData for any fields per laureate that contain
     * any word in the search term.
     * @param searchTerm the string to be searched for
     * @return Map of laureates resulting from the search
     */
    public Map<String,Laureate> searchAll(String searchTerm) {
        Map<String, String> copy = laureateData.getLaureateInfo();
        Map<String, Laureate> results = new HashMap<>();
        StringTokenizer st = new StringTokenizer(searchTerm.toLowerCase());
        // Go through each token and search the laureates for similarities
        while (st.hasMoreTokens()) {
            String current = st.nextToken();
            String patternString = ".*" + current + ".*";
            Pattern pattern = Pattern.compile(patternString);
            for (String l : copy.keySet()) {
               String text = copy.get(l);
               Matcher matcher = pattern.matcher(text);
               // Add the laureate if it's a match
               if (matcher.matches()) {
                   Laureate toAdd = laureateDataCopy.get(l);
                   String name = toAdd.getFirstname() + " " + toAdd.getSurname();
                   results.put(name, toAdd);
               }
            }
        }
        return results;
    }
    
    /**
     * Searches the laureateData for any fields per laureate that contain
     * the exact words in the search term. (e.x. John Bardeen)
     * @param searchTerm the string to be searched for
     * @return Map of laureate(s) resulting from the search
     */
    
    public Map<String,Laureate> searchOne(String searchTerm) {
        Map<String, String> copy = laureateData.getLaureateInfo();
        Map<String, Laureate> results = new HashMap<>();
        String patternString = ".*" + searchTerm.toLowerCase() + ".*";
        Pattern pattern = Pattern.compile(patternString);
        for (String l : copy.keySet()) {
           String text = copy.get(l);
           Matcher matcher = pattern.matcher(text);
           // Add the laureate if it's a match
           if (matcher.matches()) {
               Laureate toAdd = laureateDataCopy.get(l);
               String name = toAdd.getFirstname() + " " + toAdd.getSurname();
               results.put(name, toAdd);
           }
        }
        return results;
    }
    
    /**
     * Checks if a country is in use by a laureate for either born/died fields
     * @param c country to check
     * @return boolean of whether the country is in use
     */
    public boolean checkIfCountryInUse(String c) {
        return laureateData.getCountriesInUse().containsKey(c);
    }
    /**
     * Gets the deep copy Map of data from the Country section of the API.
     * @return Map of the country data
     */
    public Map<String, String> getCountryData() {
        return countryDataCopy;
    }
    /**
     * Gets the deep copy Map of data from the Prize section of the API.
     * @return Map of the prize data
     */
    public Map<String, Category> getPrizeData() {
        return prizeDataCopy;
    }
    /**
     * Gets the deep copy Map of data from the Laureate section of the API.
     * @return Map of the laureate data
     */
    public Map<String, Laureate> getLaureateData() {
        return laureateDataCopy;
    }
    /**
     * Gets the keys in order from the countryData.
     * @return list of the country keysin order
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
     * @return list of the prize keysin order
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
     * @return list of the laureate keys in order
     */
    public List<String> getLaureateKeysInOrder() {
        HashMap<String, String> copy = (HashMap) getLaureateData();
        List<String> keys = new ArrayList<>(copy.size());
        keys.addAll(copy.keySet());
        Collections.sort(keys);
        return keys;
    }
}
