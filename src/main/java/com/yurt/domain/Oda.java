package com.yurt.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "odalar")
public class Oda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String odaNumarasi;

    private int kapasite;


    @OneToMany(
            mappedBy = "oda",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Ogrenci> ogrenciler = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public String getOdaNumarasi() {
        return odaNumarasi;
    }

    public void setOdaNumarasi(String odaNumarasi) {
        this.odaNumarasi = odaNumarasi;
    }

    public int getKapasite() {
        return kapasite;
    }

    public void setKapasite(int kapasite) {
        this.kapasite = kapasite;
    }

    public List<Ogrenci> getOgrenciler() {
        return ogrenciler;
    }


    public void ogrenciEkle(Ogrenci ogrenci) {
        ogrenciler.add(ogrenci);
        ogrenci.setOda(this);
    }

    public void ogrenciCikar(Ogrenci ogrenci) {
        ogrenciler.remove(ogrenci);
        ogrenci.setOda(null);
    }
}
