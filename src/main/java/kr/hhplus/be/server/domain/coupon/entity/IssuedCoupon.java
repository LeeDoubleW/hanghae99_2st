package kr.hhplus.be.server.domain.coupon.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity(name = "issued_coupon")
@Getter
@Accessors(fluent = true)
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class IssuedCoupon {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long couponId;
    private LocalDate expiryDate;
    private LocalDateTime usedAt;
    
    @Builder
    public IssuedCoupon(Long id, Long userId, Long couponId, LocalDate expiryDate) {
		this.userId = userId;
		this.couponId = couponId;
		this.expiryDate = expiryDate;
		this.usedAt = null;
	}
    
    public static IssuedCoupon create(Long userId, Long couponId, LocalDate expiryDate) {
    	return IssuedCoupon.builder()
    			.userId(userId)
    			.couponId(couponId)
    			.expiryDate(expiryDate)
    			.build();
    }

    public void use() {
    	if(expiryDate.isBefore(LocalDate.now())) {
    		throw new IllegalArgumentException();
    	}
    	this.usedAt = LocalDateTime.now();
    }
}
