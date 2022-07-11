package com.pedidos.dtobasemodel;

import java.math.BigDecimal;

import com.pedidos.datatransfers.PersistenDTOObject;

public class PedidosDetalleDTO {
	
	private Integer producto;
	private Integer cantidad;
	private BigDecimal importe;
	private String nombre;
	
	public Integer getProducto() {
		return producto;
	}
	public void setProducto(Integer producto) {
		this.producto = producto;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
