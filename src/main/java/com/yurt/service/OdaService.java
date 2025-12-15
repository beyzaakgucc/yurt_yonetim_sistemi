package com.yurt.service;

import com.yurt.domain.Oda;
import com.yurt.domain.Ogrenci;
import com.yurt.repository.OdaRepository;
import com.yurt.repository.OgrenciRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdaService {

    private final OdaRepository odaRepository;
    private final OgrenciRepository ogrenciRepository;

    public OdaService(OdaRepository odaRepository,
                      OgrenciRepository ogrenciRepository) {
        this.odaRepository = odaRepository;
        this.ogrenciRepository = ogrenciRepository;
    }

    // ODA OLUŞTUR (CREATE)
    public Oda odaOlustur(String odaNumarasi, int kapasite) {
        Oda oda = new Oda();
        oda.setOdaNumarasi(odaNumarasi);
        oda.setKapasite(kapasite);
        return odaRepository.save(oda);
    }

    // TÜM ODALARI GETİR (READ)
    public List<Oda> tumOdalariGetir() {
        return odaRepository.findAll();
    }

    // ÖĞRENCİYİ ODAYA YERLEŞTİR (UPDATE + iş kuralı)
    public void ogrenciyiOdayaYerlestir(Long odaId, Long ogrenciId) {
        Oda oda = odaRepository.findById(odaId)
                .orElseThrow(() -> new IllegalArgumentException("Oda bulunamadı"));

        Ogrenci ogrenci = ogrenciRepository.findById(ogrenciId)
                .orElseThrow(() -> new IllegalArgumentException("Öğrenci bulunamadı"));

        // KAPASİTE KONTROLÜ (çok önemli)
        if (oda.getOgrenciler().size() >= oda.getKapasite()) {
            throw new IllegalStateException("Oda kapasitesi dolu!");
        }

        ogrenci.setOda(oda);
        ogrenciRepository.save(ogrenci);
    }
}
