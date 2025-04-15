package kr.hhplus.be.server.interfaces.point;

import java.util.List;

import kr.hhplus.be.server.domain.user.UserPointHistory;
import lombok.Builder;

public class UserPointResponse {
	
	// 유저 조회 나 충전
	public record V1(Long userId, Long amount) {
		
		@Builder
		public V1(Long userId, Long amount) {
			this.userId = userId;
			this.amount = amount;
		}
		
		public static V1 of(Long userId, Long amount) {
			return V1.builder()
					.userId(userId)
					.amount(amount)
					.build();
		}
	}
	
	public record V2(List<UserPointHistory> histories) {
		@Builder
		public V2(List<UserPointHistory> histories) {
			this.histories = histories;
		}
		
		public static V2 of(List<UserPointHistory> histories) {
			return V2.builder()
					.histories(histories)
					.build();
		}
	}
	
}
