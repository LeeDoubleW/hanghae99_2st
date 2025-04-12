package kr.hhplus.be.server.domain.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import kr.hhplus.be.server.domain.coupon.CouponInfo;
import kr.hhplus.be.server.domain.coupon.IssuedCoupon;
import kr.hhplus.be.server.domain.product.Product;
import kr.hhplus.be.server.domain.product.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private OrderService orderService;

    private Product product;
    private IssuedCoupon coupon;

    @BeforeEach
    void setUp() {
    	product = Product.builder()
                .id(100L)
                .productName("My")
                .price(50L)
                .totalQuantity(10L)
                .remainQuantity(10L)
                .build();
        
        coupon = null;
    }


    @Test
    void 주문_생성_성공() {
        // given
        OrderCommand.Order.Product orderItem = new OrderCommand.Order.Product(100L, 2L);
        List<OrderCommand.Order.Product> orderItems = List.of(orderItem);
        OrderCommand.Order orderCommand = new OrderCommand.Order(1L, null, orderItems);

        when(productRepository.findById(100L)).thenReturn(product);
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // when
        Order orderInfo = orderService.createOrder(orderCommand, CouponInfo.IssueCoupon.of(coupon));

        // then
        assertThat(orderInfo.totalAmount()).isEqualTo(100L);
    }
}