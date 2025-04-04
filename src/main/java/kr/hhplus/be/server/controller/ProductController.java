package kr.hhplus.be.server.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.hhplus.be.server.dto.product.ProductRequest;

@RestController
@RequestMapping("/product")
@Tag(name = "Product", description = "상품 관련 API")
public class ProductController {
	// 상품조회
	@GetMapping("/{productId}")
    @Operation(summary = "상품 상세 조회", description = "상품 ID를 기반으로 상세 정보를 조회합니다.")
    public ResponseEntity<Map<String, Object>> getProduct(
            @RequestBody ProductRequest request
    ) {
		long productId = 100001;
		
        Map<String, Object> product = Map.of(
                "productId", productId,
                "productNm", "고래밥",
                "categoryId", 5,
                "categoryName", "스낵류",
                "price", 1500,
                "stock", 200
        );

        return ResponseEntity.ok(Map.of(
                "data", product,
                "message", "상품 상세 조회 성공"
        ));
    }
	
	// 상품목록조회
	@GetMapping("/list")
    @Operation(summary = "상품 목록 조회", description = "전체 상품 목록을 조회합니다.")
    public ResponseEntity<Map<String, Object>> productList() {
        List<Map<String, Object>> products = List.of(
                Map.of(
                        "productId", 100001,
                        "productNm", "베이컨",
                        "categoryId", 3,
                        "categoryName", "육가공류",
                        "price", 12000,
                        "stock", 100
                ),
                Map.of(
                        "productId", 100002,
                        "productNm", "프라이팬",
                        "categoryId", 6,
                        "categoryName", "주방도구",
                        "price", 35000,
                        "stock", 50
                )
        );

        return ResponseEntity.ok(Map.of(
                "data", products,
                "message", "상품 목록 조회 성공"
        ));
    }
	
	// 인기상품목록조회
	@GetMapping("/popularProduct")
    @Operation(summary = "인기 상품 조회", description = "최근 3일간 가장 많이 팔린 상위 5개 상품을 조회합니다.")
    public ResponseEntity<List<Map<String, Object>>> getPopularProducts() {
        List<Map<String, Object>> popularProducts = List.of(
                Map.of("productId", 100001, "productNm", "티라노모형", 
                		"categoryId", 1, "categoryName", "장난감", "price", 15000, "totalSold", 100),
                Map.of("productId", 100002, "productNm", "스웨터", 
                		"categoryId", 2, "categoryName", "의류", "price", 25000, "totalSold", 100),
                Map.of("productId", 100003, "productNm", "육포", 
                		"categoryId", 3, "categoryName", "육가공류", "price", 10000, "totalSold", 100),
                Map.of("productId", 100004, "productNm", "은접시", 
                		"categoryId", 4, "categoryName", "식기류", "price", 12000, "totalSold", 100),
                Map.of("productId", 100005, "productNm", "바나나킥", 
                		"categoryId", 5, "categoryName", "스낵류", "price", 3000, "totalSold", 100)
        );
        return ResponseEntity.ok(popularProducts);
    }
}
