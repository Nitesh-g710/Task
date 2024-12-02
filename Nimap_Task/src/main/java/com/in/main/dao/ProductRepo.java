package com.in.main.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in.main.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

}
