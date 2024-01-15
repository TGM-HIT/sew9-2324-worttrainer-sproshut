package controller;

import model.WTEntry;
import model.WTInOutGSON;
import model.WTModel;
import model.exceptions.InvalidURLException;
import model.exceptions.InvalidWordException;
import model.exceptions.WTIOException;
import view.WTView;
import view.WTWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URL;
import java.util.ArrayList;

public class WTControl implements WindowListener, ActionListener {
    private final WTView view;
    private final WTModel model;

    public WTControl(){
        view = new WTView(this);
        model = new WTModel(new WTInOutGSON());
        new WTWindow(view, this);
    }

    public static void main(String[] args) {
        new WTControl();
    }

    /**
     * Handles all button clicks
     * @param e Button event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd) {
            case "reset_total": // Reset asked statistic
                view.setTotal(0);
                model.resetTotal();
                break;
            case "reset_correct":   // Reset correct statistic
                view.setCorrect(0);
                model.resetCorrect();
                break;
            case "add_entry":   // Add word entry
                ArrayList<String> entry = view.promptWordEntry();
                if (entry == null) return;
                try {
                    model.addWordEntry(entry.get(0), entry.get(1));
                } catch (InvalidURLException | InvalidWordException ex) {
                    view.showError(ex.getMessage());
                }
                break;
            case "enter":   // Check answer
                String answer = view.getAnswer().replace(" ", "");
                if (answer.isEmpty()) {
                    view.showError("Empty answer");
                    return;
                }
                if (model.checkAnswer(answer)){
                    model.increaseCorrect();
                    view.setCorrect(model.getCorrect());
                }
                model.increaseAsked();
                view.setTotal(model.getAsked());
                updateImage(model.nextEntry().getURL());
                view.resetAnswer();
                break;
        }
    }

    /**
     * Updates image box
     * @param url URL of image
     */
    private void updateImage(String url) {
        URL url_obj = model.getUrlObj(url);
        if (url_obj != null) {
            view.toggleReset();
            view.toggleInput();
            view.setImage(null);
            Thread t = new Thread(() -> {   // Perform load and update in separate thread
                Image img = model.getImage(url_obj);
                SwingUtilities.invokeLater(() -> {
                    view.setImage(img);
                    view.toggleInput();
                    view.toggleReset();
                });
            });
            t.start();
        }
    }

    /**
     * Load word list on window open
     * @param windowEvent Window event
     */
    @Override
    public void windowOpened(WindowEvent windowEvent) {
        try {
            model.loadWordList();
        } catch (WTIOException e) {
            view.showError(e.getMessage());
            return;
        }
        view.loadComplete(true);
        WTEntry entry = model.nextEntry();
        if (entry != null) updateImage(entry.getURL());
    }

    /**
     * Save word list on window close
     * @param windowEvent Window event
     */
    @Override
    public void windowClosing(WindowEvent windowEvent) {
        try {
            model.saveWordList();
        } catch (WTIOException e) {
            view.showError(e.getMessage());
        }
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}
