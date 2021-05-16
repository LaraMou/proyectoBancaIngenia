package com.ingenia.banca;

import com.ingenia.banca.dao.CuentaDao;
import com.ingenia.banca.dao.MovimientoDAO;

import com.ingenia.banca.model.*;
import com.ingenia.banca.repository.CategoriaRepository;
import com.ingenia.banca.repository.CuentaRepository;
import com.ingenia.banca.repository.MovimientoRepository;
import com.ingenia.banca.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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


    public static void main(String[] args) {
        SpringApplication.run(BancaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Usuario usuario = new Usuario("12345678b",
                "TEST1","AAAA","BBBB",
                LocalDate.of(2000,02,02),
                666556655L,"m@mailto.com", Interviniente.TITULAR);


//        Optional<Usuario> usuariodb = usuarioDao.findUsuarioByNif("12345678b");
//        if(usuariodb.isPresent())
//            System.out.println(usuariodb.get().getNif());
//
//        /**
//         * Creación cuenta y asociación
//         */
//        List<Cuenta> cuentas = new ArrayList<>();
//        cuentas.add( new Cuenta(1234123401123456789l,LocalDate.of(2000,02,02),
//                LocalDate.now(),LocalDate.of(2021,05,15),Estado.ACTIVO,
//                1000D,2000D));
//        cuentas.add( new Cuenta(234123401123456789l,LocalDate.of(2000,02,02),
//                LocalDate.now(),LocalDate.of(2021,05,15),Estado.ACTIVO,
//                1000D,2000D));
//
//        usuario.setCuentas(cuentas);

        /**
         * Creación cuentas Y MOVMIENTO
         */
        Movimiento  movimiento1 = new Movimiento(50D, LocalDateTime.now(),LocalDateTime.now(),"Pago gaso","gasolinaera");
        Movimiento  movimiento2 = new Movimiento(50D, LocalDateTime.now(),LocalDateTime.now(),"Pago gaso","gasolinaera");
        Movimiento  movimiento3 = new Movimiento(50D, LocalDateTime.now(),LocalDateTime.now(),"Pago gaso","gasolinaera");
        Cuenta cuenta = new Cuenta(LocalDate.now(),LocalDate.now(),LocalDate.now(),Estado.ACTIVO,1000D,2000D);
//       movimiento2.setCuenta(cuenta);
//        movimiento1.setCuenta(cuenta);
//        movimiento3.setCuenta(cuenta);
        movimientoDAO.createMovimiento(movimiento1);
        movimientoDAO.createMovimiento(movimiento2);
        movimientoDAO.createMovimiento(movimiento3);




        //        List<Movimiento> movimientos = new ArrayList<>();
//        movimientos.add(movimiento1);
//        movimientos.add(movimiento2);
//        movimientos.add(movimiento3);
//         Cuenta cuenta1 = new Cuenta(LocalDate.of(2000,02,02),LocalDate.now(),LocalDate.now(),Estado.ACTIVO,1000D,2000D);
//         cuenta1.setListaMovimientos(movimientos);
//        cuentaRepository.save(cuenta1);

        /**
         * Asocio Usuario anterior a la cuenta
         // TODO
         */
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

        /**
         * Asociar movimientos a categorias
         *
//         */
//
//        Categoria restaurantes = new Categoria("RESTAURANTES");
//        categoriaRepository.save(restaurantes);
////        Categoria categoria2 = new Categoria("GASTOS ESCOLARES");
////        Categoria categoria3 = new Categoria("GASOLINERAS");
////        Categoria categoria4 = new Categoria("RECIBOS");
//
////
//        Movimiento  movrestau1 = new Movimiento(50D, LocalDateTime.now(),LocalDateTime.now(),"Restaurante Salomon","Restaurante tarje***");
//        Movimiento  movrestau2 = new Movimiento(50D, LocalDateTime.now(),LocalDateTime.now(),"Restaurant Marujita Salita","Pago tarje***");
//        Movimiento  movrestau3 = new Movimiento(50D, LocalDateTime.now(),LocalDateTime.now(),"Maria La portuguesa","REst. Pago tarje***");
//        movrestau1.setCategoria(restaurantes);
//        movrestau2.setCategoria(restaurantes);
//        movrestau3.setCategoria(restaurantes);
//        movimientoDAO.createMovimiento(movrestau1);
//        movimientoDAO.createMovimiento(movrestau2);
//        movimientoDAO.createMovimiento(movrestau3);
//
////        List<Movimiento> movrestaurants = new ArrayList<>();
////        movrestaurants.add(movrestau1);
////        movrestaurants.add(movrestau2);
////        movrestaurants.add(movrestau3);
////
////        restaurantes.setMovimientos(movrestaurants);
//            //categoriaRepository.save(restaurantes);

    }}
