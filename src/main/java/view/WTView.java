package view;

import controller.WTControl;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WTView extends JPanel {
    private final JTextField answer;
    private final JLabel lImage, correct, total;
    private final JButton resetCorrect, resetTotal, addEntry;

    public WTView(WTControl wtControl) {
        Font italic = new Font(Font.SANS_SERIF, Font.ITALIC, 16);
        Font bold = new Font(Font.SANS_SERIF, Font.BOLD, 16);
        Font normal = new Font(Font.SANS_SERIF, Font.PLAIN, 16);

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

        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        addEntry = new JButton("Wortpaar hinzufügen");
        addEntry.addActionListener(wtControl);
        addEntry.setActionCommand("add_entry");
        addEntry.setFont(bold);
        this.add(addEntry, gbc);

        loadComplete(false);
    }

    /**
     * Enable/disable buttons and text fields when window loaded
     * @param b Enable/disable
     */
    public void loadComplete(boolean b) {
        for (Component cp : getComponents() ){
            if (cp instanceof JButton || cp instanceof JTextField) cp.setEnabled(b);
        }
    }

    /**
     * Set total score
     * @param i total score
     */
    public void setTotal(int i) {
        total.setText(Integer.toString(i));
    }

    /**
     * Set correct score
     * @param i correct score
     */
    public void setCorrect(int i) {
        correct.setText(Integer.toString(i));
    }

    /**
     * @return Answer
     */
    public String getAnswer() {
        return answer.getText();
    }

    /**
     * Reset answer field
     */
    public void resetAnswer() {
        answer.setText("");
    }

    /**
     * Show error message
     * @param message Error message
     */
    public void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Enable/disable score reset buttons
     */
    public void toggleReset() {
        resetCorrect.setEnabled(!resetCorrect.isEnabled());
        resetTotal.setEnabled(!resetTotal.isEnabled());
    }

    /**
     * Ask for a word and URL to add
     * @return Word and URL, or null if empty
     */
    public ArrayList<String> promptWordEntry() {
        ArrayList<String> result = new ArrayList<>();
        JTextField wort = new JTextField();
        JTextField url = new JTextField();
        Object[] message = {
                "Wort:", wort,
                "Bild-URL:", url
        };
        int option = JOptionPane.showConfirmDialog(null, message, "Wortpaar hinzufügen", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            if (wort.getText().isEmpty() || url.getText().isEmpty()) {
                result = null;
            }
            else {
                result.add(wort.getText());
                result.add(url.getText());
            }
        }
        return result;
    }

    /**
     * Enable/disable answer field
     */
    public void toggleInput() {
        answer.setEnabled(!answer.isEnabled());
    }

    /**
     * Set image
     * @param image Image
     */
    public void setImage(Image image) {
        lImage.setIcon(image != null ? new ImageIcon(image) : null);
    }
}
