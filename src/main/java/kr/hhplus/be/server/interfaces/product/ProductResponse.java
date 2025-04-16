package kr.hhplus.be.server.interfaces.product;

import java.util.List;

import kr.hhplus.be.server.domain.product.entity.Product;
import lombok.Builder;

public class ProductResponse {
	
	public record V1(Long id, String name, Long price, Long remain) {
		
		@Builder
		public V1(Long id, String name, Long price, Long remain) {
			this.id = id;
			this.name = name;
			this.price = price;
			this.remain = remain;
		}
		
		public static V1 of(Long id, String name, Long price, Long remain) {
			return V1.builder()
					.id(id)
					.name(name)
					.price(price)
					.remain(remain)
					.build();
		}
	}
	
	public record V2(List<Product> products) {
		
		@Builder
		public V2(List<Product> products) {
			this.products = products;
		}
		
		public static V2 of(List<Product> products) {
			return V2.builder()
					.products(products)
					.build();
		}
	}
}
