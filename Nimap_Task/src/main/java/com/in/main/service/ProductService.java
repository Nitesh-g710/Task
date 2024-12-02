package com.in.main.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.in.main.dao.ProductRepo;
import com.in.main.entity.Product;

@Service
public class ProductService {

	@Autowired
	private ProductRepo productRepo;

	public Iterable<Product> getAllProduct(int page) {
		return productRepo.findAll(PageRequest.of(page,10));

	}

	public Product createProduct(Product product) {
		return productRepo.save(product);

	}

	public Object UpdateProductById(Integer productId, Product product) {
		Optional<Product> existProduct = productRepo.findById(productId);
		if (existProduct.isPresent()) {
			product.setProductId(productId);
			return productRepo.save(product);
		}
		return null;
	}


	public Optional<Product> getProductById(Integer productId) {
		return productRepo.findById(productId);
	}

	public boolean deleteById(Integer productId) {
		if (productRepo.existsById(productId)) {
			productRepo.deleteById(productId);
			return true;
		}
		return false;

	}

}
