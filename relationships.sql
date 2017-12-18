USE TecSos
	
--Creating main relationships
CREATE TABLE dir_colonia
(
	id_direccion INT,
	id_colonia INT,
	FOREIGN KEY (id_direccion) REFERENCES direccion (id_direccion) ON DELETE CASCADE,
	FOREIGN KEY (id_colonia) REFERENCES colonia (id_colonia) ON DELETE CASCADE
)
GO

CREATE TABLE colonia_ciudad
(
	id_colonia int,
	id_ciudad int,
	FOREIGN KEY (id_colonia) REFERENCES colonia (id_colonia) ON DELETE CASCADE,
	FOREIGN KEY (id_ciudad) REFERENCES ciudad (id_ciudad) ON DELETE CASCADE
)
GO

CREATE TABLE ciudad_estado
(
	id_ciudad int,
	id_estado int,
	FOREIGN KEY (id_ciudad) REFERENCES ciudad (id_ciudad) ON DELETE CASCADE,
	FOREIGN KEY (id_estado) REFERENCES estado (id_estado) ON DELETE CASCADE
)
GO

CREATE TABLE tel_cliente
(
	id_cliente INT,
	id_telefono INT,
	FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente) ON DELETE CASCADE,
	FOREIGN KEY (id_telefono) REFERENCES telefono (id_telefono) ON DELETE CASCADE
)
GO

CREATE TABLE tel_empleado
( 
	id_empleado INT,
	id_telefono INT,
	FOREIGN KEY (id_empleado) REFERENCES empleado (id_empleado) ON DELETE CASCADE,
	FOREIGN KEY (id_telefono) REFERENCES telefono (id_telefono) ON DELETE CASCADE
)
GO

CREATE TABLE dir_cliente
(
	id_cliente INT,
	id_direccion INT,
	FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente) ON DELETE CASCADE,
	FOREIGN KEY (id_direccion) REFERENCES direccion (id_direccion) ON DELETE CASCADE
)
GO

CREATE TABLE dir_empleado
(
	id_empleado INT,
	id_direccion INT,
	FOREIGN KEY (id_empleado) REFERENCES empleado (id_empleado) ON DELETE CASCADE,
	FOREIGN KEY (id_direccion) REFERENCES direccion (id_direccion) ON DELETE CASCADE
)
GO

CREATE TABLE dir_candidato
(
	id_candidato INT,
	id_direccion INT,
	FOREIGN KEY (id_candidato) REFERENCES candidato (id_candidato) ON DELETE CASCADE,
	FOREIGN KEY (id_direccion) REFERENCES direccion (id_direccion) ON DELETE CASCADE
)
GO

CREATE TABLE correo_empleado
(
	id_empleado INT,
	id_correo INT,
	FOREIGN KEY (id_empleado) REFERENCES empleado (id_empleado) ON DELETE CASCADE,
	FOREIGN KEY (id_correo) REFERENCES correo (id_correo) ON DELETE CASCADE
)
GO

CREATE TABLE correo_cliente
(
	id_cliente INT,
	id_correo INT,
	FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente) ON DELETE CASCADE,
	FOREIGN KEY (id_correo) REFERENCES correo (id_correo) ON DELETE CASCADE
)
GO

CREATE TABLE vacaciones
(
	id_empleado INT,
	FOREIGN KEY (id_empleado) REFERENCES empleado (id_empleado) ON DELETE CASCADE,
	fecha_inicio_vacaciones date NULL,
	fecha_fin_vacaciones 	date NULL,
	folio_vacaciones		varchar(10) NULL
	--el folio será el día y mes de inicio, el día y mes de finalizacion
	--y el primer y ultimo digito del rfc del empleado, todo concatenado
)
GO

CREATE TABLE faltas
(
	id_empleado INT,
	FOREIGN KEY (id_empleado) REFERENCES empleado (id_empleado) ON DELETE CASCADE,
	fecha_falta				date NULL,
	folio_falta		INT IDENTITY NOT NULL PRIMARY KEY		
	--el folio será el día y mes de la falta y el primer y ultimo digito del rfc del empleado, todo concatenado
)
GO



CREATE TABLE tel_candidato
(
	id_candidato INT,
	id_telefono INT,
	FOREIGN KEY (id_candidato) REFERENCES candidato (id_candidato) ON DELETE CASCADE,
	FOREIGN KEY (id_telefono) REFERENCES telefono (id_telefono) ON DELETE CASCADE
)
GO

CREATE TABLE correo_candidato
(
	id_candidato INT,
	id_correo INT,
	FOREIGN KEY (id_candidato) REFERENCES candidato(id_candidato) ON DELETE CASCADE,
	FOREIGN KEY (id_correo) REFERENCES correo (id_correo) ON DELETE CASCADE
)
GO


--Creating Contabilidad department's relationships
CREATE TABLE ingresos
(
	id_presupuesto INT,
	id_cuenta_bancaria INT,
	FOREIGN KEY (id_presupuesto) REFERENCES presupuesto_anual (id_presupuesto) ON DELETE CASCADE,
	FOREIGN KEY (id_cuenta_bancaria) REFERENCES cuenta_bancaria (id_cuenta_bancaria) ON DELETE CASCADE,
	cantidad_ing int NOT NULL,
	fecha_ingresos date NOT NULL,
	nom_ingresos varchar(20) NOT NULL
)
GO

