package kr.hhplus.be.server.domain.order;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	private Long orderId;
    private int productPrice;
    private int quantity;
    private int amount;

    public OrderItem(Long orderId, int productPrice,int quantity) {
    	this.orderId = orderId;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.amount = productPrice * quantity;
    }
}
