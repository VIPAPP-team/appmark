package com.vipapp.appmark2.item;

public class StringData {
    private int cursor_start;
    private String string;

    public StringData(String string, int cursor_start) {
        this.string = string;
        this.cursor_start = cursor_start;
    }

    public String getString() {
        return this.string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public int getCursorStart() {
        return this.cursor_start;
    }

    public void setCursorStart(int cursor_start) {
        this.cursor_start = cursor_start;
    }
}