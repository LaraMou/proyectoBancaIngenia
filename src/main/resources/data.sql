INSERT INTO `usuario` (`nif`, `ciudad`, `created_date`, `email`, `fecha_nacimiento`, `genero`, `interviniente`, `last_modified_date`, `nombre`, `ocupacion`, `pais_origen`, `primerapellido`, `segundoapellido`, `telefono`) VALUES ('123456789B', 'Málaga', '2021-05-12 13:23:34.000000', 'usuario1@test.com', '1960-05-12', 'Masculino', '1', '2021-05-12 13:23:34.000000', 'Usuario1', 'Sector Agrario', 'España', 'Apellido Usuario1', 'Apellido2 Usuario1', '666778899')
    INSERT INTO `cuenta` (`numero_cuenta`, `estado`, `fechaactual`, `fechaapertura`, `saldo_actual`, `saldo_inicial`) VALUES ('123412340112345678', 'ACTIVO', '2021-05-12 13:47:39.000000', '2021-01-01 13:47:39.000000', '1000', '1000');

insert into usuario_cuenta (usuario_id, cuenta_id) values ('123456789B', '123412340112345678');
