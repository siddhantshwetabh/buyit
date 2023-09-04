package com.order.serviceimpl;

import com.order.dao.CartDAO;
import com.order.dao.OrderDao;
import com.order.dao.UserDao;
import com.order.dto.OrderDto;
import com.order.dto.OrderRequest;
import com.order.exception.ResourceNotFoundException;
import com.order.exception.ResourceServerException;
import com.order.model.*;
import com.order.service.OrderService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	private UserDao userDao;
	@Autowired
	private CartDAO cartDAO;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private  ModelMapper mapper;
	

	@Override
	public OrderDto create(OrderRequest request, long userId) {
		logger.info("OrderServiceImpl - entering create method..");
	   User findByUserId = this.userDao.findByUserId(userId);
	  	int cartId=request.getCartID();
	   Cart cart = this.cartDAO.findById(cartId).orElseThrow(() -> new ResourceNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Cart id not found"));
	    Set<CartItem> items= cart.getIteam();
	    Order order=new Order();
	    AtomicReference<Double>totalOrderPrize= new AtomicReference<>(0.0);
	    Set<OrderItem> orderItems = items.stream().map(cartItem ->{
	    	OrderItem orderItem=new OrderItem();
	    	  orderItem.setProduct(cartItem.getProduct());
	    	  orderItem.setQuantity(cartItem.getQuantity());
	    	  orderItem.setTotalProductPrize(cartItem.getTotalProductPrize());
	    	  orderItem.setOrder(order);
	    	 totalOrderPrize.set(totalOrderPrize.get()+orderItem.getTotalProductPrize());
	    	return orderItem;
	    }).collect(Collectors.toSet());
	          order.setItem(orderItems);
			  String  address = request.getAddress();
	          order.setBillingAddress(address);
	          order.setPaymentStatus("Not Paid");
	          order.setOrderCreated(new Date());
	          order.setOrderDelivered(null);
	          order.setOrderStatus("Created");
	          order.setUser(findByUserId);
	          order.setOrderAmount(totalOrderPrize.get());
	         
	         if(order.getOrderAmount()>0) {
	        	 Order savedOrder = this.orderDao.save(order);
			       cart.getIteam().clear();
			       this.cartDAO.save(cart);
			return this.mapper.map(savedOrder,OrderDto.class) ;
	        	 
	         }else {
				 throw new ResourceServerException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Plz Add Cart First and then place Order");
	         }
	}

	@Override
	public List<OrderDto> getAllOrder(long userId) {
		logger.info("OrderServiceImpl - entering getAllOrder method..");
		User findByUserId = this.userDao.findByUserId(userId);
		List<Order> findAll = this.orderDao.findByUser(findByUserId).orElseThrow(()-> new ResourceServerException(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Order Not Found"));
		return findAll.stream().map(each -> this.mapper.map(each,OrderDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<OrderDto> listAllOrder() {
		logger.info("OrderServiceImpl - entering listAllOrder method..");
		List<Order> listallorder = this.orderDao.findAll();
		return listallorder.stream().map(each-> this.mapper.map(each,OrderDto.class)).collect(Collectors.toList());
	}

	@Override
	public OrderDto getOrder(int orderId) {
		logger.info("OrderServiceImpl - entering getOrder method..");
		Order order = this.orderDao.findByOrderId(orderId);
		return this.mapper.map(order,OrderDto.class);
	}

	@Override
	public void deleteOrder(int orderId) {
		logger.info("OrderServiceImpl - entering deleteOrder method..");
		Order findById = this.orderDao.findByOrderId(orderId);
		if(findById != null)
			orderDao.delete(findById);
		else
			throw new ResourceServerException(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Order not found!");
	}


	@Override
	public OrderDto updateOrder(int orderId, OrderDto orderDto) {
		logger.info("OrderServiceImpl - entering updateOrder method..");
		Order findOrder = this.orderDao.findById(orderId).orElseThrow(()->new ResourceNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR.value(),"order not Found"));
		findOrder.setOrderDelivered(orderDto.getOrderDelivered());
		findOrder.setPaymentStatus(orderDto.getPaymentStatus());
		findOrder.setOrderStatus(orderDto.getOrderStatus());
		Order save = this.orderDao.save(findOrder);

		return this.mapper.map(save,OrderDto.class);
	}

}
