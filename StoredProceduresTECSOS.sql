	
CREATE PROCEDURE SP_Insert_User
@user as varchar(20),
@pass as varchar(20),
@rol as varchar(20),
@regDate as date
AS
BEGIN
	INSERT INTO users ([correo], [pass], [rol], [fecha_registro], [active_us]) 
				values (@user, @pass, @rol, @regDate, 1)
END
GO

CREATE PROCEDURE SP_Login
@user as varchar(20),
@pass as varchar(20)
AS
BEGIN
	SELECT * FROM users WHERE correo = @user AND pass = @pass
END
GO

CREATE PROCEDURE SP_Search_User
@user as varchar(20)
AS
BEGIN
	SELECT * FROM dbo.users WHERE correo = @user
END
GO

CREATE PROCEDURE SP_Delete_User
@user as varchar(20)
AS
BEGIN
	DELETE FROM users where correo = @user
END
GO

CREATE PROCEDURE SP_Delete_User_ID
@id as varchar(3)
AS
BEGIN
	DELETE FROM users WHERE id_user=@id
END
GO

CREATE PROCEDURE SP_Search_User_ID
@id as varchar(20)
AS
BEGIN
	SELECT * FROM users WHERE id_user=@id
END
GO

CREATE PROCEDURE SP_Search_Active_Users
AS
BEGIN
	SELECT * FROM USERS WHERE active_us = 1
END
GO

CREATE PROCEDURE SP_Search_User_Like
@username as varchar(20)
AS
BEGIN
	SELECT * FROM users WHERE correo LIKE @username+'%' AND active_us=1
END
GO

CREATE PROCEDURE SP_CHANGE_STATUS_USER
@id as varchar(20)
AS
BEGIN
	IF (SELECT active_us FROM users WHERE id_user=@id) = 1
		BEGIN
			UPDATE users SET active_us = 0 WHERE id_user=@id
		END
	ELSE
		BEGIN
			UPDATE users SET active_us = 1 WHERE id_user=@id
		END
END
GO

CREATE PROCEDURE SP_Update_User
@id as varchar(3),
@username as varchar(20),
@password as varchar(20)
AS
BEGIN
	UPDATE users SET correo = @username, pass = @password where id_user = @id
END
GO

CREATE PROCEDURE SP_Show_Candidatos
AS
BEGIN
	SELECT * FROM candidato WHERE active_can = 1
END
GO

CREATE PROCEDURE SP_Insert_Candidato
@nom_can as varchar(50),
@app_can as varchar(50),
@apm_can as varchar(50),
@experiencia_can as varchar(20),
@expectativa_salarial as int,
@puesto_deseado as varchar(50)
AS
BEGIN
	INSERT INTO candidato ([nom_can], [app_can], [apm_can], [experiencia_can], [expectativa_salarial], [puesto_deseado], [active_can])
	values (@nom_can, @app_can, @apm_can, @experiencia_can, @expectativa_salarial, @puesto_deseado, 1)
END
GO

CREATE PROCEDURE SP_Delete_Candidato
@id_candidato as varchar(3)
AS
BEGIN
	DELETE FROM candidato WHERE id_candidato= @id_candidato
END
GO

CREATE PROCEDURE SP_Update_Candidato
@id_candidato as varchar(3),
@nom_can as varchar(50),
@app_can as varchar(50),
@apm_can as varchar(50),
@experiencia_can as varchar(20),
@expectativa_salarial as int,
@puesto_deseado as varchar(50)
AS
BEGIN
	UPDATE candidato SET nom_can = @nom_can, app_can = @app_can, apm_can = @apm_can, experiencia_can=@experiencia_can,
	expectativa_salarial=@expectativa_salarial, puesto_deseado=@puesto_deseado
	 where id_candidato = @id_candidato
END
GO

CREATE PROCEDURE SP_Search_Candidato_Like
@name as varchar(50)
AS
BEGIN
	SELECT * FROM candidato  WHERE nom_can LIKE @name+'%' 
END
GO

CREATE PROCEDURE SP_Insert_Phone
@number as varchar(20),
@ext as varchar(20),
@desc as varchar (50)
AS
BEGIN
	INSERT INTO telefono ([numero], [extension], [desc_tel]) values (@number, @ext, @desc)
END
GO

CREATE PROCEDURE SP_Phone_Candidato
@id_candidato as varchar(4)
AS
BEGIN
	INSERT INTO tel_candidato(id_candidato, id_telefono) values(@id_candidato, (SELECT IDENT_CURRENT('telefono')))
END
GO

CREATE PROCEDURE SP_Insert_Email
@email as varchar(50),
@desc as varchar(50)
AS
BEGIN
	INSERT INTO correo([email],[desc_correo]) values(@email, @desc)
END
GO


