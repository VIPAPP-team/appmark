package com.vipapp.appmark2.item;

public class ScrollChange {
    private int vert;
    private int horiz;
    private int oldVert;
    private int oldHoriz;

    public ScrollChange(int vert, int horiz, int oldVert, int oldHoriz) {
        this.vert = vert;
        this.horiz = horiz;
        this.oldVert = oldVert;
        this.oldHoriz = oldHoriz;
    }

    public int getVert() {
        return vert;
    }

    public void setVert(int vert) {
        this.vert = vert;
    }

    public int getHoriz() {
        return horiz;
    }

    public void setHoriz(int horiz) {
        this.horiz = horiz;
    }

    public int getOldVert() {
        return oldVert;
    }

    public void setOldVert(int oldVert) {
        this.oldVert = oldVert;
    }

    public int getOldHoriz() {
        return oldHoriz;
    }

    public void setOldHoriz(int oldHoriz) {
        this.oldHoriz = oldHoriz;
    }
}
