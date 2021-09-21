package com.ingenia.banca.services.impl;

import com.ingenia.banca.dao.TarjetaDAO;
import com.ingenia.banca.model.Tarjeta;
import com.ingenia.banca.services.TarjetaService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TarjetaServiceImpl implements TarjetaService {

    public TarjetaDAO tarjetaDAO;
    public TarjetaServiceImpl(TarjetaDAO tarjetaDAO) {this.tarjetaDAO = tarjetaDAO;}

    @Override
    public Tarjeta createTarjeta(Tarjeta tarjeta){ return this.tarjetaDAO.createTarjeta(tarjeta);}

    @Override
    public Tarjeta updateTarjeta(Tarjeta tarjeta){return this.tarjetaDAO.updateTarjeta(tarjeta);}

    @Override
    public void deleteTarjeta(Long numeroTarjeta){this.tarjetaDAO.deleteTarjeta(numeroTarjeta);};

    @Override
    public List<Tarjeta> findTarjetas(){return this.tarjetaDAO.findTarjetas();}

    @Override
    public Optional<Tarjeta> findOneTarjeta(Long numeroTarjeta) {

            return this.tarjetaDAO.findOneTarjeta(numeroTarjeta);

    }

    @Override
    public List<Tarjeta> findTarjetasByCuenta(Long numeroCuenta){
        return this.tarjetaDAO.findTarjetasByCuenta(numeroCuenta);
    }

    @Override
    public Double getSaldo(Long numeroTarjeta, LocalDate fechaentrada, LocalDate fechafin) {
        return tarjetaDAO.getSaldoFecha(numeroTarjeta,fechaentrada,fechafin);
    }
}