CREATE PROCEDURE SP_Email_Candidato
@id_candidato as varchar(4)
AS
BEGIN
	INSERT INTO correo_candidato(id_candidato, id_correo) values(@id_candidato, (SELECT IDENT_CURRENT('correo')))
END
GO

CREATE PROCEDURE SP_Email_Candidato
@id_cuenta as varchar(4)
AS
BEGIN
	INSERT INTO ingresos(id_cuenta_bancaria, id_presupuesto) values(@id_cuenta, (SELECT IDENT_CURRENT('presupuesto')))
END
GO
|



CREATE PROCEDURE SP_Insert_Add_Can
@id_candidato as varchar(4)
AS
BEGIN
	INSERT INTO dir_candidato([id_candidato],[id_direccion]) values(@id_candidato, (SELECT IDENT_CURRENT('direccion')))
END
GO

SELECT IDENT_CURRENT('direccion')
select * from direccion

CREATE PROCEDURE SP_Get_Phones
@id_candidato as varchar(4)
AS
BEGIN
	SELECT * FROM candidato JOIN tel_candidato ON candidato.id_candidato = tel_candidato.id_candidato JOIN telefono on 
	tel_candidato.id_telefono = telefono.id_telefono where candidato.id_candidato=@id_candidato
END
GO

CREATE PROCEDURE SP_Get_Email_Candidato
@id_candidato as varchar(4)
AS
BEGIN
	SELECT correo.id_correo, email, desc_correo FROM candidato JOIN correo_candidato ON candidato.id_candidato = correo_candidato.id_candidato JOIN correo on 
	correo_candidato.id_correo= correo.id_correo WHERE candidato.id_candidato=@id_candidato
END
GO

/*
CREATE PROCEDURE SP_Get_Id_Address_Candidato
@id_candidato as varchar(4)
AS
BEGIN
	SELECT direccion.id_direccion FROM candidato JOIN dir_candidato ON dir_candidato.id_candidato = candidato.id_candidato JOIN direccion 
	ON dir_candidato.id_direccion=direccion.id_direccion WHERE candidato.id_candidato = @id_candidato
END
GO
*/



CREATE PROCEDURE SP_Get_Tel_Candidato
@id_candidato as varchar(4)
AS
BEGIN
	SELECT telefono.id_telefono, numero, extension, desc_tel FROM telefono JOIN tel_candidato ON telefono.id_telefono= tel_candidato.id_telefono 
	JOIN candidato on tel_candidato.id_candidato= candidato.id_candidato WHERE candidato.id_candidato=@id_candidato
END
GO

/*CREATE SP TO GET PEOPLE'S ADDRESSES*/


CREATE PROCEDURE SP_Update_Phone
@id_tel as varchar(4),
@number as varchar(20),
@ext as varchar(20),
@desc as varchar (50)
AS
BEGIN
	UPDATE telefono SET numero=@number, desc_tel=@desc, extension=@ext WHERE id_telefono=@id_tel
END
GO

CREATE PROCEDURE SP_Update_Email
@id_email as varchar(4),
@email as varchar(20),
@desc as varchar (50)
AS
BEGIN
	UPDATE correo SET email=@email, desc_correo=@desc WHERE id_correo=@id_email
END
GO


CREATE PROCEDURE SP_Delete_Tel_Candidato
@id_tel as varchar(4)
AS
BEGIN
	DELETE FROM tel_candidato where id_telefono = @id_tel 
	DELETE FROM telefono WHERE id_telefono = @id_tel
	
END
GO


/*STORED PROCEDURES TO HIRE A APPLICANT*/
CREATE PROCEDURE SP_Delete_Rel_Tel_Can
@id_candidato as varchar(4)
AS
BEGIN
	DELETE FROM tel_candidato WHERE id_candidato = @id_candidato
END
GO

CREATE PROCEDURE SP_Delete_Rel_Email_Can
@id_candidato as varchar(4)
AS
BEGIN
	DELETE FROM correo_candidato WHERE id_candidato = @id_candidato
END
GO

CREATE PROCEDURE SP_Delete_Rel_Dir_Can
@id_candidato as varchar(4)
AS
BEGIN
	DELETE FROM dir_candidato WHERE id_candidato=@id_candidato
END
GO

CREATE PROCEDURE SP_Insert_Rel_Tel_Emp
@id_tel as varchar(4)
AS
BEGIN
	INSERT INTO tel_empleado(id_empleado, id_telefono) values((SELECT IDENT_CURRENT('empleado')),@id_tel)
END
GO

CREATE PROCEDURE SP_Insert_Rel_Email_Emp	
@id_correo as varchar(4)
AS
BEGIN
	INSERT INTO correo_empleado(id_empleado, id_correo) values((SELECT IDENT_CURRENT('empleado')), @id_correo)
END
GO

