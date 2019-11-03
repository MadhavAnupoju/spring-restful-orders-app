package com.spring.restful.orders.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.restful.orders.app.common.OrderURIs;
import com.spring.restful.orders.app.model.Order;
import com.spring.restful.orders.app.service.OrderService;

@RestController
public class OrderController {
	@Autowired
	private OrderService service;

	@RequestMapping(value = OrderURIs.ORDER_API_ROOT, method = RequestMethod.POST)
	public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order)  {
		order=service.createOrder(order);
		return new ResponseEntity<Order>(order, HttpStatus.CREATED);
	}

	@RequestMapping(value = OrderURIs.ORDER_API_URI_BY_ID, method = RequestMethod.GET)
	public ResponseEntity<Order> getOrderById(@Valid @PathVariable("id") long id)  {
		Order order = service.getOrderById(id);
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}

	@RequestMapping(value = OrderURIs.ORDER_API_ROOT, method = RequestMethod.GET)
	public ResponseEntity<List<Order>> getAllOrders()  {
		List<Order> orders = service.getAllOrders();
		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
	}

	@RequestMapping(value = OrderURIs.ORDER_API_URI_BY_ID, method = RequestMethod.PUT)
	public ResponseEntity<Order> updateOrderById(@Valid @RequestBody Order order, @PathVariable("id") long id)
			 {
		order = service.updateOrderById(order, id);
		order = service.getOrderById(id);
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}

	@RequestMapping(value = OrderURIs.ORDER_API_URI_BY_ID, method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteOrder(@Valid @PathVariable("id") long id)  {
		service.deleteOrderById(id);
		return new ResponseEntity<String>("Resources is deleted ", HttpStatus.GONE);
	}

	@RequestMapping(value = OrderURIs.ORDER_API_ROOT_BATCH, method = RequestMethod.POST)
	public ResponseEntity<List<Order>> orderBatchInsert(@Valid @RequestBody List<Order> orders)  {
		service.batchInsertOrder(orders);
		return new ResponseEntity<List<Order>>(orders, HttpStatus.CREATED);
	}

}
