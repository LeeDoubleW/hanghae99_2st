package kr.hhplus.be.server.interfaces.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.hhplus.be.server.domain.product.dto.ProductInfo;
import kr.hhplus.be.server.domain.product.entity.Product;
import kr.hhplus.be.server.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
@Tag(name = "Product", description = "상품 관련 API")
public class ProductController {
	private final ProductService productService;
	
	// 상품조회
	@GetMapping("/{productId}")
    @Operation(summary = "상품 상세 조회", description = "상품 ID를 기반으로 상세 정보를 조회합니다.")
    public ResponseEntity<ProductResponse.V1> getProduct(
    		@PathVariable Long productId
    ) {
		ProductInfo.V1 product = productService.getProduct(productId);
		
		return ResponseEntity.ok(ProductResponse.V1.of(product.getId(), product.getProductName(), product.getPrice(), product.getRemainQuantity()));
    }
	
	// 상품목록조회
	@GetMapping("/list")
    @Operation(summary = "상품 목록 조회", description = "전체 상품 목록을 조회합니다.")
    public ResponseEntity<ProductResponse.V2> getProductList() {
        List<ProductInfo.V1> productList = productService.getProductList();
        List<Product> products = new ArrayList<Product>();
        for(ProductInfo.V1 productInfo : productList) {
        	Product data = new Product(productInfo.getId(), productInfo.getProductName(), productInfo.getPrice(), productInfo.getTotalQuantity(), productInfo.getRemainQuantity());
        	products.add(data);
        }

        return ResponseEntity.ok(ProductResponse.V2.of(products));
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
