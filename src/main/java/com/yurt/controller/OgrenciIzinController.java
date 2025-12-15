package com.yurt.controller;

import com.yurt.domain.*;
import com.yurt.repository.IzinTalebiRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/ogrenci")
public class OgrenciIzinController {

    private final IzinTalebiRepository izinTalebiRepository;

    public OgrenciIzinController(IzinTalebiRepository izinTalebiRepository) {
        this.izinTalebiRepository = izinTalebiRepository;
    }


    @GetMapping("/izin-talebi")
    public String izinFormu(HttpSession session) {

        AbstractUser user = (AbstractUser) session.getAttribute("kullanici");

        if (user == null || user.getRol() != Role.STUDENT) {
            return "redirect:/login";
        }

        return "ogrenci-izin-talebi";
    }


    @PostMapping("/izin-olustur")
    public String izinKaydet(

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate baslangicTarihi,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate bitisTarihi,

            @RequestParam String neden,
            HttpSession session
    ) {

        Ogrenci ogrenci = (Ogrenci) session.getAttribute("kullanici");

        if (ogrenci == null) {
            return "redirect:/login";
        }

        IzinTalebi izin = new IzinTalebi();
        izin.setBaslangicTarihi(baslangicTarihi);
        izin.setBitisTarihi(bitisTarihi);
        izin.setNeden(neden);
        izin.setDurum(IzinDurumu.BEKLEMEDE);
        izin.setOgrenci(ogrenci);

        izinTalebiRepository.save(izin);

        return "redirect:/ogrenci/anasayfa";
    }


    @GetMapping("/izin-gecmisi")
    public String izinGecmisi(HttpSession session, Model model) {

        Ogrenci ogrenci = (Ogrenci) session.getAttribute("kullanici");

        if (ogrenci == null || ogrenci.getRol() != Role.STUDENT) {
            return "redirect:/login";
        }

        List<IzinTalebi> izinler =
                izinTalebiRepository.findByOgrenci_Id(ogrenci.getId());

        model.addAttribute("izinler", izinler);

        return "ogrenci-izin-gecmisi";
    }
}
