package com.project.product.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.product.model.ProductModel;
import com.project.product.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	ProductService productService;

	@PostMapping("/saveProcess")
	public ResponseEntity<ProductModel> addProduct(@RequestBody ProductModel product) {

		try {
			return new ResponseEntity<ProductModel>(productService.addProductDetails(product), HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/getProcess")
	public ResponseEntity<List<ProductModel>> getProduct(){
		
		try {
			return new ResponseEntity<List<ProductModel>>(productService.getAllProcuts(), HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<List<ProductModel>> getProductById(@PathVariable Integer id) {
		try {
			 List<ProductModel> products = productService.getProductById(id);
		        return new ResponseEntity<List<ProductModel>>(products, HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
    }
	
	
	@PutMapping("/{id}")
    public ResponseEntity<ProductModel> updateProduct(@PathVariable Integer id, @RequestBody ProductModel productDetails) {
        try {
            return ResponseEntity.ok(productService.updateProduct(id, productDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        String msg = productService.deleteProduct(id);
        return msg;
    }
	
    @GetMapping("/filter")
    public List<ProductModel> filterByStatus(@RequestParam ProductModel.Status status) {
        return productService.filterByStatus(status);
    }

}
