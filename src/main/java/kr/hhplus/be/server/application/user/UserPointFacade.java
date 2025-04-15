package kr.hhplus.be.server.application.user;

import java.util.List;

import org.springframework.stereotype.Component;

import kr.hhplus.be.server.domain.user.UserPoint;
import kr.hhplus.be.server.domain.user.UserPointCommand;
import kr.hhplus.be.server.domain.user.UserPointHistory;
import kr.hhplus.be.server.domain.user.UserPointService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserPointFacade {
	private final UserPointService userService;
	
	public UserPoint getUser(Long userId) {
		return userService.getUser(userId);
	}
	
	public UserPoint charge(UserPointCommand.Charge command) {
		UserPoint user = userService.charge(command);
		userService.addHistory(UserPointHistory.of(user.userId(), "CHARGE", user.point()));
		return user;
	}
	
	public List<UserPointHistory> getPointHistories(Long userId) {
		return userService.getHistoryList(userId);
	}
}
