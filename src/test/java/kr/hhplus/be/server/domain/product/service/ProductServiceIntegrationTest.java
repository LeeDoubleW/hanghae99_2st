package kr.hhplus.be.server.domain.product.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.hhplus.be.server.domain.product.dto.ProductInfo;
import kr.hhplus.be.server.domain.product.entity.Product;

@SpringBootTest
public class ProductServiceIntegrationTest {
	
	@Autowired
    private ProductService productService;
	
	private Product productInfo;
	
	@BeforeEach
	void setUp() {
		productInfo = productService.save(Product.of(1L, "nike", 100000L, 10L, 5L));
	}
	
	// 상품 상세 조회
	@Test
	void 상품_상세_조회() {
		assertThat(productService.getProduct(productInfo.id())).isEqualTo(productInfo);
	}
	
	// 상품 수량 차감
	@Test
	void 상품_수량_차감() {
		productService.decreaseQuantity(productInfo.id(), 1L);
		
		ProductInfo.V1 product = productService.getProduct(productInfo.id());
		assertThat(product.getRemainQuantity()).isEqualTo(4L);
	}
	
	// 상품 수량 차감 실패 남은수량 0
	@Test
	void 상품_수량_차감_실패() {
		assertThatThrownBy(()->productService.decreaseQuantity(productInfo.id(), 10L))
		.isInstanceOf(IllegalArgumentException.class)
        .hasMessage("수량이 부족합니다.");
	}
	
	// 상품 조회
	@Test
	void 상품_조회() {
		assertThat(productService.getProductList()).hasSize(1);
	}
}
