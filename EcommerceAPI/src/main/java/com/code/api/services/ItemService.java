package com.code.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.code.api.models.Item;
import com.code.api.reposatories.IItemReposatory;

@Service
public class ItemService implements IItemService  {

	//add dependency
	@Autowired
	IItemReposatory itemReposatory;
	@Override
	public Item add(Item item) {
		// TODO Auto-generated method stub
		return itemReposatory.save(item);
	}

	@Override
	public Item update(Item item) {
		// TODO Auto-generated method stub
		return itemReposatory.save(item);
	}

	@Override
	public String delete(Item item) {
		// TODO Auto-generated method stub
		itemReposatory.delete(item);
		return "Item is deleted successfully";
	}

	@Override
	public String delete(int id) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Optional<Item> itemOptional=itemReposatory.findById(id);
		//check the users is present or not
	if(itemOptional.isPresent())
	{
		//get the user object and delete it
		Item item=itemOptional.get();
		itemReposatory.delete(item);
		return "Record is deleted sucessfully";
	}
	return "Item with Id "+id+" not found";
	}

	@Override
	public List<Item> getAll() {
		// TODO Auto-generated method stub
		return itemReposatory.findAll();
	}

	@Override
	public Item getById(int id) {
		// TODO Auto-generated method stub
		return itemReposatory.findById(id).get();
	}

}
