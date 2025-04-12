package kr.hhplus.be.server.domain.coupon;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    private long userId;
    private LocalDate expiryDate;
    private LocalDateTime usedAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id", nullable = false)
    private Coupon coupon;
    
    public IssuedCoupon(long userId, LocalDate expiryDate, Coupon coupon) {
		this.userId = userId;
		this.expiryDate = expiryDate;
		this.coupon = coupon;
		this.usedAt = null;
	}

    public void use() {
    	this.usedAt = LocalDateTime.now();
    }
}
