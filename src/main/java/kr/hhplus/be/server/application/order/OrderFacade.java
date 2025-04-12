package kr.hhplus.be.server.application.order;

import org.springframework.stereotype.Component;

import kr.hhplus.be.server.domain.coupon.CouponCommand.IssueCoupon;
import kr.hhplus.be.server.domain.coupon.CouponInfo;
import kr.hhplus.be.server.domain.coupon.CouponService;
import kr.hhplus.be.server.domain.order.Order;
import kr.hhplus.be.server.domain.order.OrderService;
import kr.hhplus.be.server.domain.payment.PaymentCommand;
import kr.hhplus.be.server.domain.payment.PaymentService;
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
	private final PaymentService paymentService;
	private final CouponService couponService;
	
	public OrderResult orderAndPayment(OrderCriteria cri) {
		// 유저
		UserPoint userPoint = userPointService.getUser(cri.getUserId());
		
		// 쿠폰 조회(유저가 소유하고 있는지) 쿠폰 정보도 포함
		CouponInfo.IssueCoupon issueCoupon = couponService.getUserCoupon(IssueCoupon.of(cri.getCouponId(), cri.getUserId()));
		
		Order order = orderService.createOrder(cri.toOrderCommand(), issueCoupon);
		
		cri.getProducts().forEach(product -> productService.decreaseQuantity(product.getProductId(), product.getQuantity()));
		userPoint.use(order.finalAmount());
		userPointService.use(UserPointCommand.Use.of(userPoint.userId(), userPoint.point()));
		issueCoupon.getIssuedCoupon().use();
		
		paymentService.pay(PaymentCommand.Pay.of(cri.getUserId(), order.id(), order.finalAmount()));
		
		return null;
	}
}
