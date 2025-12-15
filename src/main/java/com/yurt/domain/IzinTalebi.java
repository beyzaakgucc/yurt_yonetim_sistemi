package com.yurt.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class IzinTalebi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate baslangicTarihi;
    private LocalDate bitisTarihi;

    @Column(length = 500)
    private String neden;

    @Enumerated(EnumType.STRING)
    private IzinDurumu durum;

    private LocalDateTime olusturmaTarihi;

    @ManyToOne(fetch = FetchType.LAZY)
    private Ogrenci ogrenci;

    // GETTER - SETTER

    public Long getId() {
        return id;
    }

    public LocalDate getBaslangicTarihi() {
        return baslangicTarihi;
    }

    public void setBaslangicTarihi(LocalDate baslangicTarihi) {
        this.baslangicTarihi = baslangicTarihi;
    }

    public LocalDate getBitisTarihi() {
        return bitisTarihi;
    }

    public void setBitisTarihi(LocalDate bitisTarihi) {
        this.bitisTarihi = bitisTarihi;
    }

    public String getNeden() {
        return neden;
    }

    public void setNeden(String neden) {
        this.neden = neden;
    }

    public IzinDurumu getDurum() {
        return durum;
    }

    public void setDurum(IzinDurumu durum) {
        this.durum = durum;
    }

    public LocalDateTime getOlusturmaTarihi() {
        return olusturmaTarihi;
    }

    public void setOlusturmaTarihi(LocalDateTime olusturmaTarihi) {
        this.olusturmaTarihi = olusturmaTarihi;
    }

    public Ogrenci getOgrenci() {
        return ogrenci;
    }

    public void setOgrenci(Ogrenci ogrenci) {
        this.ogrenci = ogrenci;
    }
}
