package com.nisum.sampleapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Order;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.sampleapp.model.Orders;
import com.nisum.sampleapp.model.Product;
import com.nisum.sampleapp.model.SampleProduct;
import com.nisum.sampleapp.service.OrderService;
import com.nisum.sampleapp.service.ProductService;


@RestController
public class ProductOrderController {

	private static final Logger log = LoggerFactory.getLogger(ProductOrderController.class);
	
	@Autowired
	ProductService productService;
		
	@Autowired
	OrderService orderService;
		
		
	@RequestMapping(value = "/getProduct/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Product getProductById(@PathVariable String id) {
	return	productService.findByProdcutId(id);
	}

	@RequestMapping(value = "/createProduct", method = RequestMethod.POST, headers = "Accept=application/json")
	public Product addProduct(@RequestBody Product product) {
		
		log.info("product id=="+product.getProductId()+""+"productDescription="+product.getProductDesc());
		return productService.addProduct(product);
	}
	
	@RequestMapping(value = "/createOrder", method = RequestMethod.POST, headers = "Accept=application/json")
	public Orders addOrder(@RequestBody Orders orders) {
		
		log.info("order name=="+orders.getOrderName()+"order price--"+orders.getOrderPrice()+"order status--"+orders.getOrderStatus()+"prod id=="+orders.getProductId());
		return orderService.createOrders(orders);
		
	}

	@RequestMapping(value = "/updateProduct", method = RequestMethod.PUT, headers = "Accept=application/json")
	public void updateProduct(@RequestBody Product product) {
		System.out.println("in update controller product name=="+product.getProductName()+""+"productDescription="+product.getProductDesc()+"quantity="+product.getQuantity());
		
		productService.updateProduct(product); 

	}	
	
	@RequestMapping(value = "/updateOrder", method = RequestMethod.PUT, headers = "Accept=application/json")
	public void updateOrder(@RequestBody Orders orders) {
		log.info("order name=="+orders.getOrderName()+"order price--"+orders.getOrderPrice()+"order status--"+orders.getOrderStatus()+"prod id=="+orders.getProductId());
		orderService.updateOrder(orders);

	}	


	@RequestMapping(value = "/deleteProduct/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void deleteProduct(@PathVariable("id") String id) {
		log.info("in prod controller delete id="+id);
		productService.deleteProduct(id);

	}	
	@RequestMapping(value = "/", method = RequestMethod.GET, headers = "Accept=application/json")
	public String goToHomePage() {
		return "redirect:/getAllCustomers";
	}
	@RequestMapping(value = "/getAllProducts", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Product> getAllProducts(Model model) {
		log.info("in getALLProducts****"); 
		List<Product> listOfProducts = productService.getAllProducts();
		model.addAttribute("product", new Product());
		model.addAttribute("listOfProducts", listOfProducts);
		return listOfProducts;
	}

	@RequestMapping(value = "/getAllOrders", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Orders> getAllOrders(Model model) {
		log.info("in getALLOrders##****"); 
		List<Orders> listOfProducts = orderService.getAllOrders();
		model.addAttribute("orders", new Orders());
		model.addAttribute("listOfProducts", listOfProducts);
		return listOfProducts;
	}
	@RequestMapping(value = "/deleteOrder/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void deleteOrder(@PathVariable("id") String id) {
		log.info("in prod ordcontroller delete id="+id);
		orderService.deleteOrder(id);
		
	}	

	//for insert prod AND ORDERs
	@RequestMapping(value = "/insertProductOrder", method = RequestMethod.POST, headers = "Accept=application/json")
	public SampleProduct insertProductOrder(@RequestBody SampleProduct productInfo) {
		
		log.info("productDescription="+productInfo.getProductDesc());
		return productService.addSampleProduct(productInfo);
	}
	
}
