# Usa la imagen oficial de Oracle como base
FROM container-registry.oracle.com/database/express:latest

# Cambia al usuario root para tener permisos de modificación
USER root

# Copia tus scripts SQL al contenedor
COPY setup.sql /opt/oracle/scripts/startup/
COPY script_ddl.sql /opt/oracle/scripts/startup/

# Cambia los permisos para asegurarte de que Oracle pueda ejecutar los scripts
RUN chown oracle:dba /opt/oracle/scripts/startup/*.sql
RUN chmod 644 /opt/oracle/scripts/startup/*.sql

# Vuelve al usuario oracle
USER oracle