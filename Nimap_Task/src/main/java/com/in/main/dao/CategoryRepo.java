package com.in.main.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in.main.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
