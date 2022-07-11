package com.pedidos.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PEDIDOS_DETALLE")
public class PedidosDetalle {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
	private Integer id;
	
	@Column(name = "CANTIDAD")
	private Integer cantidad;
	
	@Column(name = "PRECIO_UNITARIO")
	private BigDecimal precioUnitario;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="PEDIDO_CABECERA_ID")
    private PedidosCabecera cabecera;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="PRODUCTO_ID")
    private Productos producto;
	
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	
	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public PedidosCabecera getCabecera() {
		return cabecera;
	}
	public void setCabecera(PedidosCabecera cabecera) {
		this.cabecera = cabecera;
	}
	public Productos getProducto() {
		return producto;
	}
	public void setProducto(Productos producto) {
		this.producto = producto;
	}
	
	
}
