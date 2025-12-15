package com.yurt.observer;

public class OgrenciObserver implements Observer {

    @Override
    public void update(String mesaj) {
        System.out.println("ÖĞRENCİ BİLDİRİMİ: " + mesaj);
    }
}
