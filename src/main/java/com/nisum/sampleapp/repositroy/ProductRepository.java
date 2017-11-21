package com.nisum.sampleapp.repositroy;


import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nisum.sampleapp.model.Product;
import java.lang.String;
import java.util.List;

@Transactional
public interface ProductRepository extends MongoRepository<Product, String>{
	
Product findByProductId(String productid);

}
