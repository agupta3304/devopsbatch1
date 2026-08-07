package com.code.api.controllers;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.api.models.CartItems;
import com.code.api.models.ConfirmPaymentRequest;
import com.code.api.models.ItemOrder;
import com.code.api.models.ItemOrderDetails;
import com.code.api.models.OrderRequestDTO;
import com.code.api.models.Payments;
import com.code.api.models.Users;
import com.code.api.services.IItemOrderDetailsService;
import com.code.api.services.IItemOrderService;
import com.code.api.services.IPaymentService;
import com.code.api.services.IUsersService;
import com.razorpay.RazorpayClient;



@RestController
@RequestMapping("api/payment/")
public class PaymentController {
	@Value("${razorpay.key_id}")
    private String keyId;

    @Value("${razorpay.key_secret}")
    private String keySecret;

@Autowired
IItemOrderService orderService;
@Autowired
IUsersService usersService;
@Autowired
IPaymentService paymentService;
@Autowired
IItemOrderDetailsService itemOrderDetailsService;

@GetMapping(value="getRazorpayOrderId/{id}")
public ItemOrder getRazorId(@PathVariable("id")String id)
{
	return orderService.getRazorpayOrderId(id);
}

@PostMapping(value="createorder")
public ResponseEntity<?> create(@RequestBody OrderRequestDTO request) throws Exception {
    double total = request.getTotalamount();

    RazorpayClient client = new RazorpayClient(keyId, keySecret);
    JSONObject options = new JSONObject();
    options.put("amount", (int)(total * 100));
    options.put("currency", "INR");
    options.put("receipt", "txn_" + System.currentTimeMillis());

    com.razorpay.Order razorOrder = client.orders.create(options);

    ItemOrder order = new ItemOrder();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
    String formattedDate = order.getCreatedAt().format(formatter);
     order.setOrderdate(formattedDate);
    Users users=usersService.getUserById(request.getUserid());
    
    order.setUsers(users);
    order.setRazorpayOrderId(razorOrder.get("id"));
    order.setTotalamount(total);
    order.setStatus("CREATED");
    orderService.add(order);
    for (CartItems item:request.getItems()) {
        ItemOrderDetails detail = new ItemOrderDetails();
        detail.setProductname(item.getItemName());
        detail.setCategoryname(item.getCategory().getCatname());
        detail.setPrice(item.getItemPrice());
        detail.setQty(item.getQuantity());
        detail.setItemvalue(detail.getPrice()*detail.getQty());
        detail.setItemorder(order); // Set parent
        itemOrderDetailsService.add(detail);
    }

    //order.setOrderDetails(details);
    	//orderService.add(order);
    	
    	return ResponseEntity.ok(order);
}

@PostMapping(value="confirmpayment")
public Payments confirmPayment(@RequestBody ConfirmPaymentRequest crequest) {
	System.out.println("razororderid"+crequest.getRazorpayOrderId());
    ItemOrder order = orderService.getRazorpayOrderId(crequest.getRazorpayOrderId());
    
    if (order == null) 
    	return null;

    Payments payment = new Payments();
    payment.setOrder(order);
    payment.setRazorpayPaymentId(crequest.getRazorpayPaymentId());
    payment.setAmount(order.getTotalamount());
    payment.setStatus("SUCCESS");

    order.setStatus("PAID");
    orderService.update(order);
    paymentService.creatPayment(payment);
    return payment;
}
}
