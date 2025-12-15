package com.yurt.controller;

import com.yurt.domain.*;
import com.yurt.repository.OgrenciRepository;
import com.yurt.repository.PersonelRepository;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final OgrenciRepository ogrenciRepository;
    private final PersonelRepository personelRepository;

    public LoginController(
            OgrenciRepository ogrenciRepository,
            PersonelRepository personelRepository
    ) {
        this.ogrenciRepository = ogrenciRepository;
        this.personelRepository = personelRepository;
    }

    // LOGIN SAYFASI
    @GetMapping("/login")
    public String loginSayfasi() {
        return "login";
    }

    // LOGIN İŞLEMİ
    @PostMapping("/login")
    public String login(
            @RequestParam String kimlik,
            @RequestParam String sifre,
            HttpSession session,
            Model model
    ) {


        Ogrenci ogrenci = ogrenciRepository
                .findByTcKimlikOrEpostaOrKullaniciAdi(kimlik, kimlik, kimlik)
                .orElse(null);

        if (ogrenci != null && ogrenci.getSifre().equals(sifre)) {
            session.setAttribute("kullanici", ogrenci);
            return "redirect:/ogrenci/anasayfa";
        }


        Personel personel = personelRepository
                .findByTcKimlikOrEpostaOrKullaniciAdi(kimlik, kimlik, kimlik)
                .orElse(null);

        if (personel != null && personel.getSifre().equals(sifre)) {
            session.setAttribute("kullanici", personel);
            return "redirect:/personel/anasayfa";
        }

        model.addAttribute("hata", "Bilgiler hatalı");
        return "login";
    }
}
