package kr.hhplus.be.server.infrastructure.user;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import kr.hhplus.be.server.domain.user.entity.UserPoint;
import kr.hhplus.be.server.domain.user.entity.UserPointHistory;
import kr.hhplus.be.server.domain.user.repository.UserPointRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserPointRepositoryImpl implements UserPointRepository{

	private final UserPointJpaRepository userPointJpaRepository;
	private final UserPointHistoryJpaRepository userPointHistoryJpaRepository;
	
	@Override
	public Optional<UserPoint> findById(Long userId) {

		return userPointJpaRepository.findById(userId);
	}

	@Override
	public void update(UserPoint user) {
		
		userPointJpaRepository.save(user);
	}

	@Override
	public List<UserPointHistory> getHistory(Long userId) {
		
		return userPointHistoryJpaRepository.findAllByUserId(userId);
	}

	@Override
	public void historySave(UserPointHistory userPointHistory) {
		
		userPointHistoryJpaRepository.save(userPointHistory);
	}

}
