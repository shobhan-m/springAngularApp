package com.nisum.sampleapp.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.nisum.sampleapp.SpringAngualrMongo1Application;
import com.nisum.sampleapp.model.Orders;
import com.nisum.sampleapp.model.Product;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringAngualrMongo1Application.class, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerIT {

	private static final Logger log = LoggerFactory.getLogger(ProductControllerIT.class);
	
	@LocalServerPort
	private int port;
	
	TestRestTemplate restTemplate= new TestRestTemplate();
	
	
	HttpHeaders headers= new HttpHeaders();

	@Test
	//@Ignore
	public void testCreateProduct() {
	
	Product product =new Product("prod_6604","proddname3","desc","type4",4);
	headers.setContentType(MediaType.APPLICATION_JSON);
	
	HttpEntity<Product> entity = new HttpEntity<Product>(product,headers);
	
	ResponseEntity<Product> responseEntity=restTemplate.exchange
			(createURLWithPort("/createProduct"),HttpMethod.POST,entity,Product.class);
	
	assertNotNull(responseEntity);
	assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
	
	}
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

	@Test
	public void testGetProductById() {
		String uri="http://localhost:9000/getProduct/prod_6601";
		ResponseEntity<Product> responseEntity=	restTemplate.getForEntity(uri, Product.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertNotNull(responseEntity);
		assertEquals("desc",responseEntity.getBody().getProductDesc());
	}
	
	@Test
	public void testGetAllProducts() {
		String uri="http://localhost:9000/getAllProducts";
		ResponseEntity<Product[]> responseEntity=	restTemplate.getForEntity(uri, Product[].class);
			//Object[]	obj=responseEntity.getBody();
		log.info("resEntity all products size=="+responseEntity.getBody().length);
		assertNotNull(responseEntity);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		
	}
	
	@Test
	public void testCreateOrder() {
	
		Orders order =new Orders("orddname2","50000","new","prod_6601","Ord5666");
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Orders> entity = new HttpEntity<Orders>(order,headers);
	
	ResponseEntity<Orders> responseEntity=restTemplate.exchange
			(createURLWithPort("/createOrder"),HttpMethod.POST,entity,Orders.class);
	
	assertNotNull(responseEntity);
	assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
	}
	@Test
	public void testGetAllOrders() {
		String uri="http://localhost:9000/getAllOrders";
		ResponseEntity<Orders[]> responseEntity=	restTemplate.getForEntity(uri, Orders[].class);
			//Object[]	obj=responseEntity.getBody();
		log.info("****resEntity all orders size=="+responseEntity.getBody().length);
		assertNotNull(responseEntity);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		
	}
}
