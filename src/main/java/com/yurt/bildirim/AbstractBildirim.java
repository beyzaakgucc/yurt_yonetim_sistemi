package com.yurt.bildirim;

import java.time.LocalDateTime;

public abstract class AbstractBildirim {

    protected String mesaj;
    protected LocalDateTime tarih;

    public AbstractBildirim() {
        this.tarih = LocalDateTime.now();
    }

    public abstract String hedef();

    public void gonder() {
        System.out.println(
                "🚀 [" + tarih + "] " + hedef() + " -> " + mesaj
        );
    }
}
