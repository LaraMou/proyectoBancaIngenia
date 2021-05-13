package com.ingenia.banca.services.impl;

import com.ingenia.banca.dao.TarjetaDAO;
import com.ingenia.banca.model.Tarjeta;
import com.ingenia.banca.services.TarjetaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarjetaServiceImpl implements TarjetaService {

    public TarjetaDAO tarjetaDAO;
    public TarjetaServiceImpl(TarjetaDAO tarjetaDAO) {this.tarjetaDAO = tarjetaDAO;}

    @Override
    public Tarjeta createTarjeta(Tarjeta tarjeta){ return this.tarjetaDAO.createTarjeta(tarjeta);}

    @Override
    public Tarjeta updateTarjeta(Tarjeta tarjeta){return this.tarjetaDAO.updateTarjeta(tarjeta);}

    @Override
    public void deleteTarjeta(Long numero_tarjeta){this.tarjetaDAO.deleteTarjeta(numero_tarjeta);};

    @Override
    public Tarjeta findOneTarjeta(Long numero_tarjeta){return this.tarjetaDAO.findOneTarjeta(numero_tarjeta);}

    @Override
    public List<Tarjeta> findTarjetas(){return this.tarjetaDAO.findTarjetas();}
}
