package com.pedidos.models;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.pedidos.enums.EstadoPedido;

@Entity
@Table(name = "PEDIDOS_CABECERA")
public class PedidosCabecera implements java.io.Serializable {

	private static final long serialVersionUID = -3097006336946961217L;
	
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "ID", updatable = false, nullable = false)
	private UUID id;
	
	@Column(name = "DIRECCION")
	private String direccion;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "TELEFONO")
	private String telefono;
	
	@Column(name = "HORARIO")
	private String horario;
	
	@Column(name = "FECHA_ALTA")
	private Date fechaAlta;
	
	@Column(name = "MONTO_TOTAL")
	private BigDecimal montoTotal;
	
	@Column(name = "APLICO_DESCUENTO")
	private Boolean aplicoDescuento;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ESTADO")
	private EstadoPedido estado;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "cabecera", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidosDetalle> detalle = new ArrayList<>();
	
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
	
	
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
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
	
	
	public EstadoPedido getEstado() {
		return estado;
	}
	public void setEstado(EstadoPedido estado) {
		this.estado = estado;
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	
	public List<PedidosDetalle> getDetalle() {
		return detalle;
	}
	public void setDetalle(List<PedidosDetalle> detalle) {
		this.detalle = detalle;
	}

	
}
