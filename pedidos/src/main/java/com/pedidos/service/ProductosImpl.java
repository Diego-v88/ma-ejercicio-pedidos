package com.pedidos.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedidos.dao.PedidosException;
import com.pedidos.dao.PedidosRepoImpl;
import com.pedidos.dao.ProductoRepoImpl;
import com.pedidos.datatransfers.PedidosCabeceraDTO;
import com.pedidos.datatransfers.PedidosDetalleDTO;
import com.pedidos.datatransfers.ProductosDTO;
import com.pedidos.enums.EstadoPedido;
import com.pedidos.models.PedidosCabecera;
import com.pedidos.models.PedidosDetalle;
import com.pedidos.models.Productos;

@Service
@Transactional
public class ProductosImpl implements IProductos {

	@Autowired
    private ProductoRepoImpl productodao;
	
	@Autowired
    private PedidosRepoImpl pedidodao;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	@Override
	public void addProducto(ProductosDTO productoDTO) throws PedidosException {
		
		try {
			Productos producto = modelMapper.map(productoDTO, Productos.class);
			productodao.save(producto);
        } catch (Exception e) {
            throw new PedidosException("Hubo un inconveniente y no se pudo agregar el producto", e);
        }
	}

	@Override
	public void deleteProducto(Integer id) throws PedidosException {
		productodao.deleteById(id);
	}

	@Override
	public ProductosDTO getProducto(Integer id) throws PedidosException {
		ProductosDTO result = null;
		Optional<Productos> o = productodao.findById(id);
		if (o!=null && !o.isEmpty()) {
			Productos pr = o.get();
			if (pr!=null) {
				result = modelMapper.map(pr, ProductosDTO.class);
			}
			
		}
		return result;
		
	}

	@Override
	public Integer putProducto(ProductosDTO productodto, Integer id) throws PedidosException {
		Productos producto = modelMapper.map(productodto, Productos.class);
		if (producto !=null) {
			producto.setId(id);
			Productos idP = productodao.save(producto);
			return idP.getId();
		}
		return null;
	}
	
	@Override
	public List<PedidosCabeceraDTO> getPedidoPorFecha(Date fecha) throws PedidosException {
		List<PedidosCabeceraDTO> listaDTO = new ArrayList<PedidosCabeceraDTO>();
		List<PedidosCabecera> lista = pedidodao.findPedidosByDate(fecha);
		for (PedidosCabecera pedidosCabecera : lista) {
			listaDTO.add(getDatatransfer(pedidosCabecera));
		}
		return listaDTO;
	}

	@Override
	public PedidosCabeceraDTO addPedido(PedidosCabeceraDTO pedido) throws PedidosException {
		Integer cantidad = 0;
		BigDecimal total = BigDecimal.ZERO;
		
		PedidosCabecera pc = new PedidosCabecera();
		pc.setDireccion(pedido.getDireccion());
		pc.setEmail(pedido.getEmail());
		pc.setTelefono(pedido.getTelefono());
		pc.setHorario(pedido.getHorario());
		pc.setFechaAlta(new Date());
		pedido.setFecha(pc.getFechaAlta());
		pc.setEstado(EstadoPedido.PENDIENTE);
		pedido.setEstado(EstadoPedido.PENDIENTE.name());
		pc.setAplicoDescuento(false);
		pedido.setAplicoDescuento(false);
		
		for (PedidosDetalleDTO detalle : pedido.getDetalle()) {
			PedidosDetalle det = new PedidosDetalle();
			det.setCantidad(detalle.getCantidad());
			cantidad = cantidad + detalle.getCantidad();
			Optional<Productos> o = productodao.findById(detalle.getProducto());
			if (o!=null && !o.isEmpty()) {
				Productos pr = o.get();
				det.setProducto(pr);
				det.setPrecioUnitario(pr.getPrecioUnitario());
				detalle.setImporte(pr.getPrecioUnitario());
				detalle.setNombre(pr.getNombre());
				det.setCabecera(pc);
				total = total.add(pr.getPrecioUnitario());
				
			} else {
				throw new PedidosException("Pedido incorrecto");
			}
			pc.getDetalle().add(det);
		}
		pc.setMontoTotal(total);
		pedido.setMontoTotal(total);
		if (cantidad > 3) {
			pc.setAplicoDescuento(true);
			pedido.setAplicoDescuento(true);
			BigDecimal montoTotal = total.multiply(new BigDecimal("0.7"));
			pc.setMontoTotal(montoTotal);
			pedido.setMontoTotal(montoTotal);
		}
		pedidodao.save(pc);
		return pedido;
	}
	
	private PedidosCabeceraDTO getDatatransfer(PedidosCabecera persistent) {
		PedidosCabeceraDTO dto = new PedidosCabeceraDTO();
		dto.setDireccion(persistent.getDireccion());
		dto.setEmail(persistent.getEmail());
		dto.setTelefono(persistent.getTelefono());
		dto.setHorario(persistent.getHorario());
		dto.setFecha(persistent.getFechaAlta());
		dto.setEstado(persistent.getEstado().name());
		dto.setAplicoDescuento(persistent.getAplicoDescuento());
		dto.setMontoTotal(persistent.getMontoTotal());
		
		for (PedidosDetalle persistentDetalle : persistent.getDetalle()) {
			PedidosDetalleDTO detalleDto = new PedidosDetalleDTO();
			detalleDto.setCantidad(persistentDetalle.getCantidad());
			detalleDto.setImporte(persistentDetalle.getPrecioUnitario());
			detalleDto.setNombre(persistentDetalle.getProducto().getNombre());
			detalleDto.setProducto(persistentDetalle.getProducto().getId());
			dto.getDetalle().add(detalleDto);
		}
		return dto;
	}

}
