package kr.hhplus.be.server.domain.order.service;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.hhplus.be.server.application.order.OrderCriteria;
import kr.hhplus.be.server.domain.coupon.dto.CouponCommand;
import kr.hhplus.be.server.domain.coupon.dto.CouponInfo;
import kr.hhplus.be.server.domain.coupon.entity.Coupon;
import kr.hhplus.be.server.domain.coupon.service.CouponService;
import kr.hhplus.be.server.domain.order.dto.OrderInfo;
import kr.hhplus.be.server.domain.payment.dto.PaymentCommand;
import kr.hhplus.be.server.domain.payment.service.PaymentService;
import kr.hhplus.be.server.domain.product.dto.ProductCommand;
import kr.hhplus.be.server.domain.product.dto.ProductInfo;
import kr.hhplus.be.server.domain.product.entity.Product;
import kr.hhplus.be.server.domain.product.service.ProductService;
import kr.hhplus.be.server.domain.user.dto.UserPointCommand;
import kr.hhplus.be.server.domain.user.entity.UserPoint;
import kr.hhplus.be.server.domain.user.service.UserPointService;

@SpringBootTest
public class OrderServiceIntegrationTest {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserPointService userPointService;
	
	@Autowired
	private CouponService couponService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private ProductService productService;
	
	private UserPoint user;
	private OrderCriteria orderCriteria;
	private List<OrderCriteria.Product> products = new ArrayList<>();
	private Coupon coupon;
	private CouponInfo.IssueCoupon issuedCoupon;
	private Product product;
	private Product product2;
	private ProductCommand.Products productList;
	
	/*
	 * // 유저
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
	 */
	
	@BeforeEach
	void setUp() {
		// 유저 등록
		user = userPointService.save(UserPoint.of(1L, "james", 10000L));
		// 쿠폰 등록
		coupon = couponService.save(Coupon.of("AMOUNT", 1000L, 10L, 10L, LocalDate.now(), LocalDate.now().plusDays(3), 3));
		// 쿠폰 발급
		issuedCoupon = couponService.IssueCoupon(CouponCommand.IssueCoupon.of(coupon.id(), user.userId()));
		// 상품 등록
		product = productService.save(Product.of(101L, "노트", 100L, 10L, 10L));
		product2 = productService.save(Product.of(102L, "샤프", 100L, 10L, 10L));
		
		products.add(OrderCriteria.Product.of(product.id(), 1L));
		products.add(OrderCriteria.Product.of(product2.id(), 1L));
		
		orderCriteria = OrderCriteria.of(user.userId(), coupon.id(), products);
		
		productList = orderCriteria.toProductCommand();
	}
	
	// 주문
	@Test
	void 주문() {
		// 쿠폰 정보 조회
		CouponInfo.IssueCoupon issueCoupon = couponService.getUserCoupon(CouponCommand.IssueCoupon.of(issuedCoupon.getIssuedCoupon().id(), user.userId()));
		CouponInfo.CouponData couponData = couponService.getCouponData(CouponCommand.IssueCoupon.of(issuedCoupon.getIssuedCoupon().id(), user.userId()));
		
		List<ProductInfo.V1> productIds = productService.getProductListById(productList);
		//ProductCommand.Products
		OrderInfo orderInfo = orderService.createOrder(orderCriteria.toOrderCommand(), issueCoupon, couponData, productIds);
		
		assertThat(orderInfo).isNotNull();
		
		orderCriteria.getProducts().forEach(product -> productService.decreaseQuantity(product.getProductId(), product.getQuantity()));
		
		assertThat(productService.getProduct(product.id()).getRemainQuantity()).isEqualTo(9L);
		assertThat(productService.getProduct(product2.id()).getRemainQuantity()).isEqualTo(9L);
		
		user.use(orderInfo.order().finalAmount());
		userPointService.use(UserPointCommand.Use.of(user.userId(), user.point()));
		issueCoupon.getIssuedCoupon().use();
		
		assertThat(issueCoupon.getIssuedCoupon().usedAt()).isNotNull();
		
		paymentService.pay(PaymentCommand.Pay.of(user.userId(), orderInfo.order().id(), orderInfo.order().finalAmount()));
	}
}
