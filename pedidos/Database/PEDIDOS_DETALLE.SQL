DO $$
DECLARE
   scount INTEGER := 0;
   schema_name VARCHAR := 'public';
BEGIN
    SELECT COUNT(table_name) into scount FROM information_schema.tables
    WHERE table_schema = schema_name AND table_type='BASE TABLE' AND
    UPPER(table_name) = UPPER('PEDIDOS_DETALLE');
    IF scount = 0 THEN
        CREATE TABLE PEDIDOS_DETALLE (
                ID UUID NOT NULL,
                CANTIDAD INTEGER NOT NULL,
                PRECIO_UNITARIO NUMERIC(30,8) NOT NULL,
                PEDIDO_CABECERA_ID UUID,
                PRODUCTO_ID UUID,
                CONSTRAINT FK_PEDIDOS_CABECERA FOREIGN KEY(PEDIDO_CABECERA_ID) REFERENCES PEDIDOS_CABECERA(ID),
                CONSTRAINT FK_PRODUCTOS FOREIGN KEY(PRODUCTO_ID) REFERENCES PRODUCTOS(ID),
                PRIMARY KEY (ID));
    END IF;
END; $$