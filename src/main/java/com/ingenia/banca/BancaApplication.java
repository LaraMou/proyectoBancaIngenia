package com.ingenia.banca;

import com.ingenia.banca.dao.CuentaDao;
import com.ingenia.banca.dao.MovimientoDAO;

import com.ingenia.banca.model.*;
import com.ingenia.banca.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class BancaApplication implements CommandLineRunner {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    CuentaRepository cuentaRepository;
    @Autowired
    CuentaDao cuentaDao;
    @Autowired
    MovimientoRepository movimientoRepository;
    @Autowired
    MovimientoDAO movimientoDAO;
    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    TarjetaRepository tarjetaRepository;


    public static void main(String[] args) {
        SpringApplication.run(BancaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Usuario usuario = new Usuario("12345678b",
                "TEST1","AAAA","BBBB",
                LocalDate.of(2000,02,02),
                666556655L,"m@mailto.com", Interviniente.TITULAR);


        /**
         * Creación cuentas Y MOVMIENTO
         */
        Movimiento  movimiento1 = new Movimiento(-50D, LocalDateTime.now(),LocalDate.now(),"Pago gaso","gasolinaera");
        Movimiento  movimiento2 = new Movimiento(-50D, LocalDateTime.now(),LocalDate.now(),"Pago gaso","gasolinaera");
        Movimiento  movimiento3 = new Movimiento(-50D, LocalDateTime.now(),LocalDate.now(),"Pago gaso","gasolinaera");
        Cuenta cuenta = new Cuenta(LocalDate.now(),LocalDate.now(),LocalDate.now(),Estado.ACTIVO,1000D,2000D);
        movimientoDAO.createMovimiento(movimiento1);
        movimientoDAO.createMovimiento(movimiento2);
        movimientoDAO.createMovimiento(movimiento3);




        List<Cuenta> cuentas = new ArrayList<>();
        Cuenta cuenta2 = new Cuenta(LocalDate.of(2000,02,02),
                LocalDate.now(),LocalDate.now(),Estado.ACTIVO,1000D,2000D);
        Cuenta cuenta3 = new Cuenta(LocalDate.of(2000,02,02),
                LocalDate.now(),LocalDate.now(),Estado.ACTIVO,1000D,2000D);
        Cuenta cuenta4 = new Cuenta(LocalDate.of(2000,02,02),
                LocalDate.now(),LocalDate.now(),Estado.ACTIVO,1000D,2000D);
        cuentas.add(cuenta2);
        cuentas.add(cuenta3);
        cuentas.add(cuenta4);
        usuario.setCuentas(cuentas);
        usuarioRepository.save(usuario);

;

        /**
         * Asociar movimientos a categorias
         *
        */

        Categoria restaurantes = new Categoria("RESTAURANTES");
        categoriaRepository.save(restaurantes);
        cuentaRepository.save(cuenta2);

        Movimiento  movrestau1 = new Movimiento(50D, LocalDateTime.now(),LocalDate.now(),"Restaurante Salomon","Restaurante tarje***");
        Movimiento  movrestau2 = new Movimiento(50D, LocalDateTime.now(),LocalDate.now(),"Restaurant Marujita Salita","Pago tarje***");
        Movimiento  movrestau3 = new Movimiento(50D, LocalDateTime.now(),LocalDate.now(),"Maria La portuguesa","REst. Pago tarje***");
        movrestau1.setCuenta(cuenta2);
        movrestau1.setCategoria(restaurantes);
        movrestau2.setCategoria(restaurantes);
        movrestau3.setCategoria(restaurantes);
        movimientoDAO.createMovimiento(movrestau1);
        movimientoDAO.createMovimiento(movrestau2);
        movimientoDAO.createMovimiento(movrestau3);



    }}
