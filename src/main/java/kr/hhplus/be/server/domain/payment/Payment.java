package kr.hhplus.be.server.domain.payment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kr.hhplus.be.server.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity(name = "payment")
@Getter
@Accessors(fluent = true)
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Payment extends BaseEntity{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long userId;
	private Long orderId;
	private Long amount;
	
	public Payment(Long userId,Long orderId,Long amount) {
		this.orderId = orderId;
		this.userId = userId;
		this.amount = amount;
	}
	
	public static Payment of(Long userId,Long orderId,Long amount) {
		return new Payment(userId, orderId, amount);
	}
}
