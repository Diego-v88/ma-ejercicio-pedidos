DO $$
DECLARE
   scount INTEGER := 0;
   schema_name VARCHAR := 'public';
BEGIN
    SELECT COUNT(table_name) into scount FROM information_schema.tables
    WHERE table_schema = schema_name AND table_type='BASE TABLE' AND
    UPPER(table_name) = UPPER('PEDIDOS_CABECERA');
    IF scount = 0 THEN
        CREATE TABLE PEDIDOS_CABECERA (
                ID UUID NOT NULL,
                DIRECCION VARCHAR(60) NOT NULL,
                EMAIL VARCHAR(60) NOT NULL,
                TELEFONO VARCHAR(60) NOT NULL,
                HORARIO VARCHAR(10) NOT NULL,
                FECHA_ALTA TIMESTAMP NOT NULL,
                ESTADO VARCHAR(10) NOT NULL,
                APLICO_DESCUENTO BOOLEAN NOT NULL,
                MONTO_TOTAL NUMERIC(30,8) NOT NULL,
                PRIMARY KEY (ID));
    END IF;
END; $$