--Creating main tables

--Add a status field to cliente, users and empleados tables. This to disable
--that record and not to delete it. to keep the information.
use TecSos
CREATE TABLE cliente
(
	id_cliente INT IDENTITY NOT NULL PRIMARY KEY,
	nom_c varchar(50) NULL,
	app_c varchar(50) NULL,
	apm_c varchar(50) NULL,
	rfc_c varchar(15) NULL,
	genero_c varchar(9) NULL,
	active_cl BIT NOT NULL
)
GO

CREATE TABLE empleado
(
	id_empleado INT IDENTITY NOT NULL PRIMARY KEY,
	nom_e varchar(50) NOT NULL,
	app_e varchar(50) NOT NULL,
	apm_e varchar(50) NOT NULL,
	rfc_e varchar(15) NOT NULL,
	sueldo INT NOT NULL,
	puesto varchar(50) NOT NULL,
	genero_e varchar(9) NOT NULL,
	horario varchar(30) NOT NULL,
	active_em BIT NOT NULL
)
GO



CREATE TABLE dias
(
	id_dia INT IDENTITY NOT NULL PRIMARY KEY,
	dia varchar(10)
)

CREATE TABLE candidato
(
	id_candidato INT IDENTITY NOT NULL PRIMARY KEY,
	nom_can varchar(50) NOT NULL,
	app_can varchar(50) NOT NULL,
	apm_can varchar(50) NOT NULL,
	experiencia_can varchar(20) NOT NULL,
	expectativa_salarial INT NOT NULL,
	puesto_deseado varchar(50) NOT NULL,
	active_can BIT NOT NULL
)
GO

CREATE TABLE direccion
(
	id_direccion INT IDENTITY NOT NULL PRIMARY KEY,
	calle varchar(50) NULL,
	num_int varchar(50) NULL,
	num_ext varchar(50) NULL
)
GO

CREATE TABLE colonia
(
	id_colonia INT IDENTITY NOT NULL PRIMARY KEY,
	nom_col varchar(50) NULL,
	cp varchar(6) NULL
)
GO

CREATE TABLE ciudad
(
	id_ciudad INT IDENTITY NOT NULL PRIMARY KEY,
	nom_ciudad varchar(50) NULL
)
GO

CREATE TABLE estado
(
	id_estado  INT IDENTITY NOT NULL PRIMARY KEY,
	nom_estado varchar(50) NULL
)
GO

CREATE TABLE telefono
(
	id_telefono INT IDENTITY NOT NULL PRIMARY KEY,
	numero  	varchar(20) NULL,
	extension 	varchar(20) NULL,
	desc_tel 	varchar(50) NULL
)
GO

CREATE TABLE correo
(
	id_correo 	INT IDENTITY NOT NULL PRIMARY KEY,
	email  		varchar(50) NULL,
	desc_correo varchar(50) NULL
)
GO

CREATE TABLE users
(
	id_user 		INT IDENTITY NOT NULL PRIMARY KEY,
	correo  		varchar(20) NULL,
	pass 			varchar(20) NULL,
	rol 			varchar(20) NOT NULL,
	fecha_registro	date NULL,
	active_us 		BIT NOT NULL
)
GO

--Creating Contabilidad department's tables
CREATE TABLE cuenta_bancaria
(
	id_cuenta_bancaria INT IDENTITY NOT NULL PRIMARY KEY,
	banco 			   varchar(50) NULL
)
GO

CREATE TABLE presupuesto_anual
(
	id_presupuesto 	INT IDENTITY NOT NULL PRIMARY KEY,
	nom_presupuesto varchar(50) NULL,
	cantidad_total	INT NOT NULL
)
GO

--Creating Analisis department's tables
CREATE TABLE proyecto
(
	id_proyecto 			INT IDENTITY NOT NULL PRIMARY KEY,
	nom_pro 			  	varchar(50) NOT NULL,
	fecha_inicio_proyecto 	date NOT NULL,
	fecha_entrega_proyecto 	date NULL,
	costo_proyecto  		INT NOT NULL,
	avances 				varchar(50) NULL,
	--why do we have avances in this table? avances has to be in a 
	--different table and be connected to this table
	active_pr 				BIT NOT NULL
)
GO

CREATE TABLE caso_de_uso
(
	id_caso_de_uso 			INT IDENTITY NOT NULL PRIMARY KEY,
	nombre_cu 			  	varchar(50) NOT NULL,
	fecha_mod_cu		 	date NOT NULL,
	desc_cu 				varchar(250) NULL
)
GO

