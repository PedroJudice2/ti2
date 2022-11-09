/* Lógico 1.2: */

CREATE TABLE Golpes (
    Nome VARCHAR(20) UNIQUE,
    Tipo VARCHAR(15),
    Periculosidade VARCHAR(5),
    Frequencia VARCHAR(5),
    Id SERIAL PRIMARY KEY
);

CREATE TABLE Pessoa (
    Id SERIAL PRIMARY KEY,
    Login VARCHAR(50) UNIQUE,
    Email VARCHAR(50),
    Password VARCHAR(50),
    Status BOOLEAN
);

CREATE TABLE Comentario (
    Data DATE,
    Texto VARCHAR(400),
    Id SERIAL PRIMARY KEY
);

CREATE TABLE Possui_Golpes_Comentario_Pessoa (
    fk_Golpes_Id SERIAL,
    fk_Comentario_Id SERIAL,
    fk_Pessoa_Id SERIAL
);
 
ALTER TABLE Possui_Golpes_Comentario_Pessoa ADD CONSTRAINT FK_Possui_Golpes_Comentario_Pessoa_1
    FOREIGN KEY (fk_Golpes_Id)
    REFERENCES Golpes (Id)
    ON DELETE NO ACTION;
 
ALTER TABLE Possui_Golpes_Comentario_Pessoa ADD CONSTRAINT FK_Possui_Golpes_Comentario_Pessoa_2
    FOREIGN KEY (fk_Comentario_Id)
    REFERENCES Comentario (Id)
    ON DELETE NO ACTION;
 
ALTER TABLE Possui_Golpes_Comentario_Pessoa ADD CONSTRAINT FK_Possui_Golpes_Comentario_Pessoa_3
    FOREIGN KEY (fk_Pessoa_Id)
    REFERENCES Pessoa (Id)
    ON DELETE NO ACTION;