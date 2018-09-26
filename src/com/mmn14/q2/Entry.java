package com.mmn14.q2;

public class Entry {
    private String key;
    private String value;

    //presenting key pair value for JList model
    public Entry(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return key + "->" + value;
    }
}
