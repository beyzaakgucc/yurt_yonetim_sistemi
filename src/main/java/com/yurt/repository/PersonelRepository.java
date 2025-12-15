package com.yurt.repository;

import com.yurt.domain.Personel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonelRepository extends JpaRepository<Personel, Long> {

    Optional<Personel> findByTcKimlikOrEpostaOrKullaniciAdi(
            String tcKimlik,
            String eposta,
            String kullaniciAdi
    );
}
