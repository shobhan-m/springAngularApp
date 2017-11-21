package com.nisum.sampleapp.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.nisum.sampleapp.model.Product;
import com.nisum.sampleapp.repositroy.ProductRepository;
import com.nisum.sampleapp.service.ProductService;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

	@InjectMocks
	ProductService productService;
	
	@Mock
	ProductRepository productRepository;
	Product product= new Product();
	
	@Before
	public void setUp()throws Exception{
		MockitoAnnotations.initMocks(this);
		
		product.setProductId("prd99");
		product.setProductName("sony bravo");
		product.setProductDesc("sony led");
		product.setQuantity(2);
		
	}
	
	@Test
	public void testCreateProduct() {
	when(productRepository.save(product)).thenReturn(product);
	Product productResp=	productService.addProduct(product);
	assertNotNull(productResp);	
	assertEquals(product.getProductId(), productResp.getProductId());
	
	verify(productRepository,atLeastOnce()).save(product);
	
	}
	
	@Test
	public void testGetProductById() {
		when(productRepository.findByProductId(product.getProductId())).thenReturn(product);
		Product resultProd=productService.findByProdcutId(product.getProductId());
		assertNotNull(resultProd);
		assertEquals(product.getProductName(), resultProd.getProductName());
	
		verify(productRepository,atLeastOnce()).findByProductId(product.getProductId());
	
	}
	
	@Test
	public void testDeleteProduct() {
		doNothing().when(productRepository).delete(any(Product.class));
		productService.deleteProduct(anyString());
		verify(productRepository,atLeastOnce()).delete(any(Product.class));
	}
	
	
}