CREATE PROCEDURE SP_Insert_Rel_Dir_Emp
@id_direccion as varchar(4)
AS
BEGIN
	INSERT INTO dir_empleado(id_empleado, id_direccion) VALUES((SELECT IDENT_CURRENT('empleado')), @id_direccion)
END
GO


CREATE PROCEDURE SP_Insert_Empleado
@nom_e as varchar(50),
@app_e as varchar(50),
@apm_e as varchar(50),
@rfc_e as varchar(15),
@sueldo as int,
@puesto as varchar(50),
@genero_e as varchar(9)
AS
BEGIN
	INSERT INTO empleado([nom_e],[app_e],[apm_e],[rfc_e], [sueldo], [puesto],[genero_e],[active_em])
	values(@nom_e, @app_e, @apm_e, @rfc_e, @sueldo, @puesto, @genero_e, 1)
END
GO



CREATE PROCEDURE SP_Insert_Address
@calle as varchar(50),
@numInt as varchar(4),
@numExt as varchar(4),
@colonia as varchar(50),
@cp as varchar(6),
@ciudad as varchar(50),
@estado as varchar(50)
AS 
BEGIN
	INSERT INTO direccion([calle],[num_int],[num_ext]) VALUES(@calle, @numInt, @numExt)
	INSERT INTO colonia([nom_col],[cp]) VALUES(@colonia, @cp)
	/*RELACION: Seleccionar último ID de una tabla (SELECT IDENT_CURRENT('nombre_tabla'))*/
	INSERT INTO dir_colonia([id_colonia],[id_direccion]) VALUES((SELECT IDENT_CURRENT('colonia')), (SELECT IDENT_CURRENT('direccion')))
	INSERT INTO ciudad([nom_ciudad]) VALUES(@ciudad)
	/*RELACIÓN*/
	INSERT INTO colonia_ciudad([id_ciudad],[id_colonia]) VALUES((SELECT IDENT_CURRENT('ciudad')), (SELECT IDENT_CURRENT('colonia')))
	INSERT INTO estado([nom_estado]) VALUES (@estado)
	/*RELACIÓN*/
	INSERT INTO ciudad_estado([id_ciudad],[id_estado]) VALUES((SELECT IDENT_CURRENT('ciudad')), (SELECT IDENT_CURRENT('estado')))
END
GO

CREATE PROCEDURE SP_Update_Address
@id_direccion as varchar(4),
@calle as varchar(50),
@numInt as varchar(4),
@numExt as varchar(4),
@colonia as varchar(50),
@cp as varchar(6),
@ciudad as varchar(50),
@estado as varchar(50)
AS
BEGIN
	DECLARE @id_colonia as INT
	DECLARE @id_ciudad AS INT
	DECLARE @id_estado AS INT

	SELECT @id_colonia = colonia.id_colonia FROM colonia JOIN dir_colonia ON dir_colonia.id_colonia=colonia.id_colonia WHERE dir_colonia.id_direccion=@id_direccion
	SELECT @id_ciudad = ciudad.id_ciudad FROM ciudad JOIN colonia_ciudad ON colonia_ciudad.id_ciudad = ciudad.id_ciudad WHERE colonia_ciudad.id_colonia=@id_colonia
	SELECT @id_estado = estado.id_estado FROM estado JOIN ciudad_estado ON ciudad_estado.id_estado = estado.id_estado WHERE ciudad_estado.id_ciudad =@id_ciudad

	UPDATE direccion SET calle = @calle, num_int = @numInt, num_ext = @numExt WHERE id_direccion=@id_direccion
	UPDATE colonia SET nom_col = @colonia, cp=@cp WHERE id_colonia = @id_colonia
	UPDATE ciudad SET nom_ciudad = @ciudad WHERE id_ciudad=@id_ciudad
	UPDATE estado SET nom_estado = @estado WHERE id_estado = @id_estado
END
GO


/*
SELECT * FROM candidato JOIN tel_candidato ON candidato.id_candidato = tel_candidato.id_candidato JOIN telefono on 
	tel_candidato.id_telefono = telefono.id_telefono where candidato.id_candidato=@id_candidato

SELECT direccion.id_direccion, calle, num_int, num_ext, nom_col, cp, nom_ciudad, nom_estado FROM direccion JOIN dir_colonia ON direccion.id_direccion=dir_colonia.id_direccion JOIN colonia ON 
	dir_colonia.id_colonia = colonia.id_colonia JOIN colonia_ciudad ON colonia.id_colonia = colonia_ciudad.id_colonia JOIN
	ciudad ON colonia_ciudad.id_ciudad = ciudad.id_ciudad JOIN ciudad_estado ON ciudad.id_ciudad=ciudad_estado.id_ciudad JOIN
	estado ON ciudad_estado.id_estado=estado.id_estado
GO
*/

