package com.pedidos.dtobasemodel;

import java.math.BigDecimal;

import com.pedidos.datatransfers.PersistenDTOObject;

public class ProductosDTO extends PersistenDTOObject {

	private static final long serialVersionUID = 2419551671495358595L;
	
	private String nombre;
	private String descripcionCorta;
	private String descripcionLarga;
	private BigDecimal precioUnitario;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	public String getDescripcionCorta() {
		return descripcionCorta;
	}
	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}
	
	
	public String getDescripcionLarga() {
		return descripcionLarga;
	}
	public void setDescripcionLarga(String descripcionLarga) {
		this.descripcionLarga = descripcionLarga;
	}
	
	
	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	
}
