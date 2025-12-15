package com.yurt.bildirim;

public class UygulamaBaslatildiBildirim extends AbstractBildirim {

    public UygulamaBaslatildiBildirim() {
        this.mesaj = "Yurt Yönetim Sistemi başarıyla başlatıldı.";
    }

    @Override
    public String hedef() {
        return "SİSTEM";
    }
}
