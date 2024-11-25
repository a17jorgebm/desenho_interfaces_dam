/*--------------------------------------------------------------------------------*/
/*CONSULTAS PROFESORA*/
/*--------------------------------------------------------------------------------*/
/*1.	Obtener los productos  que proporciona un  proveedor  concreto. Se requiere  código del producto, tipo de producto y stock que tiene*/
/*EJEMPLO PROVEEDOR: Matutano*/
select nombre_prov, productos.cod_producto, tipo_producto, stock  from productos, almacena 
		where productos.cod_producto = almacena.cod_producto and almacena.nombre_prov = 'Matutano'

/*2.	Listado de las máquinas de azar que nunca han repartido un premio de 5.000 euros*/
select puede_dar.cod_maquina, maquina.descripcion, puede_dar.cantidad from puede_dar, maquina 
		where maquina.cod_maquina = puede_dar.cod_maquina and cantidad < 5000

/*3.	Listado de los mecánicos de azar que han sido contratados por algún fabricante que no sea el que tiene en nomina. Se requiere: nombre del mecánico, 
nombre del fabricante que lo contrata y fecha de contratación.*/
select mecanico.nombre, contrata.cod_mecanico, contrata.nombre_fab, contrata.fecha_contrato from repara, maquina_l, maquina, contrata, mecanico
	where (repara.cod_maquina = maquina_l.cod_maquina) and (maquina_l.cod_maquina = maquina.cod_maquina) 
		and (repara.cod_mecanico = contrata.cod_mecanico) and (maquina.nombre_fab != contrata.nombre_fab)
			and (mecanico.cod_mecanico = contrata.cod_mecanico)

/*4.	Obtener el código y nombre de los mecánicos que hayan reparado maquinas de azar y de otro tipo en una misma fecha*/
create view reparaciones as
select mecanico.nombre, repara.cod_mecanico, repara.cod_maquina, repara.fecha_reparacion, (select licencia from maquina_l where maquina_l.cod_maquina = repara.cod_maquina) Licencia from mecanico, repara 
		where (mecanico.cod_mecanico = repara.cod_mecanico) 

create view misma_fecha as
select cod_mecanico, nombre, cod_maquina, fecha_reparacion, Licencia from reparaciones M 
	where (select count(*)from reparaciones where M.cod_mecanico = reparaciones.cod_mecanico) >=2

select distinct cod_mecanico, nombre from misma_fecha C
	where  (exists(select * from misma_fecha where C.cod_mecanico = cod_mecanico and Licencia is not null) and  C.Licencia is null) 
		or (exists (select * from misma_fecha where C.cod_mecanico = cod_mecanico and Licencia is null) and C.Licencia is not null)


/*5.	Obtener por cada producto de tipo fungible la cantidad disponible entre todas las máquinas*/
select sum(cantidad) from dispone, productos where productos.cod_producto = dispone.cod_producto and tipo_producto = 'Fungible'

/*6.	Obtener la máquina de azar que mas premios ha repartido durante el mes de Diciembre del 2009*/
select cod_maquina, descripcion from maquina where cod_maquina in
	(select cod_maquina from puede_dar where month(fecha)=12 group by cod_maquina having count(puede_dar.cod_maquina) >= all 
		(select count(puede_dar.cod_maquina) from puede_dar where  month(fecha)=12 group by cod_maquina))

/*7.	Queremos saber las averías registradas de las máquinas que no son de azar, con los siguientes datos: código de la maquina, tipo de máquina, 
tipo de avería, fecha de reparación, mecánico que la arregla y si estaba en garantía o no en el momento de la reparación apareciendo la palabra “garantía”  
en la salida*/
select repara.cod_maquina, averia.descripcion, repara.cod_averia, fecha_reparacion, repara.cod_mecanico, maquina.t_garantia garantia from repara, maquina, averia 
	where (repara.cod_maquina = maquina.cod_maquina) and (repara.cod_averia = averia.cod_averia)


/*8.	Obtener la máquina de productos más rentable, teniendo en cuenta por cada máquina los ingresos producidos  , el pago de la cantidad abonada por su 
ubicación y coste total de las reparaciones por avería efectuadas sobre la maquina : Nota puede utilizarse una vista para resolver este apartado*/
create view gastos_reparaciones as
select sum(precio_reparacion) precio_reparaciones ,maquina_p.cod_maquina from maquina_p, repara 
	where repara.cod_maquina = maquina_p.cod_maquina group by maquina_p.cod_maquina

