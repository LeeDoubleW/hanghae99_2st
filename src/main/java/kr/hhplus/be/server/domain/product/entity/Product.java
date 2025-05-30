package kr.hhplus.be.server.domain.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kr.hhplus.be.server.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity(name = "product")
@Getter
@Accessors(fluent = true)
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Product extends BaseEntity{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String productName;
	private Long price;
	private Long totalQuantity;
	private Long remainQuantity;
	
	@Builder
	public Product(Long id, String productName, Long price, Long totalQuantity, Long remainQuantity) {
		validateId(id);
		this.id = id;
		this.productName = productName;
		this.price = price;
		this.totalQuantity = totalQuantity;
		this.remainQuantity = remainQuantity;
	}
	
	public static Product of(Long id, String productName, Long price, Long totalQuantity, Long remainQuantity) {
		return Product.builder()
				.id(id)
				.productName(productName)
				.price(price)
				.totalQuantity(totalQuantity)
				.remainQuantity(remainQuantity)
				.build();
	}
	
	public void decreaseQuantity(Long quantity) {
		if(quantity <= 0) {
			throw new IllegalArgumentException();
		}
		
		if(this.remainQuantity - quantity < 0) {
			throw new IllegalArgumentException();
		}
		
		this.remainQuantity -= quantity;
	}
	
	private void validateId(Long id) {
        if (id == null || id == 0) {
            throw new IllegalArgumentException("상품ID가 잘못되었습니다.");
        }
    }
}
