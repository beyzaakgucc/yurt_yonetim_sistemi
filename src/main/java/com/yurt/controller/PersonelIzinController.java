package com.yurt.controller;

import com.yurt.domain.*;
import com.yurt.repository.IzinTalebiRepository;

import javax.servlet.http.HttpSession;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/personel")
public class PersonelIzinController {

    private final IzinTalebiRepository izinTalebiRepository;

    public PersonelIzinController(IzinTalebiRepository izinTalebiRepository) {
        this.izinTalebiRepository = izinTalebiRepository;
    }


    @GetMapping("/izin-talepleri")
    public String bekleyenIzinler(HttpSession session, Model model) {

        AbstractUser user = (AbstractUser) session.getAttribute("kullanici");

        if (user == null || user.getRol() != Role.STAFF) {
            return "redirect:/login";
        }

        List<IzinTalebi> bekleyenler =
                izinTalebiRepository.findByDurum(IzinDurumu.BEKLEMEDE);

        model.addAttribute("izinler", bekleyenler);

        return "personel-izin-talepleri";
    }


    @PostMapping("/izin-onayla/{id}")
    public String izinOnayla(@PathVariable Long id) {

        IzinTalebi izin = izinTalebiRepository.findById(id).orElse(null);

        if (izin != null) {
            izin.setDurum(IzinDurumu.ONAYLANDI);
            izinTalebiRepository.save(izin);
        }

        return "redirect:/personel/izin-talepleri";
    }


    @PostMapping("/izin-reddet/{id}")
    public String izinReddet(@PathVariable Long id) {

        IzinTalebi izin = izinTalebiRepository.findById(id).orElse(null);

        if (izin != null) {
            izin.setDurum(IzinDurumu.REDDEDILDI);
            izinTalebiRepository.save(izin);
        }

        return "redirect:/personel/izin-talepleri";
    }
}
