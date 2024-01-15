package model;

import model.base.WTBaseIO;
import model.exceptions.WTIOException;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class WTModel {
    private int correct;    // Stats
    private int asked;
    private final WTList entryList;
    private final WTBaseIO inOut;

    public WTModel(WTBaseIO inOut) {
        entryList = new WTList();
        this.inOut = inOut;
        this.inOut.setWordList(entryList);
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
        if (entryList.getEntryList().isEmpty()) {
            entryList.addEntry(new WTEntry("Hund", "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/golden-retriever-royalty-free-image-506756303-1560962726.jpg"));
            entryList.addEntry(new WTEntry("Cat", "https://www.wien.info/resource/image/295704/19x10/1200/630/a1a79e07fd1d150d06059b4feca64844/Sn/city-airport-train-cat-1-.jpg"));
            entryList.addEntry(new WTEntry("Flugzeug", "https://upload.wikimedia.org/wikipedia/commons/c/c8/Cessna172-CatalinaTakeOff.JPG"));
            entryList.addEntry(new WTEntry("Bus", "https://flotte.at/NewsImages-870x598/pic1642_1-wiener-linien-setzen-auf-saubere-busse.jpg"));
        }
    }

    /**
     * Save word list to file
     */
    public void saveWordList() throws WTIOException {
        inOut.saveWordList();
    }
}
