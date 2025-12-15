package com.yurt.dto;

import javax.validation.constraints.NotBlank;


public class PersonelKayitRequest {

    @NotBlank
    public String kullaniciAdi;

    @NotBlank
    public String eposta;

    @NotBlank
    public String tcKimlik;

    @NotBlank
    public String sifre;

    @NotBlank
    public String ad;

    @NotBlank
    public String soyad;

    public String telefon;
    public String adres;
}
