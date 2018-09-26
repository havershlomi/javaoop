package com.mmn14.q2;

import javax.swing.*;
import java.awt.*;

public class DictionaryWindow extends JFrame {

    private Dictionary dic = new Dictionary();

    public DictionaryWindow() {

        //set dictionary form and all its parts
        setLayout(new BorderLayout());
        DefaultListModel<Entry> listEntries = new DefaultListModel<>();
        JList<Entry> list = new JList<>(listEntries);
        add(new Controllers(dic, listEntries, list), BorderLayout.SOUTH);

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(list);

        Container contentPane = this.getContentPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);


        setSize(400, 700);
        setVisible(true);
    }
}
