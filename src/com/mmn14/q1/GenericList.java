package com.mmn14.q1;

import com.sun.tools.javah.Gen;

public class GenericList<T> {

    private GenericNode<T> head;
    private GenericNode<T> tail;

    public GenericList() {
    }

    public GenericNode<T> getHead() {
        return head;
    }

    public GenericNode<T> getTail() {
        return tail;
    }

    public void add(T value) {
        if (head == null) {
            head = new GenericNode<>(value);
            tail = head;
        } else {
            GenericNode<T> newNode = new GenericNode<>(value);
            tail.setNext(newNode);
            tail = newNode;
        }
    }

    public T remove() throws EmptyListException {
        if (head == null) {
            throw new EmptyListException();
        } else {
            T val = head.getValue();
            head = head.getNext();
            return val;
        }
    }

    @Override
    public String toString() {
        GenericNode<T> current = head;
        String res = "";
        while (current != null) {
            res += current.getValue() + " -> ";
            current = current.getNext();
        }
        res += " null";
        return res;
    }
}
