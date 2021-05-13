package com.ingenia.banca.services.impl;

import com.ingenia.banca.dao.CuentaDao;
import com.ingenia.banca.model.Cuenta;
import com.ingenia.banca.repository.CuentaRepository;
import com.ingenia.banca.services.CuentaService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
        log.info("findOneUserById");
        if(numerocuenta!=null)
            return cuentaDao.findCuentaByNumerocuenta(numerocuenta);
        return Optional.empty();
    }


    @Override
    public Cuenta saveCuenta(Cuenta cuenta) {
        return null;
    }

}
