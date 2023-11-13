package controller;

import model.WTModel;
import view.WTView;
import view.WTWindow;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WTControl implements WindowListener {
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
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {

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
