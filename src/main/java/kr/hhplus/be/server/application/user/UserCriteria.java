package kr.hhplus.be.server.application.user;

import kr.hhplus.be.server.domain.user.dto.UserPointCommand;
import lombok.Getter;

public class UserCriteria {
	
	@Getter
	public static class UserPoint {
		private Long userId;
		private Long point;
		
		public UserPoint(Long userId, Long point) {
			this.userId = userId;
			this.point = point;
		}
		
		public static UserPoint of(Long userId, Long point) {
			return new UserPoint(userId, point);
		}
		
		public UserPoint empty() {
			return new UserPoint(this.userId, 0L);
		}
		
		public UserPointCommand.Charge toChargeCommand() {
			return UserPointCommand.Charge.of(this.userId, this.point);
		}
	}
}
