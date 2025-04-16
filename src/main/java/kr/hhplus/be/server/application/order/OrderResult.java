package kr.hhplus.be.server.application.order;

import kr.hhplus.be.server.domain.order.dto.OrderInfo;
import kr.hhplus.be.server.domain.order.entity.Order;

public record OrderResult(
        Order order
) {

    public static OrderResult from(OrderInfo orderInfo) {
        return new OrderResult(
                orderInfo.order()
        );
    }
}
