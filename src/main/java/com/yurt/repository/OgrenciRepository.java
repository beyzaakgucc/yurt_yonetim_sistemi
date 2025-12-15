package com.yurt.repository;

import com.yurt.domain.Ogrenci;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OgrenciRepository extends JpaRepository<Ogrenci, Long> {


    Optional<Ogrenci> findByTcKimlikOrEpostaOrKullaniciAdi(
            String tcKimlik,
            String eposta,
            String kullaniciAdi
    );


    @Query("SELECT o FROM Ogrenci o LEFT JOIN FETCH o.oda WHERE o.id = :id")
    Optional<Ogrenci> findByIdWithOda(@Param("id") Long id);


    List<Ogrenci> findByOdaId(Long odaId);

    List<Ogrenci> findByAdContainingIgnoreCaseOrSoyadContainingIgnoreCase(String q, String q1);
}
