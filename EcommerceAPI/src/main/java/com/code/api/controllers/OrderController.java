package com.code.api.controllers;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.api.models.CartItems;
import com.code.api.models.Item;
import com.code.api.models.ItemOrder;
import com.code.api.models.ItemOrderDetails;
import com.code.api.models.OrderRequestDTO;
import com.code.api.models.Users;
import com.code.api.reposatories.IOrderDetailsReposatory;
import com.code.api.reposatories.IUsersrepository;
import com.code.api.services.IItemOrderDetailsService;
import com.code.api.services.IItemOrderService;
import com.code.api.services.IUsersService;

@RestController
@RequestMapping("api/orders/")
public class OrderController {
//add dependency
	@Autowired
	IItemOrderService itemOrderService;
	@Autowired
	IUsersService usersService ;
	@Autowired
	IItemOrderDetailsService itemOrderDetailsService;
//request mapping to get all orders
@GetMapping(value="/")	
public List<ItemOrder> getAllOrders()
{
	return itemOrderService.getAll();
}
@GetMapping(value="{id}")	
public ItemOrder getOrdersById(@PathVariable("id") int id)
{
	return itemOrderService.getById(id);
}
@PostMapping(value="create")	
public ItemOrder createOrder(@RequestBody ItemOrder itemOrder)
{
	return itemOrderService.add(itemOrder);
}
@PutMapping(value="edit")	
public ItemOrder editOrder(@RequestBody ItemOrder itemOrder)
{
	return itemOrderService.update(itemOrder);
}
@DeleteMapping(value="delete/{id}")	
public String deleteOrder(@PathVariable("id") int id)
{
	return itemOrderService.delete(id);
}

@PostMapping("placeorder")
public ResponseEntity<ItemOrder> placeOrder(@RequestBody OrderRequestDTO orderRequest) {
	System.out.println("aaaa"+orderRequest.getUserid());
	int userid=orderRequest.getUserid();
Users users=usersService.getUserById(userid);
ItemOrder order = new ItemOrder();
   if(users==null)
   {
	   return ResponseEntity.ok(order);
   }
   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
   String formattedDate = order.getCreatedAt().format(formatter);
    order.setOrderdate(formattedDate);
    order.setTotalamount(orderRequest.getTotalamount());
    System.out.println("orderdate"+order.getOrderdate().toString());
    order.setUsers(users);
    itemOrderService.add(order);
    ItemOrderDetails detail=null;
   for (CartItems item:orderRequest.getItems()) {
            detail= new ItemOrderDetails();
            detail.setProductname(item.getItemName());
            detail.setCategoryname(item.getCategory().getCatname());
            detail.setPrice(item.getItemPrice());
            detail.setQty(item.getQuantity());
            detail.setItemvalue(detail.getPrice()*detail.getQty());
            detail.setItemorder(order); // Set parent
            itemOrderDetailsService.add(detail);
        }

    
    //itemOrderRepository.save(order);

    return ResponseEntity.ok(order);
}
}
