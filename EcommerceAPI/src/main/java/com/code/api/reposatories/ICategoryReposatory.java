package com.code.api.reposatories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code.api.models.Category;


@Repository
public interface ICategoryReposatory extends JpaRepository<Category, Integer> {
		
	
}
