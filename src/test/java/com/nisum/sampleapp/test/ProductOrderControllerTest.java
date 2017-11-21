package com.nisum.sampleapp.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import com.nisum.sampleapp.controller.ProductOrderController;
import com.nisum.sampleapp.model.Orders;
import com.nisum.sampleapp.model.Product;
import com.nisum.sampleapp.service.OrderService;
import com.nisum.sampleapp.service.ProductService;

@RunWith(MockitoJUnitRunner.class)
public class ProductOrderControllerTest {
	
	private static final Logger log = LoggerFactory.getLogger(ProductOrderControllerTest.class);
	
	@InjectMocks
	private ProductOrderController productOrdController;
	
	@Mock
	private ProductService productService;
	
	@Mock
	private OrderService orderService;
	
	@Mock
	private Model model;
	
	Product product;
	Orders order= new Orders();
	List<Product> prodList=new ArrayList<>();

	@Before
	public void setUp()throws Exception{
		MockitoAnnotations.initMocks(this);
		product=new Product("prd666","hhh","sss","type3",2);
		model.addAttribute("product", product);
		order.setOrderName(""); 
		order.setProductId("prd666");
		order.setOrderStatus("new");
	}
	
	
	@Test
	public void testAddProduct(){
	
	when(productService.addProduct(product)).thenReturn(product);
	Product productResponse=	productOrdController.addProduct(product);
	assertNotNull(productResponse);
	assertEquals(product.getProductId(), productResponse.getProductId());
	assertEquals(product.getProductName(),productResponse.getProductName());
	
	verify(productService,atLeastOnce()).addProduct(product);
	
	}
	
	@Test
	public void testGetAllProducts() {
		Product prod2=new Product();
		prod2.setProductId("122");
		prod2.setProductName("saf");
		prod2.setQuantity(3);
		prodList.add(product);
		prodList.add(prod2);
		when(productService.getAllProducts()).thenReturn(prodList);
	List<Product>	products= productOrdController.getAllProducts(model);
	log.info("products size**="+products.size());
	assertEquals(product.getProductId(),products.stream().findAny().get().getProductId());
	assertEquals(product.getProductName(),products.stream().findAny().get().getProductName());
	
	verify(productService,atLeastOnce()).getAllProducts();
	}
	
	@Test
	public void testGetProductById() {
		when(productService.findByProdcutId(product.getProductId())).thenReturn(product);
		Product resultProduct=productOrdController.getProductById(product.getProductId());
		assertNotNull(resultProduct);
		assertEquals(product.getProductName(),resultProduct.getProductName());
		
		verify(productService,atLeastOnce()).findByProdcutId(product.getProductId());
	}
	
	@Test
	public void testUpdateProduct() {
		product.setProductName("prod34");
		product.setProductType("kkk");
		doNothing().when(productService).updateProduct(product);
		productOrdController.updateProduct(product);
		
		verify(productService,atLeastOnce()).updateProduct(product);
	}
	
	@Test
	public void testCreateOrder() {
		when(orderService.createOrders(order)).thenReturn(order);
		
		Orders orderResponse=productOrdController.addOrder(order);
		assertNotNull(orderResponse);
		assertEquals(order.getOrderName(),orderResponse.getOrderName());
		assertEquals(order.getProductId(), orderResponse.getProductId());
		
		verify(orderService,times(1)).createOrders(order);
		
		
	}
}