package kr.hhplus.be.server.domain.order.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import kr.hhplus.be.server.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity(name = "order_items")
@Getter
@Accessors(fluent = true)
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Table(name = "order_items", indexes = {
    @Index(name = "idx_order_id", columnList = "order_id")
})
public class OrderItem extends BaseEntity{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
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
