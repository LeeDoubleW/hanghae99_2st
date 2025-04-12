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
}
