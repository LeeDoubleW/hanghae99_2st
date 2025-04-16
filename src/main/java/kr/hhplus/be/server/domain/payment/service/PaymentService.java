package kr.hhplus.be.server.domain.payment.service;

import org.springframework.stereotype.Service;

import kr.hhplus.be.server.domain.order.repository.OrderRepository;
import kr.hhplus.be.server.domain.payment.dto.PaymentCommand;
import kr.hhplus.be.server.domain.payment.entity.Payment;
import kr.hhplus.be.server.domain.payment.repository.PaymentRepository;
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
