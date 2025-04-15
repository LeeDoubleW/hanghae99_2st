package kr.hhplus.be.server.domain.order;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import kr.hhplus.be.server.domain.BaseEntity;
import kr.hhplus.be.server.domain.coupon.Coupon;
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
	
	@Transient
	private Coupon coupon;
	
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
	
	public void calculateDiscountPrice(Coupon coupon) {
		this.discountAmount = coupon.calDiscount(this.totalAmount);
	}
	
	public void calculateFinalPrice() {
		this.finalAmount = this.totalAmount - this.discountAmount;
	}
}
