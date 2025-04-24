package kr.hhplus.be.server.infrastructure.coupon;

import org.springframework.stereotype.Component;

import kr.hhplus.be.server.domain.coupon.entity.Coupon;
import kr.hhplus.be.server.domain.coupon.entity.IssuedCoupon;
import kr.hhplus.be.server.domain.coupon.repository.CouponRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CouponRepositoryImpl implements CouponRepository{
	
	private final CouponJpaRepository couponJpaRepository;
	private final IssuedCouponJpaRepository issuedCouponJpaRepository;

	@Override
	public IssuedCoupon findByIssuedCouponForId(Long userId, Long couponId) {
		
		return issuedCouponJpaRepository.findByUserIdAndCouponId(userId, couponId);
	}

	@Override
	public Coupon findByCouponDataForId(Long couponId) {

		return couponJpaRepository.findByCouponId(couponId);
	}

	@Override
	public IssuedCoupon save(IssuedCoupon coupon) {

		return issuedCouponJpaRepository.save(coupon);
	}

	@Override
	public Coupon save(Coupon coupon) {

		return couponJpaRepository.save(coupon);
	}
	
}
