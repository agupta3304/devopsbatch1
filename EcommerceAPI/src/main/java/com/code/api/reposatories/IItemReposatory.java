package com.code.api.reposatories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code.api.models.Item;


@Repository
public interface IItemReposatory extends JpaRepository<Item, Integer> {
		

}
