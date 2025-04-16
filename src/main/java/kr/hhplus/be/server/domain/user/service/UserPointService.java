package kr.hhplus.be.server.domain.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import kr.hhplus.be.server.domain.user.dto.UserPointCommand;
import kr.hhplus.be.server.domain.user.entity.UserPoint;
import kr.hhplus.be.server.domain.user.entity.UserPointHistory;
import kr.hhplus.be.server.domain.user.repository.UserPointRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserPointService {
	private final UserPointRepository userRepo;
	
	public UserPoint getUser(Long userId) {
		return userRepo.findById(userId).get();
	}
	
	public UserPoint charge(UserPointCommand.Charge command) {
		Optional<UserPoint> user = userRepo.findById(command.getUserId());
		user.get().charge(command.getAmount());
		
		userRepo.update(user.get());
		
		return user.get();
	}
	
	public UserPoint use(UserPointCommand.Use command) {
		Optional<UserPoint> user = userRepo.findById(command.getUserId());
		user.get().use(command.getAmount());
		
		userRepo.update(user.get());
		
		return user.get();
	}
	
	public void addHistory(UserPointHistory history) {
		userRepo.historySave(UserPointHistory.of(history.userId(), history.type(), history.point()));
	}
	
	public List<UserPointHistory> getHistoryList(Long userId) {
		
		return userRepo.getHistory(userId);
	}
}
