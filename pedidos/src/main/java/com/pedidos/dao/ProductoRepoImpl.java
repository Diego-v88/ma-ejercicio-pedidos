package com.pedidos.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pedidos.models.Productos;

/**
 *
 * @author Diego
 */
@Repository
public interface ProductoRepoImpl extends CrudRepository<Productos, Integer> {
    
}
