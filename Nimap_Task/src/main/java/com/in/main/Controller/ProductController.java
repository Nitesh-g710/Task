package com.in.main.Controller;



import java.util.Optional;

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

import com.in.main.entity.Product;
import com.in.main.service.ProductService;

@RestController
@RequestMapping("api/products")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping	
	public ResponseEntity<Iterable<Product>> getAllCategories(@RequestParam(defaultValue = "0") int page) {
		return new ResponseEntity<>(productService.getAllProduct(page), HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		Product createProduct = productService.createProduct(product);
		return new ResponseEntity<>(createProduct, HttpStatus.CREATED);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") Integer id) {
		Optional<Product> product = productService.getProductById(id);
		if (product!=null) {
			return new ResponseEntity<>(product.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);


	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> UpdateProduct(@PathVariable("id") Integer id, @RequestBody Product product) {
		Product updateProduct =(Product)  productService.UpdateProductById(id, product);
		if (updateProduct!=null) {
			return new ResponseEntity<>(updateProduct, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProductById(@PathVariable("id") Integer id) {
		if (productService.deleteById(id)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

}
