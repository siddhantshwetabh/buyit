package com.payment.controller;


import com.payment.dto.OrderDto;
import com.payment.dto.PaymentDTO;
import com.payment.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/payment")
public class PaymentController {

    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private PaymentService paymentService;

    @PutMapping(value="/payment")
    public ResponseEntity<OrderDto> payment(@RequestBody PaymentDTO payment){
        logger.info("Payment received for orderId: {} ", payment.getOrderId());
        OrderDto orderDto = paymentService.processPayment(payment);
        return ResponseEntity.ok().body(orderDto);
    }

}
