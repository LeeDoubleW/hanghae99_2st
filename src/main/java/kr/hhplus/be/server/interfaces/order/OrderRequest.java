package kr.hhplus.be.server.interfaces.order;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import kr.hhplus.be.server.application.order.OrderCriteria;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class OrderRequest {
	
	@Getter
    @NoArgsConstructor
	public static class V1 {
		
		@NotNull(message = "유저ID는 필수 입니다.")
		private Long userId;
		private Long couponId;
		private List<Product> products;
		
		@Builder
		private V1(Long userId, Long couponId, List<Product> products) {
            this.userId = userId;
            this.couponId = couponId;
            this.products = products;
        }

        public static V1 of(Long userId, Long couponId, List<Product> products) {
            return V1.builder()
            		.userId(userId)
            		.couponId(couponId)
            		.products(products)
            		.build();
        }
        
        public OrderCriteria toCommand() {
            return OrderCriteria.of(userId, couponId, products.stream()
                    .map(Product::toCommand)
                    .toList());
        }
	}
	
	@Getter
    @NoArgsConstructor
    public static class Product {

        @NotNull(message = "상품ID는 필수입니다.")
        private Long productId;

        @NotNull(message = "구매수량은 필수입니다.")
        @Positive(message = "구매수량은 양수여야 합니다.")
        private Long quantity;

        @Builder
        private Product(Long productId, Long quantity) {
            this.productId = productId;
            this.quantity = quantity;
        }
        
        public static Product of(Long productId, Long quantity) {
        	return Product.builder()
        			.productId(productId)
        			.quantity(quantity)
        			.build();
        }
        
        public OrderCriteria.Product toCommand() {
            return OrderCriteria.Product.of(productId, quantity);
        }
    }
}
