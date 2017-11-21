package com.nisum.sampleapp.test;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.nisum.sampleapp.model.Orders;
import com.nisum.sampleapp.model.Product;
import com.nisum.sampleapp.repositroy.OrdersRepository;
import com.nisum.sampleapp.service.OrderService;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {
	
	
	@InjectMocks
	OrderService orderService;
	
	@Mock
	OrdersRepository orderRepository;
	
	Orders orders= new Orders();
	
	@Before
	public void setUp()throws Exception{
		MockitoAnnotations.initMocks(this);
		orders.setProductId("prd99");
		orders.setOrderName("ord123");
		orders.setOrderPrice("45000");
		orders.setOrderStatus("new");
	}
	
	@Test
	public void testCreateOrder() {
	when(orderRepository.save(orders)).thenReturn(orders);

	Orders respOrders=	orderService.createOrders(orders);
	assertNotNull(respOrders);
	assertEquals(orders.getOrderName(), respOrders.getOrderName());
	
	verify(orderRepository,times(1)).save(orders);
	
	}
	
	@Test
	public void testUpdateOrder() {
		orders.setOrderName("ord78");
		orders.setOrderStatus("pending");
		doNothing().when(orderRepository).delete(orders);
		orderService.updateOrder(orders);
		verify(orderRepository,times(1)).save(orders);
	}
	@Test
	public void testDeleteOrder() {
		doNothing().when(orderRepository).delete(any(Orders.class));
		List<Orders> orders1=new ArrayList<>();
		doReturn(orders1).when(orderRepository).findByProductId(anyString());
		orderService.deleteOrder(anyString());
		
	}
}