select maquina_p.cod_maquina, maquina.descripcion, (maquina_p.ingresos - (maquina_p.pago + precio_reparaciones)) Ingreso_neto from maquina_p, maquina, gastos_reparaciones
	where (maquina_p.cod_maquina = maquina.cod_maquina) and (maquina_p.cod_maquina = gastos_reparaciones.cod_maquina) 
		and maquina_p.ingresos - (maquina_p.pago + precio_reparaciones) =
			(select max(maquina_p.ingresos - (maquina_p.pago + precio_reparaciones)) from  maquina_p, gastos_reparaciones 
				where (maquina_p.cod_maquina = gastos_reparaciones.cod_maquina))

/*9.	Obtener el nombre de aquellos proveedores que pueden suministrar todos los productos que están disponibles en las maquinas*/
select nombre_prov from almacena where (select count(cod_producto) from productos) =any (select count(*) from almacena group by nombre_prov)

select count(*) from almacena group by nombre_prov /*productos por proveedor*/
select count(cod_producto) from productos  /*numero productos*/

/*10.	Obtener la máquina de azar que más premios de 200 euros haya concedido en un mismo día*/
create view premios_validos as
select puede_dar.cod_maquina, maquina.descripcion, fecha from puede_dar, maquina where (puede_dar.cod_maquina = maquina.cod_maquina) and (cantidad = 200)

select cod_maquina, descripcion from premios_validos 
	where cod_maquina = (select cod_maquina from premios_validos group by cod_maquina having count(*) >= all (select count(*) from premios_validos group by cod_maquina))

/*--------------------------------------------------------------------------------*/
/*NUESTRAS CONSULTAS*/
/*--------------------------------------------------------------------------------*/
/*STOCK*/

/*listado de productos por maquina*/
select cod_maquina, dispone.cod_producto, descripcion from dispone, productos 
	where dispone.cod_producto = productos.cod_producto

/*listado de precios de productos por maquina*/
select cod_maquina, dispone.cod_producto, descripcion, precio from dispone, productos 
	where dispone.cod_producto = productos.cod_producto

/*listado de productos y proveedores*/
select descripcion, nombre_prov from productos, almacena 
	where productos.cod_producto = almacena.cod_producto

/*listado de proveedores de un producto (ejemplo:Types)*/
select descripcion, nombre_prov from productos, almacena 
	where productos.cod_producto = almacena.cod_producto and productos.descripcion = 'Types'

/*listado de productos de un proveedor (ejemplo:Matutano)*/
select nombre_prov, descripcion  from productos, almacena 
	where productos.cod_producto = almacena.cod_producto and almacena.nombre_prov = 'Matutano'

/*listado de precios de los productos de un proveedor*/
select nombre_prov, descripcion, precio  from productos, almacena 
	where productos.cod_producto = almacena.cod_producto and almacena.nombre_prov = 'Matutano'

/*lista de proveedores(nombre, domicilio...)*/
select nombre_prov, domicilio, cobro from proveedor order by nombre_prov

/*cambio de las maquinas*/
select maquina_p.cod_maquina, maquina.descripcion, cp, cambio from maquina, maquina_p 
	where maquina.cod_maquina = maquina_p.cod_maquina 

/*--------------------------------------------------------------------------------*/
/*MAQUINARIA*/

/*listado de maquinas por fabricante*/
select nombre_fab, cod_maquina, descripcion from maquina order by nombre_fab

/*fabricante de una maquina (ejemplo:00021458)*/
select nombre_fab, cod_maquina, descripcion from maquina where cod_maquina = 00021458

/*caracteristicas de una maquina*/
select cod_maquina, descripcion, t_garantia, nombre_fab, fecha_venta from maquina order by nombre_fab

/*listado de maquinas de un fabricante (ejemplo:KONAMI)*/
select cod_maquina, descripcion, nombre_fab from maquina where nombre_fab='KONAMI'

/*listado de maquinas por fecha de compra*/
select cod_maquina, descripcion, fecha_venta from maquina  order by fecha_venta

/*listado de fabricantes*/
select nombre_fab, domicilio, serv_tecnico from fabricante

/*listado de maquinas*/
select cod_maquina, descripcion, nombre_fab from maquina order by nombre_fab

/*--------------------------------------------------------------------------------*/
/*AVERIA*/

/*numero de averias de una maquina (ejemplo:00021458)*/
select count(*) from repara where cod_maquina = 00021458 group by cod_maquina

/*saber quien reparo una averia (ejemplo:404)*/
select repara.cod_mecanico, mecanico.nombre from repara, mecanico 
	where cod_averia = 404 and mecanico.cod_mecanico =  repara.cod_mecanico

