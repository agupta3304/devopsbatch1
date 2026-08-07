package com.code.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.api.models.ItemOrder;
import com.code.api.reposatories.IOrdersReposatory;

@Service
public class ItemOrderService implements IItemOrderService {
@Autowired
IOrdersReposatory ordersReposatory;
	@Override
	public ItemOrder add(ItemOrder itemOrder) {
		// TODO Auto-generated method stub
		return ordersReposatory.save(itemOrder);
	}

	@Override
	public ItemOrder update(ItemOrder itemOrder) {
		// TODO Auto-generated method stub
		return ordersReposatory.save(itemOrder);
	}

	@Override
	public String delete(ItemOrder itemOrder) {
		// TODO Auto-generated method stub
		ordersReposatory.delete(itemOrder);
		return "Record is deletd";
	}

	@Override
	public String delete(int id) {
		// TODO Auto-generated method stub
		ItemOrder itemOrder=ordersReposatory.findById(id).get();
		if(itemOrder==null)
		{
			return "Item Order with Id "+id+" not found";
		}
		ordersReposatory.delete(itemOrder);
		return "Record is deletd";
	}

	@Override
	public List<ItemOrder> getAll() {
		// TODO Auto-generated method stub
		return ordersReposatory.findAll();
	}

	@Override
	public ItemOrder getById(int id) {
		// TODO Auto-generated method stub
		ItemOrder itemOrder=ordersReposatory.findById(id).get();
		return itemOrder;
	}

	@Override
	public ItemOrder getRazorpayOrderId(String id) {
		// TODO Auto-generated method stub
		return ordersReposatory.findByRazorpayOrderId(id);
	}

}
