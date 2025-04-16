package kr.hhplus.be.server.domain.coupon.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import kr.hhplus.be.server.domain.coupon.dto.CouponCommand;
import kr.hhplus.be.server.domain.coupon.dto.CouponInfo;
import kr.hhplus.be.server.domain.coupon.entity.Coupon;
import kr.hhplus.be.server.domain.coupon.entity.IssuedCoupon;
import kr.hhplus.be.server.domain.coupon.repository.CouponRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CouponService {
	
	private final CouponRepository couponRepo;
	
	public CouponInfo.IssueCoupon getUserCoupon(CouponCommand.IssueCoupon issueCommand) {
		// CouponInfo 셋팅 발급된 쿠폰과 쿠폰 정보
		return CouponInfo.IssueCoupon.of(couponRepo.findByIssuedCouponForId(issueCommand.getUserId(), issueCommand.getIssuedCouponId())) ;
	}
	
	public CouponInfo.CouponData getCouponData(CouponCommand.IssueCoupon issueCommand) {
		return CouponInfo.CouponData.of(couponRepo.findByCouponDataForId(issueCommand.getIssuedCouponId()));
	}
	
	public CouponInfo.IssueCoupon IssueCoupon(CouponCommand.IssueCoupon issue) {
		Coupon coupon = couponRepo.findByCouponDataForId(issue.getIssuedCouponId());
		IssuedCoupon issuedCoupon = null;
		
		// 쿠폰 정보로 validation 체크 
		if(coupon.isIssuedAble()) {
			issuedCoupon = couponRepo.save(IssuedCoupon.create(issue.getUserId(), issue.getIssuedCouponId(), LocalDate.now().plusDays(coupon.expiredDay())));
			coupon.decreaseRemainQuantity();
			couponRepo.save(coupon);
		}
		
		return CouponInfo.IssueCoupon.of(issuedCoupon);		
	}
}
