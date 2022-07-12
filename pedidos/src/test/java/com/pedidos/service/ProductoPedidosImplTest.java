package com.pedidos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pedidos.dao.PedidosException;
import com.pedidos.dao.PedidosRepoImpl;
import com.pedidos.dao.ProductoRepoImpl;
import com.pedidos.datatransfers.PedidosCabeceraDTO;
import com.pedidos.datatransfers.PedidosDetalleDTO;
import com.pedidos.enums.EstadoPedido;
import com.pedidos.models.PedidosCabecera;
import com.pedidos.models.Productos;

public class ProductoPedidosImplTest {

	@Mock
    private ProductoRepoImpl productodao;
	
	@Mock
    private PedidosRepoImpl pedidodao;
	
	@InjectMocks
	private ProductoPedidosImpl service;
	
	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testAddPedido() throws PedidosException {
		Productos pr = new Productos();
		pr.setNombre("nombre");
		pr.setPrecioUnitario(BigDecimal.TEN);
		Optional<Productos> o = Optional.of(pr);
		when(productodao.findById(any(UUID.class))).thenReturn(o);
		when(pedidodao.save(any(PedidosCabecera.class))).thenReturn(null);
		
		PedidosCabeceraDTO rta = service.addPedido(getDatatransferDescuento());
		assertNotNull(rta);
		assertEquals(rta.getAplicoDescuento(), true);
		assertEquals(rta.getEstado(), EstadoPedido.PENDIENTE.name());
		assertEquals(rta.getMontoTotal(), BigDecimal.TEN.multiply(new BigDecimal("4")).multiply(new BigDecimal("0.7")));
		
		PedidosCabeceraDTO rta2 = service.addPedido(getDatatransferSinDescuento());
		assertNotNull(rta2);
		assertEquals(rta2.getAplicoDescuento(), false);
		assertEquals(rta2.getEstado(), EstadoPedido.PENDIENTE.name());
		assertEquals(rta2.getMontoTotal(), BigDecimal.TEN.multiply(new BigDecimal("3")));
	}

	private PedidosCabeceraDTO getDatatransferDescuento() {
		PedidosCabeceraDTO dto = new PedidosCabeceraDTO();
		dto.setDireccion("Dorton Road 80");
		dto.setEmail("tsayb@opera.com");
		dto.setTelefono("(0351) 48158101");
		dto.setHorario("21:00");
		dto.setFecha(new Date());
		
		PedidosDetalleDTO detalleDto = new PedidosDetalleDTO();
		detalleDto.setCantidad(4);
		detalleDto.setProducto(UUID.randomUUID().toString());
		dto.getDetalle().add(detalleDto);

		return dto;
	}
	
	private PedidosCabeceraDTO getDatatransferSinDescuento() {
		PedidosCabeceraDTO dto = new PedidosCabeceraDTO();
		dto.setDireccion("Dorton Road 80");
		dto.setEmail("tsayb@opera.com");
		dto.setTelefono("(0351) 48158101");
		dto.setHorario("21:00");
		dto.setFecha(new Date());
		
		PedidosDetalleDTO detalleDto = new PedidosDetalleDTO();
		detalleDto.setCantidad(3);
		detalleDto.setProducto(UUID.randomUUID().toString());
		dto.getDetalle().add(detalleDto);

		return dto;
	}
}
