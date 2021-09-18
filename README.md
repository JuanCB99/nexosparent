![Spring_boot](https://1.bp.blogspot.com/-35zJyw-bP1I/X4-8J5rR5uI/AAAAAAAAJbo/EXlqiwZieY0B5HcQlZQiwBbwwAjj8suJwCPcBGAYYCw/s1024/Microservicios.jpg)

<h1 align="center">Micro servicios Springboot</h1> <br>

<p align="center">
  El proyecto esta estructurado eb maven, para gestionar librerias redundantes en cada microservicio, hay un test unitario en el microservicio de mercancia pero
  no es posible ejecutarlo, dado que netBeans no me dejaba importar la clase enteidad para pioderla usar en el test, a continuación dejo en las consultas sql usadas para la BD.
</p>

<h2 align="center">Consultas SQL</h2> <br>

<p>
create table cargos(id_cargo serial PRIMARY KEY,
				   nombre_cargo varchar(60) UNIQUE);

create table usuarios(id_usuario serial PRIMARY KEY,
					 nombre_usuario varchar(45) UNIQUE NOT NULL,
					 edad_usuario int NOT NULL,
					 fecha_ingreso_usuario date NOT NULL,
					 cargo_id int NOT NULL,
					 CONSTRAINT fk_cargo_usuario FOREIGN KEY (cargo_id) REFERENCES cargos (id_cargo),
					 CONSTRAINT ch_fecha_maxima_check CHECK (fecha_ingreso_usuario <= CURRENT_DATE));

create table mercancia(id_producto serial PRIMARY KEY,
					  nombre_producto varchar(60) UNIQUE NOT NULL,
					  cantidad_producto int NOT NULL,
					  fecha_ingreso_producto date NOT NULL,
					  usuario_id int NOT NULL,
					  CONSTRAINT fk_usuario_mercancia FOREIGN KEY (usuario_id) REFERENCES usuarios (id_usuario),
					  CONSTRAINT ch_fecha_maxima_check CHECK (fecha_ingreso_producto <= CURRENT_DATE));


create table registroedicion(id_registro serial PRIMARY KEY,
				   nombre_cargo varchar(60) UNIQUE);

					  
insert into cargos(nombre_cargo) values('Asesor de ventas');
insert into cargos(nombre_cargo) values('Administrador');
insert into cargos(nombre_cargo) values('Sopórte');
					  
insert into usuarios(nombre_usuario,edad_usuario,fecha_ingreso_usuario,cargo_id) values('Juan', 22, '2021-05-20',1);
insert into usuarios(nombre_usuario,edad_usuario,fecha_ingreso_usuario,cargo_id) values('Valentina', 23, '2020-10-1',2);
insert into usuarios(nombre_usuario,edad_usuario,fecha_ingreso_usuario,cargo_id) values('Diego', 20, '2019-02-15',3);

insert into mercancia(nombre_producto,cantidad_producto,fecha_ingreso_producto,usuario_id) values('TRANSMISION', 45, '2020-05-15', 1);
insert into mercancia(nombre_producto,cantidad_producto,fecha_ingreso_producto,usuario_id) values('BATERIAS', 22, '2019-09-18', 1);
insert into mercancia(nombre_producto,cantidad_producto,fecha_ingreso_producto,usuario_id) values('LLANTAS', 60, '2020-12-15', 2);
insert into mercancia(nombre_producto,cantidad_producto,fecha_ingreso_producto,usuario_id) values('FAROS DELANTEROS', 34, '2021-01-10', 2);
insert into mercancia(nombre_producto,cantidad_producto,fecha_ingreso_producto,usuario_id) values('PANORAMICO', 25, '2021-04-04', 3);
insert into mercancia(nombre_producto,cantidad_producto,fecha_ingreso_producto,usuario_id) values('MOTOR', 12, '2020-09-17', 1);
</p>



## Requisitos
Para correr esta aplicación en local sin ningun problema, se detallan los requisitos a continuación.


### Local
* [Java 8 JDK o superior](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) -Obligatorio
* [Netbeans](https://www.oracle.com/technetwork/java/javase/downloads/jdk-netbeans-jsp-3413139-esa.html) - Opcional puede usar otro IDE 

## Probar en local

* Primero debe iniciar el microservicio de eureka, dado que este descubre los demas microservicios, (se recomienda ejecutar este servicio mediante consola para evitar problemas)
despues de iniciar ese servicio, puede iniciar los demas sin orden en especifico, una ves esten funcionando podra probarlos con swagger mediante este link 
http://localhost:PUERTODELMICROSERVICIO/swagger-ui/index.html
