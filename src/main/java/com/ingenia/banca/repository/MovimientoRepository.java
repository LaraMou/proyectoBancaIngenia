package com.ingenia.banca.repository;

import com.ingenia.banca.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientoRepository extends JpaRepository<Movimiento,Long>
{
}
