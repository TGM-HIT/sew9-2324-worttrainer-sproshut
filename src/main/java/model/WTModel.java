package model;

public class WTModel {
    private int correct;
    private int asked;
    private final WTList entryList;
    private final WTInOut inOut;

    public WTModel() {
        entryList = new WTList();
        inOut = new WTInOut();
        correct = 0;
        asked = 0;
    }

    public void resetTotal() {
        asked = 0;
    }

    public void resetCorrect() {
        correct = 0;
    }

    public boolean checkAnswer(String answer) {
        return false;
    }

    public WTEntry nextEntry() {
        return null;    // TODO
    }
}
