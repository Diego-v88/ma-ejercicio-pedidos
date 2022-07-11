package com.pedidos.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pedidos.models.PedidosDetalle;

/**
 *
 * @author Diego
 */
@Repository
public interface PedidosDetalleRepoImpl extends CrudRepository<PedidosDetalle, Integer> {

}
