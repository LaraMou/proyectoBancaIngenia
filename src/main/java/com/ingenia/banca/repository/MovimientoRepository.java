package com.ingenia.banca.repository;

import com.ingenia.banca.model.Movimiento;
import com.ingenia.banca.model.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento,Long> {
}
