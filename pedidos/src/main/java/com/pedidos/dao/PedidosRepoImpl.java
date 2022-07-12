package com.pedidos.dao;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pedidos.models.PedidosCabecera;

/**
 *
 * @author Diego
 */
@Repository
public interface PedidosRepoImpl extends CrudRepository<PedidosCabecera, UUID> {
	
	@Query(value = "select pc from PedidosCabecera pc where DATE(pc.fechaAlta) = DATE(:fecha)", nativeQuery = false)
	public List<PedidosCabecera> findPedidosByDate(@Param("fecha") Date fecha);
	
}
