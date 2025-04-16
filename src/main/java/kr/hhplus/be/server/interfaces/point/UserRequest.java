package kr.hhplus.be.server.interfaces.point;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import kr.hhplus.be.server.domain.user.dto.UserPointCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserRequest {
	
	@Getter
	@NoArgsConstructor
	public static class V1 {
		
		@NotNull(message = "잔액은 필수 입니다.")
        @Positive(message = "잔액은 양수여야 합니다.")
		private Long amount;
		
		private V1(Long amount) {
			this.amount = amount;
		}
		
		public static V1 of(Long amount) {
			return new V1(amount);
		}
		
		
		
		public UserPointCommand.Charge toCommand(Long userId) {
			return UserPointCommand.Charge.of(userId, amount);
		}
	}
	
}
