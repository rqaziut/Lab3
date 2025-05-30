package org.translation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * This class provides the service of converting language codes to their names.
 */
public class LanguageCodeConverter {

    // TODO Task: pick appropriate instance variables to store the data necessary for this class
    private final Map<String, String> codeToLanguage = new HashMap<>();
    private final Map<String, String> languageToCode = new HashMap<>();
    private int numlanguages;
    /**
     * Default constructor which will load the language codes from "language-codes.txt"
     * in the resources folder.
     */

    public LanguageCodeConverter() {
        this("language-codes.txt");
    }

    /**
     * Overloaded constructor which allows us to specify the filename to load the language code data from.
     * @param filename the name of the file in the resources folder to load the data from
     * @throws RuntimeException if the resource file can't be loaded properly
     */
    public LanguageCodeConverter(String filename) {

        try {
            List<String> lines = Files.readAllLines(Paths.get(getClass()
                    .getClassLoader().getResource(filename).toURI()));

            // TO DO Task: use lines to populate the instance variable
            //           tip: you might find it convenient to create an iterator using lines.iterator()
            Iterator<String> iter = lines.iterator();

            if (iter.hasNext()) {
                iter.next();
            }

            while (iter.hasNext()) {
                String line = iter.next();
                String[] parts = line.split("\t");

                if (parts.length >= 2) {
                    String languageName = parts[0].strip();
                    String code = parts[1].strip();

                    codeToLanguage.put(code, languageName);
                    languageToCode.put(languageName.toLowerCase(), code);
                }
            }
            numlanguages = codeToLanguage.size();
        }
        catch (IOException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }

    }

    /**
     * Returns the name of the language for the given language code.
     * @param code the language code
     * @return the name of the language corresponding to the code
     */
    public String fromLanguageCode(String code) {
        // TODO Task: update this code to use your instance variable to return the correct value
        return codeToLanguage.getOrDefault(code, "Unknown Language");
    }

    /**
     * Returns the code of the language for the given language name.
     * @param language the name of the language
     * @return the 2-letter code of the language
     */
    public String fromLanguage(String language) {
        // TODO Task: update this code to use your instance variable to return the correct value
        return languageToCode.getOrDefault(language.toLowerCase(), "Unknown Code");
    }

    /**
     * Returns how many languages are included in this code converter.
     * @return how many languages are included in this code converter.
     */
    public int getNumLanguages() {
        // TODO Task: update this code to use your instance variable to return the correct value
        return numlanguages;
    }
}
