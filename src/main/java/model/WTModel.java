package model;

import model.exceptions.WTIOException;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class WTModel {
    private int correct;
    private int asked;
    private final WTList entryList;
    private final WTInOut inOut;

    public WTModel() {
        entryList = new WTList();
        inOut = new WTInOut(entryList);
        correct = 0;
        asked = 0;
    }

    public void resetTotal() {
        asked = 0;
    }

    public void resetCorrect() {
        correct = 0;
    }

    public void increaseAsked() {
        asked++;
    }

    public void increaseCorrect() {
        correct++;
    }

    public int getAsked() {
        return asked;
    }

    public int getCorrect() {
        return correct;
    }

    public boolean checkAnswer(String answer) {
        return entryList.currentEntry().getWord().equals(answer);
    }

    public WTEntry nextEntry() {
        entryList.moveNext();
        return entryList.currentEntry();
    }

    public Image getImage(URL urlObj){
        ImageIcon icon = new ImageIcon(urlObj);
        Image image = icon.getImage(); // Get Image
        image = image.getScaledInstance(300, 300,  Image.SCALE_FAST); // Resize image
        return image;
    }

    public URL getUrlObj(String url) {
        URL urlObj;
        try {
            urlObj = new URL(url);
        } catch (MalformedURLException e) {
            return null;
        }
        return urlObj;
    }

    public void loadWordList() throws WTIOException {
        inOut.loadWordList();
    }

    public void saveWordList() throws WTIOException {
        inOut.saveWordList();
    }
}
