package com.yurt.repository;

import com.yurt.domain.IzinTalebi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IzinRepository extends JpaRepository<IzinTalebi, Long> {
}
