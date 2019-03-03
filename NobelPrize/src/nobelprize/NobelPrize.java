package nobelprize;

import com.google.gson.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * MAIN
 *
 * @author Nemi R, Andrew D, Jad A, Seth T, Sitharthan E
 */
public class NobelPrize {
    /**
     * Main call: currently runs a test to get and display information from
     *            the Nobel Prize API.
     * @param args 
     */
    public static void main(String[] args) throws IOException {
        NobelPrizeDriver program = new NobelPrizeDriver();
        program.runTest();
    }
}
