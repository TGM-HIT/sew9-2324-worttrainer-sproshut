package controller;

import model.WTEntry;
import model.WTModel;
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

public class WTControl implements WindowListener, ActionListener {
    private final WTView view;
    private final WTModel model;

    public WTControl(){
        view = new WTView(this);
        model = new WTModel();
        new WTWindow(view, this);
    }

    public static void main(String[] args) {
        new WTControl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd) {
            case "reset_total":
                view.setTotal(0);
                model.resetTotal();
                break;
            case "reset_correct":
                view.setCorrect(0);
                model.resetCorrect();
                break;
            case "enter":
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

    private void updateImage(String url) {
        URL url_obj = model.getUrlObj(url);
        if (url_obj != null) {
            view.toggleReset();
            view.toggleInput();
            view.setImage(null);
            Thread t = new Thread(() -> {
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
