package controller;

import model.WTModel;
import view.WTView;
import view.WTWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WTControl implements WindowListener, ActionListener {
    private WTView view;
    private WTModel model;

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
                if (answer.equals("")) {
                    view.showError("Empty answer");
                    return;
                }
                if (model.checkAnswer(answer)) view.setCorrect(view.getCorrect() + 1);
                view.setTotal(view.getTotal() + 1);
                // updateImage(model.next().getURL());
                view.resetAnswer();
                break;
        }
    }

    public void updateImage(String url) {
        // TODO
    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {
        // TODO: Load all data
    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        // TODO: Save all data
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
