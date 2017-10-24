
    create table cuenta (
        id integer generated by default as identity (start with 1),
        nombre varchar(255),
        periodos varbinary(255),
        primary key (id)
    )

    create table empresa (
        id integer generated by default as identity (start with 1),
        cuentas varbinary(255),
        nombre varchar(255),
        primary key (id)
    )

    create table indicador (
        id integer generated by default as identity (start with 1),
        formula varchar(255),
        nombre varchar(255),
        sePuedeBorrar boolean not null,
        usuario_id integer not null,
        primary key (id)
    )

    create table periodo (
        id integer not null,
        desde varchar(255),
        hasta varchar(255),
        valorCuenta integer not null,
        primary key (id)
    )

    create table usuario (
        idUsuario integer generated by default as identity (start with 1),
        contraseņa varchar(255),
        nombre varchar(255),
        primary key (idUsuario)
    )

    alter table indicador 
        add constraint FK_3laghgbgb3agc2a4xf5027b2f 
        foreign key (usuario_id) 
        references usuario
