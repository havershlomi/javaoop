package com.mmn14.q2;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Controllers extends JPanel {

    private Dictionary dictionary;
    private DefaultListModel<Entry> listData;
    private JList list;
    private JButton addBtn;
    private JButton removeBtn;
    private JButton updateBtn;
    private JButton searchBtn;
    private int selectedIndex = -1;

    //Set all the Controllers for the dictionary
    public Controllers(Dictionary dictionary, DefaultListModel<Entry> listData, JList list) {

        this.dictionary = dictionary;
        this.list = list;
        this.listData = listData;
        this.list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectedIndex = e.getFirstIndex();
                updateBtn.setEnabled(true);
                removeBtn.setEnabled(true);
            }
        });

        JPanel bottomPanel = setBottomPanel();
        JPanel topPanel = setTopPanel();

        //put all the panels in place
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    //set the elements for the load and save actions and their callbacks
    private JPanel setBottomPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        //set the clear button
        JButton loadBtn = new JButton("Load Dictionary");
        loadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //open dialog
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV", "csv");
                fileChooser.setFileFilter(filter);

                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    //check if file exists
                    if (selectedFile.exists()) {
                        //load the file
                        dictionary.loadQuestions(selectedFile.getPath());
                        updateList();
                    } else {
                        JOptionPane.showMessageDialog(null, "This file doesn't exists");
                    }
                }
            }
        });
        bottomPanel.add(loadBtn, BorderLayout.CENTER);

        JButton saveBtn = new JButton("Save Dictionary");
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //open save dialog
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV", "csv");
                fileChooser.setFileFilter(filter);
                int rVal = fileChooser.showSaveDialog(null);
                if (rVal == JFileChooser.APPROVE_OPTION) {
                    // if file valid save it
                    Path selectedFile = Paths.get(fileChooser.getSelectedFile().getPath());
                    if (!selectedFile.getFileName().toString().toLowerCase().endsWith("csv")) {
                        JOptionPane.showMessageDialog(null, "Couldn't save file with different extention then CSV");
                        return;
                    }
                    //get the content of the file
                    List<String> lines = dictionary.getListOfTerms("|");

                    try {
                        Files.write(selectedFile, lines, Charset.forName("UTF-8"));
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(null, "Couldn't save file");
                    }
                }
            }
        });
        bottomPanel.add(saveBtn, BorderLayout.CENTER);

        return bottomPanel;
    }

    //set the elements for the add, update.. actions and their callbacks
    private JPanel setTopPanel() {
        JPanel topPanel = new JPanel();

        addBtn = new JButton("Add");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //get inputs
                String term = JOptionPane.showInputDialog("Please enter a term");
                if (term == null || term.length() == 0)
                    return;
                String value = JOptionPane.showInputDialog("Please enter a the meaning");
                if (value == null || value.length() == 0)
                    return;
                //add item and refresh list
                if (dictionary.search(term) == null) {
                    dictionary.add(term, value);
                    updateList();
                } else {
                    JOptionPane.showMessageDialog(null, "'" + term + "' is already found in the dictionary");
                }
            }
        });
        topPanel.add(addBtn);

        removeBtn = new JButton("Delete");
        removeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //get selected index and remove it
                Entry term = listData.get(selectedIndex);
                dictionary.remove(term.getKey());
                listData.remove(selectedIndex);
                resetSelection();
            }
        });
        topPanel.add(removeBtn);
        removeBtn.setEnabled(false);

        updateBtn = new JButton("Update");
        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //find element and update its meaning
                Entry term = listData.get(selectedIndex);

                String newValue = JOptionPane.showInputDialog("Please enter a new value", term.getValue());

                if ((newValue != null) && (newValue.length() > 0)) {
                    dictionary.update(term.getKey(), newValue);
                    term.setValue(newValue);
                }
            }
        });
        topPanel.add(updateBtn);
        updateBtn.setEnabled(false);

        searchBtn = new JButton("Search");
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //get the term from the user and show result "Case sensitive"
                String term = JOptionPane.showInputDialog("Please enter a search term");

                if ((term != null) && (term.length() > 0)) {
                    String meaning = dictionary.search(term);
                    if (meaning != null) {
                        JOptionPane.showMessageDialog(null, "Results: " + term + "->" + meaning);
                        return;
                    }
                }
                JOptionPane.showMessageDialog(null, "Couldn't find the meaning of '" + term + "'");
            }
        });
        topPanel.add(searchBtn);
        return topPanel;
    }

    //reset selection in the JLIst
    private void resetSelection() {
        selectedIndex = -1;
        updateBtn.setEnabled(false);
        removeBtn.setEnabled(false);
    }

    //update ui list
    private void updateList() {
        this.listData.clear();
        for (Entry element : dictionary.getListOfTerms()) {
            this.listData.addElement(element);
        }
    }
}
