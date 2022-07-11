package com.pedidos.endpoints;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pedidos.dao.PedidosException;
import com.pedidos.datatransfers.ErrorDTO;
import com.pedidos.datatransfers.PedidosCabeceraDTO;
import com.pedidos.service.IProductos;

/**
*
* @author Diego
*/

@Controller
public class PedidosController {
	
    @Autowired
    private IProductos pedidosService;
    
    private static final String REQ_PARAM_FECHA = "fecha";
    
    @RequestMapping(value="/pedidos", produces="application/json; charset=UTF-8", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Object> addPedido(@RequestBody PedidosCabeceraDTO cabecera){
    	PedidosCabeceraDTO result = null;
    	try {
			result = pedidosService.addPedido(cabecera);
		} catch (PedidosException e) {
			ErrorDTO err = new ErrorDTO();
			err.setError(e.getMessage());
			return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
		}
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @RequestMapping(value="/pedidos", produces="application/json; charset=UTF-8", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Object> getPedidoByDate(@RequestParam(REQ_PARAM_FECHA) 
    @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
    	List<PedidosCabeceraDTO> result = null;
    	try {
			result = pedidosService.getPedidoPorFecha(date);
		} catch (PedidosException e) {
			ErrorDTO err = new ErrorDTO();
			err.setError("Error");
			return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
		}
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
