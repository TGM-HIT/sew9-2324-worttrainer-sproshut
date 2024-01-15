package model;

import model.exceptions.InvalidURLException;
import model.exceptions.InvalidWordException;

import java.net.MalformedURLException;
import java.net.URL;

public class WTEntry {
    private String word;
    private String url;
    private transient boolean isCorrect;

    public WTEntry(String word, String url) {
        setWord(word);
        setURL(url);
        isCorrect = false;
    }

    /**
     * Checks if the URL is valid
     * @param url URL to check
     * @return True if valid, false if not
     */
    private boolean checkURL(String url) {
        if (url == null || url.isEmpty()) return false;
        url = url.toLowerCase();
        try {
            new URL(url);
        } catch (MalformedURLException e) {
            return false;
        }
        return (url.endsWith(".jpg") || url.endsWith(".jpeg") || url.endsWith(".png"));
    }

    /**
     * Checks if the word is INVALID
     * @param word Word to check
     * @return True if INVALID, false if VALID
     */
    private boolean isWordInvalid(String word) {
        return word == null || word.isEmpty();
    }

    /**
     * @return Image URL
     */
    public String getURL() {
        return url;
    }

    /**
     * @param url Image URL
     */
    public void setURL(String url) {
        if(!checkURL(url)) throw new InvalidURLException("Invalid URL. Make sure not to use subdomains and add picture path (png, jpg, jpeg) at the end");
        this.url = url;
    }

    /**
     * @return Word
     */
    public String getWord() {
        return word;
    }

    /**
     * @param word Word
     */
    public void setWord(String word) {
        if(isWordInvalid(word)) throw new InvalidWordException("Empty word entered");
        this.word = word;
    }

    /**
     * Already answered correctly
     * @param b True if already answered correctly, false if not
     */
    public void setCorrect(boolean b) {
        isCorrect = b;
    }

    /**
     * Was this word already answered correctly?
     */
    public boolean isCorrect() {
        return isCorrect;
    }

    @Override
    public String toString() {
        return word + "|" + url;
    }
}
