-- cuento movimientos---
insert into cuenta (codpais,digitocontrol,entidad,estado, fechaactual, fechaapertura,fechacontable, saldo_actual,saldo_inicial)
values (11,01,1234,0,NOW(),'2018-01-01',NOW(),1000,2000);


insert into movimiento(concepto,descripcion,fecha,fecha_valor,importe,id_categoria,numerocuenta)
values ('Ingreso','ingreso',NOW(),NOW(),50,null,1);

insert into movimiento(concepto,descripcion,fecha,fecha_valor,importe,id_categoria,numerocuenta)
values ('Pago','Pago Gasolinera',NOW(),NOW(),50,null,1);


insert into tarjeta(ccv, estado_tarjeta,fecha_expedicion, fecha_expiracion, limite, tipo,cuenta_id)
values (124,0,'2021-05-12 13:47:39.000000','2023-05-12 13:47:39.000000',500,'debito',1);
insert into tarjeta(ccv, estado_tarjeta,fecha_expedicion, fecha_expiracion, limite, tipo,cuenta_id)
values (124,0,'2021-05-12 13:47:39.000000','2023-05-12 13:47:39.000000',500,'debito',1);