CREATE PROCEDURE SP_Get_Add_Candidato
@id_candidato as varchar(4)
AS
BEGIN
	SELECT direccion.id_direccion, calle, num_int, num_ext, nom_col, cp, nom_ciudad, nom_estado FROM candidato JOIN dir_candidato 
	ON dir_candidato.id_candidato = candidato.id_candidato JOIN direccion ON direccion.id_direccion=dir_candidato.id_direccion 
	JOIN dir_colonia ON direccion.id_direccion=dir_colonia.id_direccion JOIN colonia ON 
	dir_colonia.id_colonia = colonia.id_colonia JOIN colonia_ciudad ON colonia.id_colonia = colonia_ciudad.id_colonia JOIN
	ciudad ON colonia_ciudad.id_ciudad = ciudad.id_ciudad JOIN ciudad_estado ON ciudad.id_ciudad=ciudad_estado.id_ciudad JOIN
	estado ON ciudad_estado.id_estado=estado.id_estado WHERE candidato.id_candidato=@id_candidato
END
GO

CREATE PROCEDURE SP_Search_Empleado_Like
@nombre as varchar(50)
AS
BEGIN
	SELECT * FROM empleado WHERE nom_e LIKE @nombre+'%' AND active_em=1
END
GO
CREATE PROCEDURE SP_Get_Email_Empleado
@id_empleado as varchar(4)
AS
BEGIN
	SELECT correo.id_correo, email, desc_correo FROM empleado JOIN correo_empleado ON empleado.id_empleado = correo_empleado.id_empleado JOIN correo on 
	correo_empleado.id_correo= correo.id_correo WHERE empleado.id_empleado=@id_empleado
END
GO

CREATE PROCEDURE SP_Get_Tel_Empleado
@id_empleado as varchar(4)
AS
BEGIN
	SELECT telefono.id_telefono, numero, extension, desc_tel FROM telefono JOIN tel_empleado ON telefono.id_telefono= tel_empleado.id_telefono 
	JOIN empleado on tel_empleado.id_empleado= empleado.id_empleado WHERE empleado.id_empleado=@id_empleado
END
GO

CREATE PROCEDURE SP_Get_Add_Empleado
@id_empleado as varchar(4)
AS
BEGIN
	SELECT direccion.id_direccion, calle, num_int, num_ext, nom_col, cp, nom_ciudad, nom_estado FROM empleado JOIN dir_empleado 
	ON dir_empleado.id_empleado = empleado.id_empleado JOIN direccion ON direccion.id_direccion=dir_empleado.id_direccion 
	JOIN dir_colonia ON direccion.id_direccion=dir_colonia.id_direccion JOIN colonia ON 
	dir_colonia.id_colonia = colonia.id_colonia JOIN colonia_ciudad ON colonia.id_colonia = colonia_ciudad.id_colonia JOIN
	ciudad ON colonia_ciudad.id_ciudad = ciudad.id_ciudad JOINid ciudad_estado ON ciudad.id_ciudad=ciudad_estado.id_ciudad JOIN
	estado ON ciudad_estado.id_estado=estado.id_estado WHERE empleado.id_empleado=@id_empleado
END
GO

CREATE PROCEDURE SP_Update_Empleado
@id_empleado as varchar(4),
@name as varchar(50),
@app as varchar(50),
@apm as varchar(50),
@rfc as varchar(15),
@sueldo as int,
@puesto as varchar(50),
@genero as varchar(9)
AS
BEGIN
	UPDATE empleado SET nom_e=@name, app_e = @app, apm_e = @apm, rfc_e = @rfc, sueldo = @sueldo, 
		puesto = @puesto, genero_e = @genero WHERE id_empleado=@id_empleado
END
GO


CREATE PROCEDURE SP_COUNT_ASISTENCIAS
@id_empleado as varchar(4)
AS
BEGIN
	SELECT COUNT(fecha_a) FROM asistencia WHERE id_empleado=@id_empleado
END
GO

CREATE PROCEDURE SP_COUNT_FALTAS
@id_empleado as varchar(4)
AS
BEGIN
	SELECT COUNT(fecha_falta) FROM faltas WHERE id_empleado = @id_empleado
END
GO

SELECT COUNT(fecha_a) FROM asistencia WHERE id_empleado=1

CREATE PROCEDURE SP_Insert_Falta
@id_empleado as varchar(4),
@fecha as date
AS
BEGIN
	INSERT INTO faltas(id_empleado, fecha_falta) values(@id_empleado, @fecha)
END
GO

CREATE PROCEDURE SP_Entrada
@id_empleado as varchar(4),
@fecha as date
AS
BEGIN
	INSERT INTO asistencia(id_empleado, fecha_a) values(@id_empleado, @fecha)
END
GO





select * from asistencia

/*
CREATE PROCEDURE SP_ADD_FALTA
@id_empleado as varchar(4),
@fecha_falta as date,
@folio_vacaciones()*/