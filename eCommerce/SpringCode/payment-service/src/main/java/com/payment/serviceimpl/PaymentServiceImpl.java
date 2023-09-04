package com.payment.serviceimpl;


import com.payment.dao.OrderDao;
import com.payment.dto.OrderDto;
import com.payment.dto.PaymentDTO;
import com.payment.exception.ResourceDataException;
import com.payment.exception.ResourceNotFoundException;
import com.payment.model.Order;
import com.payment.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.TimeZone;

@Service
public class PaymentServiceImpl implements PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    @Autowired
    OrderDao orderDao;

    @Override
    public OrderDto processPayment(PaymentDTO payment) {
        logger.info("Payment ServiceImpl - ProcessPayment called()");
        Order order = orderDao.checkOrderId(payment.getOrderId());
        if (order==null) {
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND.value(), "order id not found");
        }
        if (payment.getCardNumber().length() != 16) {
            throw new ResourceDataException(HttpStatus.UNAUTHORIZED.value(), "card number is wrong");
        }
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        if ((year > payment.getGetExpirationYear()) || (year == payment.getGetExpirationYear() && month < payment.getExpirationMonth())) {
            throw new ResourceDataException(HttpStatus.UNAUTHORIZED.value(), "card expired");
        }
        if(payment.getCode()<100 || payment.getCode()>999)
        {
            throw new ResourceDataException(HttpStatus.UNAUTHORIZED.value(), "code is wrong");
        }
        order.setPaymentStatus("Paid");
        orderDao.save(order);
        Order order1 = orderDao.checkOrderId(order.getOrderId());
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(order1.getOrderId());
        orderDto.setOrderStatus(order1.getOrderStatus());
        orderDto.setOrderAmount(order1.getOrderAmount());
        orderDto.setPaymentStatus(order1.getPaymentStatus());
        orderDto.setBillingAddress(order1.getBillingAddress());
        orderDto.setOrderCreated(order1.getOrderCreated());
        return orderDto;
    }

}