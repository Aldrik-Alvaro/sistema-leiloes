package com.fatec.repository;

import com.fatec.entity.DispositivoInformatica;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface DispositivoRepository extends JpaRepository<DispositivoInformatica, Long> {
    //@Query("SELECT d FROM DispositivoInformatica d WHERE d.id = :id AND d.lances IS EMPTY")
    //Optional<DispositivoInformatica> findByIdAndNoLances(Long id);
}

