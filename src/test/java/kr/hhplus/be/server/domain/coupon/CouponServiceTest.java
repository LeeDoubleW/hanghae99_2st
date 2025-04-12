package kr.hhplus.be.server.domain.coupon;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserCouponTest {

    Long userId;
    Coupon coupon;

    @BeforeEach
    void setUp() {
    	userId = 1L;
    	coupon = new Coupon("AMOUNT", 100L, 10L, 10L, LocalDate.now(), LocalDate.now().plusDays(10), 5);
    }

    @Test
    void 쿠폰_사용_성공() {
    	
        IssuedCoupon userCoupon = new IssuedCoupon(userId, null, coupon);

        userCoupon.use();

        assertThat(userCoupon.usedAt()).isNotNull();
    }
}