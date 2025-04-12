package kr.hhplus.be.server.domain.coupon;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CouponService {
	
	private final CouponRepository couponRepo;
	
	public CouponInfo.IssueCoupon getUserCoupon(CouponCommand.IssueCoupon issueCommand) {
		return CouponInfo.IssueCoupon.of(couponRepo.findById(issueCommand)) ;
	}
}
