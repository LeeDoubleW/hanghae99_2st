package kr.hhplus.be.server.domain.user;

import java.util.List;

public interface UserPointHistoryRepository {
	void save(UserPointHistory userPointHistory);
	List<UserPointHistory> getHistory(Long id);
}
