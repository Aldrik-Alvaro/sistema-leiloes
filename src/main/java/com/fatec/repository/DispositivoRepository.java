package com.fatec.repository;

import com.fatec.entity.DispositivoInformatica;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface DispositivoRepository extends JpaRepository<DispositivoInformatica, Long> {
}

