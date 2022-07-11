package com.pedidos.dtobasemodel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pedidos.datatransfers.PersistenDTOObject;

public class PedidosCabeceraDTO {

	private String direccion;
	private String email;
	private String telefono;
	private String horario;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date fecha;
	private BigDecimal montoTotal;
	private Boolean aplicoDescuento;
	private String estado;
	private List<PedidosDetalleDTO> detalle;
	
	
	public PedidosCabeceraDTO() {
		super();
		detalle = new ArrayList<>();
	}
	
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(BigDecimal montoTotal) {
		this.montoTotal = montoTotal;
	}
	public Boolean getAplicoDescuento() {
		return aplicoDescuento;
	}
	public void setAplicoDescuento(Boolean aplicoDescuento) {
		this.aplicoDescuento = aplicoDescuento;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<PedidosDetalleDTO> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<PedidosDetalleDTO> detalle) {
		this.detalle = detalle;
	}
	
	
}
