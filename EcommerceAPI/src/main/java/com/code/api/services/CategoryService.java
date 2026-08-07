package com.code.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.api.models.Category;

import com.code.api.reposatories.ICategoryReposatory;

@Service
public class CategoryService implements ICategoryService {
//add the dependency
@Autowired
ICategoryReposatory categoryRepo;

@Override
public Category creatCategory(Category category) {
	// TODO Auto-generated method stub
	return categoryRepo.save(category);
}

@Override
public Category updateCategory(Category category) {
	// TODO Auto-generated method stub
	return categoryRepo.save(category);
}

@Override
public String deleteCategory(int id) {
	// TODO Auto-generated method stub
	//search the object using the id
Optional<Category> category=	categoryRepo.findById(id);
//check the object is ther or not

	if(category.isPresent())
	{
		Category cat=category.get();
		//delete
		categoryRepo.delete(cat);
		return "Category is deleted Successfully";
	}
	return "Category with "+id+" not found";
}

@Override
public Category getCategorytById(int id) {
	// TODO Auto-generated method stub
Optional<Category> optionalCategory=	categoryRepo.findById(id);
	
	return (Category)optionalCategory.get();
	
}

@Override
public List<Category> getAllCategory() {
	// TODO Auto-generated method stub
	return categoryRepo.findAll();
}

}