/*averia(cod, tipo ...)*/
select cod_averia, descripcion from averia

/*saber que maquinas estan reparadas y cuales no*/
select repara.cod_maquina, maquina.descripcion, repara.cod_averia, repara.fecha_reparacion from repara, maquina 
	where repara.cod_maquina = maquina.cod_maquina

/*busqueda de un mecanico segun la maquina que reparo (ejemplo:808)*/
select repara.cod_mecanico, mecanico.nombre from repara, mecanico 
	where cod_averia = 808 and mecanico.cod_mecanico =  repara.cod_mecanico

/*--------------------------------------------------------------------------------*/
/*MECANICOS*/

/*listado de mecanicos por fabricante*/
select contrata.nombre_fab, contrata.cod_mecanico, mecanico.nombre from contrata, mecanico 
	where contrata.cod_mecanico = mecanico.cod_mecanico order by nombre_fab

/*responsabe de una maquina (ejemplo:00021458)*/
select mecanico.nombre, maquina.cod_mecanico, maquina.cod_maquina from mecanico, maquina 
	where maquina.cod_maquina = 00021458 and maquina.cod_mecanico = mecanico.cod_mecanico

/*listado de mecanicos*/
select cod_mecanico, nombre, area, antiguedad, tipo from mecanico order by cod_mecanico

/*busqueda de un mecanico en concreto (ejemplo:03453004)*/
select cod_mecanico, nombre, area, antiguedad, tipo from mecanico where cod_mecanico = 03453004

/*busqueda de un mecanicos free-lance*/
select cod_mecanico, nombre, area, antiguedad, tipo from mecanico where tipo='Free-lance'

/*busqueda de mecanico por area (ejemplo:Ourense)*/
select cod_mecanico, nombre, antiguedad, tipo from mecanico where area='Ourense'

/*--------------------------------------------------------------------------------*/
/*MAQUINAS LUDICAS*/

/*cantidad que dio una maquina (ejemplo:00014598)*/
select sum(cantidad) from puede_dar where cod_maquina = 00014598

/*premios que dio una maquina (fecha, hora)  (ejemplo:00014598)*/3
select cantidad, fecha, hora from puede_dar where cod_maquina = 00014598

/*listado de maquinas por licencia*/
select maquina_l.cod_maquina, maquina_l.licencia, maquina.descripcion from maquina_l, maquina 
	where (maquina.cod_maquina = maquina_l.cod_maquina) order by maquina_l.licencia

/*nº de jugadas de una maquina (ejemplo:00014598)*/
select num_jugadas from maquina_l where cod_maquina = 00014598

/*premio disponible en una maquina*/
select disponible from maquina_l where cod_maquina = 00014598

/*ingresos de maquina determinada*/
select tantoporciento  from maquina_l where cod_maquina = 00014598

/*listado de maquinas ludicas*/
select maquina_l.cod_maquina, maquina.descripcion, maquina_l.licencia, maquina_l.tantoporciento, maquina_l.num_jugadas, maquina_l.disponible from maquina_l, maquina 
	where maquina.cod_maquina = maquina_l.cod_maquina

/*--------------------------------------------------------------------------------*/
/*ESTADISTICA*/

/*ingresos por maquina (productos)*/
select maquina_p.cod_maquina, maquina.descripcion, maquina_p.ingresos from maquina, maquina_p 
	where (maquina_p.cod_maquina = maquina.cod_maquina)

/*cuantas veces se averia una determinada maquina*/
select repara.cod_maquina, maquina.descripcion, (select count(*) from repara 
	where repara.cod_maquina = 00014598) Num_averias from repara, maquina where (repara.cod_maquina = maquina.cod_maquina) and (repara.cod_maquina = 00014598)

/*cantidades de premio que dieron las maquinas*/
select maquina.cod_maquina, maquina.descripcion, puede_dar.cantidad from puede_dar, maquina 
	where maquina.cod_maquina = puede_dar.cod_maquina		

/*cantidad total de reparaciones*/
select count(*) as 'Cantidad de Reparaciones' from repara

/*cantidad de reparaciones de un mecanico concreto (ejemplo:10674003)*/
select count(*) numero_reparaciones from repara where (repara.cod_mecanico = '10674003') 

/*cantidad de ventas de los productos*/
select maquina.cod_maquina, maquina.descripcion, productos.cod_producto, productos.descripcion, cantidad from productos, vende, maquina
	where productos.cod_producto = vende.cod_producto and vende.cod_maquina = maquina.cod_maquina order by cod_maquina

/*listado de la forma de pago a los proveedores*/
select nombre_prov, cobro from proveedor order by nombre_prov