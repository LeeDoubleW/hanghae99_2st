package kr.hhplus.be.server.domain.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProductInfo {
	
	@Getter
	public static class V1 {
		private Long id;
		private String productName;
		private Long price;
		private Long totalQuantity;
		private Long remainQuantity;
		
		@Builder
		private V1(Long id, String productName, Long price, Long totalQuantity, Long remainQuantity) {
			this.id = id;
			this.productName = productName;
			this.price = price;
			this.totalQuantity = totalQuantity;
			this.remainQuantity = remainQuantity;
		}
		
		public static V1 of(Long id, String productName, Long price, Long totalQuantity, Long remainQuantity) {
			return V1.builder()
					.id(id)
					.productName(productName)
					.price(price)
					.totalQuantity(totalQuantity)
					.remainQuantity(remainQuantity)
					.build();
		}
	}
}
