package kr.hhplus.be.server.domain.coupon.repository;

import kr.hhplus.be.server.domain.coupon.entity.Coupon;
import kr.hhplus.be.server.domain.coupon.entity.IssuedCoupon;

public interface CouponRepository {
	IssuedCoupon findByIssuedCouponForId(Long userId, Long couponId);
	Coupon findByCouponDataForId(Long couponId);
	IssuedCoupon save(IssuedCoupon coupon);
	Coupon save(Coupon coupon);
}
