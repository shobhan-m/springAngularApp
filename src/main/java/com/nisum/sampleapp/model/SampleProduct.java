package com.nisum.sampleapp.model;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

public class SampleProduct {
	@Id
	private String id;

	private String productName;

	private String productDesc;

	private String productType;

	private Integer quantity;

	
	private Orders orders;

	public SampleProduct(String productName,String productDesc,
			String productType,Integer quantity,Orders orders){
		this.productName=productName;
		this.productDesc=productDesc;
		this.productType=productType;
		this.quantity=quantity;
		this.orders=orders;
	}

	public SampleProduct() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

}
