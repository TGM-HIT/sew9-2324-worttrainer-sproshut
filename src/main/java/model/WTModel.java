package model;

import model.exceptions.WTIOException;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class WTModel {
    private int correct;    // Stats
    private int asked;
    private final WTList entryList;
    private final WTInOut inOut;

    public WTModel() {
        entryList = new WTList();
        inOut = new WTInOut(entryList);
        correct = 0;
        asked = 0;
    }

    /**
     * Reset total asked
     */
    public void resetTotal() {
        asked = 0;
    }

    /**
     * Reset correct answers
     */
    public void resetCorrect() {
        correct = 0;
    }

    /**
     * Increase total asked
     */
    public void increaseAsked() {
        asked++;
    }

    /**
     * Increase correct answers
     */
    public void increaseCorrect() {
        correct++;
    }

    /**
     * @return Total asked
     */
    public int getAsked() {
        return asked;
    }

    /**
     * @return Correct answers
     */
    public int getCorrect() {
        return correct;
    }

    /**
     * @param answer Answer to check if it matches with current word
     */
    public boolean checkAnswer(String answer) {
        return entryList.currentEntry().getWord().equals(answer);
    }

    /**
     * @return Next entry
     */
    public WTEntry nextEntry() {
        entryList.moveNext();
        return entryList.currentEntry();
    }

    /**
     * Get image from URL and resize it
     * @param urlObj Load image from URL
     * @return Resized image object
     */
    public Image getImage(URL urlObj){
        ImageIcon icon = new ImageIcon(urlObj);
        Image image = icon.getImage(); // Get Image
        image = image.getScaledInstance(300, 300,  Image.SCALE_FAST); // Resize image
        return image;
    }

    /**
     * Validate URL and return URL object
     * @param url URL as string
     * @return Checked URL object
     */
    public URL getUrlObj(String url) {
        URL urlObj;
        try {
            urlObj = new URL(url);
        } catch (MalformedURLException e) {
            return null;
        }
        return urlObj;
    }

    /**
     * Load word list from file
     */
    public void loadWordList() throws WTIOException {
        inOut.loadWordList();
    }

    /**
     * Save word list to file
     */
    public void saveWordList() throws WTIOException {
        inOut.saveWordList();
    }
}
