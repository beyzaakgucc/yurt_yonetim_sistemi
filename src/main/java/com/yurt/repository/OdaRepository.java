package com.yurt.repository;

import com.yurt.domain.Oda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OdaRepository extends JpaRepository<Oda, Long> {

    Optional<Oda> findByOdaNumarasi(String odaNumarasi);
}
