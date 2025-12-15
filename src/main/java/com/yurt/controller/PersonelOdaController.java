package com.yurt.controller;

import com.yurt.domain.*;
import com.yurt.repository.OdaRepository;

import javax.servlet.http.HttpSession;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/personel")
public class PersonelOdaController {

    private final OdaRepository odaRepository;

    public PersonelOdaController(OdaRepository odaRepository) {
        this.odaRepository = odaRepository;
    }


    @GetMapping("/odalar")
    public String odaListesi(HttpSession session, Model model) {

        AbstractUser user = (AbstractUser) session.getAttribute("kullanici");

        if (user == null || user.getRol() != Role.STAFF) {
            return "redirect:/login";
        }

        model.addAttribute("odalar", odaRepository.findAll());
        return "personel-oda-listesi";
    }


    @GetMapping("/oda-ekle")
    public String odaEkleForm(HttpSession session) {

        AbstractUser user = (AbstractUser) session.getAttribute("kullanici");

        if (user == null || user.getRol() != Role.STAFF) {
            return "redirect:/login";
        }

        return "personel-oda-ekle";
    }


    @PostMapping("/oda-ekle")
    public String odaEkleKaydet(
            @RequestParam String odaNumarasi,
            @RequestParam int kapasite,
            HttpSession session
    ) {

        AbstractUser user = (AbstractUser) session.getAttribute("kullanici");

        if (user == null || user.getRol() != Role.STAFF) {
            return "redirect:/login";
        }

        Oda oda = new Oda();
        oda.setOdaNumarasi(odaNumarasi);
        oda.setKapasite(kapasite);

        odaRepository.save(oda);
        return "redirect:/personel/odalar";
    }


    @PostMapping("/oda-sil/{id}")
    public String odaSil(@PathVariable Long id, HttpSession session) {

        AbstractUser user = (AbstractUser) session.getAttribute("kullanici");

        if (user == null || user.getRol() != Role.STAFF) {
            return "redirect:/login";
        }

        Oda oda = odaRepository.findById(id).orElseThrow();

        if (!oda.getOgrenciler().isEmpty()) {
            return "redirect:/personel/odalar";
        }

        odaRepository.delete(oda);
        return "redirect:/personel/odalar";
    }
}
