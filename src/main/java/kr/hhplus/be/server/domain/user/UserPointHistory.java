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

@Entity(name = "point_history")
@Getter
@Accessors(fluent = true)
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class UserPointHistory extends BaseEntity{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long userId;
	private String type;
	private Long point;
	
	@Builder
	public UserPointHistory(Long userId, String type, Long point) {
		this.userId = userId;
		this.type = type;
		this.point = point;
	}
	
	public static UserPointHistory of(Long userId, String type, Long point) {
		return UserPointHistory.builder()
				.userId(userId)
				.type(type)
				.point(point)
				.build();
	}
}
