package kr.hhplus.be.server.application.order;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.hhplus.be.server.domain.coupon.dto.CouponCommand;
import kr.hhplus.be.server.domain.coupon.dto.CouponInfo;
import kr.hhplus.be.server.domain.coupon.service.CouponService;
import kr.hhplus.be.server.domain.order.dto.OrderInfo;
import kr.hhplus.be.server.domain.order.service.OrderService;
import kr.hhplus.be.server.domain.payment.dto.PaymentCommand;
import kr.hhplus.be.server.domain.payment.service.PaymentService;
import kr.hhplus.be.server.domain.product.dto.ProductInfo;
import kr.hhplus.be.server.domain.product.service.ProductService;
import kr.hhplus.be.server.domain.user.dto.UserPointCommand;
import kr.hhplus.be.server.domain.user.entity.UserPoint;
import kr.hhplus.be.server.domain.user.service.UserPointService;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
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
		CouponInfo.IssueCoupon issueCoupon = couponService.getUserCoupon(CouponCommand.IssueCoupon.of(cri.getCouponId(), cri.getUserId()));
		CouponInfo.CouponData couponData = couponService.getCouponData(CouponCommand.IssueCoupon.of(cri.getCouponId(), cri.getUserId()));
		
		// ProductCommand ?
		List<ProductInfo.V1> productIds = productService.getProductListById(cri.toProductCommand());
		
		OrderInfo orderinfo = orderService.createOrder(cri.toOrderCommand(), issueCoupon, couponData, productIds);
		
		// 추후에 리팩토링
		cri.getProducts().forEach(product -> productService.decreaseQuantity(product.getProductId(), product.getQuantity()));
		userPoint.use(orderinfo.order().finalAmount());
		userPointService.use(UserPointCommand.Use.of(userPoint.userId(), userPoint.point()));
		issueCoupon.getIssuedCoupon().use();
		
		paymentService.pay(PaymentCommand.Pay.of(cri.getUserId(), orderinfo.order().id(), orderinfo.order().finalAmount()));
		
		return OrderResult.from(orderinfo);
	}
}
