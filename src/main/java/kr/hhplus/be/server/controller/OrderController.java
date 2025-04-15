package kr.hhplus.be.server.controller;

import java.util.List;
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
import kr.hhplus.be.server.dto.order.OrderRequest;

@RestController
@RequestMapping("/order")
@Tag(name = "주문", description = "주문 관련 API")
public class OrderController {
	// 주문
	@PostMapping("/order")
	@Operation(summary = "주문", description = "주문을 요청합니다.")
    public ResponseEntity<Map<String, Object>> createOrder(@RequestBody OrderRequest request) {
		
		Map<String, Object> response = Map.of(
        	"orderId", 10001,
        	"totalAmount", 100000,
        	"couponId", 10001
		);
        return ResponseEntity.ok(Map.of(
        		"orderData", response, 
        		"messege", "주문완료"
        ));
    }
	// 주문내역 목록조회
	@PostMapping("/list")
	@Operation(summary = "주문목록 조회", description = "주문목록을 조회합니다.")
    public ResponseEntity<Map<String, Object>> getOrderList() {
		
		List<Map<String, Object>> products = List.of(
				Map.of(
                        "orderId", 100001,
                        "totalAmount", "베이컨",
                        "couponId", 3
                ),
                Map.of(
                        "orderId", 100002,
                        "totalAmount", "프라이팬",
                        "couponId", 6
                )
		);
		
        return ResponseEntity.ok(Map.of(
        		"orderData", products, 
        		"messege", "주문목록 조회 성공"
        ));
    }
	
	// 주문내역조회
	@GetMapping("/detail/{orderId}")
	@Operation(summary = "주문상세", description = "주문상세내역을 조회합니다.")
    public ResponseEntity<Map<String, Object>> getOrder(
    		 @Parameter(description = "주문ID", example = "1")
    		@PathVariable("orderId") Long orderId
    ) {
        return ResponseEntity.ok(Map.of(
        		"orderId", 100001,
                "totalAmount", "베이컨",
                "couponId", 3,
                "productId", 10001,
                "productNm", "뽀로로 운동화"
        ));
    }
	
	// 주문취소
	@GetMapping("/cancel/{orderId}")
	@Operation(summary = "주문취소", description = "주문을 취소요청합니다.")
    public ResponseEntity<Map<String, Object>> orderCancel(
    		@Parameter(description = "주문ID", example = "1")
    		@PathVariable("orderId") Long orderId
    ) {
		Map<String, Object> response = Map.of(
				"orderId", 10001,
        		"totalAmount", 100000,
        		"couponId", 10001,
        		"quantity", 1
        );
		
        return ResponseEntity.ok(Map.of(
        		"orderData", response, 
        		"messege", "주문취소 성공"
        ));
    }
}
