package kr.hhplus.be.server.interfaces.order;

import lombok.Builder;

public class OrderResponse {
	
	public record V1(Long userId, Long orderId) {
		
		@Builder
		public V1(Long userId, Long orderId) {
			this.userId = userId;
			this.orderId = orderId;
		}
		
		public static V1 of(Long userId, Long orderId) {
			return V1.builder()
					.userId(userId)
					.orderId(orderId)
					.build();
		}
	}
}
