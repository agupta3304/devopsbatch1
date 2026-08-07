package com.code.api.services;

import java.util.List;

import com.code.api.models.Category;
import com.code.api.models.ItemOrder;
import com.code.api.models.Payments;

public interface IPaymentService {
public Payments creatPayment(Payments payment);
public Payments getPaymentById(int id);
public List<Payments> getAllPayments();

}
