package com.order.controller;

import com.order.dto.ApiResponse;
import com.order.dto.OrderDto;
import com.order.dto.OrderRequest;
import com.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	OrderService orderService;
	
	@PostMapping("/createOrderByUserId")
	public ResponseEntity<OrderDto> createOrder(@RequestBody OrderRequest request){
		logger.info("Order Controller - entering createOrder method..");
		OrderDto orderDto = orderService.create(request,request.getUserId());
	    return ResponseEntity.ok().body(orderDto);
	}
	
	//Getting all Order from user
	@GetMapping("/getOrderByUserId")
	public ResponseEntity<List<OrderDto>>getOrderByUser(@RequestParam Long userId){
		logger.info("Order Controller - entering getOrderByUser method..");
		List<OrderDto> allOrder = this.orderService.getAllOrder(userId);
		return ResponseEntity.ok().body(allOrder);
	}
	//Getting all Order
	@GetMapping("/getAllOrder")
	public ResponseEntity<List<OrderDto>> getAllOrder(){
		logger.info("Order Controller - entering getAllOrder method..");
		List<OrderDto> listAllOrder = this.orderService.listAllOrder();
		return ResponseEntity.ok().body(listAllOrder);
	}


	@DeleteMapping("/deleteOrderById/{orderId}")
	public ResponseEntity<ApiResponse> delete(@RequestParam int orderId){
		logger.info("Order Controller - entering delete method..");

		orderService.deleteOrder(orderId);

		return new ResponseEntity<>(new ApiResponse("Order Deleted",true),HttpStatus.OK);
	}

	@PutMapping("/updateById/{id}")
	public ResponseEntity<OrderDto> update( @PathVariable int id,@RequestBody OrderDto orderDto){
		logger.info("Order Controller - entering update method..");
		OrderDto updateOrder = this.orderService.updateOrder(id, orderDto);
		return ResponseEntity.ok().body(updateOrder);
	}

	@GetMapping("/getByOrderId/{orderId}")
   public ResponseEntity<OrderDto>getByOrderId(@PathVariable int orderId){
		logger.info("Order Controller - entering getByOrderId method..");
		OrderDto order = this.orderService.getOrder(orderId);
	   return ResponseEntity.ok().body(order);
   }
}
