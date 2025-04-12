package kr.hhplus.be.server.domain.order;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import kr.hhplus.be.server.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity(name = "order")
@Getter
@Accessors(fluent = true)
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Order extends BaseEntity{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long userId;
	private Long issuedCouponId;
	private Long totalAmount;
	private Long discountAmount;
	private Long finalAmount;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<OrderItem> items;
	
	
	public static Order createOrder(Long userId, Long issuedCouponId, List<OrderItem> items) {
        Order order = Order.builder()
                .userId(userId)
                .issuedCouponId(issuedCouponId)
                .items(items)
                .build();
        order.calculateTotalPrice();
        return order;
    }
	
	public void calculateTotalPrice() {
        this.totalAmount = items.stream().mapToLong(OrderItem::getTotalPrice).sum();
    }
	
	public void calculateDiscountPrice(String type, Long amount) {
		if(type.equals("PERCENT")) {
			this.discountAmount = this.totalAmount * amount;
		} else {
			this.discountAmount = amount;
		}
	}
	
	public void calculateFinalPrice() {
		this.finalAmount = this.totalAmount - this.discountAmount;
	}
}
