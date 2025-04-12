package kr.hhplus.be.server.domain.coupon;

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
}
