package com.yurt.factory;

import com.yurt.domain.Personel;
import com.yurt.domain.Role;
import com.yurt.dto.PersonelKayitRequest;

public class PersonelFactory implements UserFactory<PersonelKayitRequest> {

    @Override
    public Personel create(PersonelKayitRequest dto) {
        Personel p = new Personel();

        p.setKullaniciAdi(dto.kullaniciAdi);
        p.setEposta(dto.eposta);
        p.setTcKimlik(dto.tcKimlik);
        p.setSifre(dto.sifre);

        p.setAd(dto.ad);
        p.setSoyad(dto.soyad);
        p.setTelefon(dto.telefon);
        p.setAdres(dto.adres);

        p.setRol(Role.STAFF); // Personel rolü STAFF

        return p;
    }
}