CREATE TABLE egresos
(
	id_presupuesto INT,
	id_cuenta_bancaria INT,
	FOREIGN KEY (id_presupuesto) REFERENCES presupuesto_anual (id_presupuesto) ON DELETE CASCADE,
	FOREIGN KEY (id_cuenta_bancaria) REFERENCES cuenta_bancaria (id_cuenta_bancaria) ON DELETE CASCADE,
	cantidad_egresos int NOT NULL,
	fecha_egresos date NOT NULL,
	nom_egresos varchar(20) NOT NULL
)
GO

CREATE TABLE facturacion
(
	id_cliente INT,
	id_proyecto INT,
	FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente) ON DELETE CASCADE,
	FOREIGN KEY (id_proyecto) REFERENCES proyecto (id_proyecto) ON DELETE CASCADE,
	iva int NOT NULL
)
GO

--Creating Analisis department's relationships
CREATE TABLE client_project
(
	id_cliente INT,
	id_empleado INT,
	id_proyecto INT,
	FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente) ON DELETE CASCADE,
	FOREIGN KEY (id_empleado) REFERENCES empleado (id_empleado) ON DELETE CASCADE,
	FOREIGN KEY (id_proyecto) REFERENCES proyecto (id_proyecto) ON DELETE CASCADE
)
GO

CREATE TABLE project_d
(
	id_proyecto INT,
	id_diagrama INT,
	FOREIGN KEY (id_proyecto) REFERENCES proyecto (id_proyecto) ON DELETE CASCADE,
	FOREIGN KEY (id_diagrama) REFERENCES diagrama (id_diagrama) ON DELETE CASCADE
)
GO

CREATE TABLE project_cu
(
	id_caso_de_uso INT,
	id_proyecto INT,
	FOREIGN KEY (id_caso_de_uso) REFERENCES caso_de_uso (id_caso_de_uso) ON DELETE CASCADE,
	FOREIGN KEY (id_proyecto) REFERENCES proyecto (id_proyecto) ON DELETE CASCADE
)
GO
--Creating Marketing department's relationships
CREATE TABLE encargado_medio
(
	id_medio_comunicacion INT,
	id_encargado INT,
	FOREIGN KEY (id_medio_comunicacion) REFERENCES medio_comunicacion(id_medio_comunicacion) ON DELETE CASCADE,
	FOREIGN KEY (id_encargado) REFERENCES encargado(id_encargado) ON DELETE CASCADE
)
GO

CREATE TABLE evento_medio
(
	id_evento INT,
	id_medio_comunicacion INT,
	FOREIGN KEY (id_evento) REFERENCES evento(id_evento) ON DELETE CASCADE,
	FOREIGN KEY (id_medio_comunicacion) REFERENCES medio_comunicacion (id_medio_comunicacion) ON DELETE CASCADE,
	fecha_inicio date NOT NULL,
	fecha_fin date NOT NULL
)
GO
--Creating Ventas department's relationships
CREATE TABLE contrato
(
	id_cliente INT,
	id_medio_comunicacion INT,
	FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente) ON DELETE CASCADE,
	FOREIGN KEY (id_medio_comunicacion) REFERENCES medio_comunicacion (id_medio_comunicacion) ON DELETE CASCADE,
	fecha_inicio date NOT NULL,
	fecha_fin date NOT NULL
)
GO

CREATE TABLE venta_producto
(
	id_venta INT,
	id_producto INT,
	FOREIGN KEY (id_venta) REFERENCES venta (id_venta) ON DELETE CASCADE,
	FOREIGN KEY (id_producto) REFERENCES producto(id_producto) ON DELETE CASCADE,
	cantidad int NOT NULL
)
GO


--Creating Soporte department's relationships
CREATE TABLE cliente_problema
(
	id_cliente INT,
	id_problema INT,
	FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente) ON DELETE CASCADE,
	FOREIGN KEY (id_problema) REFERENCES problema (id_problema) ON DELETE CASCADE
)
GO

CREATE TABLE problema_solucion
(
	id_solucion INT,
	id_problema INT,
	FOREIGN KEY (id_solucion) REFERENCES solucion(id_solucion) ON DELETE CASCADE,
	FOREIGN KEY (id_problema) REFERENCES problema(id_problema) ON DELETE CASCADE
)
GO

CREATE TABLE problema_tecnico
(
	id_tecnico INT,
	id_problema INT,
	FOREIGN KEY (id_tecnico) REFERENCES tecnico(id_tecnico) ON DELETE CASCADE,
	FOREIGN KEY (id_problema) REFERENCES problema(id_problema) ON DELETE CASCADE
)
GO
--Creating Administración department's relationships
CREATE TABLE contratos_mkt
(
	id_recurso_economico INT,
	id_medio_comunicacion INT,
	FOREIGN KEY (id_recurso_economico) REFERENCES recurso_economico(id_recurso_economico) ON DELETE CASCADE,
	FOREIGN KEY (id_medio_comunicacion) REFERENCES medio_comunicacion (id_medio_comunicacion) ON DELETE CASCADE
)
GO

