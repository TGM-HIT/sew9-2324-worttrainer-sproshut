package model.base;

import model.WTList;
import model.exceptions.WTIOException;

/**
 * Interface for IO classes
 */
public interface WTBaseIO {
    /**
     * Set word list to save to / load from
     * @param wordList WTList to be set
     */
    void setWordList(WTList wordList);

    /**
     * Save word list
     * @throws WTIOException
     */
    void saveWordList() throws WTIOException;

    /**
     * Load word list
     * @throws WTIOException
     */
    void loadWordList() throws WTIOException;
}
