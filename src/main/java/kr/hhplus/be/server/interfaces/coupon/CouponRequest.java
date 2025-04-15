package kr.hhplus.be.server.interfaces.coupon;

import jakarta.validation.constraints.NotNull;
import kr.hhplus.be.server.domain.coupon.CouponCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CouponRequest {
	@Getter
	@NoArgsConstructor
	public static class V1 {		
		@NotNull
		private Long couponId;
		
		public V1(Long couponId) {
			this.couponId = couponId;
		}
		
		public CouponCommand.IssueCoupon toCommand(Long userId) {
			return CouponCommand.IssueCoupon.of(couponId, userId);
		}
	}
}
