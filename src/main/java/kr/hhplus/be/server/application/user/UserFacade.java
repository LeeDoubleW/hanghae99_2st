package kr.hhplus.be.server.application.user;

import java.util.List;

import org.springframework.stereotype.Component;

import kr.hhplus.be.server.domain.user.dto.UserPointCommand;
import kr.hhplus.be.server.domain.user.entity.UserPoint;
import kr.hhplus.be.server.domain.user.entity.UserPointHistory;
import kr.hhplus.be.server.domain.user.service.UserPointService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserFacade {
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
