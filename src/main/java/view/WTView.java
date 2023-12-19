package view;

import controller.WTControl;

import javax.swing.*;
import java.awt.*;

public class WTView extends JPanel {
    private final JTextField answer;
    private final JLabel lImage, correct, total;
    private final JButton resetCorrect, resetTotal;
    private final Font italic = new Font(Font.SANS_SERIF, Font.ITALIC, 16);
    private final Font bold = new Font(Font.SANS_SERIF, Font.BOLD, 16);
    private final Font normal = new Font(Font.SANS_SERIF, Font.PLAIN, 16);

    public WTView(WTControl wtControl) {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel lab1 = new JLabel("Welches Wort wird unten dargestellt?");
        lab1.setFont(italic);
        this.add(lab1, gbc);

        gbc.gridy = 1;  // Next row
        answer = new JTextField();
        answer.addActionListener(wtControl);
        answer.setActionCommand("enter");
        answer.setFont(normal);
        this.add(answer, gbc);

        gbc.gridy = 2;
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 3.0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        lImage = new JLabel("Loading...");
        lImage.setHorizontalAlignment(SwingConstants.CENTER);
        lImage.setMinimumSize(new Dimension(300, 300));
        lImage.setPreferredSize(new Dimension(300, 300));
        this.add(lImage, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel lab2 = new JLabel("Richtige Wörter:");
        lab2.setFont(italic);
        this.add(lab2, gbc);
        gbc.gridx = 1;
        correct = new JLabel("0");
        correct.setHorizontalAlignment(SwingConstants.CENTER);
        correct.setFont(normal);
        this.add(correct, gbc);
        gbc.gridx = 2;
        resetCorrect = new JButton("Zurücksetzen");
        resetCorrect.addActionListener(wtControl);
        resetCorrect.setActionCommand("reset_correct");
        resetCorrect.setFont(bold);
        this.add(resetCorrect, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        JLabel lab3 = new JLabel("Anzahl Wörter:");
        lab3.setFont(italic);
        this.add(lab3, gbc);
        gbc.gridx = 1;
        total = new JLabel("0");
        total.setHorizontalAlignment(SwingConstants.CENTER);
        total.setFont(normal);
        this.add(total, gbc);
        gbc.gridx = 2;
        resetTotal = new JButton("Zurücksetzen");
        resetTotal.addActionListener(wtControl);
        resetTotal.setActionCommand("reset_total");
        resetTotal.setFont(bold);
        this.add(resetTotal, gbc);

        loadComplete(false);
    }

    public void loadComplete(boolean b) {
        for (Component cp : getComponents() ){
            if (cp instanceof JButton || cp instanceof JTextField) cp.setEnabled(b);
        }
    }

    public void setTotal(int i) {
        total.setText(Integer.toString(i));
    }

    public int getTotal() {
        return Integer.parseInt(total.getText());
    }

    public void setCorrect(int i) {
        correct.setText(Integer.toString(i));
    }

    public int getCorrect() {
        return Integer.parseInt(correct.getText());
    }

    public String getAnswer() {
        return answer.getText();
    }

    public void resetAnswer() {
        answer.setText("");
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void toggleReset() {
        resetCorrect.setEnabled(!resetCorrect.isEnabled());
        resetTotal.setEnabled(!resetTotal.isEnabled());
    }

    public void toggleInput() {
        answer.setEnabled(!answer.isEnabled());
    }

    public void setImage(Image image) {
        lImage.setIcon(image != null ? new ImageIcon(image) : null);
    }
}
