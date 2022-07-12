package com.pedidos.dao;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pedidos.models.Productos;

/**
 *
 * @author Diego
 */
@Repository
public interface ProductoRepoImpl extends CrudRepository<Productos, UUID> {
    
}
