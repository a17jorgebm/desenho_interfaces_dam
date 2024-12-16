create database ALQUILER

drop database ALQUILER
use ALQUILER

create table MECANICO
(
cod_mecanico char(8) not null primary key,
nombre varchar(10) not null,
area varchar(20) not null,
antiguedad smallint not null,
tipo char(20) not null check (tipo in ('Contratado','Free-lance'))
)

create table FABRICANTE
(
nombre_fab varchar(15) not null primary key,
serv_tecnico char(2) not null check (serv_tecnico in ('Si','NO')),
domicilio  varchar(25) not null
)

create table MAQUINA
(
cod_maquina char(8) not null primary key,
descripcion varchar(40) not null,
t_garantia smallint not null,
fecha_venta smalldatetime not null, 
nombre_fab varchar(15) not null references FABRICANTE(nombre_fab),
cod_mecanico char(8) not null references MECANICO(cod_mecanico)
)

create table MAQUINA_P
(
cod_maquina char(8) not null references MAQUINA(cod_maquina),
CP char(6) not null,
tipo_maquina char(10) not null check (tipo_maquina in ('Bebidas','Bocadillos','Dulces','Fungibles','Combinado')),
ingresos smallint not null,
cambio smallint not null,
pago smallint not null,
primary key (cod_maquina)
)

create table MAQUINA_L
(
cod_maquina char(8) not null references MAQUINA(cod_maquina),
licencia char(15) not null unique,
tantoporciento smallint not null,
num_jugadas smallint not null,
disponible smallint not null,
primary key (cod_maquina)
)

create table PREMIO
(
cantidad smallint not null primary key
)

create table CONTIENE
(
cod_maquina char(8) not null references MAQUINA_L(cod_maquina),
cantidad smallint not null references PREMIO(cantidad),
primary key (cod_maquina)
)

create table PUEDE_DAR
(
cod_maquina char(8) not null references MAQUINA_L(cod_maquina),
fecha smalldatetime not null, 
hora char(5) not null,
cantidad smallint not null references PREMIO(cantidad),
primary key (cod_maquina,fecha,hora,cantidad)
)

create table PRODUCTOS
(
cod_producto char(12) not null primary key,
tipo_producto char(10) not null check (tipo_producto in ('Bebida','Bocadillo','Dulce','Fungible','Bolsa')),
precio smallmoney not null,
descripcion varchar(40) not null
)

create table PROVEEDOR
(
nombre_prov varchar(15) not null primary key,
domicilio varchar(25) not null,
cobro char(15) not null check (cobro in ('Efectivo','Cheque','Transferencia')),  
)

create table ALMACENA
(
nombre_prov varchar(15) not null references PROVEEDOR(nombre_prov),
cod_producto char(12) not null references PRODUCTOS(cod_producto),
stock smallint not null,
primary key (nombre_prov, cod_producto)
)

create table CARGA
(
cod_maquina char(8) not null references MAQUINA_P(cod_maquina),
nombre_prov varchar(15) not null references PROVEEDOR(nombre_prov),
cod_producto char(12) not null references PRODUCTOS(cod_producto),
primary key (cod_maquina, nombre_prov, cod_producto)
)

create table DISPONE
(
cod_maquina char(8) not null references MAQUINA_P(cod_maquina),
cod_producto char(12) not null references PRODUCTOS(cod_producto),
cantidad smallint not null,
primary key (cod_maquina, cod_producto)
)

create table VENDE
(
cod_maquina char(8) not null references MAQUINA_P(cod_maquina),
cod_producto char(12) not null references PRODUCTOS(cod_producto),
cantidad smallint not null,
primary key (cod_maquina, cod_producto)
)

create table CONTRATA
(
fecha_contrato smalldatetime not null, 
nombre_fab varchar(15) not null references FABRICANTE(nombre_fab),
cod_mecanico char(8) not null references MECANICO(cod_mecanico),
primary key (fecha_contrato, nombre_fab, cod_mecanico)
)

create table AVERIA
(
cod_averia char(12) not null primary key,
descripcion varchar(40) not null,
tiempo smalldatetime not null
)

create table REPARA
(
fecha_reparacion smalldatetime not null,
cod_maquina char(8) not null references MAQUINA(cod_maquina),
cod_mecanico char(8) not null references MECANICO(cod_mecanico),
cod_averia char(12) not null references AVERIA(cod_averia),
precio_reparacion smallint,
primary key (fecha_reparacion, cod_maquina, cod_mecanico, cod_averia)
)