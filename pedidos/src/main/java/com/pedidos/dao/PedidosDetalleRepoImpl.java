package com.pedidos.dao;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pedidos.models.PedidosDetalle;

/**
 *
 * @author Diego
 */
@Repository
public interface PedidosDetalleRepoImpl extends CrudRepository<PedidosDetalle, UUID> {

}
