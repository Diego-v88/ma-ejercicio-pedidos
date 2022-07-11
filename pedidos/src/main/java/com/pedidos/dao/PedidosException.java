package com.pedidos.dao;

/**
*
* @author Diego
*/

public class PedidosException extends Exception {

   private static final long serialVersionUID = 1L;

   public PedidosException(String descripcion, Exception excepcion) {
       super(descripcion, excepcion);
   }
   
   public PedidosException(String descripcion) {
       super(descripcion);
   }
   
   public PedidosException(Throwable causa) { 
       super(causa); 
   } 
}
