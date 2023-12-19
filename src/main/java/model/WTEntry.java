package model;

import model.exceptions.InvalidURLException;
import model.exceptions.InvalidWordException;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

public class WTEntry {
    private String word;
    private String url;

    public WTEntry(String word, String url) {
        setWord(word);
        setURL(url);
    }

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

    private boolean isWordInvalid(String word) {
        return word == null || word.isEmpty();
    }

    public String getURL() {
        return url;
    }

    public void setURL(String url) {
        if(checkURL(url)) throw new InvalidURLException("Invalid URL. Make sure not to use subdomains and add picture path (png, jpg, jpeg) at the end");
        this.url = url;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        if(isWordInvalid(word)) throw new InvalidWordException("Empty word entered");
        this.word = word;
    }
}
