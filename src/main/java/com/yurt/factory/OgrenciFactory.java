package com.yurt.factory;

import com.yurt.domain.Oda;
import com.yurt.domain.Ogrenci;
import com.yurt.domain.Role;
import com.yurt.dto.OgrenciKayitRequest;

public class OgrenciFactory implements UserFactory<OgrenciKayitRequest> {

    private final Oda oda;

    public OgrenciFactory(Oda oda) {
        this.oda = oda;
    }

    @Override
    public Ogrenci create(OgrenciKayitRequest dto) {
        return new Ogrenci.Builder()
                .kullaniciAdi(dto.kullaniciAdi)
                .eposta(dto.eposta)
                .tcKimlik(dto.tcKimlik)
                .sifre(dto.sifre)
                .ad(dto.ad)
                .soyad(dto.soyad)
                .telefon(dto.telefon)
                .adres(dto.adres)
                .rol(Role.STUDENT)
                .oda(oda)
                .build();
    }
}
