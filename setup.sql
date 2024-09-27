ALTER SESSION SET "_ORACLE_SCRIPT"=true;

CREATE USER c##db_tech_emporium IDENTIFIED BY enrique;
ALTER SESSION SET "_ORACLE_SCRIPT"=true;

-- alter para crear el user
ALTER SYSTEM SET "_COMMON_USER_PREFIX"='';
-- crear user
CREATE USER db_tech_emporium IDENTIFIED BY enrique;
-- add privilegios
GRANT CONNECT, RESOURCE TO db_tech_emporium;
GRANT CREATE TABLE, CREATE SEQUENCE, CREATE TRIGGER TO db_tech_emporium;
GRANT UNLIMITED TABLESPACE TO db_tech_emporium;

ALTER SESSION SET CURRENT_SCHEMA = db_tech_emporium;