package com.yurt.controller;

import com.yurt.domain.AbstractUser;
import com.yurt.domain.Role;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PersonelPageController {

    @GetMapping("/personel/anasayfa")
    public String anasayfa(HttpSession session, Model model) {

        AbstractUser user =
                (AbstractUser) session.getAttribute("kullanici");

        if (user == null || user.getRol() != Role.STAFF) {
            return "redirect:/login";
        }

        model.addAttribute("kullanici", user);

        return "personel-anasayfa";
    }
}