CREATE TABLE diagrama
(
	id_diagrama 			INT IDENTITY NOT NULL PRIMARY KEY,
	nombre_diagrama		  	varchar(50) NOT NULL,
	fecha_mod_dia		 	date NOT NULL,
	desc_dia 				varchar(250) NULL
)
GO

--Creating Mercadotecnia deparment's tables
CREATE TABLE evento
(
	id_evento	 			INT IDENTITY NOT NULL PRIMARY KEY,
	nombre_evento		  	varchar(50) NOT NULL,
	lugar_evento		 	varchar(50) NOT NULL,
	razon_evento 			varchar(50) NOT NULL
)
GO

CREATE TABLE encargado
(
	id_encargado 			INT IDENTITY NOT NULL PRIMARY KEY,
	nombre_encargado	  	varchar(50) NOT NULL,
	app_en				  	varchar(50) NOT NULL,
	apm_en				  	varchar(50) NOT NULL,
	rfc_en				  	varchar(50) NOT NULL,
	puesto_encargado	  	varchar(50) NOT NULL
)
GO

CREATE TABLE medio_comunicacion
(
	id_medio_comunicacion	INT IDENTITY NOT NULL PRIMARY KEY,
	nombre_medio		  	varchar(50) NOT NULL,
	cantidad_repeticiones 	INT NOT NULL,
	tipo_medio			  	varchar(50) NOT NULL,
	costo_medio			  	INT NOT NULL,
	fecha_inicio_medio 		date NOT NULL,
	fecha_fin_medio 		date NOT NULL
)
GO

-- Creating Ventas department's tables

CREATE TABLE venta
(
	id_venta				INT IDENTITY NOT NULL PRIMARY KEY,
	fecha_venta		 		date NOT NULL,
	monto_final 			INT NOT NULL,
)
GO

CREATE TABLE producto
(
	id_producto				INT IDENTITY NOT NULL PRIMARY KEY,
	nombre_producto 		varchar(50) NOT NULL,
	precio					INT NOT NULL,
	tipo 					varchar(50) NOT NULL,
	marca					varchar(50) NOT NULL,
	cantidad 				INT NOT NULL	
)
GO



--Creating Soporte department's tables
CREATE TABLE tecnico
--why tf does this table only has name and last name?
(
	id_tecnico				INT IDENTITY NOT NULL PRIMARY KEY,
	nom_tec					varchar(50) NOT NULL,
	app_tec		 			varchar(50) NOT NULL,
	apm_tec		 			varchar(50) NOT NULL
)
GO

CREATE TABLE problema
(
	id_problema				INT IDENTITY NOT NULL PRIMARY KEY,
	detalle					varchar(50) NOT NULL,
	fecha_inicio_problema	date NOT NULL,
	origen_problema			varchar(50) NOT NULL,
	desc_problema			varchar(250) NULL,
	observacion_tecnico		varchar(250) NULL,
)
GO

CREATE TABLE solucion
(
	id_solucion				INT IDENTITY NOT NULL PRIMARY KEY,
	desc_problema			varchar(250) NOT NULL,
	fecha_inicio_problema	date NOT NULL
)
GO

--Creating Administracion department's tables
CREATE TABLE equipo
(
	id_equipo				INT IDENTITY NOT NULL PRIMARY KEY,
	estado_maq				varchar(250) NOT NULL,
	num_maq					INT NOT NULL,
	tipo_maq				varchar(50) NOT NULL,
	desc_maq				varchar(50) NOT NULL
)
GO

CREATE TABLE recurso_economico
(
	id_recurso_economico	INT IDENTITY NOT NULL PRIMARY KEY,
	presupuesto				INT NOT NULL
)
GO

--Creating Diseño deparment's tables
CREATE TABLE caso_uso_pantalla
(
	id_caso_uso_pantalla	INT IDENTITY NOT NULL PRIMARY KEY,
	desc_pantalla			varchar(250) NOT NULL,
	fecha_salida			date NOT NULL
)
GO


--Creating Pruebas department's tables
CREATE TABLE prueba
(
	id_prueba			INT IDENTITY NOT NULL PRIMARY KEY,
	comentarios			varchar(250) NOT NULL,
	fecha_prueba		date NOT NULL
)
GO

CREATE TABLE error
(
	id_error			INT IDENTITY NOT NULL PRIMARY KEY,
	desc_error			varchar(250) NOT NULL,
	nombre_error		varchar(250) NOT NULL,
	status				varchar(50) NOT NULL,
)
GO

--Creating desarrollo Deparment's table
CREATE TABLE documentacion
(
	id_documentacion	INT IDENTITY NOT NULL PRIMARY KEY,
	desc_error			varchar(250) NOT NULL,
	nombre_error		varchar(250) NOT NULL,
	status				varchar(50) NOT NULL,
)
GO












































































