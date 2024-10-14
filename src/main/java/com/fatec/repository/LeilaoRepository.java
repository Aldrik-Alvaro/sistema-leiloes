package com.fatec.repository;

import com.fatec.entity.Leilao;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface LeilaoRepository extends JpaRepository<Leilao, Long> {
}
