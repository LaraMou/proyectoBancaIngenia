package com.ingenia.banca.services.impl;

import com.ingenia.banca.dao.MovimientoDAO;
import com.ingenia.banca.dao.TarjetaDAO;
import com.ingenia.banca.model.Movimiento;
import com.ingenia.banca.model.Tarjeta;
import com.ingenia.banca.services.MovimientoService;
import com.ingenia.banca.services.TarjetaService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MovimientoServiceImpl implements MovimientoService {

    public MovimientoDAO movimientoDAO;
    public MovimientoServiceImpl(TarjetaDAO tarjetaDAO) {this.movimientoDAO = movimientoDAO;}

    @Override
    public Movimiento createMovimiento(Movimiento movimiento){ return this.movimientoDAO.createMovimiento(movimiento);}

    @Override
    public Movimiento updateMovimiento(Movimiento movimiento){return this.movimientoDAO.updateMovimiento(movimiento);}

    @Override
    public void deleteMovimiento(Long id){this.movimientoDAO.deleteMovimiento(id);};

    @Override
    public Movimiento findOneMovimiento(Long id){return this.movimientoDAO.findOneMovimiento(id);}

    @Override
    public List<Movimiento> findMovimientos(){return this.movimientoDAO.findMovimientos();}
}
