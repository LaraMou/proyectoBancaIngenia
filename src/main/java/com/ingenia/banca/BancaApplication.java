package com.ingenia.banca;

import com.ingenia.banca.dao.CuentaDao;
import com.ingenia.banca.dao.UsuarioDao;
import com.ingenia.banca.model.*;
import com.ingenia.banca.repository.CuentaRepository;
import com.ingenia.banca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDate;
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
    UsuarioDao usuarioDao;

    public static void main(String[] args) {
        SpringApplication.run(BancaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Usuario usuario = new Usuario("12345678b",
                "TEST1","AAAA","BBBB",
                LocalDate.of(2000,02,02),
                666556655L,"m@mailto.com", Interviniente.TITULAR);
        usuarioRepository.save(usuario);

        Optional<Usuario> usuariodb = usuarioDao.findUsuarioByNif("12345678b");
        if(usuariodb.isPresent())
            System.out.println(usuariodb.get().getNif());

        /**
         * Creación cuenta y asociación
         */
        List<Cuenta> cuentas = new ArrayList<>();
        cuentas.add( new Cuenta(1234123401123456789l,LocalDate.of(2000,02,02),
                LocalDate.now(),LocalDate.of(2021,05,15),Estado.ACTIVO,
                1000D,2000D));
        cuentas.add( new Cuenta(234123401123456789l,LocalDate.of(2000,02,02),
                LocalDate.now(),LocalDate.of(2021,05,15),Estado.ACTIVO,
                1000D,2000D));

        usuario.setCuentas(cuentas);

        /**
         * Creación cuentas Y MOVMIENTO
         */

//        Cuenta cuenta1 = new Cuenta(678L,LocalDate.of(2000,02,02,00,00),LocalDate.now(),LocalDate.now(),Estado.ACTIVO,1000D,2000D);
//
//
//        List<Movimiento> movimientos = new ArrayList<>();
//        movimientos.add(new Movimiento(1000d, LocalDate.now(),LocalDate.now(),"Gasolinera BP estacion.","Gasolinera BP"));
//
//        cuenta1.setListaMovimientos(movimientos);
//        cuentaRepository.save(cuenta1);
  }
}
