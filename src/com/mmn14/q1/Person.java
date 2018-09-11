package com.mmn14.q1;

public class Person implements Comparable<Person> {
    private String name;
    private String id;
    private int yearOfBirth;

    public Person(String name, String id, int yearOfBirth) {
        this.name = name;
        this.id = id;
        this.yearOfBirth = yearOfBirth;
    }

    @Override
    public int compareTo(Person o) {
        return o.yearOfBirth - yearOfBirth;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                '}';
    }
}
