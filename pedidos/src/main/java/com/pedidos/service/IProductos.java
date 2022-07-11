package com.pedidos.service;


import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pedidos.dao.PedidosException;
import com.pedidos.datatransfers.PedidosCabeceraDTO;
import com.pedidos.datatransfers.ProductosDTO;

public interface IProductos {
	
	@Transactional(propagation=Propagation.SUPPORTS, rollbackFor = Exception.class)
	public ProductosDTO getProducto(Integer id) throws PedidosException;
	
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void addProducto(ProductosDTO producto) throws PedidosException;
	
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void deleteProducto(Integer id) throws PedidosException;
	
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public Integer putProducto(ProductosDTO producto, Integer id) throws PedidosException;
	
	@Transactional(propagation=Propagation.SUPPORTS, rollbackFor = Exception.class)
	public List<PedidosCabeceraDTO> getPedidoPorFecha(Date fecha) throws PedidosException;
	
	@Transactional(propagation=Propagation.SUPPORTS, rollbackFor = Exception.class)
	public PedidosCabeceraDTO addPedido(PedidosCabeceraDTO pedido) throws PedidosException;
	
}
