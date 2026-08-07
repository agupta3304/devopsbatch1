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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.code.api.models.Item;
import com.code.api.models.Users;
import com.code.api.services.IItemService;
import com.code.api.services.IUsersService;

@RestController
@RequestMapping("api/item/")
public class ItemController {
//add the dependency
	@Autowired
	IItemService itemService;
	//root path
	@GetMapping(value="/")
	public List<Item> getAllItem()
	{
		return itemService.getAll();
	}
	@GetMapping(value="/{id}")
	public Item getItemById(@PathVariable("id") int id)
	{
		return itemService.getById(id);
	}
	
	@PostMapping(value="create")
	public Item itemCreate(@RequestBody Item item)
	{
		return itemService.add(item);
	}
	@PutMapping(value="edit")
	public Item userEdit(@RequestBody Item item)
	{
		return itemService.update(item);
	}
	@DeleteMapping(value="delete/{id}")
	public String itemDelete(@PathVariable("id") int id)
	{
		return itemService.delete(id);
	}
	
}
