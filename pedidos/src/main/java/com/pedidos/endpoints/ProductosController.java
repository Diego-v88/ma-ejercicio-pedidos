package com.pedidos.endpoints;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pedidos.dao.PedidosException;
import com.pedidos.datatransfers.ErrorDTO;
import com.pedidos.datatransfers.ProductosDTO;
import com.pedidos.service.IProductoPedidosService;

/**
*
* @author Diego
*/

@RestController
public class ProductosController {
	
    @Autowired
    private IProductoPedidosService productosService;
    
    @RequestMapping(value="/productos/{uuid}", produces="application/json; charset=UTF-8", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Object> getProductoByUUID(@PathVariable String uuid){
    	ProductosDTO result = null;
    	try {
			result = productosService.getProducto(UUID.fromString(uuid));
		} catch (PedidosException e) {
			return new ResponseEntity<>(new ProductosDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	if (result == null) {
    		return new ResponseEntity<>(new ErrorDTO(), HttpStatus.FOUND);
		}
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @RequestMapping(value="/productos/{uuid}", produces="application/json; charset=UTF-8", method = RequestMethod.DELETE)
    public @ResponseBody ResponseEntity<Object> deleteProductoByUUID(@PathVariable String uuid){
    	try {
			productosService.deleteProducto(UUID.fromString(uuid));
		} catch (PedidosException e) {
			ErrorDTO err = new ErrorDTO();
			err.setError("Error al eliminar");
			return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
		}
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @RequestMapping(value="/productos/{uuid}", produces="application/json; charset=UTF-8", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity<Object> putProducto(@PathVariable String uuid, @RequestBody ProductosDTO producto){
    	UUID result = null;
    	try {
			result = productosService.putProducto(producto, UUID.fromString(uuid));
		} catch (PedidosException e) {
			ErrorDTO err = new ErrorDTO();
			err.setError("Error al modificar");
			return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	if (result == null) {
    		return new ResponseEntity<>(new ErrorDTO(), HttpStatus.FOUND);
		}
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value="/productos", produces="application/json; charset=UTF-8", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Object> addProducto(@RequestBody ProductosDTO producto){
    	try {
			productosService.addProducto(producto);
		} catch (PedidosException e) {
			ErrorDTO err = new ErrorDTO();
			err.setError("Error al agregar");
			return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
		}
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
}
