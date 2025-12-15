package com.yurt.controller;

import com.yurt.domain.Ogrenci;
import com.yurt.dto.OgrenciKayitRequest;
import com.yurt.service.OgrenciService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ogrenciler")
public class OgrenciController {

    private final OgrenciService ogrenciService;

    public OgrenciController(OgrenciService ogrenciService) {
        this.ogrenciService = ogrenciService;
    }

    // ✅ ÖĞRENCİ KAYIT
    @PostMapping("/kayit")
    public ResponseEntity<Ogrenci> kayit(@RequestBody OgrenciKayitRequest request) {
        Ogrenci ogrenci = ogrenciService.ogrenciEkle(request);
        return ResponseEntity.ok(ogrenci);
    }

    // ✅ ÖĞRENCİ PROFİL GÜNCELLEME (API)
    @PutMapping("/{id}")
    public ResponseEntity<Ogrenci> guncelle(
            @PathVariable Long id,
            @RequestBody OgrenciKayitRequest request
    ) {
        Ogrenci ogrenci = ogrenciService.ogrenciGuncelle(id, request);
        return ResponseEntity.ok(ogrenci);
    }

    // ✅ TEST
    @GetMapping("/test")
    public String test() {
        return "Ogrenci API calisiyor ✅";
    }
}
