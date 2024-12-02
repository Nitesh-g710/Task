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

import com.in.main.entity.Category;
import com.in.main.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {


	@Autowired
	CategoryService categoryService;

	@GetMapping
    public ResponseEntity<Iterable<Category>> getAllCategories(@RequestParam(defaultValue = "0") int page) {
        return new ResponseEntity<>(categoryService.getAllCategory(page), HttpStatus.OK);
    }


	@PostMapping
	public ResponseEntity<Category> createCategory(@RequestBody Category category) {
		return new ResponseEntity<>(categoryService.createCategory(category), HttpStatus.CREATED);

	}


	@GetMapping("/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable Integer id) {
		Optional<Category> existCategory = categoryService.getCategoryById(id);

		if (existCategory.isPresent()) {
			return new ResponseEntity<>(existCategory.get() , HttpStatus.OK );

		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@PutMapping("/{id}")
	public ResponseEntity<Category> updateProduct(@PathVariable int id, @RequestBody Category category) {
		Category updateCategory =(Category) categoryService.updateCategoryById(id, category);

		if (updateCategory!=null) {
			return new ResponseEntity<>(updateCategory, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
		if (categoryService.deleteCategory(id)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}


}

