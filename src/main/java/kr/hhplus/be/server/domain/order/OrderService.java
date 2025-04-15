package kr.hhplus.be.server.domain.order;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import kr.hhplus.be.server.domain.coupon.CouponInfo;
import kr.hhplus.be.server.domain.product.Product;
import kr.hhplus.be.server.domain.product.ProductInfo;
import kr.hhplus.be.server.domain.product.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	private final OrderRepository orderRepository;
	
	public Order createOrder(OrderCommand.Order command, CouponInfo.IssueCoupon issueCoupon, CouponInfo.CouponData couponData, List<ProductInfo.V1> products) {
		List<OrderItem> orderItems = new ArrayList<>();
		IntStream.range(0, command.getProducts().size()).forEach(idx -> {		
			OrderItem orderItem = OrderItem.of(products.get(idx).getId(), command.getProducts().get(idx).getQuantity(), products.get(idx).getPrice());
            orderItems.add(orderItem);
        });

        // 주문 생성
        Order order = Order.createOrder(command.getUserId(), command.getCouponId(), orderItems);
        
        if(issueCoupon != null) {
        	order.calculateDiscountPrice(couponData.getCoupon());
        }
        
        order.calculateFinalPrice();
        
        orderRepository.save(order);
		
		return order;		
    }
}
