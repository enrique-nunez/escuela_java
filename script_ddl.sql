/*
  ------------------------------
  @Author: Jose Enrique Nuñez Constantino
  @Email: jeconstantino@stefanini.com
  @Date: 2024-06-28
  ------------------------------
*/
-------------------------------************************************************************--------------------------------
/******************************         ELIMINACIÓN DE TABLAS - DB_TECH_EMPORIUM          *********************************/
-------------------------------************************************************************--------------------------------
/******** Drop Table ********/
/*DROP TABLE logs CASCADE CONSTRAINTS;
DROP TABLE customers CASCADE CONSTRAINTS;
DROP TABLE order_details CASCADE CONSTRAINTS;
DROP TABLE orders CASCADE CONSTRAINTS;
DROP TABLE products CASCADE CONSTRAINTS;*/

/******** Drop Sequence ********/
/*DROP SEQUENCE logs_seq;
DROP SEQUENCE customers_seq;
DROP SEQUENCE order_details_seq;
DROP SEQUENCE orders_seq;
DROP SEQUENCE products_seq;*/

/******** Drop Trigger ********/
/*DROP TRIGGER trg_logs_id_seq;
DROP TRIGGER trg_logs_insert_customers;
DROP TRIGGER trg_logs_insert_orders;
DROP TRIGGER trg_logs_insert_products;
DROP TRIGGER trg_logs_insert_order_details;*/


-------------------------------************************************************************--------------------------------
/******************************         CREACIÓN DE TABLAS - DB_TECH_EMPORIUM             *********************************/
-------------------------------************************************************************--------------------------------
/******** Table Logs ********/
CREATE TABLE logs
(
    log_id      INTEGER      NOT NULL
    , "table"     VARCHAR2(45) NOT NULL
    , operation   VARCHAR2(45) NOT NULL
    , log_value   JSON         NOT NULL
    , log_date    TIMESTAMP    DEFAULT SYSDATE NOT NULL
    , CONSTRAINT logs_pk PRIMARY KEY ( log_id  ) ENABLE
);
--comments
COMMENT ON COLUMN logs.OPERATION IS 'INSERT, UPDATE, DELETE';
--create sequence
CREATE SEQUENCE logs_seq START WITH 1 INCREMENT BY 1;
--create trigger sequence
CREATE OR REPLACE TRIGGER trg_logs_id_seq BEFORE
INSERT ON logs
FOR EACH ROW
WHEN ( new.log_id IS NULL )
BEGIN
    :new.log_id := logs_seq.NEXTVAL;
END;
/

/******** Table Customers ********/
CREATE TABLE customers
(
    customer_id      INTEGER       NOT NULL
    , customer_name    VARCHAR2(45)  NOT NULL
    , customer_address VARCHAR2(145) NOT NULL
    , document_type    INTEGER       NOT NULL
    , document_number  VARCHAR2(25)  NOT NULL
    , CONSTRAINT customers_pk PRIMARY KEY ( customer_id ) ENABLE
);
--comments
COMMENT ON COLUMN customers.document_type IS '1:DNI, 2:CE';
COMMENT ON COLUMN customers.document_number IS 'Numero del Documento';
--create sequence
CREATE SEQUENCE customers_seq START WITH 1 INCREMENT BY 1;


/******** Table Orders ********/
CREATE TABLE orders
(
    order_id            INTEGER       NOT NULL
    , total_order_amount  INTEGER       NOT NULL
    , total_order_price   NUMBER(14,2)  NOT NULL
    , order_date          DATE          NOT NULL
    , service_order_date  DATE
    , status              INTEGER       NOT NULL
    , customer_id         INTEGER       NOT NULL
    , CONSTRAINT orders_pk PRIMARY KEY ( order_id ) ENABLE
);
--alter table
ALTER TABLE orders
    ADD CONSTRAINT customers_fk FOREIGN KEY ( customer_id ) REFERENCES customers ( customer_id )
    ENABLE;
--comments
COMMENT ON COLUMN orders.total_order_amount IS 'Cantidad total de pedidos';
COMMENT ON COLUMN orders.total_order_price IS 'Precio total de pedidos';
COMMENT ON COLUMN orders.status IS '1=PENDIENTE, 2=ATENDIDO, 0=RECHAZADO';
COMMENT ON COLUMN orders.customer_id IS 'Llave Foranea';
--create sequence
CREATE SEQUENCE orders_seq START WITH 1 INCREMENT BY 1;

