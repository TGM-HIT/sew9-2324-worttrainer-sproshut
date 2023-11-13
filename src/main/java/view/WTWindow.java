package view;

import controller.WTControl;

import javax.swing.*;
import java.awt.*;

public class WTWindow extends JFrame {
    public WTWindow(WTView view, WTControl wtControl) {
        super("Worttrainer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowListener(wtControl);

        this.setContentPane(view);
        this.setPreferredSize(new Dimension(650, 500));
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }
}
