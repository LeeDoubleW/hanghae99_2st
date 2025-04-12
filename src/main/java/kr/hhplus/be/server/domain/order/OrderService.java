package kr.hhplus.be.server.domain.order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import kr.hhplus.be.server.domain.coupon.CouponInfo;
import kr.hhplus.be.server.domain.product.Product;
import kr.hhplus.be.server.domain.product.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	private final OrderRepository orderRepository;
	private final ProductRepository productRepository;
	
	public Order createOrder(OrderCommand.Order command, CouponInfo.IssueCoupon issueCoupon) {
		List<OrderItem> orderItems = new ArrayList<>();
		command.getProducts().forEach(op -> {
			Product data =  productRepository.findById(op.getProductId());
			
			OrderItem orderItem = OrderItem.of(data.id(), op.getQuantity(), data.price());
            orderItems.add(orderItem);
        });

        // 주문 생성
        Order order = Order.createOrder(command.getUserId(), command.getCouponId(), orderItems);
        
        if(issueCoupon != null) {
        	order.calculateDiscountPrice(issueCoupon.getIssuedCoupon().coupon().type(), issueCoupon.getIssuedCoupon().coupon().amount());
        }
        
        order.calculateFinalPrice();
        
        orderRepository.save(order);
		
		return order;		
    }
}
