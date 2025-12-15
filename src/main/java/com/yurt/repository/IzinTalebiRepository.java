package com.yurt.repository;

import com.yurt.domain.IzinTalebi;
import com.yurt.domain.IzinDurumu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface IzinTalebiRepository extends JpaRepository<IzinTalebi, Long> {

    List<IzinTalebi> findByOgrenci_Id(Long ogrenciId);

    List<IzinTalebi> findByDurum(IzinDurumu durum);

    List<IzinTalebi>
    findByBaslangicTarihiLessThanEqualAndBitisTarihiGreaterThanEqual(
            LocalDate bitis,
            LocalDate baslangic
    );
}
