package org.translation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class provides the service of converting country codes to their names.
 */
public class CountryCodeConverter {
    private static final int COUNTRYCODETEXTLINELENGTH = 4;

    // TO DO Task: pick appropriate instance variable(s) to store the data necessary for this class
    private final Map<String, String> codeToCountry = new HashMap<>();
    private final Map<String, String> countryToCode = new HashMap<>();
    private int numcountries;

    /**
     * Default constructor which will load the country codes from "country-codes.txt"
     * in the resources folder.
     */

    public CountryCodeConverter() {
        this("country-codes.txt");
    }

    /**
     * Overloaded constructor which allows us to specify the filename to load the country code data from.
     * @param filename the name of the file in the resources folder to load the data from
     * @throws RuntimeException if the resource file can't be loaded properly
     */
    public CountryCodeConverter(String filename) {

        try {
            List<String> lines = Files.readAllLines(Paths.get(getClass()
                    .getClassLoader().getResource(filename).toURI()));
            // TO DO Task: use lines to populate the instance variable(s)
            int i = 1;

            while (i < lines.size()) {
                String[] parts = lines.get(i).split("\t");
                if (parts.length >= COUNTRYCODETEXTLINELENGTH) {
                    String countryName = parts[0].trim();
                    String alpha3 = parts[2].trim();

                    codeToCountry.put(alpha3, countryName);
                    countryToCode.put(countryName.toLowerCase(), alpha3);
                    i += 1;
                }
            }
            this.numcountries = codeToCountry.size();
        }
        catch (IOException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }

    }

    /**
     * Returns the name of the country for the given country code.
     * @param code the 3-letter code of the country
     * @return the name of the country corresponding to the code
     */
    public String fromCountryCode(String code) {
        // TO DO Task: update this code to use an instance variable to return the correct value
        return codeToCountry.get(code.toUpperCase());
    }

    /**
     * Returns the code of the country for the given country name.
     * @param country the name of the country
     * @return the 3-letter code of the country
     */
    public String fromCountry(String country) {
        // TO DO Task: update this code to use an instance variable to return the correct value
        String code = countryToCode.get(country);
        return code;
    }

    /**
     * Returns how many countries are included in this code converter.
     * @return how many countries are included in this code converter.
     */
    public int getNumCountries() {
        // TO DO Task: update this code to use an instance variable to return the correct value
        return numcountries;
    }
}
