package kr.hhplus.be.server.infrastructure.payment;

import org.springframework.stereotype.Component;

import kr.hhplus.be.server.domain.payment.entity.Payment;
import kr.hhplus.be.server.domain.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository{

	private final PaymentJpaRepository paymentJpaRepository;
	
	@Override
	public Payment save(Payment payment) {

		return paymentJpaRepository.save(payment);
	}

}
