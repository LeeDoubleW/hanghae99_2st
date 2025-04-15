package kr.hhplus.be.server.domain.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kr.hhplus.be.server.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity(name = "users")
@Getter
@Accessors(fluent = true)
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class UserPoint extends BaseEntity{
	final int MAX_AMOUNT = 1_000_000_000;
	final int CHARGE_MAX_LIMIT_AMOUNT = 1_000_000_000;
	final int CHARGE_MIN_LIMIT_AMOUNT = 10;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private Long userId;
    private String name;
    private Long point;
    
    @Builder
    public UserPoint(Long id, String name, Long point) {
    	this.id = id;
    	this.name = name;
    	this.point = point;
    }
    
    public static UserPoint of(Long id, String name, Long point) {
    	return UserPoint.builder()
    			.id(id)
    			.name(name)
    			.point(point)
    			.build();
    }
    
    public void charge(Long point) {
    	// 금액체크 0보다 커야하고 max 보다 작아야
    	if(point > MAX_AMOUNT) {
    		throw new IllegalArgumentException();
    	}
    	
    	if(point < 0) {
    		throw new IllegalArgumentException();
    	}
    	
    	this.point += point;
    }
    
    public void use(Long point) {
    	// 금액체크 min 보다 커야함
    	if(point < CHARGE_MIN_LIMIT_AMOUNT) {
    		throw new IllegalArgumentException();
    	}
    	
    	this.point -= point;
    }
}
