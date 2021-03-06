package com.ingenia.banca.services.impl;

import com.ingenia.banca.dao.CuentaDao;
import com.ingenia.banca.model.Cuenta;
import com.ingenia.banca.model.Usuario;
import com.ingenia.banca.repository.CuentaRepository;
import com.ingenia.banca.services.CuentaService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaServiceImpl implements CuentaService {
    private final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);
    private final CuentaRepository cuentaRepository;
    private final CuentaDao cuentaDao;

    public CuentaServiceImpl(CuentaRepository cuentaRepository, CuentaDao cuentaDao) {
        this.cuentaRepository = cuentaRepository;
        this.cuentaDao = cuentaDao;
    }

    @Override
    @ApiOperation("Retrieve all cuentas ")
    public List<Cuenta> findAll() {
        return cuentaRepository.findAll();
    }

    @Override
    public Optional<Cuenta> findCuentaByNumerocuenta(Long numerocuenta) {
        log.info("findOneCuentaById");
        if(numerocuenta!=null)
            return cuentaDao.findCuentaByNumerocuenta(numerocuenta);
        return Optional.empty();
    }


    @Override
    public Cuenta saveCuenta(Cuenta cuenta) {
        log.info("createTag");
        if(ObjectUtils.isEmpty(cuenta))
            return null;

        return cuentaRepository.save(cuenta);
    }
    @Override
    public void deleteById(Long id) {
        cuentaRepository.deleteById(id);
    }

    @Override
    public List<Cuenta> findAccountsByUsuario(Long idUsuario){
        return cuentaDao.findAccountsByUsuario(idUsuario);
    }

    @Override
    public Double getSaldo(Long numerocuenta) {
        return cuentaDao.getSaldo(numerocuenta);
    }

    @Override
    public Double getSaldoFecha(Long numerocuenta, LocalDate fechaentrada,LocalDate fechafin) {
        return cuentaDao.getSaldoFecha(numerocuenta,fechaentrada,fechafin);
    }

    @Override
    public Double getAverageSaldo(Long numerocuenta, LocalDate fechaentrada, LocalDate fechafin) {
        return cuentaDao.getAverageSaldo(numerocuenta,fechaentrada,fechafin);
    }

    @Override
    public void saveSaldo(Long numerocuenta, LocalDate fechaentrada, LocalDate fechafin) {
        cuentaDao.saveSaldo(numerocuenta,fechaentrada,fechafin);
    }

}