/******** Table Products ********/
CREATE TABLE products
(
    product_id    INTEGER       NOT NULL
    , product_name  VARCHAR2(45)  NOT NULL
    , product_price NUMBER(14,2)  NOT NULL
    , status        INTEGER       NOT NULL
    , CONSTRAINT products_pk PRIMARY KEY ( product_id )ENABLE
);
--comments
COMMENT ON COLUMN products.status IS '0=INACTIVO, 1=ACTIVO';
--create sequence
CREATE SEQUENCE products_seq START WITH 1 INCREMENT BY 1;

/******** Table OrderDetails ********/
CREATE TABLE order_details
(
    amount      INTEGER       NOT NULL
    , total_price NUMBER(14,2)  NOT NULL
    , order_id    INTEGER       NOT NULL
    , product_id  INTEGER       NOT NULL
);
--alter table
ALTER TABLE order_details
    ADD CONSTRAINT orders_fk FOREIGN KEY( order_id ) REFERENCES orders ( order_id ) ENABLE;

ALTER TABLE order_details
    ADD CONSTRAINT products_fk FOREIGN KEY( product_id ) REFERENCES products( product_id ) ENABLE;

--comments
COMMENT ON COLUMN order_details.amount IS 'Cantidad (Unidad)';
COMMENT ON COLUMN order_details.order_id IS 'Llave Foranea';
COMMENT ON COLUMN order_details.product_id IS 'Llave Foranea';

-------------------------------************************************************************--------------------------------
/******************************                 TRIGGER - DB_TECH_EMPORIUM                *********************************/
-------------------------------************************************************************--------------------------------
/******** Trigger - Table Customers ********/
CREATE OR REPLACE TRIGGER trg_logs_insert_customers
AFTER INSERT ON customers
FOR EACH ROW
DECLARE
nameTable VARCHAR2(50) := 'customers';
BEGIN
INSERT INTO logs ("table", operation, log_value)
VALUES (nameTable,'INSERT',
        JSON_OBJECT('customer_id' is :new.customer_id,
                    'customer_name' is :new.customer_name,
                    'customer_address' is :new.customer_address,
                    'document_type' is :new.document_type,
                    'document_number' is :new.document_number));
END;
/

/******** Trigger - Table Orders ********/
CREATE OR REPLACE TRIGGER trg_logs_insert_orders
AFTER INSERT ON orders
FOR EACH ROW
DECLARE
nameTable VARCHAR2(50) := 'orders';
BEGIN
INSERT INTO logs ("table", operation, log_value)
VALUES (nameTable,'INSERT',
        JSON_OBJECT('order_id' is :new.order_id,
                    'total_order_amount' is :new.total_order_amount,
                    'total_order_price' is :new.total_order_price,
                    'order_date' is :new.order_date,
                    'service_order_date' is :new.service_order_date,
                    'status' is :new.status,
                    'customer_id' is :new.customer_id));
END;
/

/******** Trigger - Table Products ********/
CREATE OR REPLACE TRIGGER trg_logs_insert_products
AFTER INSERT ON products
FOR EACH ROW
DECLARE
nameTable VARCHAR2(50) := 'products';
BEGIN
INSERT INTO logs ("table", operation, log_value)
VALUES (nameTable,'INSERT',
        JSON_OBJECT('product_id' is :new.product_id,
                    'product_name' is :new.product_name,
                    'product_price' is :new.product_price,
                    'status' is :new.status));
END;
/

/******** Trigger - Table OrderDetails ********/
CREATE OR REPLACE TRIGGER trg_logs_insert_order_details
AFTER INSERT ON order_details
FOR EACH ROW
DECLARE
nameTable VARCHAR2(50) := 'order_details';
BEGIN
INSERT INTO logs ("table", operation, log_value)
VALUES (nameTable,'INSERT',
        JSON_OBJECT('amount' is :new.amount,
                    'total_price' is :new.total_price,
                    'order_id' is :new.order_id,
                    'product_id' is :new.product_id));
END;
/


