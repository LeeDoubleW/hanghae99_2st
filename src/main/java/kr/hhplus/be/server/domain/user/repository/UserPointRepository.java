package kr.hhplus.be.server.domain.user.repository;

import java.util.List;
import java.util.Optional;

import kr.hhplus.be.server.domain.user.entity.UserPoint;
import kr.hhplus.be.server.domain.user.entity.UserPointHistory;

public interface UserPointRepository {
	Optional<UserPoint> findById(Long userId);
	void update(UserPoint user);
	List<UserPointHistory> getHistory(Long userId);
	void historySave(UserPointHistory userPointHistory);
}
