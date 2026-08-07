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

import com.code.api.models.ItemOrder;
import com.code.api.models.ItemOrderDetails;
import com.code.api.services.IItemOrderDetailsService;
import com.code.api.services.IItemOrderService;

@RestController
@RequestMapping("api/orderdetails/")
public class OrderDetailsController {
	//add dependency
		@Autowired
		IItemOrderDetailsService itemOrderDetailsService;
		@Autowired
		IItemOrderService itemOrderService;
	//request mapping to get all orders
	@GetMapping(value="/")	
	public List<ItemOrderDetails> getAllOrdersItem()
	{
		return itemOrderDetailsService.getAll();
	}
	@GetMapping(value="{id}")	
	public ItemOrderDetails getOrdersById(@PathVariable("id") int id)
	{
	//ItemOrder itemorder=	itemOrderService.getByO(id);
		return itemOrderDetailsService.getByOrderId(id);
	}
	@PostMapping(value="create")	
	public ItemOrderDetails createOrder(@RequestBody ItemOrderDetails itemOrderDetails)
	{
		return itemOrderDetailsService.add(itemOrderDetails);
	}
	@PutMapping(value="edit")	
	public ItemOrderDetails editOrder(@RequestBody ItemOrderDetails itemOrderDetails)
	{
		
		return itemOrderDetailsService.update(itemOrderDetails);
	}
	@DeleteMapping(value="delete/{id}")	
	public String deleteOrder(@PathVariable("id") int id)
	{
		return itemOrderDetailsService.delete(id);
	}
	
	}

