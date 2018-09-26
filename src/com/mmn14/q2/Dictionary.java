package com.mmn14.q2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Dictionary {
    private TreeMap<String, String> dic = new TreeMap<>();

    // add an item to the dictionary
    public void add(String term, String meaning) {
        dic.put(term, meaning);
    }

    // update an item in the dictionary
    public void update(String term, String meaning) {
        dic.put(term, meaning);
    }

    //remoe an item from the dictionary
    public void remove(String term) {
        dic.remove(term);
    }

    //search an item in the dictionary
    public String search(String term) {
        return dic.get(term);
    }

    //load all the term from the file to the dictionary
    //it is possible to load only CSV files with | delimiter
    public boolean loadQuestions(String filePath) {
        dic.clear();
        Scanner input = null;
        try {

            input = new Scanner(new File(filePath));
            //I assumed that all the questions are in a valid format
            while (input.hasNextLine()) {
                String entry = input.nextLine();
                String[] entryPart = entry.split("\\|");
                this.add(entryPart[0], entryPart[1]);
            }

            return true;
        } catch (FileNotFoundException e) {
            return false;
        } finally {
            if (input != null)
                input.close();
        }
    }

    //get all the items from the dictionary for JList presentation
    public ArrayList<Entry> getListOfTerms() {
        ArrayList<Entry> terms = new ArrayList<>();
        for (String term : dic.keySet()) {
            terms.add(new Entry(term, dic.get(term)));
        }
        return terms;
    }

    //get all the items from the dictionary for for saving as CSV
    public List<String> getListOfTerms(String delimiter) {
        List<String> terms = new ArrayList<>();
        for (String term : dic.keySet()) {
            terms.add(term + delimiter + dic.get(term));
        }
        return terms;
    }
}
