package kr.hhplus.be.server.infrastructure.order;

import org.springframework.stereotype.Component;

import kr.hhplus.be.server.domain.order.entity.Order;
import kr.hhplus.be.server.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository{

	private final OrderJpaRepository orderJpaRepository;
	
	@Override
	public Order save(Order order) {

		return orderJpaRepository.save(order);
	}

}
