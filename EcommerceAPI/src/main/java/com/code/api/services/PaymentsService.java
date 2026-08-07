package com.code.api.services;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.code.api.models.Category;
import com.code.api.models.ItemOrder;
import com.code.api.models.OrderRequestDTO;
import com.code.api.models.Payments;
import com.code.api.reposatories.IOrdersReposatory;
import com.code.api.reposatories.IPaymentReposatory;
import com.razorpay.RazorpayClient;

@Service
public class PaymentsService implements IPaymentService {
	@Value("${razorpay.key_id}")
    private String keyId;

    @Value("${razorpay.key_secret}")
    private String keySecret;

    @Autowired
    private IOrdersReposatory ordersReposatory;
    @Autowired
    private IPaymentReposatory paymentReposatory;
	@Override
	public Payments creatPayment(Payments payment) {
		// TODO Auto-generated method stub
		return paymentReposatory.save(payment);
	}
	
	@Override
	public Payments getPaymentById(int id) {
		// TODO Auto-generated method stub
		return paymentReposatory.findById(id).get();
	}
	@Override
	public List<Payments> getAllPayments() {
		// TODO Auto-generated method stub
		return paymentReposatory.findAll();
	}
	
}
