package kr.hhplus.be.server.domain.order;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	private final OrderRepository orderRepository;
	
	public Order createOrder() {
        Order order = new Order();
        return orderRepository.save(order);
    }
}
