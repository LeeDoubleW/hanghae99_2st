package kr.hhplus.be.server.domain.payment.dto;

public class PaymentCommand {
	public record Pay(Long userId, Long orderId, long amount) {

        public static Pay of(Long userId, Long orderId, long amount) {
            return new Pay(userId, orderId, amount);
        }
    }
}
