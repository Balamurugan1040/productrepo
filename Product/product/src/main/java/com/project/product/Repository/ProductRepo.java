/**
 * 
 */
package com.project.product.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.product.model.ProductModel;

public interface ProductRepo extends JpaRepository<ProductModel, Integer>{
	
	  List<ProductModel> findByStatus(ProductModel.Status status);

}
