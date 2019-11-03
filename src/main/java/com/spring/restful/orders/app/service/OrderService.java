package com.spring.restful.orders.app.service;

import java.util.List;

import com.spring.restful.orders.app.model.Order;

public interface OrderService {
	public Order createOrder(Order order);
	public List<Order> getAllOrders();
	public Order getOrderById(Long id);
	public Order updateOrderById(Order order, Long id);
	public Order deleteOrderById(Long id);
	public List<Order> batchInsertOrder(List<Order> orders);

}
