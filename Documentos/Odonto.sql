--
-- ER/Studio 8.0 SQL Code Generation
-- Company :      SW2
-- Project :      ODONTODO.DM1
-- Author :       marcelo
--
-- Date Created : Sunday, May 18, 2014 19:44:51
-- Target DBMS : MySQL 5.x
--

-- 
-- TABLE: TblFonecedor 
--

CREATE TABLE TblFornecedor(
    NrCNPJ             CHAR(14)        NOT NULL,
    nmRazaoSocial      VARCHAR(100)    NOT NULL,
    DsEndereco         VARCHAR(100),
    DsCidade           VARCHAR(60),
    DsEstado           CHAR(2),
    DsCEP              CHAR(10),
    DsInscEstadual     VARCHAR(15),
    DsInscMunicipal    VARCHAR(16),
    DtCadastro         DATETIME,
    PRIMARY KEY (NrCNPJ)
)ENGINE=MYISAM
;



-- 
-- TABLE: TblMaterial 
--

CREATE TABLE TblMaterial(
    CdMaterial        CHAR(13)         NOT NULL,
    DsMaterial        VARCHAR(100),
    QtdMinima         DECIMAL(7, 3),
    QtdMaxima         DECIMAL(7, 3),
    DsLocal           VARCHAR(100),
    CdTipoMaterial    INT              NOT NULL,
    PRIMARY KEY (CdMaterial)
)ENGINE=MYISAM
;



-- 
-- TABLE: TblOrcaItem 
--

CREATE TABLE TblOrcaItem(
    NrPedido      INT               NOT NULL,
    NrCNPJ        CHAR(14)          NOT NULL,
    CdMaterial    CHAR(13)          NOT NULL,
    VlMaterial    DECIMAL(10, 2),
    PRIMARY KEY (NrPedido, NrCNPJ, CdMaterial)
)ENGINE=MYISAM
;



-- 
-- TABLE: TblOrcamento 
--

CREATE TABLE TblOrcamento(
    NrPedido     Autonumeric         NOT NULL,
    NrCNPJ       CHAR(14)    NOT NULL,
    DtEnvio      DATETIME,
    DtRetorno    DATETIME,
    PRIMARY KEY (NrPedido, NrCNPJ)
)ENGINE=MYISAM
;



-- 
-- TABLE: TblPedido 
--

CREATE TABLE TblPedido(
    NrPedido    INT         NOT NULL,
    DsPedido    DATETIME,
    PRIMARY KEY (NrPedido)
)ENGINE=MYISAM
;



-- 
-- TABLE: TblPedidoItem 
--

CREATE TABLE TblPedidoItem(
    NrPedido      INT              NOT NULL,
    CdMaterial    CHAR(13)         NOT NULL,
    NrQuant       DECIMAL(7, 3),
    PRIMARY KEY (NrPedido, CdMaterial)
)ENGINE=MYISAM
;



-- 
-- TABLE: TblTipoMaterial 
--

CREATE TABLE TblTipoMaterial(
    CdTipoMaterial    INT             NOT NULL,
    DsTipoMaterial    VARCHAR(100)    NOT NULL,
    PRIMARY KEY (CdTipoMaterial)
)ENGINE=MYISAM
;



-- 
-- TABLE: TblMaterial 
--

ALTER TABLE TblMaterial ADD CONSTRAINT RefTblTipoMaterial1 
    FOREIGN KEY (CdTipoMaterial)
    REFERENCES TblTipoMaterial(CdTipoMaterial)
;


-- 
-- TABLE: TblOrcaItem 
--

ALTER TABLE TblOrcaItem ADD CONSTRAINT RefTblOrcamento6 
    FOREIGN KEY (NrPedido, NrCNPJ)
    REFERENCES TblOrcamento(NrPedido, NrCNPJ)
;

ALTER TABLE TblOrcaItem ADD CONSTRAINT RefTblMaterial7 
    FOREIGN KEY (CdMaterial)
    REFERENCES TblMaterial(CdMaterial)
;


-- 
-- TABLE: TblOrcamento 
--

ALTER TABLE TblOrcamento ADD CONSTRAINT RefTblPedido4 
    FOREIGN KEY (NrPedido)
    REFERENCES TblPedido(NrPedido)
;

ALTER TABLE TblOrcamento ADD CONSTRAINT RefTblFonecedor5 
    FOREIGN KEY (NrCNPJ)
    REFERENCES TblFonecedor(NrCNPJ)
;


-- 
-- TABLE: TblPedidoItem 
--

ALTER TABLE TblPedidoItem ADD CONSTRAINT RefTblPedido2 
    FOREIGN KEY (NrPedido)
    REFERENCES TblPedido(NrPedido)
;

ALTER TABLE TblPedidoItem ADD CONSTRAINT RefTblMaterial3 
    FOREIGN KEY (CdMaterial)
    REFERENCES TblMaterial(CdMaterial)
;


