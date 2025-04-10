package kr.hhplus.be.server.application.order;

import org.springframework.stereotype.Component;

import kr.hhplus.be.server.domain.order.Order;
import kr.hhplus.be.server.domain.order.OrderService;
import kr.hhplus.be.server.domain.product.ProductService;
import kr.hhplus.be.server.domain.user.UserPoint;
import kr.hhplus.be.server.domain.user.UserPointCommand;
import kr.hhplus.be.server.domain.user.UserPointService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderFacade {
	private final UserPointService userPointService;
	private final ProductService productService;
	private final OrderService orderService;
	
	public void orderAndPayment(OrderCriteria cri) {
		// 유저
		UserPoint userPoint = userPointService.getUser(cri.getUserId());
		Order order = orderService.createOrder();
		cri.getProducts().forEach(product -> productService.decreaseQuantity(product.getProductId(), product.getQuantity()));
		
		// 결제
		userPointService.use(UserPointCommand.Use.of(userPoint.userId(), 100000l));
	}
}
