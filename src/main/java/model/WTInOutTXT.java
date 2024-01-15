package model;

import model.base.WTBaseIO;
import model.exceptions.WTIOException;

import java.io.*;

public class WTInOutTXT implements WTBaseIO {
    private static final String FILENAME = "worttrainer.txt";  // Output file
    private WTList list;

    @Override
    public void setWordList(WTList wordList) {
        this.list = wordList;
    }

    @Override
    public void saveWordList() throws WTIOException {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(FILENAME))) {
            for (WTEntry word : list.getEntryList()) {  // Get each entry and write it as a separate line
                out.write(word.toString() + "\n");
            }
        } catch (IOException e) {
            throw new WTIOException("Error saving word list");
        }
    }

    @Override
    public void loadWordList() throws WTIOException {
        try (BufferedReader in = new BufferedReader(new FileReader(FILENAME))) {
            String text; // Current read line
            String[] parts;  // Line split into parts
            while ((text = in.readLine()) != null) { // Try to read next line
                parts = text.split("\\|");   // Split by "|" --> wort|url
                if (parts.length == 2){
                    list.addEntry(new WTEntry(parts[0], parts[1]));
                }
            }
        }
        catch (IOException e) {
            throw new WTIOException("Error loading word list");
        }
    }
}
