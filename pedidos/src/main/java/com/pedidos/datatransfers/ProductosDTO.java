package com.pedidos.datatransfers;

import java.math.BigDecimal;

public class ProductosDTO {
	
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