/*
  ------------------------------
  @Author: Jose Enrique Nuñez Constantino
  @Email: jeconstantino@stefanini.com
  @Date: 2024-06-28
  ------------------------------
*/
-------------------------------************************************************************--------------------------------
/******************************           INSERT DE TABLAS - DB_TECH_EMPORIUM             *********************************/
-------------------------------************************************************************--------------------------------
/******** Inserts Customers ********/
--customer_id = 1
INSERT INTO customers (customer_id, customer_name, customer_address, document_type, document_number)
VALUES (customers_seq.NEXTVAL, 'Juan Pérez', 'Calle Falsa 123', 1, '12345678');
--customer_id = 2
INSERT INTO customers (customer_id, customer_name, customer_address, document_type, document_number)
VALUES (customers_seq.NEXTVAL, 'María López', 'Avenida Siempre Viva 742', 2, 'A1234567');
--customer_id = 3
INSERT INTO customers (customer_id, customer_name, customer_address, document_type, document_number)
VALUES (customers_seq.NEXTVAL, 'Carlos García', 'Calle del Prado 456', 1, '87654321');
--customer_id = 4
INSERT INTO customers (customer_id, customer_name, customer_address, document_type, document_number)
VALUES (customers_seq.NEXTVAL, 'Ana González', 'Boulevard Central 5', 1, '45678912');
--customer_id = 5
INSERT INTO customers (customer_id, customer_name, customer_address, document_type, document_number)
VALUES (customers_seq.NEXTVAL, 'Luis Fernández', 'Calle de la Luna 789', 2, 'B7654321');
--customer_id = 6
INSERT INTO customers (customer_id, customer_name, customer_address, document_type, document_number)
VALUES (customers_seq.NEXTVAL, 'Laura Martínez', 'Avenida del Sol 101', 1, '23456789');
--customer_id = 7
INSERT INTO customers (customer_id, customer_name, customer_address, document_type, document_number)
VALUES (customers_seq.NEXTVAL, 'Pedro Sánchez', 'Calle de las Flores 202', 2, 'C3456789');
--customer_id = 8
INSERT INTO customers (customer_id, customer_name, customer_address, document_type, document_number)
VALUES (customers_seq.NEXTVAL, 'Marta Rodríguez', 'Paseo de la Reforma 303', 1, '56789123');
--customer_id = 9
INSERT INTO customers (customer_id, customer_name, customer_address, document_type, document_number)
VALUES (customers_seq.NEXTVAL, 'José Hernández', 'Avenida Los Pinos 404', 2, 'D9876543');
--customer_id = 10
INSERT INTO customers (customer_id, customer_name, customer_address, document_type, document_number)
VALUES (customers_seq.NEXTVAL, 'Lucía Ramírez', 'Calle de las Nubes 505', 1, '34567890');
COMMIT;


/******** Inserts Products ********/
--product_id = 1
INSERT INTO products (product_id, product_name, product_price, status)
VALUES (products_seq.NEXTVAL, 'Laptop Dell XPS 13', 1200.00, 1);
--product_id = 2
INSERT INTO products (product_id, product_name, product_price, status)
VALUES (products_seq.NEXTVAL, 'Monitor LG UltraWide 34"', 600.00, 1);
--product_id = 3
INSERT INTO products (product_id, product_name, product_price, status)
VALUES (products_seq.NEXTVAL, 'Teclado Mecánico Corsair K95', 150.00, 1);
--product_id = 4
INSERT INTO products (product_id, product_name, product_price, status)
VALUES (products_seq.NEXTVAL, 'Mouse Logitech MX Master 3', 99.99, 1);
--product_id = 5
INSERT INTO products (product_id, product_name, product_price, status)
VALUES (products_seq.NEXTVAL, 'Disco Duro Externo Seagate 2TB', 80.00, 1);
--product_id = 6
INSERT INTO products (product_id, product_name, product_price, status)
VALUES (products_seq.NEXTVAL, 'Memoria RAM Kingston 16GB', 75.00, 1);
--product_id = 7
INSERT INTO products (product_id, product_name, product_price, status)
VALUES (products_seq.NEXTVAL, 'Tarjeta Gráfica Nvidia RTX 3080', 699.99, 1);
--product_id = 8
INSERT INTO products (product_id, product_name, product_price, status)
VALUES (products_seq.NEXTVAL, 'Procesador Intel Core i9-11900K', 539.99, 1);
--product_id = 9
INSERT INTO products (product_id, product_name, product_price, status)
VALUES (products_seq.NEXTVAL, 'Placa Madre ASUS ROG Strix Z590-E', 379.99, 1);
--product_id = 10
INSERT INTO products (product_id, product_name, product_price, status)
VALUES (products_seq.NEXTVAL, 'Case NZXT H510 Elite', 149.99, 1);
--product_id = 11
INSERT INTO products (product_id, product_name, product_price, status)
VALUES (products_seq.NEXTVAL, 'Fuente de Poder EVGA 750W', 109.99, 1);
--product_id = 12
INSERT INTO products (product_id, product_name, product_price, status)
VALUES (products_seq.NEXTVAL, 'Audífonos HyperX Cloud II', 99.99, 1);
--product_id = 13
INSERT INTO products (product_id, product_name, product_price, status)
VALUES (products_seq.NEXTVAL, 'Impresora HP LaserJet Pro M404n', 209.99, 1);
--product_id = 14
INSERT INTO products (product_id, product_name, product_price, status)
VALUES (products_seq.NEXTVAL, 'Cámara Web Logitech C920', 79.99, 1);
--product_id = 15
INSERT INTO products (product_id, product_name, product_price, status)
VALUES (products_seq.NEXTVAL, 'Hub USB-C Anker 7-en-1', 59.99, 1);
COMMIT;


