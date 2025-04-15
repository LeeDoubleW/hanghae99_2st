package kr.hhplus.be.server.domain.coupon;

public interface CouponRepository {
	IssuedCoupon findByIssuedCouponForId(Long userId, Long couponId);
	Coupon findByCouponDataForId(Long couponId);
	IssuedCoupon save(IssuedCoupon coupon);
	Coupon save(Coupon coupon);
}
