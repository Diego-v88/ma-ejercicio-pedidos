package com.pedidos.datatransfers;

import java.util.UUID;

@SuppressWarnings("serial")
public class PersistenDTOObject extends DatatransferObject {

	private UUID id;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	
}
