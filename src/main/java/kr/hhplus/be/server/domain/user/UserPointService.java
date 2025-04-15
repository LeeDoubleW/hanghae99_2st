package kr.hhplus.be.server.domain.user;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserPointService {
	private final UserPointRepository userRepo;
	
	public UserPoint getUser(Long userId) {
		return userRepo.findById(userId);
	}
	
	public UserPoint charge(UserPointCommand.Charge command) {
		UserPoint user = userRepo.findById(command.getUserId());
		user.charge(command.getAmount());
		
		userRepo.update(user);
		
		return user;
	}
	
	public UserPoint use(UserPointCommand.Use command) {
		UserPoint user = userRepo.findById(command.getUserId());
		user.use(command.getAmount());
		
		userRepo.update(user);
		
		return user;
	}
	
	public void addHistory(UserPointHistory history) {
		userRepo.historySave(UserPointHistory.of(history.userId(), history.type(), history.point()));
	}
	
	public List<UserPointHistory> getHistoryList(Long userId) {
		
		return userRepo.getHistory(userId);
	}
}
