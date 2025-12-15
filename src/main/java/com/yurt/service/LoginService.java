package com.yurt.service;

import com.yurt.domain.AbstractUser;
import com.yurt.domain.Ogrenci;
import com.yurt.domain.Personel;
import com.yurt.repository.OgrenciRepository;
import com.yurt.repository.PersonelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private OgrenciRepository ogrenciRepository;

    @Autowired
    private PersonelRepository personelRepository;

    public AbstractUser girisYap(String kimlik, String sifre) {

        Optional<Ogrenci> ogrenciOpt =
                ogrenciRepository.findByTcKimlikOrEpostaOrKullaniciAdi(
                        kimlik, kimlik, kimlik
                );

        if (ogrenciOpt.isPresent()) {
            Ogrenci ogrenci = ogrenciOpt.get();
            if (ogrenci.getSifre().equals(sifre)) {
                return ogrenci;
            }
        }

        Optional<Personel> personelOpt =
                personelRepository.findByTcKimlikOrEpostaOrKullaniciAdi(
                        kimlik, kimlik, kimlik
                );

        if (personelOpt.isPresent()) {
            Personel personel = personelOpt.get();
            if (personel.getSifre().equals(sifre)) {
                return personel;
            }
        }

        return null;
    }
}
