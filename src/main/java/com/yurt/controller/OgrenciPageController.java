package com.yurt.controller;

import com.yurt.domain.AbstractUser;
import com.yurt.domain.Ogrenci;
import com.yurt.domain.Role;
import com.yurt.repository.OgrenciRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OgrenciPageController {

    private final OgrenciRepository ogrenciRepository;

    public OgrenciPageController(OgrenciRepository ogrenciRepository) {
        this.ogrenciRepository = ogrenciRepository;
    }

    @GetMapping("/ogrenci/anasayfa")
    public String anasayfa(HttpSession session, Model model) {

        AbstractUser sessionUser =
                (AbstractUser) session.getAttribute("kullanici");

        if (sessionUser == null || sessionUser.getRol() != Role.STUDENT) {
            return "redirect:/login";
        }

        Ogrenci ogrenci = ogrenciRepository
                .findByIdWithOda(sessionUser.getId())
                .orElseThrow();

        List<Ogrenci> odaArkadaslari =
                ogrenci.getOda() == null
                        ? List.of()
                        : ogrenciRepository
                        .findByOdaId(ogrenci.getOda().getId())
                        .stream()
                        .filter(o -> !o.getId().equals(ogrenci.getId()))
                        .collect(Collectors.toList());

        model.addAttribute("kullanici", ogrenci);
        model.addAttribute("oda", ogrenci.getOda());
        model.addAttribute("odaArkadaslari", odaArkadaslari);

        return "ogrenci-anasayfa";
    }

    @PostMapping("/ogrenci/profil-guncelle")
    public String profilGuncelle(
            @RequestParam String ad,
            @RequestParam String soyad,
            @RequestParam String kullaniciAdi,
            @RequestParam String eposta,
            @RequestParam String telefon,
            @RequestParam String adres,
            HttpSession session
    ) {

        AbstractUser sessionUser =
                (AbstractUser) session.getAttribute("kullanici");

        if (sessionUser == null || sessionUser.getRol() != Role.STUDENT) {
            return "redirect:/login";
        }

        Ogrenci ogrenci = ogrenciRepository
                .findById(sessionUser.getId())
                .orElseThrow();

        ogrenci.setAd(ad);
        ogrenci.setSoyad(soyad);
        ogrenci.setKullaniciAdi(kullaniciAdi);
        ogrenci.setEposta(eposta);
        ogrenci.setTelefon(telefon);
        ogrenci.setAdres(adres);

        ogrenciRepository.save(ogrenci);

        session.setAttribute("kullanici", ogrenci);

        return "redirect:/ogrenci/anasayfa";
    }
}
