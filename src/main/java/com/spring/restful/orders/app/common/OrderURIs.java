package com.spring.restful.orders.app.common;

public interface OrderURIs {
	String ORDER_API_ROOT = "/orders";
	String ORDER_API_ROOT_BATCH = "/orders/batch";
	String ORDER_API_URI_BY_ID = ORDER_API_ROOT + "/" + "{id}";
	String GET_ORDER_BY_NAME_ID = ORDER_API_ROOT + "/" + "{name}/{id}";

}
