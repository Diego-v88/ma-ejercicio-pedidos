package com.pedidos.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "PRODUCTOS")
public class Productos implements java.io.Serializable {

	private static final long serialVersionUID = -4268557909052675411L;
	
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "ID", updatable = false, nullable = false)
	private UUID id;
	@Column(name = "NOMBRE")
	private String nombre;
	@Column(name = "DESCRIPCION_CORTA")
	private String descripcionCorta;
	@Column(name = "DESCRIPCION_LARGA")
	private String descripcionLarga;
	@Column(name = "PRECIO_UNITARIO")
	private BigDecimal precioUnitario;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidosDetalle> detalle = new ArrayList<>();
	
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	
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
	
	public List<PedidosDetalle> getDetalle() {
		return detalle;
	}
	public void setDetalle(List<PedidosDetalle> detalle) {
		this.detalle = detalle;
	}
	
	
	
}
