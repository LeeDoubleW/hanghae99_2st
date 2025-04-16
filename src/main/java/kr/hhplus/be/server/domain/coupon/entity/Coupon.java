package kr.hhplus.be.server.domain.coupon.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity(name = "coupon")
@Getter
@Accessors(fluent = true)
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Coupon {
	@Id
	@Column(name="coupon_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String type;
	private Long amount;
	private Long maxQuantity;
	private Long remainQuantity;
	private LocalDate issuedStartDate;
	private LocalDate issuedEndDate;
	private int expiredDay;
	
	@Builder
	public Coupon(String type, Long amount, Long maxQuantity, Long remainQuantity, LocalDate issuedStartDate, LocalDate issuedEndDate, int expiredDay) {
		this.type = type;
		this.amount = amount;
		this.maxQuantity = maxQuantity;
		this.remainQuantity = remainQuantity;
		this.issuedStartDate = issuedStartDate;
		this.issuedEndDate = issuedEndDate;
		this.expiredDay = expiredDay;
	}
	
	public static Coupon of(String type, Long amount, Long maxQuantity, Long remainQuantity, LocalDate issuedStartDate, LocalDate issuedEndDate, int expiredDay)  {
		return Coupon.builder()
				.type(type)
				.amount(amount)
				.maxQuantity(maxQuantity)
				.remainQuantity(remainQuantity)
				.issuedStartDate(issuedStartDate)
				.issuedEndDate(issuedEndDate)
				.expiredDay(expiredDay)
				.build();
	}
	
	public Long calDiscount(Long total) {
		Long disAmount = 0L;
		if(this.type.equals("PERCENT")) {
			disAmount = total * this.amount;
		} else {
			disAmount = this.amount;
		}
		
		return disAmount;
	}
	
	public boolean isIssuedAble() {
		if(this.remainQuantity <= 0) {
			throw new IllegalArgumentException();
		}
		
		if(this.issuedStartDate.isAfter(LocalDate.now()) || this.issuedEndDate.isBefore(LocalDate.now())) {
			throw new IllegalArgumentException();
		}
		
		return true;
	}
	
	public void decreaseRemainQuantity() {
		this.remainQuantity--;
	}
}
