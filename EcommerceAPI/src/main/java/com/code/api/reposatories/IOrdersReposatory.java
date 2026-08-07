package com.code.api.reposatories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code.api.models.ItemOrder;


@Repository
public interface IOrdersReposatory extends JpaRepository<ItemOrder, Integer> {
		
	ItemOrder findByRazorpayOrderId(String razorpayOrderId);
}
