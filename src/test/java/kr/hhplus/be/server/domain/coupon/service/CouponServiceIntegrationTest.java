package kr.hhplus.be.server.domain.coupon.service;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.hhplus.be.server.domain.coupon.dto.CouponCommand;
import kr.hhplus.be.server.domain.coupon.dto.CouponInfo.IssueCoupon;
import kr.hhplus.be.server.domain.coupon.entity.Coupon;

@SpringBootTest
public class CouponServiceIntegrationTest {
	@Autowired
    private CouponService couponService;
	
	private Coupon coupon;
	
	@BeforeEach
	void setUp() {
		coupon = couponService.save(Coupon.of("AMOUNT", 1000L, 10L, 1L, LocalDate.now(), LocalDate.now().plusDays(1), 2));
	}
	
	// 쿠폰 발급
	@Test
	void 쿠폰_발급() {
		Long userId = 1L;
		CouponCommand.IssueCoupon issueCoupon = CouponCommand.IssueCoupon.of(coupon.id(), userId);
		
		IssueCoupon issuedCoupon = couponService.IssueCoupon(issueCoupon);
		
		assertThat(issuedCoupon.getIssuedCoupon().couponId()).isEqualTo(coupon.id());
	}
	
	// 쿠폰 수량 부족
	@Test
	void 쿠폰_발급_수량부족() {
		Long userId = 1L;
		CouponCommand.IssueCoupon issueCoupon = CouponCommand.IssueCoupon.of(coupon.id(), userId);
		
		IssueCoupon issuedCoupon = couponService.IssueCoupon(issueCoupon);
		issuedCoupon.getIssuedCoupon().couponId();
		assertThatThrownBy(()->issuedCoupon.getIssuedCoupon().couponId())
		.isInstanceOf(IllegalArgumentException.class)
        .hasMessage("수량이 부족합니다.");
	}
}
