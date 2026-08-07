package com.code.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.api.models.ItemOrderDetails;
import com.code.api.reposatories.IOrderDetailsReposatory;

@Service
public class ItemOrderDetailsService implements IItemOrderDetailsService {
@Autowired
IOrderDetailsReposatory orderDetailsReposatory;
	
	@Override
	public ItemOrderDetails add(ItemOrderDetails itemOrder) {
		// TODO Auto-generated method stub
		return orderDetailsReposatory.save(itemOrder);
	}

	@Override
	public ItemOrderDetails update(ItemOrderDetails itemOrder) {
		// TODO Auto-generated method stub
		return orderDetailsReposatory.save(itemOrder);
	}

	@Override
	public String delete(ItemOrderDetails itemOrder) {
		// TODO Auto-generated method stub
		 orderDetailsReposatory.delete(itemOrder);
		return "Record is deleted Successfully";
	}

	@Override
	public String delete(int id) {
		// TODO Auto-generated method stub
		ItemOrderDetails itemOrderDetails= orderDetailsReposatory.findById(id).get();
		if(itemOrderDetails==null)
		{
			return "Record not found with id "+id;
		}
		orderDetailsReposatory.delete(itemOrderDetails);
		return "Record is deleted";
	}

	@Override
	public List<ItemOrderDetails> getAll() {
		// TODO Auto-generated method stub
		return orderDetailsReposatory.findAll();
	}

	@Override
	public ItemOrderDetails getById(int id) {
		// TODO Auto-generated method stub
		ItemOrderDetails itemOrderDetails= orderDetailsReposatory.findById(id).get();
		return itemOrderDetails;
	}

	@Override
	public ItemOrderDetails getByOrderId(int id) {
		// TODO Auto-generated method stub
		return orderDetailsReposatory.findByOrderId(id);
	}

}
