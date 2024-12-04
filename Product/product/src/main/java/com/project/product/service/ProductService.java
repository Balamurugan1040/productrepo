package com.project.product.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.product.Repository.ProductRepo;
import com.project.product.model.ProductModel;

@Service
public class ProductService {

	@Autowired
	ProductRepo productRepo;

	public ProductModel addProductDetails(ProductModel product) {

		try {
			return productRepo.save(product);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<ProductModel> getAllProcuts() {

		try {
			return productRepo.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<ProductModel> getProductById(Integer id) {

		Optional<ProductModel> optionalProduct = productRepo.findById(id);
		return optionalProduct.map(Collections::singletonList).orElse(Collections.emptyList());
	}

	public ProductModel updateProduct(Integer id, ProductModel productDetails) {
		ProductModel product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
		product.setName(productDetails.getName());
		product.setStatus(productDetails.getStatus());
		product.setDescription(productDetails.getDescription());
		return productRepo.save(product);
	}

	public String deleteProduct(Integer id) {
		productRepo.deleteById(id);
		return "Product "+id+" Deleted Successfully";

	}
	
	 public List<ProductModel> filterByStatus(ProductModel.Status status) {
	        return productRepo.findByStatus(status);
	    }

}
