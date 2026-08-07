package com.code.api.services;

import java.util.List;

import com.code.api.models.ItemOrderDetails;



public interface IItemOrderDetailsService {
	public ItemOrderDetails add(ItemOrderDetails itemOrder);
	public ItemOrderDetails update(ItemOrderDetails itemOrder);
	public String delete(ItemOrderDetails itemOrder);
	public String delete(int id);
	//create some method to get the student
	public List<ItemOrderDetails> getAll();
	public ItemOrderDetails getById(int id);
	public ItemOrderDetails getByOrderId(int id);
}
