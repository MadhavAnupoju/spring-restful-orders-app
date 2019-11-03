package com.spring.restful.orders.app.dao;

import java.util.List;

import com.spring.restful.orders.app.model.Order;

public interface OrderDao {
	public int createOrder(Order order);
	public List<Order> getAllOrders();
	public Order getOrderById(Long id);
	public int updateOrderById(Order order, Long id);
	public int deleteOrderById(Long id);
	public int[] batchInsertOrder(List<Order> orders);
}
