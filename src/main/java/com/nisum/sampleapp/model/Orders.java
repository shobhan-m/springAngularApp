package com.nisum.sampleapp.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Document
public class Orders implements Serializable{
		
	/*@Id
	private String Id;
	*/
	@Id
	private String id;
	
	private String orderName;
    
	private String orderPrice;
    
	private String orderStatus;

	private String productId;
	
	@Indexed(unique=true)
	@NotBlank
	private String orderId;
	
	public Orders(String orderName,String orderPrice,String orderStatus,String productId,String orderId) {
		this.orderName=orderName;
		this.orderStatus=orderStatus;
		this.orderPrice=orderPrice;
		this.productId=productId;
		this.orderId=orderId;
		
    }
	public Orders(){
		
		
	}
	
   	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	/*@ManyToOne
	@JoinColumn(name="product_id")
	public Product getProd() {
		return prod;
	}

	public void setProd(Product prod) {
		this.prod = prod;
	}
*/
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	@Override
    public String toString() {
    	ObjectMapper mapper = new ObjectMapper();
    	
    	String jsonString = "";
		try {
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			jsonString = mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
    	return jsonString;
    }
	public String getId() {
		return id;
	}
	public void setId(String id) {
		id = id;
	}
	

   }
