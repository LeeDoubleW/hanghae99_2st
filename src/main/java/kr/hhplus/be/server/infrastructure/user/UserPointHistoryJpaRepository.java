package kr.hhplus.be.server.infrastructure.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.hhplus.be.server.domain.user.entity.UserPointHistory;

public interface UserPointHistoryJpaRepository extends JpaRepository<UserPointHistory, Long>{
	List<UserPointHistory> findAllByUserId(Long userId);
}
