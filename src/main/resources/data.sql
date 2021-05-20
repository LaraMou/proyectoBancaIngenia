-- cuento movimientos---

INSERT INTO categoria (id, nombre) VALUES (1, 'RESTAURANTE');
INSERT INTO categoria (id, nombre) VALUES (2, 'GASOLINERAS');
INSERT INTO categoria (id, nombre) VALUES (3, 'ESCOLARES');
INSERT INTO categoria (id, nombre) VALUES (4, 'NOMINAS');


insert into cuenta (codpais,digitocontrol,entidad,estado, fechaactual, fechaapertura,fechacontable, saldo_actual,saldo_inicial)
values (11,01,1234,0,NOW(),'2018-01-01',NOW(),1000,2000);


insert into movimiento(concepto,descripcion,fecha,fecha_valor,importe,id_categoria,numerocuenta)
values ('Ingreso','ingreso',NOW(),'2021-05-12',1000,4,1);
insert into movimiento(concepto,descripcion,fecha,fecha_valor,importe,id_categoria,numerocuenta)
values ('Pago','Pago Restuarante',NOW(),'2021-05-13',50,1,1);



insert into movimiento(concepto,descripcion,fecha,fecha_valor,importe,id_categoria,numerocuenta)
values ('Pago','Pago Gasolinera',NOW(),NOW(),-1500,2,1);


insert into tarjeta(ccv, estado_tarjeta,fecha_expedicion, fecha_expiracion, limite, tipo,cuenta_id)
values (124,0,'2021-05-12 13:47:39.000000','2023-05-12 13:47:39.000000',500,'debito',1);
insert into tarjeta(ccv, estado_tarjeta,fecha_expedicion, fecha_expiracion, limite, tipo,cuenta_id)
values (124,0,'2021-05-12 13:47:39.000000','2023-05-12 13:47:39.000000',500,'debito',1);









