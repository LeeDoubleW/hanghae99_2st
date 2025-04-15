package kr.hhplus.be.server.application.order;

import kr.hhplus.be.server.domain.order.OrderInfo;

public record OrderResult(
        Long orderId,
        long totalPrice,
        long finalPrice
) {

    public static OrderResult from(OrderInfo orderInfo) {
        return new OrderResult(
                orderInfo.orderId(),
                orderInfo.totalPrice(),
                orderInfo.finalPrice()
        );
    }
}
