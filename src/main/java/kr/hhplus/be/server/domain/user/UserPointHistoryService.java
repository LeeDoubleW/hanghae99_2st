package kr.hhplus.be.server.domain.user;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserPointHistoryService {
	UserPointRepository userRepo;
	UserPointHistoryRepository userPointHistoryRepo;
	
	public void addHistory(UserPointHistory history) {
		userPointHistoryRepo.save(UserPointHistory.of(history.userId(), history.type(), history.point()));
	}
	
	public List<UserPointHistory> getHistoryList(Long id) {
		
		return userPointHistoryRepo.getHistory(id);
	}
}
