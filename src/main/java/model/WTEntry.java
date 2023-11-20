package model;

public class WTEntry {
    private String word;
    private String url;

    public WTEntry(String word, String url) {
        this.word = word;
        this.url = url;
    }

    public String getURL() {
        return url;
    }

    public void setURL(String url) {
        this.url = url;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
