package com.nisum.sampleapp.repositroy;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import com.nisum.sampleapp.model.Orders;
import com.nisum.sampleapp.model.Product;
import java.lang.String;
import java.util.List;

@Transactional
public interface OrdersRepository extends MongoRepository<Orders, String>{

	List<Orders> findByProductId(String productid);
}
