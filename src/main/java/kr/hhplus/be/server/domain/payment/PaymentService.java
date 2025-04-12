package kr.hhplus.be.server.domain.payment;

import org.springframework.stereotype.Service;

import kr.hhplus.be.server.domain.order.OrderRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {
	private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    
    public void pay(PaymentCommand.Pay command) {
    	paymentRepository.save(Payment.of(command.userId(), command.orderId(), command.amount()));
    }
}
