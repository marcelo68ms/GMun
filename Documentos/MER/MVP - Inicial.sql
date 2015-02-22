--
-- ER/Studio 8.0 SQL Code Generation
-- Company :      SW2IT
-- Project :      OdonTODO_MVP.dm1
-- Author :       Marcelo Santos
--
-- Date Created : domingo, fevereiro 22, 2015 10:13:26
-- Target DBMS : MySQL 5.x
--

-- 
-- TABLE: TblPaciente 
--

CREATE TABLE TblPaciente(
    cdPaciente        BIGINT          AUTO_INCREMENT,
    dsNome            VARCHAR(60),
    dtNascimento      DATE,
    tpSexo            CHAR(1),
    dsEndereco        VARCHAR(100),
    dsBairro          VARCHAR(60),
    dsCidade          VARCHAR(60),
    dsCEP             CHAR(10),
    dsEstado          CHAR(2),
    dsRG              CHAR(14),
    dsCPF             CHAR(14),
    dsFoneFixo        VARCHAR(14),
    dsFoneCelular1    VARCHAR(14),
    dsFoneCelular2    VARCHAR(14),
    dsResponsavel     VARCHAR(60),
    dtCadastro        DATE,
    PRIMARY KEY (cdPaciente)
)ENGINE=INNODB
;



-- 
-- TABLE: TblProfissional 
--

CREATE TABLE TblProfissional(
    DSemail       VARCHAR(100)    NOT NULL,
    DSNome        VARCHAR(60)     NOT NULL,
    DSSenha       CHAR(10)        NOT NULL,
    dtCadastro    DATE,
    PRIMARY KEY (DSemail)
)ENGINE=INNODB
;



-- 
-- TABLE: TblProfissionalxPaciente 
--

CREATE TABLE TblProfissionalxPaciente(
    DSemail       VARCHAR(100)    NOT NULL,
    cdPaciente    BIGINT          NOT NULL,
    PRIMARY KEY (DSemail, cdPaciente)
)ENGINE=INNODB
;



-- 
-- INDEX: Ref11 
--

CREATE INDEX Ref11 ON TblProfissionalxPaciente(DSemail)
;
-- 
-- INDEX: Ref22 
--

CREATE INDEX Ref22 ON TblProfissionalxPaciente(cdPaciente)
;
-- 
-- TABLE: TblProfissionalxPaciente 
--

ALTER TABLE TblProfissionalxPaciente ADD CONSTRAINT RefTblProfissional1 
    FOREIGN KEY (DSemail)
    REFERENCES TblProfissional(DSemail)
;

ALTER TABLE TblProfissionalxPaciente ADD CONSTRAINT RefTblPaciente2 
    FOREIGN KEY (cdPaciente)
    REFERENCES TblPaciente(cdPaciente)
;


