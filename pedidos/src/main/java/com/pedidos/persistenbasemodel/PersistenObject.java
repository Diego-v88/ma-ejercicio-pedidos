package com.pedidos.persistenbasemodel;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class PersistenObject implements java.io.Serializable  {

	@Id
	@Column(name = "ID", nullable = false)
	private UUID id;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	
}
