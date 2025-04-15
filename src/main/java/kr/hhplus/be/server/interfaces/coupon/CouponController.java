package kr.hhplus.be.server.interfaces.coupon;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.hhplus.be.server.domain.coupon.CouponService;

@RestController
@RequestMapping("/coupon")
@Tag(name = "Coupon", description = "쿠폰발급, 쿠폰사용, 쿠폰조회 API")
public class CouponController {
	
	CouponService couponService;
	
	// 쿠폰발급
	@PostMapping("/{userId}/issue")
    @Operation(summary = "쿠폰 발급", description = "쿠폰을 발급합니다.")
    public ResponseEntity<CouponResponse> issueCoupon( 
    		@Parameter(description = "쿠폰을 발급받을 사용자 ID", example = "1")
            @PathVariable("userId") Long userId,
            @Valid @RequestBody CouponRequest.V1 request
    ) {
		couponService.IssueCoupon(request.toCommand(userId));
		
		return ResponseEntity.ok(null);
		/*
        return ResponseEntity.ok(Map.of(
                "couponId", 1000001,
                "couponNm", "1000원 할인쿠폰",
                "discount_type", "BALANCE",
                "discount_rate", 1000,
                "expiredAt", "2025-07-31"
            ));
        */
    }
	
	// 쿠폰사용
	@GetMapping("/{userId}/useable")
    @Operation(
            summary = "사용가능 쿠폰조회", description = "유저의 쿠폰을 조회합니다."
    )
    public ResponseEntity<CouponResponse> getBalance(
    		@PathVariable("userId") Long userId,
            @Valid @RequestBody CouponRequest.V1 request
    ) {
		//couponService.getUserCoupon(request.toCommand(userId));
		couponService.getUserCoupon(request.toCommand(userId));
		
		return ResponseEntity.ok(null);
		/*
		long couponId = 100001;

        return ResponseEntity.ok(Map.of(
                "couponId", couponId,
                "counponNm", "1000원 할인쿠폰",
                "discount_type", "BALANCE",
                "discount_rate", 1000,
                "coupon_used", "USED"
        ));
        */
    }
	
	/*
	// 쿠폰조회
	@GetMapping("/{userId}/list")
    @Operation(summary = "보유쿠폰 목록조회", description = "유저가 보유한 쿠폰 목록을 조회합니다.")
    public ResponseEntity<Map<String, Object>> getUserCoupons(
            @Parameter(description = "사용자 ID", example = "1")
            @PathVariable("userId") Long userId
    ) {
        List<Map<String, Object>> coupons = List.of(
                Map.of(
                        "couponId", 10001,
                        "counponNm", "1000원 할인 쿠폰",
                        "discount_type", "BALANCE",
                        "discountRate", 1000,
                        "expiredAt", "2025-07-31",
                        "status", "ACTIVE"
                ),
                Map.of(
                        "couponId", 100002,
                        "discount_type", "PERCENT",
                        "counponNm", "5% 할인 쿠폰",
                        "discountRate", 5,
                        "expiredAt", "2025-07-31",
                        "status", "ACTIVE"
                )
        );

        return ResponseEntity.ok(Map.of(
                "couponData", coupons
        ));
    }
    */
}
