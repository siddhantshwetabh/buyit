package com.payment.service;


import com.payment.dto.OrderDto;
import com.payment.dto.PaymentDTO;

public interface PaymentService {
    OrderDto processPayment(PaymentDTO payment);
}
