package com.example.notebook;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class NoteBook {

    private final IntegerProperty ozu;
    private final IntegerProperty hdd;
    private final StringProperty os;
    private final StringProperty color;

    public NoteBook(int ozu, int hdd, String os, String color){
        this.ozu = new SimpleIntegerProperty(ozu);
        this.hdd = new SimpleIntegerProperty(hdd);
        this.os = new SimpleStringProperty(os);
        this.color = new SimpleStringProperty(color);
    }

    //ОЗУ
    public int getOZU() {
        return ozu.get();
    }
    public void setOzu(int ozu) {
        this.ozu.set(ozu);
    }
    public IntegerProperty ozuProperty() {
        return ozu;
    }

    //HDD
    public int getHdd() {
        return hdd.get();
    }
    public void setHdd(int hdd) {
        this.hdd.set(hdd);
    }
    public IntegerProperty hddProperty() {
        return hdd;
    }

    //OS
    public String getOS() {
        return os.get();
    }
    public void setOs(String os) {
        this.os.set(os);
    }
    public StringProperty osProperty() {
        return os;
    }

    //Цвет
    public String getColor() {
        return color.get();
    }
    public void setColor(String color) {
        this.color.set(color);
    }
    public StringProperty colorProperty() {
        return color;
    }
}
