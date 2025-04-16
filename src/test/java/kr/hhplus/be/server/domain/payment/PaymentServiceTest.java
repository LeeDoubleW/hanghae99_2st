package kr.hhplus.be.server.domain.payment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import kr.hhplus.be.server.domain.payment.dto.PaymentCommand;
import kr.hhplus.be.server.domain.payment.entity.Payment;
import kr.hhplus.be.server.domain.payment.repository.PaymentRepository;
import kr.hhplus.be.server.domain.payment.service.PaymentService;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;
    
    private Payment payment;

    private Long userId;
    private Long orderId;
    private Long amount;

    @BeforeEach
    void setUp() {
        userId = 1L;
        orderId = 1L;
        amount =1000L;
        
        payment = new Payment(userId, orderId, 1000L);
    }

    @Test
    void paymentSuccess() {
        // givne
        PaymentCommand.Pay command = new PaymentCommand.Pay(userId, orderId, amount);

        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        // when

        // then
        assertThat(payment).isNotNull();
    }
}