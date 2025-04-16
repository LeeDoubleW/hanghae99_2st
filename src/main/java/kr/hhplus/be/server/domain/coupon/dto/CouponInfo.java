package kr.hhplus.be.server.domain.coupon.dto;

import kr.hhplus.be.server.domain.coupon.entity.Coupon;
import kr.hhplus.be.server.domain.coupon.entity.IssuedCoupon;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class CouponInfo {
	
	@Getter
	@RequiredArgsConstructor
	public static class IssueCoupon {
		private IssuedCoupon issuedCoupon;
		
		public IssueCoupon(IssuedCoupon issuedCoupon) {
			this.issuedCoupon = issuedCoupon;
		}
		
		public static IssueCoupon of(IssuedCoupon issuedCoupon) {
			return new IssueCoupon(issuedCoupon);
		}
	}
	
	@Getter
	@RequiredArgsConstructor
	public static class CouponData {
		private Coupon coupon;
		
		public CouponData(Coupon coupon) {
			this.coupon = coupon;
		}
		
		public static CouponData of(Coupon coupon) {
			return new CouponData(coupon);
		}
	}
}
