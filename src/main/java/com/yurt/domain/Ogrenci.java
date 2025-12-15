package com.yurt.domain;

import javax.persistence.*;

@Entity
@Table(name = "ogrenciler")
public class Ogrenci extends AbstractUser {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oda_id")
    private Oda oda;

    public Oda getOda() {
        return oda;
    }

    public void setOda(Oda oda) {
        this.oda = oda;
    }


    public static class Builder {
        private final Ogrenci ogrenci = new Ogrenci();

        public Builder kullaniciAdi(String v) {
            ogrenci.setKullaniciAdi(v);
            return this;
        }

        public Builder eposta(String v) {
            ogrenci.setEposta(v);
            return this;
        }

        public Builder tcKimlik(String v) {
            ogrenci.setTcKimlik(v);
            return this;
        }

        public Builder sifre(String v) {
            ogrenci.setSifre(v);
            return this;
        }

        public Builder ad(String v) {
            ogrenci.setAd(v);
            return this;
        }

        public Builder soyad(String v) {
            ogrenci.setSoyad(v);
            return this;
        }

        public Builder telefon(String v) {
            ogrenci.setTelefon(v);
            return this;
        }

        public Builder adres(String v) {
            ogrenci.setAdres(v);
            return this;
        }

        public Builder rol(Role v) {
            ogrenci.setRol(v);
            return this;
        }

        public Builder oda(Oda v) {
            ogrenci.setOda(v);
            return this;
        }

        public Ogrenci build() {
            return ogrenci;
        }
    }
}
