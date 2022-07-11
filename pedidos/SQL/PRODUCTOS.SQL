DO $$
DECLARE
   scount INTEGER := 0;
   schema_name VARCHAR := 'public';
BEGIN
    SELECT COUNT(table_name) into scount FROM information_schema.tables
    WHERE table_schema = schema_name AND table_type='BASE TABLE' AND
    UPPER(table_name) = UPPER('PRODUCTOS');
    IF scount = 0 THEN
        CREATE TABLE PRODUCTOS (
                ID SERIAL NOT NULL,
                NOMBRE VARCHAR(60) NOT NULL,
                DESCRIPCION_CORTA VARCHAR(100) NOT NULL,
                DESCRIPCION_LARGA VARCHAR(100) NOT NULL,
                PRECIO_UNITARIO NUMERIC(30,8) NOT NULL,
                PRIMARY KEY (ID));
    END IF;
END; $$