package kr.hhplus.be.server.domain.order;

public record OrderInfo(
        Long orderId,
        long totalPrice,
        long discountAmount,
        long finalPrice
) {
    public static OrderInfo from(Order order) {
        return new OrderInfo(
                order.id(),
                order.totalAmount(),
                order.discountAmount(),
                order.finalAmount()
        );
    }
}
