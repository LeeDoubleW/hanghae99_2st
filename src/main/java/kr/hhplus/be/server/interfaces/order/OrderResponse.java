package kr.hhplus.be.server.interfaces.order;

import kr.hhplus.be.server.domain.order.entity.Order;

public class OrderResponse {
	
	public record V1(Order order) {
				
		public static V1 of(Order order) {
			return new V1(order);
		}
	}
}
