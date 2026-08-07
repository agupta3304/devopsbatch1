package com.code.api.reposatories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code.api.models.Payments;
@Repository
public interface IPaymentReposatory extends JpaRepository<Payments, Integer> {
	
	
}
