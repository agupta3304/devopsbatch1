package com.code.api.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.api.models.Category;
import com.code.api.services.ICategoryService;

@RestController
@RequestMapping("api/category/")
public class CategoryController {
	//depentent on Service Category Service
	@Autowired
	ICategoryService categoryService;
@GetMapping(value = "{id}")
public Category getCategoryByID(@PathVariable("id")int id)
{
	return categoryService.getCategorytById(id);
}
//root mapping
@GetMapping(value = "/")
public List<Category> getAllCategory()
{
	return categoryService.getAllCategory();
}

@PostMapping(value = "create")
public Category createCategory(@RequestBody Category category)
{
	return categoryService.creatCategory(category);
}
@PutMapping(value = "edit")
public Category editCategory(@RequestBody Category category)
{
	return categoryService.updateCategory(category);
}
@DeleteMapping(value = "delete/{id}")
public String deleteCategory(@PathVariable("id") int id)
{
	return categoryService.deleteCategory(id);
}
}