CREATE TABLE recursos_materiales
(
	id_equipo INT,
	id_recurso_economico INT,
	FOREIGN KEY (id_equipo) REFERENCES equipo(id_equipo) ON DELETE CASCADE,
	FOREIGN KEY (id_recurso_economico) REFERENCES recurso_economico(id_recurso_economico) ON DELETE CASCADE
)
GO

CREATE TABLE contratos_proyectos
(
	id_empleado INT,
	id_proyecto INT,
	id_recurso_economico INT,
	FOREIGN KEY (id_empleado) REFERENCES empleado(id_empleado) ON DELETE CASCADE,
	FOREIGN KEY (id_proyecto) REFERENCES proyecto(id_proyecto) ON DELETE CASCADE,
	FOREIGN KEY (id_recurso_economico) REFERENCES recurso_economico(id_recurso_economico) ON DELETE CASCADE,
	sueldo int NOT NULL,
	--if this field refers to the  of a employee salary, this field would
	--be better inside the empleado table. Not all the employees will have
	--the same salary
	fecha_inicio_contrato date NOT NULL,
	fecha_fin_contrato date NOT NULL
)
GO

--Creating Diseño department's relationships
CREATE TABLE usos
(
	id_caso_uso_pantalla INT,
	id_caso_de_uso INT,
	FOREIGN KEY (id_caso_uso_pantalla) REFERENCES caso_uso_pantalla(id_caso_uso_pantalla) ON DELETE CASCADE,
	FOREIGN KEY (id_caso_de_uso) REFERENCES caso_de_uso(id_caso_de_uso) ON DELETE CASCADE,
)
GO

CREATE TABLE demos
(
	id_caso_de_uso INT,
	FOREIGN KEY (id_caso_de_uso) REFERENCES caso_de_uso(id_caso_de_uso) ON DELETE CASCADE,
	nombre_demo varchar(50) NOT NULL,
	desc_demo varchar(250) NOT NULL,
	fecha_demo date NOT NULL
)
GO
--Creating Pruebas department's relationships
CREATE TABLE pruebas_cu
(
	id_caso_de_uso INT,
	id_prueba INT,
	FOREIGN KEY (id_caso_de_uso) REFERENCES caso_de_uso (id_caso_de_uso) ON DELETE CASCADE,
	FOREIGN KEY (id_prueba) REFERENCES prueba(id_prueba) ON DELETE CASCADE
)
GO

CREATE TABLE dias_horarios
(
	id_dia INT,
	id_empleado INT,
	horario varchar(20) NOT NULL,
	FOREIGN KEY(id_dia) REFERENCES dias(id_dia) ON DELETE CASCADE,
	FOREIGN KEY(id_empleado) REFERENCES empleado(id_empleado) ON DELETE CASCADE
		
)
GO

CREATE TABLE testeo
(
	id_error INT,
	id_prueba INT,
	FOREIGN KEY (id_error) REFERENCES error(id_error) ON DELETE CASCADE,
	FOREIGN KEY (id_prueba) REFERENCES prueba(id_prueba) ON DELETE CASCADE
)
GO
--Creating Desarrollo department's relationships
CREATE TABLE documentacion_proyecto
(
	id_documentacion INT,
	id_proyecto INT,
	FOREIGN KEY (id_documentacion) REFERENCES documentacion (id_documentacion) ON DELETE CASCADE,
	FOREIGN KEY (id_proyecto) REFERENCES proyecto (id_proyecto) ON DELETE CASCADE
)
GO

CREATE TABLE asistencia
(
	id_asistencia INT IDENTITY NOT NULL PRIMARY KEY,
	id_empleado int,	
	fecha_a date,
	FOREIGN KEY (id_empleado) references empleado (id_empleado) on delete cascade
)
GO

select count(fecha_a) from asistencia where id_empleado=1

select * from telefono

insert into asistencia(id_empleado, fecha_a) values(2, '2017-12-5')
insert into asistencia(id_empleado, fecha_a) values(2, '2017-12-4')
insert into asistencia(id_empleado, fecha_a) values(2, '2017-12-3')
insert into asistencia(id_empleado, fecha_a) values(2, '2017-12-2')
insert into asistencia(id_empleado, fecha_a) values(2, '2017-12-1')
insert into asistencia(id_empleado, fecha_a) values(2, '2017-12-7')
insert into asistencia(id_empleado, fecha_a) values(2, '2017-12-8')
insert into asistencia(id_empleado, fecha_a) values(2, '2017-12-9')
insert into asistencia(id_empleado, fecha_a) values(2, '2017-12-10')
insert into asistencia(id_empleado, fecha_a) values(2, '2017-12-11')
insert into asistencia(id_empleado, fecha_a) values(2, '2017-12-15')
insert into asistencia(id_empleado, fecha_a) values(2, '2017-12-5')
insert into asistencia(id_empleado, fecha_a) values(2, '2017-12-5')