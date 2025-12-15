package com.yurt.controller;

import com.yurt.domain.*;
import com.yurt.repository.OdaRepository;
import com.yurt.repository.OgrenciRepository;

import javax.servlet.http.HttpSession;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/personel")
public class PersonelOgrenciController {

    private final OgrenciRepository ogrenciRepository;
    private final OdaRepository odaRepository;

    public PersonelOgrenciController(
            OgrenciRepository ogrenciRepository,
            OdaRepository odaRepository
    ) {
        this.ogrenciRepository = ogrenciRepository;
        this.odaRepository = odaRepository;
    }


    @GetMapping("/ogrenciler")
    public String ogrenciler(
            @RequestParam(required = false) String q,
            HttpSession session,
            Model model
    ) {

        AbstractUser user = (AbstractUser) session.getAttribute("kullanici");
        if (user == null || user.getRol() != Role.STAFF) {
            return "redirect:/login";
        }

        List<Ogrenci> ogrenciler =
                (q != null && !q.isEmpty())
                        ? ogrenciRepository.findByAdContainingIgnoreCaseOrSoyadContainingIgnoreCase(q, q)
                        : ogrenciRepository.findAll();

        model.addAttribute("ogrenciler", ogrenciler);
        model.addAttribute("q", q);

        return "personel-ogrenci-listesi";
    }


    @GetMapping("/ogrenci-ekle")
    public String ogrenciEkleForm(HttpSession session) {

        AbstractUser user = (AbstractUser) session.getAttribute("kullanici");
        if (user == null || user.getRol() != Role.STAFF) {
            return "redirect:/login";
        }

        return "personel-ogrenci-ekle";
    }


    @PostMapping("/ogrenci-ekle")
    public String ogrenciEkleKaydet(
            @RequestParam String ad,
            @RequestParam String soyad,
            @RequestParam String kullaniciAdi,
            @RequestParam String tcKimlik,
            @RequestParam String telefon,
            HttpSession session
    ) {

        AbstractUser user = (AbstractUser) session.getAttribute("kullanici");
        if (user == null || user.getRol() != Role.STAFF) {
            return "redirect:/login";
        }

        Ogrenci ogrenci = new Ogrenci();
        ogrenci.setAd(ad);
        ogrenci.setSoyad(soyad);
        ogrenci.setKullaniciAdi(kullaniciAdi);
        ogrenci.setTcKimlik(tcKimlik);
        ogrenci.setTelefon(telefon);


        ogrenci.setRol(Role.STUDENT);
        ogrenci.setSifre("1234");
        ogrenci.setEposta(kullaniciAdi + "@yurt.com");
        ogrenci.setAdres("Belirtilmedi");

        ogrenciRepository.save(ogrenci);

        return "redirect:/personel/ogrenciler";
    }


    @GetMapping("/ogrenci/{id}/oda-ata")
    public String odaAtaForm(
            @PathVariable Long id,
            HttpSession session,
            Model model
    ) {

        AbstractUser user = (AbstractUser) session.getAttribute("kullanici");
        if (user == null || user.getRol() != Role.STAFF) {
            return "redirect:/login";
        }

        Ogrenci ogrenci = ogrenciRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Öğrenci bulunamadı"));

        model.addAttribute("ogrenci", ogrenci);
        model.addAttribute("odalar", odaRepository.findAll());

        return "personel-ogrenci-oda-ata";
    }


    @PostMapping("/ogrenci/{id}/oda-ata")
    public String odaAtaKaydet(
            @PathVariable Long id,
            @RequestParam Long odaId,
            HttpSession session
    ) {

        AbstractUser user = (AbstractUser) session.getAttribute("kullanici");
        if (user == null || user.getRol() != Role.STAFF) {
            return "redirect:/login";
        }

        Ogrenci ogrenci = ogrenciRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Öğrenci bulunamadı"));

        Oda oda = odaRepository.findById(odaId)
                .orElseThrow(() -> new RuntimeException("Oda bulunamadı"));

        if (oda.getOgrenciler().size() >= oda.getKapasite()) {
            return "redirect:/personel/ogrenciler";
        }

        ogrenci.setOda(oda);
        ogrenciRepository.save(ogrenci);

        return "redirect:/personel/ogrenciler";
    }
}
