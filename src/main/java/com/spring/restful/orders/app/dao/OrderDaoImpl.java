package com.spring.restful.orders.app.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.restful.orders.app.common.DbQueries;
import com.spring.restful.orders.app.model.Order;

@Repository
public class OrderDaoImpl implements OrderDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int createOrder(Order order) {

		Object[] params = { order.getName(), order.getDescription(), order.getStatus(),
				new java.sql.Timestamp(new Date().getTime()), // orderDate
				new java.sql.Timestamp(new Date().getTime())// lastUpdated
		};
		int count = jdbcTemplate.update(DbQueries.INSERT_ORDER, params);
		return count;
	}

	@Override
	public List<Order> getAllOrders() {
		List<Order> orders = null;
		orders = jdbcTemplate.query(DbQueries.GET_ALL_ORDERS, new BeanPropertyRowMapper<Order>(Order.class));
		return orders;
	}

	@Override
	public Order getOrderById(Long id) {
		Order order = jdbcTemplate.queryForObject(DbQueries.GET_ORDER_BY_ID,
				new BeanPropertyRowMapper<Order>(Order.class), id);
		return order;
	}

	@Override
	public int updateOrderById(Order order, Long id) {
		Object[] params = { order.getName(), order.getDescription(), order.getStatus(),
				new java.sql.Timestamp(new Date().getTime()), id };
		int count = jdbcTemplate.update(DbQueries.UPDATE_ORDER_BY_ID, params);
		return count;
	}

	@Override
	public int deleteOrderById(Long id) {
		Object[] params = { id };
		int count = jdbcTemplate.update(DbQueries.DELETE_ORDER_BY_ID, params);
		return count;
	}

	@Override
	public int[] batchInsertOrder(List<Order> orders) {
		int count[] = jdbcTemplate.batchUpdate(DbQueries.INSERT_ORDER, new OrderBatchSetter(orders));
		return count;
	}

}
