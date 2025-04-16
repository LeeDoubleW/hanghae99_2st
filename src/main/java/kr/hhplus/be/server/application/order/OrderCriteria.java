package kr.hhplus.be.server.application.order;

import java.util.List;

import kr.hhplus.be.server.domain.order.dto.OrderCommand;
import kr.hhplus.be.server.domain.product.dto.ProductCommand;
import kr.hhplus.be.server.interfaces.order.OrderRequest.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderCriteria {
	
	private final Long userId;
	private final Long couponId;
	private final List<Product> products;
	
	@Builder
	public OrderCriteria(Long userId, Long couponId, List<Product> products) {
		this.userId = userId;
		this.couponId = couponId;
		this.products = products;
	}
	
	public static OrderCriteria of(Long userId, Long couponId, List<Product> products) {
		return OrderCriteria.builder()
				.userId(userId)
				.couponId(couponId)
				.products(products)
				.build();
	}
	
	public ProductCommand.Products toProductCommand() {
        return ProductCommand.Products.of(products.stream()
                .map(OrderCriteria.Product::toCommand)
                .toList());
    }
	
	public OrderCommand.Order toOrderCommand() {
		return OrderCommand.Order.of(couponId, userId, products.stream()
				.map(Product::toOrdCommand)
                .toList());
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
		
		public OrderCommand.Order.Product toOrdCommand() {
			return OrderCommand.Order.Product.of(productId, quantity);
		}
	}
}
