package com.pedidos.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pedidos.dao.PedidosException;
import com.pedidos.datatransfers.ErrorDTO;
import com.pedidos.datatransfers.ProductosDTO;
import com.pedidos.service.IProductos;

/**
*
* @author Diego
*/

@Controller
public class ProductosController {
	
    @Autowired
    private IProductos productosService;
    
    @RequestMapping(value="/productos/{uuid}", produces="application/json; charset=UTF-8", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Object> getProductoByUUID(@PathVariable Integer uuid){
    	ProductosDTO result = null;
    	try {
			result = productosService.getProducto(uuid);
		} catch (PedidosException e) {
			return new ResponseEntity<>(new ProductosDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	if (result == null) {
    		return new ResponseEntity<>(new ErrorDTO(), HttpStatus.FOUND);
		}
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @RequestMapping(value="/productos/{uuid}", produces="application/json; charset=UTF-8", method = RequestMethod.DELETE)
    public @ResponseBody ResponseEntity<Object> deleteProductoByUUID(@PathVariable Integer uuid){
    	try {
			productosService.deleteProducto(uuid);
		} catch (PedidosException e) {
			ErrorDTO err = new ErrorDTO();
			err.setError("Error al eliminar");
			return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
		}
        return new ResponseEntity<>(new ProductosDTO(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/productos/{uuid}", produces="application/json; charset=UTF-8", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity<Object> putProducto(@PathVariable Integer uuid, @RequestBody ProductosDTO producto){
    	Integer result = null;
    	try {
			result = productosService.putProducto(producto, uuid);
		} catch (PedidosException e) {
			ErrorDTO err = new ErrorDTO();
			err.setError("Error al modificar");
			return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	if (result == null) {
    		return new ResponseEntity<>(new ErrorDTO(), HttpStatus.FOUND);
		}
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
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
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
    
}
