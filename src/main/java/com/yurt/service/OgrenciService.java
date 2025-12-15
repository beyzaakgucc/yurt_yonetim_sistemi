package com.yurt.service;

import com.yurt.domain.Oda;
import com.yurt.domain.Ogrenci;
import com.yurt.domain.Role;
import com.yurt.dto.OgrenciKayitRequest;
import com.yurt.factory.OgrenciFactory;
import com.yurt.repository.OdaRepository;
import com.yurt.repository.OgrenciRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OgrenciService {

    private final OgrenciRepository ogrenciRepository;
    private final OdaRepository odaRepository;

    public OgrenciService(OgrenciRepository ogrenciRepository,
                          OdaRepository odaRepository) {
        this.ogrenciRepository = ogrenciRepository;
        this.odaRepository = odaRepository;
    }

    // ✅ CREATE
    public Ogrenci ogrenciEkle(OgrenciKayitRequest dto) {
        Oda oda = odaRepository.findByOdaNumarasi(dto.odaNumarasi)
                .orElseThrow(() -> new IllegalArgumentException("Oda bulunamadı"));

        if (oda.getOgrenciler().size() >= oda.getKapasite()) {
            throw new IllegalStateException("Oda kapasitesi dolu!");
        }

        OgrenciFactory factory = new OgrenciFactory(oda);
        Ogrenci ogrenci = (Ogrenci) factory.create(dto);
        ogrenci.setRol(Role.STUDENT);

        return ogrenciRepository.save(ogrenci);
    }

    // ✅ READ - tüm öğrenciler
    public List<Ogrenci> tumOgrenciler() {
        return ogrenciRepository.findAll();
    }

    // ✅ READ - tek öğrenci
    public Ogrenci ogrenciGetir(Long id) {
        return ogrenciRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Öğrenci bulunamadı"));
    }

    // ✅ UPDATE - PROFİL GÜNCELLEME (DTO FIELD UYUMLU)
    public Ogrenci ogrenciGuncelle(Long id, OgrenciKayitRequest dto) {
        Ogrenci ogrenci = ogrenciGetir(id);

        if (dto.ad != null) ogrenci.setAd(dto.ad);
        if (dto.soyad != null) ogrenci.setSoyad(dto.soyad);
        if (dto.kullaniciAdi != null) ogrenci.setKullaniciAdi(dto.kullaniciAdi);
        if (dto.eposta != null) ogrenci.setEposta(dto.eposta);
        if (dto.telefon != null) ogrenci.setTelefon(dto.telefon);
        if (dto.adres != null) ogrenci.setAdres(dto.adres);

        // ❌ TC, ŞİFRE, ODA burada güncellenmez (güvenlik)
        return ogrenciRepository.save(ogrenci);
    }

    // ✅ DELETE
    public void ogrenciSil(Long id) {
        ogrenciRepository.deleteById(id);
    }
}
