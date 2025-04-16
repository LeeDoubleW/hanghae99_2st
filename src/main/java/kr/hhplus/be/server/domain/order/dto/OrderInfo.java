package kr.hhplus.be.server.domain.order.dto;

import kr.hhplus.be.server.domain.order.entity.Order;

public record OrderInfo(
		Order order
) {
    public static OrderInfo from(Order order) {
        return new OrderInfo(
        		order
        );
    }
}
