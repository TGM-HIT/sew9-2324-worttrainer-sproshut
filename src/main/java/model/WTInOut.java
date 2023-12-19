package model;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.exceptions.WTIOException;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class WTInOut {
    private static final String FILENAME = "worttrainer.json";
    private WTList list;

    public WTInOut(WTList list) {
        this.list = list;
    }

    public void saveWordList() throws WTIOException {
        Gson gson = new Gson();
        String json = gson.toJson(list.getEntryList());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME))) {
            writer.write(json);
        } catch (IOException e) {
            throw new WTIOException("Error saving word list");
        }
    }

    public void loadWordList() throws WTIOException {
        if (!new File(FILENAME).isFile()) return;

        String json;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            json = reader.readLine();
        } catch (IOException e) {
            throw new WTIOException("Error loading word list");
        }

        Gson gson = new Gson();
        Type wtListType = new TypeToken<ArrayList<WTEntry>>() {}.getType();
        ArrayList<WTEntry> wtArrayList = gson.fromJson(json, wtListType);
        for (WTEntry entry : wtArrayList) {
            list.addEntry(entry);
        }
    }
}
