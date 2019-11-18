package com.vipapp.appmark2.utils;

import java.util.ArrayDeque;

public class CustomArrayDeque<T> extends ArrayDeque<T> {
    private final int maxSize;

    public CustomArrayDeque(int i) {
        super(i);
        this.maxSize = i;
    }

    public void addFirst(T t) {
        if (this.maxSize == size()) {
            removeLast();
        }
        super.addFirst(t);
    }

    public void addLast(T t) {
        if (this.maxSize == size()) {
            removeFirst();
        }
        super.addLast(t);
    }
}