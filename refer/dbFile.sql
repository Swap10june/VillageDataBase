create table SFamily
(
  family_id NUMBER NOT NULL ,
  family_head VARCHAR(100),
   CONSTRAINT family_pk PRIMARY KEY (family_id)
);
insert into sfamily values(1,null);
insert into sfamily values(2,null);
select * from SFamily;
select max(family_id) from sfamily;
delete from sfamily;
drop table sfamily;

CREATE TABLE SMember
( 
  member_id NUMBER NOT NULL,
  family_id NUMBER NOT NULL,
  m_name_e VARCHAR(100),
  m_name_m VARCHAR(100),
  family_head_status VARCHAR(10),
  m_sex VARCHAR(10),
  m_state VARCHAR(50),
  m_dist VARCHAR(50),
  m_tal VARCHAR(200),
  m_gaon VARCHAR(200),
  m_dob VARCHAR(200),
  m_ward INTEGER,
  m_contact VARCHAR(11),
  CONSTRAINT member_pk PRIMARY KEY (family_id, member_id),
  CONSTRAINT member_fk
    FOREIGN KEY (family_id)
    REFERENCES SFamily (family_id)
);
INSERT INTO smember VALUES (1,2,'swape2','swapnilm2','yes','M','MH','A.Nagar','Parner','Babhulwade','10/06/1991',1,'9665929145');
INSERT INTO smember VALUES (2,2,'sdswape2','swdapnilm2','yes','M','MH','A.Nagar','Parner','Babhulwade','10/06/1991',1,'9665929145');
drop table smember;