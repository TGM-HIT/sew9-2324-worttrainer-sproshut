package model;

import java.util.ArrayList;

public class WTList {
    private int index;
    private final ArrayList<WTEntry> entryList;

    public WTList() {
        index = 0;
        entryList = new ArrayList<>();
    }

    public void moveNext() {
        if (index >= entryList.size() - 1) index = 0;
        else index++;
    }

    public WTEntry currentEntry() {
        if (entryList.isEmpty()) return null;
        return entryList.get(index);
    }

    public void addEntry(WTEntry entry) {
        entryList.add(entry);
    }

    public void deleteEntry(int index) {
        if (index < 0 || index >= entryList.size()) return;
        entryList.remove(index);
    }

    public void deleteEntry(WTEntry entry) {
        entryList.remove(entry);
    }

    public void deleteEntry(String word) {
        entryList.removeIf(entry -> entry.getWord().equals(word));
    }

    public ArrayList<WTEntry> getEntryList() {
        return entryList;
    }
}
