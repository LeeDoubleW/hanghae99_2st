package kr.hhplus.be.server.domain.coupon;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity(name = "coupon")
@Getter
@Accessors(fluent = true)
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Coupon {
	private String type;
	private Long amount;
	private Long maxQuantity;
	private Long remainQuantity;
	private LocalDate issuedStartDate;
	private LocalDate issuedEndDate;
	private int expiredDay;
	
	public Coupon(String type, Long amount, Long maxQuantity, Long remainQuantity, LocalDate issuedStartDate, LocalDate issuedEndDate, int expiredDay) {
		this.type = type;
		this.amount = amount;
		this.maxQuantity = maxQuantity;
		this.remainQuantity = remainQuantity;
		this.issuedStartDate = issuedStartDate;
		this.issuedEndDate = issuedEndDate;
		this.expiredDay = expiredDay;
	}
}
