package kr.hhplus.be.server.domain.order;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kr.hhplus.be.server.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity(name = "order")
@Getter
@Accessors(fluent = true)
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Order extends BaseEntity{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long userId;
	private Long issuedCouponId;
	private Long totalAmount;
	private Long discountAmount;
	private Long finalAmount;
	private List<OrderItem> items = new ArrayList<>();
	
	@Builder
	public Order(Long userId, Long issuedCouponId, Long totalAmount, Long discountAmount, Long finalAmount, List<OrderItem> items) {
		this.userId = userId;
		this.issuedCouponId = issuedCouponId;
		this.totalAmount = totalAmount;
		this.discountAmount = discountAmount;
		this.finalAmount = finalAmount;
		this.items = items;
	}
	
	public static Order of(Long userId, Long issuedCouponId, Long totalAmount, Long discountAmount, Long finalAmount, List<OrderItem> items) {
		return Order.builder()
				.userId(userId)
				.issuedCouponId(issuedCouponId)
				.totalAmount(totalAmount)
				.discountAmount(discountAmount)
				.finalAmount(finalAmount)
				.items(items)
				.build();
	}
	
	public Long calculateDiscountPrice() {
		// 쿠폰에 대한 처리필요 
		return null;
	}
	
	public Long calculateFinalPrice() {
		return null;
	}
}
