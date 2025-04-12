package kr.hhplus.be.server.domain.coupon;

public interface CouponRepository {
	IssuedCoupon findById(CouponCommand.IssueCoupon issueCommand);
}
