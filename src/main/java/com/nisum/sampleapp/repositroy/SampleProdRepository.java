package com.nisum.sampleapp.repositroy;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nisum.sampleapp.model.Product;
import com.nisum.sampleapp.model.SampleProduct;

public interface SampleProdRepository extends MongoRepository<SampleProduct, String>{

}
