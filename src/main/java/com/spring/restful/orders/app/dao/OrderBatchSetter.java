package com.spring.restful.orders.app.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import com.spring.restful.orders.app.model.Order;

public class OrderBatchSetter implements BatchPreparedStatementSetter {
	private List<Order> orders;

	public OrderBatchSetter(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public void setValues(PreparedStatement ps, int i) throws SQLException {
		ps.setString(1, orders.get(i).getName());
		ps.setString(2, orders.get(i).getDescription());
		ps.setString(3, orders.get(i).getStatus());
		ps.setTimestamp(4, new Timestamp(new Date().getTime()));
		ps.setTimestamp(5, new Timestamp(new Date().getTime()));
	}

	@Override
	public int getBatchSize() {
		int batchSize = orders.size();
		return batchSize;
	}

}
