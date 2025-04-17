package kr.hhplus.be.server.domain.order.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import kr.hhplus.be.server.domain.coupon.dto.CouponInfo;
import kr.hhplus.be.server.domain.coupon.entity.Coupon;
import kr.hhplus.be.server.domain.coupon.entity.IssuedCoupon;
import kr.hhplus.be.server.domain.order.dto.OrderCommand;
import kr.hhplus.be.server.domain.order.dto.OrderInfo;
import kr.hhplus.be.server.domain.order.entity.Order;
import kr.hhplus.be.server.domain.order.repository.OrderRepository;
import kr.hhplus.be.server.domain.order.service.OrderService;
import kr.hhplus.be.server.domain.product.dto.ProductInfo;
import kr.hhplus.be.server.domain.product.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private OrderService orderService;

    private CouponInfo.IssueCoupon issueCoupon;
    private CouponInfo.CouponData couponData;
    private List<ProductInfo.V1> products = new ArrayList<>();

    @BeforeEach
    void setUp() {
    	/*
    	product = Product.builder()
                .id(100L)
                .productName("My")
                .price(50L)
                .totalQuantity(10L)
                .remainQuantity(10L)
                .build();
        */
    	issueCoupon = CouponInfo.IssueCoupon.of(IssuedCoupon.create(1L, 10L, LocalDate.now().plusDays(5)));
    	couponData = CouponInfo.CouponData.of(Coupon.of("PERCENT", 1000L, 10L, 5L, LocalDate.now(), LocalDate.now().plusDays(10), 5));
    	products.add(ProductInfo.V1.of(1L, "product1", 100L, 10L, 5L));
    }


    @Test
    void 주문_생성_성공() {
        // given
        OrderCommand.Order.Product orderItem = new OrderCommand.Order.Product(100L, 2L);
        List<OrderCommand.Order.Product> orderItems = List.of(orderItem);
        OrderCommand.Order orderCommand = new OrderCommand.Order(1L, null, orderItems);

        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // when
        OrderInfo orderInfo = orderService.createOrder(orderCommand, issueCoupon, couponData, products);

        // then
        assertThat(orderInfo.order().totalAmount()).isEqualTo(100L);
    }
}