
         DROP TABLE accessinfo CASCADE CONSTRAINTS;
DROP TABLE volumeInfo;

DROP TABLE volumeInfo CASCADE CONSTRAINTS;

-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE accessinfo (
    id               VARCHAR2(20) NOT NULL,
    country          VARCHAR2(10) NOT NULL,
    accessviewstatus VARCHAR2(30),
    viewability      VARCHAR2(30)
);

ALTER TABLE accessinfo ADD CONSTRAINT accessinfo_pk PRIMARY KEY ( id );

CREATE TABLE volumeInfo (
    id            VARCHAR2(30) NOT NULL,
    title         VARCHAR2(200) NOT NULL,
    subtitle      VARCHAR2(200) NOT NULL,
    authors       VARCHAR2(200),
    publisheddate DATE,
    description   VARCHAR2(2384) NOT NULL,
    language      VARCHAR2(5) NOT NULL,
    pagecount     NUMBER,
    accessinfo_id VARCHAR2(20) NOT NULL
);

ALTER TABLE volumeInfo ADD CHECK ( pagecount > 0 );

ALTER TABLE volumeInfo ADD CONSTRAINT volumeInfo_pk PRIMARY KEY ( id );

ALTER TABLE volumeInfo
    ADD CONSTRAINT volumeInfo_accessinfo_fk FOREIGN KEY ( accessinfo_id )
        REFERENCES accessinfo ( id );
        
   
   
