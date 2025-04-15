package kr.hhplus.be.server.domain.user;

import java.util.List;

public interface UserPointRepository {
	UserPoint findById(Long id);
	void update(UserPoint user);
	List<UserPointHistory> getHistory(Long userId);
	void historySave(UserPointHistory userPointHistory);
}
