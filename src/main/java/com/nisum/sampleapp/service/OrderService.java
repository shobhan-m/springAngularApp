package com.nisum.sampleapp.service;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.sampleapp.model.Orders;
import com.nisum.sampleapp.model.Product;
import com.nisum.sampleapp.repositroy.OrdersRepository;
import com.nisum.sampleapp.repositroy.ProductRepository;

@Service
public class OrderService {

	private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private OrdersRepository ordersRepository;

	
	public OrderService() {
		
	}

	public Orders createOrders(Orders orders) {
		//10 digitgenerate random number
		 Random random=new Random();
	        int randomNumber=0;
	        boolean loop=true;
	        while(loop) {
	            randomNumber=random.nextInt();
	            if(Integer.toString(randomNumber).length()==10 
	            		&& !Integer.toString(randomNumber).startsWith("-")) {
	                loop=false;
	            }
	            }
	        log.info("random num::"+randomNumber);
	        String ordId="Ord"+randomNumber;
	        orders.setOrderId(ordId);
	       
        return ordersRepository.save(orders);
    }

	
	public void updateOrder(Orders orders) {
		log.info("in order service update***");
		
		List<Orders>  	ord=ordersRepository.findByProductId(orders.getOrderId());
		  log.info("size of list--"+ord.size());
		  for(Orders ords: ord) {
			//  ords.setOrderName(orders.getOrderName());
			  ords.setOrderPrice(orders.getOrderPrice());
			  ords.setOrderPrice(orders.getOrderPrice());
			  ords.setOrderStatus(orders.getOrderStatus());
			 ords.setProductId(orders.getProductId());
		  }
		   
		ordersRepository.save(orders);
		
    }

	 public List<Orders> getAllOrders() {
	        List<Orders> pageOfOrders = (List<Orders>) ordersRepository.findAll();
	        
	        return pageOfOrders;
	    }
	   
	 public void deleteOrder(String id) {
	    	List<Orders>   order=	ordersRepository.findByProductId(id);
	    	log.info("in product serive delete size**"+order.size());
	   	
	   	ordersRepository.delete(order);
	    }
}
