package com.spring.restful.orders.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.restful.orders.app.dao.OrderDao;
import com.spring.restful.orders.app.model.Order;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDao dao;

	@Override
	public Order createOrder(Order order) {
		int count = dao.createOrder(order);
		return order;
	}

	@Override
	public List<Order> getAllOrders() {
		List<Order> orders = dao.getAllOrders();
		return orders;
	}

	@Override
	public Order getOrderById(Long id) {
		Order order = dao.getOrderById(id);
		return order;
	}

	@Override
	public Order updateOrderById(Order order, Long id) {
		int count = dao.updateOrderById(order, id);
		return order;

	}

	@Override
	public Order deleteOrderById(Long id) {
		int count = dao.deleteOrderById(id);
		Order order = new Order();
		order.setId(id);
		return order;
	}

	@Override
	public List<Order> batchInsertOrder(List<Order> orders) {
		int count[] = dao.batchInsertOrder(orders);
		return orders;
	}

}
