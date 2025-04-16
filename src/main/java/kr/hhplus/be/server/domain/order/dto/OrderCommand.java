package kr.hhplus.be.server.domain.order.dto;

import java.util.List;

import kr.hhplus.be.server.domain.product.dto.ProductCommand;
import lombok.Builder;
import lombok.Getter;

public class OrderCommand {
	
	@Getter
	public static class Order {
		private final Long userId;
		private final Long couponId;
		private final List<Product> products;
		
		@Builder
		public Order(Long userId, Long couponId, List<Product> products) {
			this.userId = userId;
			this.couponId = couponId;
			this.products = products;
		}
		
		public static Order of(Long userId, Long couponId, List<Product> products) {
			return Order.builder()
					.userId(userId)
					.couponId(couponId)
					.products(products)
					.build();
		}
		
		@Getter
		public static class Product {
			private final Long productId;
			private final Long quantity;
			
			@Builder
			public Product(Long productId, Long quantity) {
				this.productId = productId;
				this.quantity = quantity;
			}
			
			public static Product of(Long productId, Long quantity) {
				return Product.builder()
						.productId(productId)
						.quantity(quantity)
						.build();
			}
			public ProductCommand.Product toCommand() {
	            return ProductCommand.Product.of(productId, quantity);
	        }
		}
	}
	
	
}
