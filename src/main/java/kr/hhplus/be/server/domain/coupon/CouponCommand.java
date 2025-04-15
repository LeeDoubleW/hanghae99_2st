package kr.hhplus.be.server.domain.coupon;

import lombok.Getter;

public class CouponCommand {
	@Getter
	public static class IssueCoupon {
		private Long issuedCouponId;
		private Long userId;
		
		public IssueCoupon(Long issuedCouponId, Long userId) {
			this.issuedCouponId = issuedCouponId;
			this.userId = userId;
		}
		
		public static IssueCoupon of(Long issuedCouponId, Long userId) {
			return new IssueCoupon(issuedCouponId, userId);
		}
	}
}
