package com.vipapp.appmark2.item;

public class StringSelectableItem {
    private boolean selected;
    private String text;

    public StringSelectableItem(String text, boolean selected){
        this.text = text;
        this.selected = selected;
    }

    public String getText() {
        return text;
    }

    public void setSelected(boolean selected){
        this.selected = selected;
    }
    public boolean isSelected() {
        return selected;
    }
}
