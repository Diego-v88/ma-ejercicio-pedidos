package com.pedidos.endpoints;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.pedidos.dao.PedidosException;
import com.pedidos.datatransfers.PedidosCabeceraDTO;
import com.pedidos.datatransfers.PedidosDetalleDTO;
import com.pedidos.service.IProductoPedidosService;

@WebMvcTest(PedidosController.class)
public class PedidosControllerTest {
	
	@Autowired
	private MockMvc mocMvc;
	
	@MockBean
    private IProductoPedidosService pedidosService;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}


	@Test
	public void testGetPedidoByDate() throws Exception {
		List<PedidosCabeceraDTO> list = new ArrayList<PedidosCabeceraDTO>();
		list.add(getDatatransfer());
		List<PedidosCabeceraDTO> emptyList = new ArrayList<PedidosCabeceraDTO>();
		String dateStr="2020-03-02";
		String dateStr2="2020-03-03";
		String dateStr3="2020-03-04";
		java.util.Date dateEmptyOK = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
		java.util.Date dateError = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr2);
		java.util.Date dateOF = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr3);

		when(pedidosService.getPedidoPorFecha(dateEmptyOK)).thenReturn(emptyList);
		when(pedidosService.getPedidoPorFecha(dateError)).thenThrow(PedidosException.class);
		when(pedidosService.getPedidoPorFecha(dateOF)).thenReturn(list);

		mocMvc.perform(get("/pedidos?fecha=2020-03-02").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON));
		
		verify(pedidosService).getPedidoPorFecha(dateEmptyOK);
		
		mocMvc.perform(get("/pedidos?fecha=2020-03-03").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isInternalServerError())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON));
		
		verify(pedidosService).getPedidoPorFecha(dateError);
		
		mocMvc.perform(get("/pedidos?fecha=2020-03-04").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$").isArray())
		.andExpect(jsonPath("$").isNotEmpty())
		.andExpect(jsonPath("$[0].direccion").value(equalTo("Dorton Road 80")))
		.andExpect(jsonPath("$[0].detalle").isArray())
		.andExpect(jsonPath("$[0].detalle").isNotEmpty())
		.andExpect(jsonPath("$[0].detalle[0].cantidad").value(equalTo(2)));
		
		verify(pedidosService).getPedidoPorFecha(dateOF);
	}

	private PedidosCabeceraDTO getDatatransfer() {
		PedidosCabeceraDTO dto = new PedidosCabeceraDTO();
		dto.setDireccion("Dorton Road 80");
		dto.setEmail("tsayb@opera.com");
		dto.setTelefono("(0351) 48158101");
		dto.setHorario("21:00");
		dto.setFecha(new Date());
		dto.setEstado("PENDIENTE");
		dto.setAplicoDescuento(false);
		dto.setMontoTotal(BigDecimal.TEN);
		
		PedidosDetalleDTO detalleDto = new PedidosDetalleDTO();
		detalleDto.setCantidad(2);
		detalleDto.setImporte(new BigDecimal("5"));
		detalleDto.setNombre("Jamón y morrones");
		detalleDto.setProducto("4971b423-e4ba-4a07-81df-ba278bed4856");
		dto.getDetalle().add(detalleDto);

		return dto;
	}
	
}
