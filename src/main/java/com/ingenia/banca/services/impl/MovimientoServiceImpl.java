package com.ingenia.banca.services.impl;

import com.ingenia.banca.dao.MovimientoDAO;
import com.ingenia.banca.dao.TarjetaDAO;
import com.ingenia.banca.model.Movimiento;
import com.ingenia.banca.services.MovimientoService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MovimientoServiceImpl implements MovimientoService {

    public MovimientoDAO movimientoDAO;
    public MovimientoServiceImpl(MovimientoDAO movimientoDAO) {this.movimientoDAO = movimientoDAO;}

    @Override
    public Movimiento createMovimiento(Movimiento movimiento){ return this.movimientoDAO.createMovimiento(movimiento);}

    @Override
    public Movimiento updateMovimiento(Movimiento movimiento){return this.movimientoDAO.updateMovimiento(movimiento);}

    @Override
    public void deleteMovimiento(Long id){this.movimientoDAO.deleteMovimiento(id);};

    @Override
    public Optional<Movimiento> findOneMovimiento(Long id){return this.movimientoDAO.findOneMovimiento(id);}


    @Override
    public List<Movimiento> findMovimientos(){return this.movimientoDAO.findMovimientos();}

    @Override
    public List<Movimiento> findMovimientosEntre(Long id, LocalDate fechaInicio, LocalDate fechaFin){return this.movimientoDAO.findMovimientosEntre(id,fechaInicio,fechaFin);}

    @Override
    public List<Movimiento> getSumaCategoriaMes(Long numerocuenta, LocalDate fechainicio,LocalDate fechafin, Long idCat){return this.movimientoDAO.getSumaCategoriaMes(numerocuenta,fechainicio,fechafin,idCat);}
}