/******** Inserts Orders ********/
--customer = juan perez | estado = pendiente
INSERT INTO orders (order_id, total_order_amount, total_order_price, order_date, service_order_date, status, customer_id)
VALUES (orders_seq.NEXTVAL, 3, 1350.00, TO_DATE('2023-10-01 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2023-10-02 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 1);
--customer = maria lopez | estado = ATENDIDO
INSERT INTO orders (order_id, total_order_amount, total_order_price, order_date, service_order_date, status, customer_id)
VALUES (orders_seq.NEXTVAL, 2, 750.00, TO_DATE('2023-10-02 09:45:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2023-10-03 11:15:00', 'YYYY-MM-DD HH24:MI:SS'), 2, 2);
--customer = carlos garcia | estado = rechazado
INSERT INTO orders (order_id, total_order_amount, total_order_price, order_date, service_order_date, status, customer_id)
VALUES (orders_seq.NEXTVAL, 4, 2000.00, TO_DATE('2023-10-03 08:00:00', 'YYYY-MM-DD HH24:MI:SS'), NULL, 0, 3);
--customer = ana gonzalez | estado = pendiente
INSERT INTO orders (order_id, total_order_amount, total_order_price, order_date, service_order_date, status, customer_id)
VALUES (orders_seq.NEXTVAL, 1, 699.99, TO_DATE('2023-10-04 07:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2023-10-05 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 4);
--customer = luis fernandez | estado = ATENDIDO
INSERT INTO orders (order_id, total_order_amount, total_order_price, order_date, service_order_date, status, customer_id)
VALUES (orders_seq.NEXTVAL, 5, 1750.00, TO_DATE('2023-10-05 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2023-10-06 15:00:00', 'YYYY-MM-DD HH24:MI:SS'), 2, 5);
COMMIT;


/******** Inserts OrderDetails ********/
-- Detalles para la primera orden | customer = juan perez | estado = pendiente
INSERT INTO order_details (amount, total_price, order_id, product_id)
VALUES (1, 1200.00, 1, 1); -- Laptop Dell XPS 13
INSERT INTO order_details (amount, total_price, order_id, product_id)
VALUES (2, 150.00, 1, 3); -- Teclado Mecánico Corsair K95
-- Detalles para la segunda orden | customer = maria lopez | estado = ATENDIDO
INSERT INTO order_details (amount, total_price, order_id, product_id)
VALUES (1, 600.00, 2, 2); -- Monitor LG UltraWide 34"
INSERT INTO order_details (amount, total_price, order_id, product_id)
VALUES (1, 150.00, 2, 3); -- Teclado Mecánico Corsair K95
-- Detalles para la tercera orden | customer = carlos garcia | estado = rechazado
INSERT INTO order_details (amount, total_price, order_id, product_id)
VALUES (1, 699.99, 3, 7); -- Tarjeta Gráfica Nvidia RTX 3080
INSERT INTO order_details (amount, total_price, order_id, product_id)
VALUES (1, 539.99, 3, 8); -- Procesador Intel Core i9-11900K
INSERT INTO order_details (amount, total_price, order_id, product_id)
VALUES (2, 379.99, 3, 9); -- Placa Madre ASUS ROG Strix Z590-E
INSERT INTO order_details (amount, total_price, order_id, product_id)
VALUES (2, 380.00, 3, 5); -- Disco Duro Externo Seagate 2TB
-- Detalles para la cuarta orden | customer = ana gonzalez | estado = pendiente
INSERT INTO order_details (amount, total_price, order_id, product_id)
VALUES (1, 699.99, 4, 7); -- Tarjeta Gráfica Nvidia RTX 3080
-- Detalles para la quinta orden | customer = luis fernandez | estado = ATENDIDO
INSERT INTO order_details (amount, total_price, order_id, product_id)
VALUES (2, 75.00, 5, 6); -- Memoria RAM Kingston 16GB
INSERT INTO order_details (amount, total_price, order_id, product_id)
VALUES (1, 149.99, 5, 10); -- Case NZXT H510 Elite
COMMIT;
