package kr.hhplus.be.server.infrastructure.coupon;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.hhplus.be.server.domain.coupon.entity.IssuedCoupon;

public interface IssuedCouponJpaRepository extends JpaRepository<IssuedCoupon, Long> {
	IssuedCoupon findByUserIdAndCouponId(Long userId, Long couponId);
}
