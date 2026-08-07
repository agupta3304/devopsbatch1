package com.code.api.reposatories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.code.api.models.ItemOrder;
import com.code.api.models.ItemOrderDetails;


@Repository
public interface IOrderDetailsReposatory extends JpaRepository<ItemOrderDetails, Integer> {

	@Query("SELECT od FROM ItemOrderDetails od WHERE od.itemorder.orderid = :id")
	ItemOrderDetails findByOrderId(@Param("id") Integer orderid);

}
