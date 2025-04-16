package kr.hhplus.be.server.domain.order;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import kr.hhplus.be.server.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity(name = "order_items")
@Getter
@Accessors(fluent = true)
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class OrderItem extends BaseEntity{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="order_id")
    private Long orderId;
    
    private Long productId;

    private Long quantity;

    private Long price;

    private Long amount;
    
    public OrderItem(Long productId, Long quantity, Long price) {
    	this.productId = productId;
    	this.quantity = quantity;
    	this.price = price;
    	this.amount = price * quantity;
    }
    
    public static OrderItem of(Long productId, Long quantity, Long price) {
    	return new OrderItem(productId, quantity, price);
    }

    public long getTotalPrice() {
        return price * quantity;
    }
}
